<template>
  <div style="background-color:'red'">
        <v-container>
            <div style="margin-top:10px; z-index:9"></div>
            <v-tabs
                color="black"          
                background-color="transparent"
                slider-color="primary"
                slider-size=4
                style="position:sticky; top:5px; z-index:9;"
                ref="menu"
                show-arrows
            >
                <v-tab @click="goto('#introduce')"><span style="font-size:16px;">소개</span></v-tab>
                <v-tab @click="goto('#curriculum')"><span style="font-size:16px">교육 과정</span></v-tab>
                <v-tab @click="goto('#changes')"><span style="font-size:16px">변경 사항</span></v-tab>
                <v-tab @click="goto('#helped')"><span style="font-size:16px">도움주신 분들</span></v-tab>
                
            </v-tabs>   
        <v-card tile flat>
                <v-layout wrap ref="main">
                    <v-flex xs12 sm12 md8 lg8 xl8 ref="left" style="margin-left:0px; padding:20px;">       
                        <v-card>
                            <v-img 
                                :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                :lazy-src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                aspect-ratio="1.7"
                            ></v-img>                            
                        </v-card>

                        <div v-show="!option" style="width:100%;">
                            <h1> {{item.title}}</h1>        
                            <h4>조회수 {{item.viewCount | convertView}}&nbsp;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}</h4>
                            <h4>총 {{item.lectureCount}}강의</h4>
                                        
                            #<v-chip                                            
                                :color="`primary lighten-4`"                                            
                                class="ma-1"
                                v-for="(tag,index) in item.tagName.split(',')"   
                                :key="index"
                                label
                                @click="move(`/search?keyword=${tag}`)"
                            >                                     
                                <span style="color:black">
                                    {{tag}}
                                </span>
                            </v-chip>
                            <div style="margin-top:5px;" /> 
                            <div style="float:left;">
                                <v-avatar
                                    class="profile"
                                    size=30
                                >
                                    <v-img 
                                        :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                    ></v-img>
                                </v-avatar>
                                <span style="margin-left:5px;font-size:16px; ">{{item.nickname}}</span>
                            </div>
                            <div style="float:right;">
                                <v-btn small class="mx-2" fab light color="white" @click="asynClickLike()" :loading="btnLoading">
                                    <v-icon :color="item.userLikeYn ? 'pink' : 'grey'">mdi-heart</v-icon>
                                </v-btn >
                                <v-btn small class="mx-2" fab color="white" @click="copyToClipboard">
                                    <v-icon class="grey--text">mdi-share-variant</v-icon>
                                </v-btn>
                            </div>
                            <div style="margin-bottom:20px; clear:both;" /> 
                            
                            <v-btn depressed dark color="primary" large block>
                                <span style="font-size:20px;">수강하기</span>
                            </v-btn>
                        </div>

                        <div id="introduce" style="margin-top:40px;">
                            <h3>프로젝트 소개</h3>    
                            <div class="content">
                                {{item.content}}
                            </div>                        
                        </div>
                        <div id="curriculum" style="margin-top:40px;">
                            <h3>교육 과정</h3>      
                            <div class="content">
                                미구현 기능입니다.
                                <div style="height:250px"/>
                            </div>                            
                        </div>
                        <div id="changes" style="margin-top:40px;">
                            <h3>변경 사항</h3>    
                            <div class="content">
                                미구현 기능입니다.
                                <div style="height:250px"/>
                            </div>                              
                        </div>
                        <div id="helped" style="margin-top:40px;">
                            <h3>도움주신 분들</h3>  
                            <div class="content">
                                미구현 기능입니다.
                                <div style="height:700px"/>
                            </div>      
                        </div>
                        
                    </v-flex>                    
                    <v-flex v-show="option" style="border-left:1px solid #e2e2e2" md4 lg4 xl4> 
                        <div style="position: sticky; top:130px;">
                            <v-layout>
                                <v-spacer></v-spacer>
                                
                                <v-btn small class="mx-2" fab light color="white" @click="asynClickLike()" :loading="btnLoading">
                                <v-icon :color="item.userLikeYn ? 'pink' : 'grey'">mdi-heart</v-icon>
                                </v-btn >
                                <v-btn small class="mx-2" fab color="white" @click="copyToClipboard">
                                <v-icon class="grey--text">mdi-share-variant</v-icon>
                                </v-btn>
                            </v-layout>
                            <v-layout>
                                <div style="margin:30px; width:100%;">
                                        <h1> {{item.title}}</h1>        
                                        
                                        <h4>조회수 {{item.viewCount | convertView}}&nbsp;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}</h4>
                                        <h4>총 {{item.lectureCount}}강의</h4>
                                        
                                        #<v-chip                                            
                                            :color="`primary lighten-4`"                                            
                                            class="ma-1"
                                            v-for="(tag,index) in item.tagName.split(',')"   
                                            :key="index"
                                            label
                                            @click="move(`/search?keyword=${tag}`)"
                                        >                                     
                                            <span style="color:black">
                                                {{tag}}
                                            </span>
                                        </v-chip>
                                        <div style="margin-top:5px;" /> 
                                        <v-avatar
                                                class="profile"
                                                size=30
                                            >
                                                <v-img 
                                                    :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                                ></v-img>
                                        </v-avatar>
                                        <span style="margin-left:5px;font-size:16px">{{item.nickname}}</span>
                                        <div style="margin-top:20px;" /> 
                                        <v-btn depressed dark color="primary" large block><span style="font-size:20px;">수강하기</span></v-btn>
                                </div>
                            </v-layout>
                        </div>
                    </v-flex>                    
                </v-layout>
        </v-card>
        <v-snackbar
            v-model="snackbar"
            timeout="1500"
        >
            주소를 클립보드로 복사했습니다.
            <template v-slot:action="{ attrs }">
                <v-btn
                color="blue"
                text
                v-bind="attrs"
                @click="snackbar = false"
                >
                    닫기
                </v-btn>
            </template>
        </v-snackbar>
        <v-overlay :value="mainLoading" opacity=0>
            <v-progress-circular indeterminate color="primary lighten-4" size="64"></v-progress-circular>
        </v-overlay>
      </v-container>
  </div>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
import eventBus from "@/lib/EventBus.js"
import http from "@/util/http_common.js"
export default {
    watch: {
        items(val,prev){
            if(val.length > 10)
                val.pop()            
        }
    },
    filters: {
        convertView(num) {
            if(num < 1000){
                return num + '회'
            }

            if(num >= 100000000){
                num /= 100000000;
                return parseFloat(num).toFixed(2) + '억회'
            }
            if(num >= 10000){
                num /= 10000;
                return parseFloat(num).toFixed(0) + '만회'
            }
            if(num >= 1000){
                num /= 1000;
                return parseFloat(num).toFixed(1) + '천회'
            }
        },
        convertLike(num){
            return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        },
    },
    data() {
        return {            
            item:{
                "title": '',
                "thumbnailUrl": '',
                "nickname": "",
                "lectureCount": 0,
                "viewCount": 0,
                "likeCount": 0,
                "tagName": '',
                "userLikeYn": false,
            },
            option: true,
            snackbar: false,

            menuHeight: 0,

            mainLoading: true,
            btnLoading: false,
        }
    },
    created(){        
        http.axios.get(`/api/v1/lectures/${this.$route.params.id}`).then(({data}) => {
            this.item = data.result;
        }).catch((error) => {
            
        }).finally(() => {
            this.mainLoading = false;
        })
    },
    mounted() {        
        this.menuHeight = this.$refs.menu.$el.offsetTop;        
        this.option = this.$refs.main.clientWidth != this.$refs.left.clientWidth;
        window.addEventListener('resize', this.handleResize)
        document.addEventListener('scroll', this.handleScroll);
    },
    beforeDestroy(){
        window.removeEventListener('resize', this.handleResize);
        document.removeEventListener('scroll', this.handleScroll);
        eventBus.$emit("displayLogo", true);
    },
    methods: {
        handleResize() {
            this.option = this.$refs.main.clientWidth != this.$refs.left.clientWidth;
        },        
        handleScroll(){
            eventBus.$emit("displayLogo", window.scrollY < 40);
        },
        copyToClipboard() {
            var url = document.createElement("textarea");
            document.body.appendChild(url);
            url.value = this.$router.app.$el.baseURI;
            url.select();
            document.execCommand('copy');
            document.body.removeChild(url);
            this.snackbar = true;
        },
        asynClickLike(){
            var clickLike = this.clickLike;            
            if(this.$router.app.$store.state.token){        
                this.btnLoading = true;        
                setTimeout(() => {
                    clickLike();
                }, 300);
            }else{
                eventBus.$emit('doLogin');
            }
        },
        clickLike(){
            http.axios.put(`/api/v1/lectures/like?lectureId=${this.item.lectureId}&likeType=1`).then(({data}) => {
                if(data.msg == 'success') {
                    this.item.likeCount += this.item.userLikeYn ? -1 : 1;
                    this.item.userLikeYn = !this.item.userLikeYn;    
                }
            }).finally(() => {                
                this.btnLoading = false;
            })
        },
        goto(target){
            this.$vuetify.goTo(target, {
                duration: 300,
                offset: 100,
                easing: 'easeInOutCubic'
            })
        },
        move(url){
            this.$router.push(url)
        }
        
    }
}
</script>

<style scoped>
    * {
        font-weight: 400;
    }
    h1 {
        font-size: 24px;
        font-weight: 400;
        margin-bottom:20px;
    }
    div.content {
        font-size:16px;
        margin-top:20px;
    }
</style>