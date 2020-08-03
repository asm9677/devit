<template>
    <div id="app" style="background-color:#f2f2f2; max-width:350px; z-index:99" >
    <v-container>
        <v-layout row class="text-xs-center">
        <v-flex class="grey lighten-4">
            <v-container class="text-xs-center">
            <v-img src="@/assets/logo.png" max-width="250px" style="margin:0px auto"></v-img>
            <!--<v-card flat>-->
                <v-form>
                    <v-text-field prepend-icon="mdi-account" name="Email" label="Email" v-model="email"></v-text-field>
                    <v-text-field prepend-icon="mdi-lock" name="Password" label="Password" type="password" v-model="password" @keydown.enter="login"></v-text-field>
                    <v-checkbox label="자동 로그인" dense style="margin:0px"></v-checkbox>
                    <v-card-actions>
                    <v-btn primary large block color="primary" @click="login" style="margin-bottom:18px">Login</v-btn>
                    </v-card-actions>                
                </v-form>

                <div class="find_info">
                    <div class="add-option">
                        <div class="wrap">
                            <p>다른 서비스로 로그인</p>
                            <a class="_kakao _serviceIcon" title="kakao" href="#" @click="forKakaoLogin"></a>
                            <a class="_naver _serviceIcon" title="naver" href="#"></a>
                            <a class="_github _serviceIcon" title="github" href="#"></a>
                        </div>
                    </div>

                    <a href="#">아이디 찾기</a>
                    <span class="bar" aria-hidden="true">|</span>
                    <a href="#">비밀번호 찾기</a>
                    <span class="bar" aria-hidden="true">|</span>
                    <a href="#" @click="moveJoin">회원가입</a>                 
                </div>
                <!-- hover underline 추가예정 -->
            <!--</v-card>-->
            </v-container>
        </v-flex>
        </v-layout>
    </v-container>
    </div>
    
</template>

<script>
import store from "@/store/index.js"
import http from "@/util/http_common.js"
import eventBus from "@/lib/EventBus.js"
import KaKaoLogin from '@/lib/oauth/KaKaoLogin.js'

export default {
    data() {
        return {
            email: '',
            password: '',
        }
    },
    created(){
        eventBus.$on('socialLogin', (email, nickname, password) => {
            http.axios.post('/api/v1/account/login', {
                email: email,
                password: password
            }).then(({data}) => {                
                store.commit('login', {token: data.result, email: email})
                this.$emit('closeDialog');
                location.reload(true);
            }).catch((error) => {
                console.log(error)
                http.axios.post("/api/v1/account/signup", {
                    nickname: nickname,
                    password: password,
                    email: email
                }).then(() => {
                    http.axios.post('/api/v1/account/login', {
                        email: email,
                        password: password
                    }).then(({data}) => {
                        store.commit('login', {token: data.result, email: email})      
                        this.$emit('closeDialog');
                        location.reload(true);
                    })
                })
            }).finally(() => {
            })
        })
    },
    methods:{
        forKakaoLogin(){
            KaKaoLogin.loginWithKakao();
        },
        login(){
            http.axios.post("/api/v1/account/login", {
                email: this.email,
                password: this.password
            }).then(({data}) => {
                store.commit('login', {token: data.result, email: this.email});
                this.$emit('closeDialog');
                location.reload(true);
            })
        },
        moveJoin(){
            this.$router.push('/join')
            this.$emit('closeDialog');
        },
    }
}
</script>

<style scoped>
    .find_info {
        font-size:14px;
        line-height:14px;
        padding-top:18px;
        text-align:center;
        color:#8e8e8e;
        border-top:1px solid #e4e4e5;
    }
    .bar{
        display: inline-block;
        width:1px;
        height:12px;
        text-indent: -999em;
        background: #e4e4e5;
        vertical-align: bottom;
    }
    .find_info .bar {
        margin: 0 3px;
    }

    ._kakao{
        background-color: #ffcd00;
        background-image: url(https://statics.goorm.io/images/social/logo/kakaoLogo.svg);
    }

    ._naver{
        background-color: #1dc800;
        background-image: url(https://statics.goorm.io/images/social/logo/naverLogo.svg);
    }
    ._github{
        background-color: white;
        background-image: url('/github.png');
        background-size: 40px
    }

     ._serviceIcon{
        background-repeat: no-repeat;
        background-position: center;
        width: 40px;
        height: 40px;
        border-radius: 3px;
        -webkit-box-shadow: 0 0 1px 0 rgba(0, 0, 0, 0.12), 0 1px 1px 0 rgba(0, 0, 0, 0.24);
        box-shadow: 0 0 1px 0 rgba(0, 0, 0, 0.12), 0 1px 1px 0 rgba(0, 0, 0, 0.24);
        display: -ms-inline-flexbox;
        display: inline-flex;
        -ms-flex-pack: center;
        justify-content: center;
        margin:10px;
     }
    .add-option{
        margin-bottom: 20px;
    }

</style>