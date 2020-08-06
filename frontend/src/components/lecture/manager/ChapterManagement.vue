<template>
  <div style="; margin-top:20px;">
        <v-card tile flat outlined >
                <v-layout wrap>
                    <v-flex xs12 sm12 md9 lg9 xl9 style="margin-left:0px;">                         
                            <v-list style="padding:20px 10px;">
                                <v-layout wrap>
                                    <v-flex  md6 lg6 xl6> 
                                        <v-list-item>
                                            <v-list-item-title>백로그</v-list-item-title> 
                                        </v-list-item> 
                                        <v-list flat style="" class="chapterList">
                                            <draggable v-model="backlog" @start="drag=true" @end="drag=false" :options="{group:'people'}" @change="log">
                                                    <template v-for="(item, index) in backlog">
                                                        <v-hover v-slot:default="{ hover }" :key="index">
                                                            <v-card :key="`${index}_list`" style="margin: 5px 0px; " link @click="openDialog(backlog, item)">
                                                                <v-list-item style="padding-left:5px;">
                                                                    <v-list-item-content style="padding-bottom:5px;">   
                                                                        <span style="padding-left:5px;">{{item.title}} </span>
                                                                        <div style="margin-top:5px" />
                                                                            <span>
                                                                            <v-chip                                            
                                                                                :color="`primary lighten-4`"                                            
                                                                                class="ma-1"
                                                                                v-for="(tag,index) in item.tags"   
                                                                                :key="index"
                                                                                label
                                                                                style="margin:0px;"
                                                                                small
                                                                            >
                                                                                <span style="color:black">
                                                                                    {{tag}}
                                                                                </span>
                                                                            </v-chip>
                                                                            </span>
                                                                    </v-list-item-content>
                                                                    <v-list-item-action v-show="hover">                                                             
                                                                        <v-icon>
                                                                            mdi-pencil
                                                                        </v-icon>
                                                                    </v-list-item-action>
                                                                </v-list-item>
                                                            </v-card>
                                                        </v-hover>  
                                                    </template>
                                            </draggable>
                                        </v-list>
                                    </v-flex>
                                    <v-flex  md6 lg6 xl6> 
                                        <v-list-item>
                                            <v-list-item-title>목차</v-list-item-title> 
                                        </v-list-item> 
                                        <v-list flat style="" class="chapterList">
                                            <draggable v-model="chapter" @start="drag=true" @end="drag=false" :options="{group:'people'}">
                                                
                                                    <template v-for="(item, index) in chapter">
                                                        <v-hover v-slot:default="{ hover }" :key="index">
                                                            <v-card :key="`${index}_list`" style="margin: 5px 0px; " link @click="openDialog(chapter, item)">
                                                                <v-list-item style="padding-left:5px;">
                                                                    <v-list-item-content style="padding-bottom:5px;">
                                                                        <span style="padding-left:5px;" class="v-list-item__subtitle"> chapter {{index+1}}    </span>
                                                                        <span style="padding-left:5px;">{{item.title}} </span>
                                                                        <div style="margin-top:5px" />
                                                                            <span>
                                                                            <v-chip                                            
                                                                                :color="`primary lighten-4`"                                            
                                                                                class="ma-1"
                                                                                v-for="(tag,index) in item.tags"   
                                                                                :key="index"
                                                                                label
                                                                                style="margin:0px;"
                                                                                small
                                                                            >
                                                                                <span style="color:black">
                                                                                    {{tag}}
                                                                                </span>
                                                                            </v-chip>
                                                                            </span>
                                                                    </v-list-item-content>
                                                                    <v-list-item-action v-show="hover">                                                            
                                                                        <v-icon>
                                                                            mdi-pencil
                                                                        </v-icon>
                                                                    </v-list-item-action>
                                                                </v-list-item>
                                                            </v-card>
                                                        </v-hover>  
                                                    </template>
                                            </draggable>
                                            <v-card flat style="margin: 10px 0px; border:1px dotted grey ; border-radius: 5px;">
                                                <v-list-item link @click="openDialog(chapter, item)">
                                                    <table width=100%>
                                                        <tr>
                                                            <th>
                                                                <v-icon x-large>
                                                                    mdi-plus
                                                                </v-icon>
                                                            </th>
                                                        </tr>
                                                    </table>
                                                </v-list-item>
                                            </v-card>
                                        </v-list>
                                    </v-flex>
                                </v-layout>
                            </v-list> 
   
                            <v-list style="padding:20px 30px;">
                                <v-layout>
                                    <v-spacer></v-spacer><v-spacer></v-spacer>
                                    <v-btn depressed color="primary" @click="ValidationForm">
                                        저장하기
                                    </v-btn>    
                                </v-layout>
                            </v-list>      
                    </v-flex>                    
                    <v-flex v-show="option" style="border-left:1px solid #e2e2e2" md3 lg3 xl3> 
                        <v-list style="position: sticky; top:60px;" three-line>
                            <v-list-item >
                                <v-list-item-title>                                    
                                    가이드                                    
                                </v-list-item-title>                                
                            </v-list-item>
                            <v-list-item>                                       
                                <v-list-item-content style="font-size:14px;">                                
                                    <div>
                                        한 번 만들어진 <span class="primary--text">목차</span>는 <span class="error--text">삭제</span>할 수 없습니다.
                                        불필요한 목차는 <span class="primary--text">백로그</span>를 통해 감춰보세요!
                                    </div>                                
                                </v-list-item-content>                                    
                            </v-list-item>                            
                        </v-list>
                    </v-flex>
                </v-layout>
        </v-card>
        <v-snackbar
            v-model="snackbar"
            timeout="1500"
            color="primary"        
            
        >
            {{msg}}
        </v-snackbar>
        <v-dialog v-model="dialog" max-width="780">
            <v-card>
                <v-card-title>
                    제목
                </v-card-title>
                <v-card-text>
                    <v-text-field v-model="title" dense></v-text-field>
                </v-card-text>
                <v-card-title>
                    검색 키워드
                </v-card-title>
                <v-card-text>
                    <v-combobox 
                        multiple 
                        counter=10 
                        hint="키워드 작성 후 엔터키를 눌러 구분할 수 있습니다." 
                        prefix="#" 
                        append-icon=""
                        v-model="tags"
                        dense
                    >
                        <template v-slot:selection="{ attrs, item, parent, selected }">
                            <v-chip
                                v-bind="attrs"
                                :color="`primary lighten-4`"
                                :input-value="selected"
                                label
                                small
                            >
                                <span style="color:black">
                                    {{item}}
                                </span>
                            </v-chip>
                        </template>
                    </v-combobox>
                </v-card-text>
                <v-card-actions>
                    <v-layout>
                        <v-spacer></v-spacer>
                        <v-btn depressed color="primary" @click="curItem.title=title; curItem.tags=tags; dialog=false;">
                            저장하기
                        </v-btn>    
                        <div style="margin-right:5px"></div>
                        <v-btn depressed @click="dialog=false">
                            취소하기
                        </v-btn>    
                    </v-layout>   
                </v-card-actions>
            </v-card>
        </v-dialog>
  </div>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
import http from "@/util/http_common.js"
import axios from "axios"
import store from "@/store/index.js"
import draggable from 'vuedraggable'

export default {
    components :{
        draggable,
    },
    props: ['option'],
    data() {
        return {         
            content: '',
            snackbar: false,
            msg: '',            
            chapter: [
                {
                    title:'Stack',
                    tags:['자료구조', '알고리즘', '스택'],
                },
                {
                    title:'Queue',
                    tags:['자료구조', '알고리즘', '큐'],
                }
                ,{
                    title:'PriorityQueue',
                    tags:['자료구조', '알고리즘', '우선순위 큐'],
                }
                ,{
                    title:'Tree',
                    tags:['자료구조', '알고리즘', '트리'],
                },
            ],
            backlog: [
            
            ],

            dialog: false,
            curItem: null,
            title: '',
            tags: [],
        }
    },
    methods: {
        goto(target, msg){
            this.$vuetify.goTo(target, {
                duration: 300,
                offset: 150,
                easing: 'easeInOutCubic'
            })
            this.msg = msg;
            this.snackbar = true;
        },
        ValidationForm(){
            if(!this.content){          
                this.goto('#content', '설명을 입력해주세요.')
                this.$refs.content.focus();                                
            }else{
                
            }
        },
        openDialog(items, item){
            if(item == null){
                item = {
                    title: '',
                    tags: [],
                    order: -1,
                }
                items.push(item)
            }

            this.curItem = item;
            this.title = item.title;
            this.tags = item.tags;
            this.dialog = true;
        },
        log(e){
            console.dir(e)
        }
    }
}
</script>

<style scoped>
    * {
        font-weight: 400;
    }
    h1 {
        font-size: 26px;
        font-weight: 500;
        margin-bottom:30px;
        
    }
    .v-list.chapterList {
        height:400px;
        overflow-y:auto;
        margin:10px;
        margin-right:20px;
        background-color:#f2f2f2;

        padding:5px 5px; 
        border-color:skyblue;
    }
</style>