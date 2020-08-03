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
                <v-btn icon>
                    <v-icon color="primary"> mdi-bell-outline </v-icon>
                </v-btn>
                 
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
            
                    <v-list flat tile>
                        <v-list-item>
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
                    >
                    <v-card tile>
                        <Login @closeDialog="dialog = false"></Login>
                    </v-card>
                    </v-dialog>                    

                    <span style="margin-left:10px;"></span>                
                    <v-btn depressed color="primary" outlined @click="move('/join')">회원가입</v-btn>                
            </div>            
        </v-app-bar>
        <temp-board :dialog="tempDialog" @closeTempBoard="tempDialog=false"></temp-board>
    </div>
</template>

<script>
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
    data() {
        return {
            search: false,
            keyword: '',
            dialog: false,
            logo:true,
            tempDialog: false,
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
            /*
                http.axios.get('/api/v1/lectures/create').get(({data}) => {
                    this.move(`/lecture/management/${data.result}/default`);
                })
            */
            this.move('/lecture/management/0/default');
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
        }

    }
}
</script>

<style>
    
</style>