<template>
  <div style="; margin-top:20px;">
        <v-card tile flat outlined >
                <v-layout wrap>
                    <v-flex xs12 sm12 md9 lg9 xl9 style="margin-left:0px;">                         
                            <v-list style="padding:20px 100px;">
                                <v-list-item>
                                    <v-list-item-title>초대</v-list-item-title> 
                                </v-list-item>
                                <v-list-item>
                                    <v-list-item-title>
                                        <!-- <v-combobox 
                                            multiple 
                                            counter=10 
                                            hint="키워드 작성 후 엔터키를 눌러 구분할 수 있습니다." 
                                            v-model="tags"
                                            :items="items"    
                                            :item-text="'123'"       
                                        >
                                            <template v-slot:selection="{ attrs, item, selected,parent }">
                                                <v-chip
                                                    v-bind="attrs"
                                                    :color="`primary lighten-4`"
                                                    :input-value="selected"
                                                    label
                                                >
                                                <span style="color:black">
                                                    <v-avatar
                                                        class="profile"                                                    
                                                    >
                                                        <v-img 
                                                            :src="item.avatar"
                                                        ></v-img>
                                                    </v-avatar>
                                                    {{item.text }}
                                                    <v-icon small right @click="parent.selectItem(item)">mdi-close</v-icon>
                                                </span>
                                                </v-chip>
                                            </template>
                                            <template v-slot:item="{parent, item, on, attrs }" >
                                                <v-list-item v-bind="attrs" link @click="tags.push(item)">
                                                    <v-list-item-avatar>
                                                        <v-img :src="item.avatar" :lazy-src="item.avatar"></v-img>
                                                    </v-list-item-avatar>
                                                    <v-list-item-content>
                                                        <v-list-item-title v-html="item.nickname"></v-list-item-title>
                                                        <v-list-item-subtitle v-html="item.email"></v-list-item-subtitle>
                                                    </v-list-item-content>
                                                </v-list-item>
                                            </template>
                                        </v-combobox> -->
                                        
                                        <v-combobox
                                            v-model="model"                                            
                                            :items="items"
                                            :search-input.sync="search"
                                            multiple
                                            small-chips                                            
                                            no-filter
                                            auto-select-first
                                            hide-selected
                                            @keydown.enter.stop.capture="selectMember"
                                        >                                            
                                            <template v-slot:selection="{ attrs, item, parent, selected }">
                                                <v-chip
                                                        v-bind="attrs"
                                                        :color="`primary lighten-4`"
                                                        :input-value="selected"
                                                        label
                                                    >
                                                    <span style="color:black">
                                                        <v-avatar
                                                            class="profile"                                                    
                                                        >
                                                            <v-img 
                                                                :src="item.avatar"
                                                            ></v-img>
                                                        </v-avatar>
                                                        {{item.text }}
                                                        <v-icon small right @click="parent.selectItem(item)">mdi-close</v-icon>
                                                    </span>
                                                </v-chip>
                                            </template>
                                            <template v-slot:item="{ index, item }">                                                
                                                <v-list-item-avatar>
                                                    <v-img :src="item.avatar" :lazy-src="item.avatar"></v-img>
                                                </v-list-item-avatar>
                                                <v-list-item-content>
                                                    <v-list-item-title v-html="item.text"></v-list-item-title>
                                                    <v-list-item-subtitle v-html="item.email"></v-list-item-subtitle>
                                                </v-list-item-content>
                                            </template>
                                        </v-combobox>
                                    </v-list-item-title>
                                </v-list-item>
                            </v-list> 
   
                            <v-list style="padding:20px 100px;">
                                <v-layout>
                                    <v-spacer></v-spacer>
                                    <v-btn depressed color="primary" @click="ValidationForm">
                                        생성하기
                                    </v-btn>    
                                    <div style="margin-right:5px"></div>
                                    <v-btn depressed>
                                        취소하기
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
                                <v-list-item-content style="font-size:14px">                                
                                    <div>
                                        소개는 
                                        <span class="primary--text"> 
                                            지식 공유자와 수강자에게 프로젝트의 방향성에 대해 소개
                                        </span>하는 공간입니다. 
                                        누구를 위해 무엇을 만들어나갈지 설명해주세요!
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
  </div>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
import http from "@/util/http_common.js"
import axios from "axios"
import store from "@/store/index.js"
export default {
    props: ['option'],
    created(){
        // console.dir(`/api/v1/users/${this.search?this.search:""}?lectureid=${this.$route.params.id}`)
        http.axios.get(`/api/v1/users/${this.search?this.search:""}?lectureid=${this.$route.params.id}`)
            .then(({data}) => {
                console.dir(data)
                for(let i in data.result){
                    this.items.push({
                        text: data.result[i].nickname,
                        email: data.result[i].email,
                        avatar: `https://picsum.photos/500/300?image=${data.result[i].userId}`,
                    })
                }
            })
    },
    watch: {
        model (val, prev) {
            this.search = '';
            if (val.length === prev.length) return

            this.model = val.map(v => {
                if (typeof v === 'string') {
                v = {
                    text: v,
                    color: this.colors[this.nonce - 1],
                }

                this.items.push(v)

                this.nonce++
                }

                return v
            })
        },        
        search(){
            http.axios.get(`/api/v1/users/${this.search?this.search:""}?lectureid=${this.$route.params.id}`)
            .then(({data}) => {
                console.dir(data)
                this.items = []
                for(let i in data.result){                    
                    this.items.push({
                        text: data.result[i].nickname,
                        email: data.result[i].email,
                        avatar: `https://picsum.photos/500/300?image=${data.result[i].userId}`,
                    })
                }
            })
        }
    },
    data() {
        return {         
            // mo:'123',
            content: '',
            snackbar: false,
            msg: '',
            tags:[],           
            items: [
                
            ],
            activator: null,
            attach: null,
            colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
            editing: null,
            index: -1,
            nonce: 1,
            menu: false,
            model: [
            ],
            x: 0,
            search: null,
            y: 0,
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
                this.createProject();
            }
        },
        createProject(){
            // http.axios.post('/api/v1/lectures', {   
            //     "tags": this.tags,
            //     "thumbnailUrl": this.thumbnailUrl,
            //     "title": this.title,
            //     "type": this.type
            // }).then(({data}) => {
            //     alert("프로젝트가 생성되었습니다.");
            //     this.$router.push("/");
            // })
        },
        edit (index, item) {
            if (!this.editing) {
                this.editing = item
                this.index = index
            } else {
                this.editing = null
                this.index = -1
            }
        },
        // filter (item, queryText, itemText) {
        //     console.log(item,queryText,itemText)
        //     if (item.header) return false

        //     const hasValue = val => val != null ? val : ''

        //     const text = hasValue(itemText)
        //     const query = hasValue(queryText)

        //     return text.toString()
        //         .toLowerCase()
        //         .indexOf(query.toString().toLowerCase()) > -1
        // },
        selectMember(){
            this.search = ''
            if(this.items[0]){
                this.model.push(this.items[0])                
            }            
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
    
</style>