<template>
    <v-card tile flat style="margin-top:50px">
        <v-img src="@/assets/logo.png" style="width:200px; margin: 0px auto"></v-img>
        <v-card tile flat outlined style="width:40%; margin: 0px auto; margin-top:50px; padding:50px;">
            <table width="100%">
                <tr width="100%">
                    <td width="100%">
                        <v-text-field
                            placeholder="닉네임"
                            prepend-icon="mdi-account"
                            v-model="nickname"
                        ></v-text-field>
                        <div>
                            <span style="float:left">
                                {{ nick_text }}
                            </span>
                            
                            <v-btn color="primary" style="width:15%; float:right" depressed large @click="vaildNickname">
                                중복확인
                            </v-btn>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <v-text-field
                            placeholder="이메일"
                            prepend-icon="mdi-email"
                            v-model="email"
                        ></v-text-field>
                        <div>
                            <span style="float:left">
                                {{ email_text }}
                            </span>
                            
                            <v-btn color="primary" style="width:20%; float:right" depressed large @click="vaildEmail">
                                이메일 인증
                            </v-btn>
                        </div>
                    </td>
                </tr>
                <!-- <tr width="100%" style="background-color='red'">
                    <td>
                        <v-expand-y-transition>
                            <v-card v-show="check" tile flat>
                                <v-text-field
                                    placeholder="인증코드"
                                    prepend-icon="mdi-key-variant"
                                    v-model="code"
                                ></v-text-field>
                            </v-card>                
                        </v-expand-y-transition>
                    </td>
                    <td width="30%">
                        <v-btn @click="check= true" depressed outlined rounded style="width:20%" v-text="check ? '인증코드 확인' : '인증코드 전송'"></v-btn>
                    </td>
                </tr> -->
                <tr>
                    <td>
                        <v-text-field
                            placeholder="패스워드"
                            prepend-icon="mdi-lock"
                            v-model="password"
                            clearable
                            type="password"
                        ></v-text-field>
                    </td>
                </tr>
                <tr>
                    <td>
                        <v-text-field
                            placeholder="패스워드 재확인"
                            prepend-icon="mdi-lock"
                            v-model="re_password"
                            clearable
                            type="password"
                        ></v-text-field>
                    </td>
                </tr>
            </table>

            <v-btn color="normal" style="margin-top:50px; width:100%" depressed large @click="signup">
                다음
            </v-btn>
        </v-card >        
    </v-card>
</template>

<script>
import http from "@/util/http_common.js"

export default {
    data() {
        return {
            nickname: '',
            email: '',
            password: '',
            re_password: '',
            code: '',    
            check: false,
            nick_text: '닉네임 중복 체크를 해주세요',
            email_text: '아메일 인증을 해주세요',
        }
    },

    methods: {
        signup(){
            http.axios.post("/api/v1/account/signup", {
                nickname: this.nickname,
                email: this.email,
                password: this.password,
            }).then(({data}) => {
                data;
                alert("회원가입이 완료되었습니다.");
                this.$router.push("/")
            }).catch((error) => {
                console.dir(error)
            })
        },
        vaildNickname(){
            http.axios.get(`/api/v1/users/user/${this.nickname}`)
            .then(({data}) => {
                if(data.msg == "success"){
                    this.nick_text = data.result;
                }else if(data.msg == "duplicate"){
                    this.nick_text = data.result;
                }
            }).catch((error) => {
                this.nick_text = "사용할 수 없는 닉네임 입니다.";
            })
        },
        vaildEmail(){
            http.axios.get(`/api/v1/mail/sendMailToConfirm?email_to=${this.email}`)
            .then(({data}) => {
                if(data.msg == "success"){
                    this.email_text = data.result;
                }
            }).catch((error) => {
                // 에러 반환
                this.email_text = "계정 인증 메일 발송 실패";
            })
        }
    }
}
</script>

<style>

</style>