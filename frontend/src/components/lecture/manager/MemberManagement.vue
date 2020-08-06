<template>
  <div style="; margin-top:20px;">
        <v-card tile flat >
                <v-layout wrap>
                    <v-flex xs12 sm12 md9 lg9 xl9 style="margin-left:0px;">                         
                            <v-list style="padding:20px 100px;">
                                <v-list outlined style="padding-top:0px;">                                        
                                    <v-list-item style="background-color:#f2f2f2;">
                                        <v-list-item-title>
                                            <v-list-item-title  color="normal">멤버 초대</v-list-item-title> 
                                        </v-list-item-title>    
                                    </v-list-item>
                                    <v-divider />
                                    
                                    <div style="margin-top:30px" />
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-subtitle>
                                            초대할 멤버를 선택하세요.
                                            </v-list-item-subtitle>
                                            <v-list-item-subtitle>
                                            <v-combobox
                                                v-model="inviteMember"                                            
                                                :items="items"
                                                :search-input.sync="search"
                                                multiple
                                                small-chips                                            
                                                no-filter
                                                auto-select-first
                                                hide-selected
                                                @keydown.enter.stop.capture="selectMember"
                                                counter=5 
                                                hint="초대할 멤버를 선택하세요."
                                                outlined
                                                dense
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
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-list-item-subtitle>
                                                초대할 멤버의 권한을 부여해주세요.
                                            </v-list-item-subtitle>
                                            <v-list-item-subtitle>
                                            <v-combobox      
                                                outlined
                                                dense
                                                v-model="selectPermissions"
                                                :items="permissions"
                                                readonly
                                            >                 
                                            </v-combobox>
                                            </v-list-item-subtitle>
                                        </v-list-item-content>
                                    </v-list-item>
                                    <v-list-item>
                                        <v-spacer />
                                        <v-btn depressed color="primary" @click="ValidationForm">
                                            초대하기
                                        </v-btn>    
                                    </v-list-item>   
                                </v-list>

                                <div style="margin-top:30px" />
                                <v-list outlined style="padding-top:0px;">                                        
                                    <v-list-item  style="background-color:#f2f2f2;">
                                        <v-list-item-title>멤버 목록</v-list-item-title> 
                                    </v-list-item>
                                    <template v-for="(item,index) in memberList">
                                        <v-divider :key="`${index}_divider`"/>
                                        <v-list-item :key="`${index}_memberList`">
                                            <v-list-item-avatar>
                                                <v-img :src="item.avatar" :lazy-src="item.avatar"></v-img>
                                            </v-list-item-avatar>
                                            <v-list-item-content>
                                                <v-list-item-title>
                                                    <b>{{item.nickname}}</b>&nbsp;
                                                     <span class="v-list-item__subtitle">{{item.email | idOfEmail}}</span></v-list-item-title>
                                                <v-list-item-subtitle > {{item.joinDate | diffDate}}</v-list-item-subtitle>
                                                
                                            </v-list-item-content>

                                                

                                            <v-list-item-action style="margin-right:5px">
                                                <table width="100%">
                                                    <tr width="100%">
                                                        <th width="100px;">
                                                            {{item.permissions}}
                                                        </th>
                                                        <th width="70px;">
                                                            <v-btn dark small outlined color="red" v-show="$router.app.$store.state.email != item.email">
                                                                <v-icon>
                                                                    mdi-crown
                                                                </v-icon>
                                                            </v-btn>
                                                        </th>
                                                        <th width="70px;">
                                                            <v-btn dark small outlined color="red" v-show="$router.app.$store.state.email != item.email">
                                                                <v-icon>
                                                                    mdi-trash-can-outline
                                                                </v-icon>
                                                            </v-btn>
                                                            
                                                            <v-btn dark small outlined color="red" v-show="$router.app.$store.state.email == item.email">
                                                                <v-icon>
                                                                    mdi-exit-to-app
                                                                </v-icon>
                                                            </v-btn>
                                                        </th>
                                                    </tr>
                                                </table>
                                                
                                            </v-list-item-action>
                                            <!-- <v-list-item-action v-show="$router.app.$store.state.email != item.email"  style="margin-right:5px">
                                                <v-btn dark small outlined color="red">
                                                    <v-icon>
                                                        mdi-crown
                                                    </v-icon>
                                                </v-btn>
                                            </v-list-item-action> 
                                            <v-list-item-action v-show="$router.app.$store.state.email != item.email">
                                                <v-btn dark small outlined color="red">
                                                    <v-icon>
                                                        mdi-trash-can-outline
                                                    </v-icon>
                                                </v-btn>
                                            </v-list-item-action>
                                            <v-list-item-action v-show="$router.app.$store.state.email == item.email" style="margin-left:0px;">
                                                <v-btn dark small outlined color="red">
                                                Exit
                                                </v-btn>
                                            </v-list-item-action> -->
                                            
                                            
                                        </v-list-item>
                                        
                                    </template>
                                </v-list>
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
                                        프로젝트를 함께 완성시켜나갈 멤버를 초대해보세요!
                                        초대된 멤버는 <span class="error--text">권한 부여</span>를 제외한
                                        <span class="primary--text">기본 정보 수정</span>과 <span class="primary--text">목차 관리 </span>, 그리고  
                                        <span class="primary--text">여러 참여자들의 요청을 프로젝트에 반영</span>시킬 수 있습니다.
                                        
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

        // http.axios.get(`/api/v1/users/${this.search?this.search:""}?lectureid=${this.$route.params.id}`)
        //     .then(({data}) => {
        //         console.dir(data)
        //         this.memberList = data.result;
        //     })
    },
    watch: {
        inviteMember () {
            this.search = '';
            if( this.inviteMember.length > 5) {
                 this.inviteMember.pop();
            }
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
        },
    },
    filters: {
        idOfEmail(val) {
            let id = val.substring(0,val.indexOf('@'))
            return '@' + (id ? id : val)
        },
        diffDate(val) {
            let diff = (new Date() - new Date(val)) / 1000;
            if(diff < 60)
                return '방금 전'
            diff /= 60;
            if(diff < 60)
                return parseInt(diff) + '분 전'

            diff /= 60;
            if(diff < 24)
                return parseInt(diff) + '시간 전'

            diff /= 24;
            if(diff < 7)
                return parseInt(diff) + '일 전'
            if (diff < 30)
                return parseInt(diff/7) + '주 전'
            if (diff < 365)
                return parseInt(diff/30) + '달 전'
            return parseInt(diff/365) + '년 전'
            

            console.dir(diff )

            return val
        }
    },
    data() {
        return {         
            snackbar: false,
            msg: '',         
            items: [],
            inviteMember: [
            ],
            selectPermissions: ['Maintainer'],
            permissions: ['Maintainer'],
            search: null,

            memberList: [
                {
                    avatar: `https://picsum.photos/500/300?image=1`,
                    nickname: '미용쓰기',
                    email: 'msnodeve@naver.com',
                    joinDate: '2019-08-03T10:00:00',
                    permissions: 'Maintainer' 
                },
                {
                    avatar: `https://picsum.photos/500/300?image=2`,
                    nickname: '안성민',
                    email: 'asm9677@naver.com',
                    joinDate: '2020-07-02',
                    permissions: 'Owner' 
                },
                {
                    avatar: `https://picsum.photos/500/300?image=3`,
                    nickname: '강슬기',
                    email: 'tmfrl8510@naver.com',
                    joinDate: '2020-07-10',
                    permissions: 'Maintainer' 
                }
            ]
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
        },       
        selectMember(){
            this.search = ''
            if(this.items[0]){
                this.inviteMember.push(this.items[0])          
                this.inviteMember.pop()      
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