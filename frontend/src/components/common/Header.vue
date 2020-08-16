<template>
    <div>
        <v-app-bar
            color="white"
            
            fixed
            ref="header"
        >
            <div ref="empty"></div>
            <v-slide-x-transition>
                <v-card  v-show="logo" flat tile>
                    <v-img src="@/assets/logo.png" style="cursor:pointer" max-width="150px" @click="move('/')"></v-img>
                </v-card>
            </v-slide-x-transition>
            <v-spacer></v-spacer>
            <v-expand-x-transition>
                 <v-card 
                    v-show="search"
                    dense
                    flat
                    rounded
                    height="70%"
                 >
                    <v-text-field
                        placeholder="검색"
                        v-model="keyword"
                        solo
                        dense
                        flat
                        outlined
                        @keydown.enter="keywordSearch"
                        autofocus
                        @blur="hideSearch($event)"
                    >
                    
                    </v-text-field>
                 </v-card>
            </v-expand-x-transition>
            <v-btn icon @click="keywordSearch" ref="searchBtn">
                <v-icon  ref="searchIcon"> mdi-magnify </v-icon>
            </v-btn>
            <span style="margin-left:50px;"></span>
            
            
            <div v-show="token">
                <v-hover
                    v-slot:default="{ hover }"                    
                >
                    <v-btn depressed text small @click="$router.app.$store.state.token ? createProject() : dialog = true">                        
                        <font :color="hover ? 'primary' : 'gray'" size="2">프로젝트 생성</font>                        
                    </v-btn>
                </v-hover>

                <v-menu left bottom offsetY>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn 
                            icon
                            v-bind="attrs"
                            v-on="on"
                        >
                            <v-icon color="primary"> mdi-bell-outline </v-icon>
                        </v-btn>
                    </template>
                    <v-list class="notice_list" outlined dense>
                        <v-list-item>
                            <v-list-item-title>전체 {{notices.length}}</v-list-item-title>
                        </v-list-item>
                        <template v-for="(notice,i) in notices">
                            <v-divider :key="`${i}_divider`" />
                            <v-list-item :key="`${i}_notice`">
                                <v-list-item-avatar size="30">
                                    <v-img
                                        :src="'http://i3a101.p.ssafy.io/images/' + notice.profile"
                                    >
                                    </v-img>
                                </v-list-item-avatar>
                                <v-list-item-content v-if="notice.noticeType == 1">
                                    <v-list-item-title> 새로운 전체 공지가 있습니다. </v-list-item-title>
                                    <v-list-item-subtitle> {{notice.boardTitle}} </v-list-item-subtitle>
                                    <v-list-item-subtitle> {{notice.nickname}} &middot; {{notice.created}} </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-content v-else-if="notice.noticeType == 2">
                                    <v-list-item-title> {{notice.boardTitle}} </v-list-item-title>
                                    <v-list-item-subtitle> {{notice.replyContent}} </v-list-item-subtitle>
                                    <v-list-item-subtitle> {{notice.nickname}} &middot; {{notice.created}} </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-content v-else-if="notice.noticeType == 3">
                                    <v-list-item-title> {{notice.lectureTitle}} </v-list-item-title>
                                    <v-list-item-subtitle> {{notice.subIndexTitle}}에 {{notice.reqType == 'wiki' ? '위키 수정 요청' : '강의 영상 업로드'}} </v-list-item-subtitle>
                                    <v-list-item-subtitle> {{notice.nickname}} &middot; {{notice.created}} </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-content v-else-if="notice.noticeType == 4">
                                    <v-list-item-title> {{notice.lectureTitle}} </v-list-item-title>
                                    <v-list-item-subtitle> {{notice.subIndexTitle}}에 {{notice.reqType == 'wiki' ? '위키 수정 요청' : '강의 영상 업로드'}} </v-list-item-subtitle>
                                    <v-list-item-subtitle> 수락 &middot; {{notice.created}} </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-action>
                                    <v-icon>
                                        mdi-close
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                        </template>
                    </v-list>
                </v-menu>

                
                 
                <v-menu
                    left
                    bottom
                    offset-y
                    flat
                >
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn
                            icon
                            v-bind="attrs"
                            v-on="on"
                        >
                            <v-icon color="primary">mdi-account-circle</v-icon>
                        </v-btn>
                    </template>
            
                    <v-list flat tile outlined style="padding:0px;">
                        <v-list-item @click="userModify">
                            <v-list-item-icon>
                                <v-icon>mdi-account</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                                <v-list-item-title>내 정보</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item @click="logout">
                            <v-list-item-icon>
                                <v-icon>mdi-power</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                                <v-list-item-title>로그아웃</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item @click="tempDialog=true">
                            <v-list-item-icon>
                                <v-icon>mdi-clipboard-text</v-icon>
                            </v-list-item-icon>
                            <v-list-item-content>
                                <v-list-item-title>임시작성 목록</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        
                    </v-list>
                </v-menu> 
            </div>
            <div v-show="!token">                
                    <v-btn depressed outlined @click.stop="dialog = true">로그인</v-btn>

                    <v-dialog
                        v-model="dialog"
                        max-width="350"
                        style="z-index:10000;"
                    >
                    <v-card tile>
                        <Login @closeDialog="dialog = false"></Login>
                    </v-card>
                    </v-dialog>                    

                    <span style="margin-left:10px;"></span>                
                    <v-btn depressed color="primary" outlined @click="move('/join')">회원가입</v-btn>                
            </div>            
        </v-app-bar>
        <v-snackbar 
            v-model="snackbar"
            timeout="2000"
            right
            color="primary"
        >
            {{msg}}
        </v-snackbar>
        <temp-board :dialog="tempDialog" @closeTempBoard="tempDialog=false"></temp-board>
    </div>
</template>

<script>
import http from "@/util/http_common.js"
import eventBus from "@/lib/EventBus.js"
import store from "@/store/index.js"
import Login from "@/components/user/Login.vue"
import TempBoard from "@/components/board/TempBoard.vue"

export default {
    components: {
       Login,
       TempBoard
    },
    created(){
        eventBus.$on("modifyNavForHeader", (width) => {
            this.$refs.empty.style.marginLeft = width + 20 + "px";
        });

        eventBus.$on("displayLogo", (logo) => {
            this.logo = logo;
        });

        eventBus.$on("doLogin", () => {
            this.dialog = true;
        })
    },
    mounted(){
        eventBus.$emit("updateHeader", this.$refs.header.computedHeight);
    },
    computed:{
        token() {
            return store.state.token;
        }
    },
    filters: {
    },
    data() {
        return {
            search: false,
            keyword: '',
            dialog: false,
            logo:true,
            tempDialog: false,
            snackbar: false,
            msg: '',

            notices: [
                {
                    noticeType: 1,
                    reqType: 'wiki',
                    profile:'defaultUser.png',
                    nickname: '미용쓰기',
                    boardTitle: '1',
                    replyContent: '2',
                    created: '3',
                    subIndexTitle: '4',
                },{
                    noticeType: 2,
                    reqType: 'wiki',
                    profile:'defaultUser.png',
                    nickname: '미용쓰기',
                    boardTitle: '1',
                    replyContent: '2',
                    created: '3',
                    subIndexTitle: '4',
                },{
                    noticeType: 3,
                    reqType: 'wiki',
                    profile:'defaultUser.png',
                    nickname: '미용쓰기',
                    lectureTitle: '5',
                    boardTitle: '1',
                    replyContent: '2',
                    created: '3',
                    subIndexTitle: '4',
                },{
                    noticeType: 4,
                    reqType: 'wiki',
                    profile:'defaultUser.png',
                    nickname: '미용쓰기',
                    lectureTitle: '5',
                    boardTitle: '1',
                    replyContent: '2',
                    created: '3',
                    subIndexTitle: '4',
                },
            ],
        }
    },
    methods: {
        keywordSearch(){
            if(this.search){
                this.$router.push(`/search?keyword=${this.keyword}`).catch(()=>{location.reload(true);});
                /***                
                검색창에 입력된 키워드로 검색하는 로직 작성                 
                ***/
            }else{
                this.search = true;
            }
        },
        hideSearch(event){
            if(!event.relatedTarget){
                this.search = false;
                this.keyword = ''
            }
        },
        createProject(){                        
            http.axios.post('/api/v1/lectures').then(({data}) => {
                if(data.result.lectureId){
                    this.snackbar = true;
                    this.msg = "프로젝트가 생성되었습니다."
                    this.move(`/lecture/management/default/${data.result.lectureId}`);
                }
            }).catch(({data}) => {
                alert("프로젝트 생성에 실패하였습니다.");
            })
            
        },
        moveJoin(){
            this.$router.push('/join').catch(()=>{location.reload(true);});    
        },
        moveMain(){
            this.$router.push('/').catch(()=>{location.reload(true);});            
        },
        move(path){
            this.$router.push(path).catch(()=>{location.reload(true);});            
        },
        logout(){
            store.commit('logout')
            location.reload(true);
        },
        userModify(){
            if (this.$router.app.$store.state.token) {
                this.$router.push('/user/modify').catch(()=>{location.reload(true);});   
            } else {
                eventBus.$emit('doLogin');
            }
        }

    }
}
</script>

<style scoped>
    
    .v-menu__content  {
        box-shadow: none;
        overflow-y: visible;
        overflow-x: visible;
        contain: none;
    }

    .notice_list {
        border-radius:3px; 
        width: 400px;
        max-height:350px;
        overflow-y:auto;
        padding:0px;
    }
</style>