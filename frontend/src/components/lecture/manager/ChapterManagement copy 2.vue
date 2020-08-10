<template>
  <div style="; margin-top:20px;">
      {{chapter}}
        <v-card tile flat outlined >
                <v-layout wrap>
                    <v-flex xs12 sm12 md9 lg9 xl9 style="margin-left:0px;">                         
                            <v-list style="padding:20px 10px;">
                                <v-layout wrap>                                    
                                    <v-flex  md12 lg12 xl12> 
                                        <v-list-item>
                                            <v-list-item-title>목차</v-list-item-title> 
                                        </v-list-item> 
                                        <v-list flat style="" class="chapterList" outlined rounded>
                                            <draggable v-model="chapter" @start="drag=true" @end="drag=false" :options="{group:'people'}">                                          
                                                    <template v-for="(item, index) in chapter">                                                        
                                                        <v-hover v-slot:default="{ hover }" :key="`${index}_hover1`">
                                                            <!-- <transition-group :key="`${index}_group`"> -->
                                                            <v-list-item class="" :key="`${index}_dragItem`"  @dragenter="logData">
                                                                <v-list-item-content class="nomargin nopadding" style="margin-right:10px;">
                                                                    <v-card :key="`${index}_list`" style="margin: 5px 0px; border:1px solid #4CAF50; cursor:pointer"  outlined>
                                                                        <v-list-item style="">                           
                                                                            <v-list-item-content>                                                                                    
                                                                                <v-list-item-title>
                                                                                    {{item.title}} 
                                                                                    <div style="float:right">
                                                                                        <v-icon>
                                                                                            mdi-play-circle-outline
                                                                                        </v-icon>    
                                                                                        <v-icon style="margin-left:5px;">
                                                                                            mdi-script-text-outline
                                                                                        </v-icon>                                                                                     
                                                                                    </div>
                                                                                </v-list-item-title>                                 
                                                                                
                                                                                <v-list-item-subtitle style="margin-top:3px;">
                                                                                    <span v-for="(tag,index) in item.tags" :key="`${index}_tag`">
                                                                                    #{{tag}}
                                                                                    </span>
                                                                                </v-list-item-subtitle>   
                                                                            </v-list-item-content>
                                                                        </v-list-item>
                                                                    </v-card>
                                                                </v-list-item-content>
                                                                <v-list-item-action v-if="hover">                                                            
                                                                    <v-btn icon @click="openDialog(item)">
                                                                        <v-icon>
                                                                            mdi-pencil
                                                                        </v-icon>
                                                                    </v-btn>
                                                                </v-list-item-action>
                                                                <v-list-item-action v-else> <v-btn icon /> </v-list-item-action>
                                                                <v-list-item-action v-if="hover">              
                                                                    <v-btn class="nomargin nopadding" icon @click="removeItem(index)">                                             
                                                                        <v-icon>
                                                                            mdi-trash-can
                                                                        </v-icon>
                                                                    </v-btn>
                                                                </v-list-item-action>
                                                                <v-list-item-action v-else> <v-btn icon /> </v-list-item-action>
                                                            </v-list-item>                  
                                                        <!-- </transition-group>                                           -->
                                                        </v-hover>  
                                                        <v-hover v-slot:default="{ hover }" :key="`${index}_hover2`">
                                                            <v-list-item style="min-height:10px;" v-if="false">
                                                                <template v-if="hover" >
                                                                    <v-icon small color="black" style="margin-right:5px;" @click="openDialog(null, index+1)">
                                                                        mdi-plus
                                                                    </v-icon>
                                                                    <v-list-item-content  style="border:2px dashed #d4d4d4; margin-right:10px;cursor:pointer" @click="openDialog(null, index+1)" />
                                                                    <v-list-item-action> <v-btn icon style="height:0px;"></v-btn> </v-list-item-action>
                                                                    <v-list-item-action> <v-btn icon style="height:0px;"></v-btn> </v-list-item-action>
                                                                </template>
                                                                                                                                
                                                            </v-list-item>
                                                        </v-hover>
                                                    </template>
                                            </draggable>                                            
                                            <v-list-item style="min-height:10px; margin-top:10px;" >
                                                <v-list-item-content  style="border:2px dashed #d4d4d4; margin-right:10px;cursor:pointer" @click="openDialog(null, chapter.length)">
                                                    <table width=100%>
                                                        <tr>
                                                            <th>
                                                                <v-icon small color="black">
                                                                    mdi-plus
                                                                </v-icon>
                                                            </th>
                                                        </tr>
                                                    </table>
                                                </v-list-item-content>

                                                <v-list-item-action> <v-btn icon style="height:0px;"></v-btn> </v-list-item-action>
                                                <v-list-item-action> <v-btn icon style="height:0px;"></v-btn> </v-list-item-action>
                                                                                                                                
                                                            </v-list-item>
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
                    title:'PriorityQueue',
                    tags: ['자료구조', '알고리즘', '우선순위큐'],
                }
                ,{
                    title:'Tree',
                    tags: ['자료구조', '알고리즘', '트리'],
                },
                {
                    title:'Stack',
                    tags: ['자료구조', '알고리즘', '스택'],
                },
                {
                    title:'Queue',
                    tags: ['자료구조', '알고리즘', '큐'],
                }
            ],

            dialog: false,
            curItem: null,
            title: '',
            tags: [],
        }
    },
    methods: {
        logData(){
            1;
        },
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
        openDialog(item, index){
            // if(item == null){
            //     item = {
            //         title: '',
            //         tags: [],
            //         order: -1,
            //     }
            //     this.chapter.splice(index, 0, item);
            //     //this.chapter.push(item)
            // }

            // this.curItem = item;
            // this.title = item.title;
            // this.tags = item.tags;
            // this.dialog = true;
        },
        removeItem(index){
            this.chapter.splice(index, 1)
        },
    }
}
</script>

<style scoped>
    * {
        font-weight: 400;       
        opacity: 1; 
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
        border-color:f2f2f2;
    }
    .nomargin{
        margin:0px;

    }
    .nopadding{
        padding:0px;         
    }

    .v-list-item__title{
        font-family:-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        font-size:16px;
        font-weight: 500;
    }
    .v-list-item__subtitle{
        font-family:-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
        font-size:12px;
        font-weight: 500;
    }
   
    .chapterContent{
        /* font-family:-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen-Sans, Ubuntu, Cantarell, "Helvetica Neue", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji"; */
        /* font-size:12px; */
    } 
</style>