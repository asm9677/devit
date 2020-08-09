<template>
    <div>
        <v-card tile="tile" flat="flat" outlined="outlined">
            <v-layout wrap="wrap">
                <v-flex
                    xs12="xs12"
                    sm12="sm12"
                    md9="md9"
                    lg9="lg9"
                    xl9="xl9"
                    ref="left"
                    style="margin-left:0px;">
                    <v-list style="padding:20px 100px;">
                        <v-list-item>
                            <v-list-item-title>프로필 사진</v-list-item-title>
                        </v-list-item>
                        <v-list-item>
                            <v-list-item-title>
                                <div
                                    style="width:200px; height:200px; border:2px dashed gray; margin-top:15px;">
                                    <input
                                        type="file"
                                        v-show="false"
                                        accept="image/png, image/jpeg, image/bmp"
                                        @change="changeImg"
                                        ref="file"
                                        id="file"/>
                                    <v-img
                                        :src="`http://i3a101.p.ssafy.io/images/${item.profile}`"
                                        min-height="100%"
                                        min-width="100%"/>
                                </div>
                            </v-list-item-title>
                            <div style="margin-right:5px"></div>
                            <v-btn depressed="depressed" color="primary" @click="clickImg($event)">
                                사진 변경
                            </v-btn>
                            <div style="margin-right:5px"></div>
                            <v-btn depressed="depressed" @click="deleteImg()">
                                삭제
                            </v-btn>
                        </v-list-item>

                    </v-list>
                    <v-list style="padding:20px 100px;">
                        <v-list-item>
                            <v-list-item-title>닉네임</v-list-item-title>
                        </v-list-item>
                        <v-list-item>
                            <v-list-item-title>
                                <v-text-field v-model="item.nickname" counter="20" ref="nickname" id="nickname"></v-text-field>
                            </v-list-item-title>
                            <v-list-item>
                                <!-- <v-btn depressed="depressed" color="primary" @click="checkDupl()">
                                    중복 검사
                                </v-btn> -->
                                <v-btn depressed="depressed" color="primary" @click="saveNickname()">
                                    닉네임 변경
                                </v-btn>
                            </v-list-item>
                        </v-list-item>
                    </v-list>
                    <v-list style="padding:20px 100px;">
                        <v-list-item>
                            <v-list-item-title>비밀번호 변경</v-list-item-title>
                        </v-list-item>
                        <v-list-item>
                            <v-list-item-title>
                                <v-text-field v-model="nowPassword" type="password" placeholder="현재 비밀번호" ref="nowPassword" id="nowPassword"></v-text-field>
                                <v-text-field v-model="newPassword" type="password" placeholder="새 비밀번호" @keyup="validatePW" ref="newPassword" id="newPassword"></v-text-field>
                                <v-text-field v-model="newPasswordCfm" type="password" placeholder="새 비밀번호 확인" @keyup="validatePW" ref="newPassword" id="newPasswordCfm"></v-text-field>
                                <span style="color:red; font-size:12px;" v-show="!isSamePW">비밀번호가 일치하지 않습니다.</span>
                            </v-list-item-title>
                        </v-list-item>
                    </v-list>
                    <v-list style="padding:20px 100px;">
                        <!-- <v-btn depressed="depressed" color="primary" @click="ValidationForm()">
                            확인
                        </v-btn> -->
                        <v-btn depressed="depressed" color="primary" @click="savePassword()">
                            비밀번호 변경
                        </v-btn>
                    </v-list>
                </v-flex>
            </v-layout>
        </v-card>

        <v-snackbar v-model="snackbar" timeout="1500" color="primary">
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
        data() {
            return {
                msg: "",
                snackbar: false,
                item: {},
                nowPassword: "",
                newPassword: "",
                newPasswordCfm: "",
                duplCheckComplete: true,
                isDupl: false,
                doneDuplChk: false,
                originNickname: "",
                isSamePW: true
            }
        },
        created() {
            if (!store.state.token) {
                this
                    .$router
                    .push('/')
            }
        },
        mounted() {

            http
                .axios
                .get("/api/v1/users/user")
                .then(({data}) => {

                    this.item = data.result;
                    this.originNickname = this.item.nickname;
                    //this.item.profile = "selenaTestImg.jpg";
                    if(this.item.profile == "") this.item.profile = "defaultUser.png";
                })
                .catch((error) => {
                    console.dir(error)
                })

            },
        methods: {
            clickImg() {
                $("#file").click();
            },
            changeImg(e) {
                var frm = new FormData();
                frm.append("file", document.getElementById("file").files[0]);
                
                axios
                    .post('http://i3a101.p.ssafy.io:8080/api/v1/file/upload', frm, {
                        headers: {
                            'accept': '*/*',
                            'X-AUTH-TOKEN': store.state.token,
                            'Content-Type': 'multipart/form-data'
                        }
                    })
                    .then(({data}) => {
                        this.item.profile = data.result;
                        console.log(data.result);

                        http
                        .axios
                        .put('/api/v1/users', {
                            "profile": this.item.profile,
                            "modifyType" : "profile"
                        })
                        .then(({data}) => {
                            alert("수정되었습니다.");
                            this.$router.push('/user/modify');
                        })
                    })
                    .catch((error) => {
                        console.dir(error)
                    })
                    . finally(() => {})
                this.$refs.file.value = ''
            },
            deleteImg(){
                this.item.profile = "defaultUser.png";
                http
                .axios
                .put('/api/v1/users', {
                    "profile": this.item.profile,
                    "modifyType" : "profile"
                })
                .then(({data}) => {
                    alert("수정되었습니다.");
                    this.$router.push('/user/modify');
                })
            },
            checkDupl() {

                http
                    .axios
                    .get(`/api/v1/account/valid/${this.item.nickname}`)
                    .then(({data}) => {
                        alert(data.msg);
                        if (data.msg == "duplicate") {
                            alert("이미 사용중인 닉네임입니다.");
                        }
                    })
                    .catch((error) => {
                        console.dir(error)
                    })

                },
            goto(target, msg) {
                this
                    .$vuetify
                    .goTo(target, {
                        duration: 300,
                        offset: 150,
                        easing: 'easeInOutCubic'
                    })
                this.msg = msg;
                this.snackbar = true;
            },
            ValidationForm() {
                if (!this.item.nickname) {
                    this.goto('#nickname', '닉네임을 입력해주세요.')
                    this
                        .$refs
                        .nickname
                        .focus();
                }else if(this.item.nickname != this.originNickname && !this.doneDuplChk){
                    alert("닉네임 중복검사를 해주세요.");
                }else if(this.item.nickname != this.originNickname && !this.isDupl){
                    alert("중복된 닉네임입니다.");
                }else if(this.newPassword != this.newPasswordCfm){
                    
                    this.goto('#newPasswordCfm', '새 비밀번호를 확인해주세요.')
                    this
                        .$refs
                        .newPasswordCfm
                        .focus();
                } else {
                    this.saveModify();
                }
            },
            saveModify() {
                http
                    .axios
                    .put('/api/v1/users', {
                        "nickname": this.item.nickname,
                        "profile": this.item.profile,
                        "password": this.nowPassword,
                        "newPassword": this.newPassword
                    })
                    .then(({data}) => {
                        alert("수정되었습니다.");
                        this
                            .$router
                            .push("/");
                    })
            },
            validatePW(){
                if(this.newPasswordCfm == ""){
                    this.isSamePW = true;
                }else if(this.newPassword == this.newPasswordCfm){
                    this.isSamePW = true;
                }else if(this.newPassword != this.newPasswordCfm){
                    this.isSamePW = false;
                }
            },
            saveNickname(){
                if(this.originNickname == this.item.nickname){
                    this.goto('#nickname', '기존 닉네임과 동일합니다.')
                    this
                        .$refs
                        .nickname
                        .focus();
                }else if (!this.item.nickname) {
                    this.goto('#nickname', '닉네임을 입력해주세요.')
                    this
                        .$refs
                        .nickname
                        .focus();
                }else{
                    http
                    .axios
                    .put('/api/v1/users', {
                        "nickname": this.item.nickname,
                        "modifyType" : "nickname"
                    })
                    .then(({data}) => {
                        alert("수정되었습니다.");
                        this.$router.push('/user/modify');
                    })
                }
                
            },
            savePassword(){
                if(!this.isSamePW){
                    
                    this.goto('#newPasswordCfm', '비밀번호가 일치하지 않습니다.')
                    this
                        .$refs
                        .newPasswordCfm
                        .focus();
                } else {
                    http
                    .axios
                    .put('/api/v1/users', {
                        "password": this.nowPassword,
                        "newPassword": this.newPassword,
                        "modifyType" : "password"
                    })
                    .then(({data}) => {
                        alert("수정되었습니다.");
                        this.$router.push('/user/modify');
                    })
                }
            }
        }
    }
</script>

<style></style>