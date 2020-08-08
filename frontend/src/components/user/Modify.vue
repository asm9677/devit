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
                                <!-- <div style="width:100%; height:25em; border:2px dashed gray;
                                cursor:pointer; margin-top:15px;" @click="clickImg($event)" id="image"> <input
                                type="file" v-show="false" accept="image/png, image/jpeg, image/bmp"
                                @change="changeImg" ref="file" id="file"/> <div v-show="thumbnailUrl" style="
                                width:100%;height:100%; display: -webkit-box; display: flex; -webkit-box-pack:
                                center; justify-content: center; -webkit-box-align: center; align-items: center;
                                "> <v-icon style="font-size:40px;" color='primary'> mdi-account-circle</v-icon>
                                <div> <div class="primary--text" sytle="display:block;font-size:14px;">이미지
                                업로드</div> <div style="font-size:14px;">클릭해서 대표 이미지를 선택하세요.</div> </div> </div>
                                <v-img v-show="!thumbnailUrl"
                                :src="`http://i3a101.p.ssafy.io/images/${profile}`" min-height="100%"
                                min-width="100%"/> </div> -->
                            </v-list-item-title>
                            <!-- </v-list-item> <v-list-item> -->
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
                                <v-btn depressed="depressed" color="primary" @click="checkDupl()">
                                    중복 검사
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
                                <v-text-field v-model="nowPassword" placeholder="현재 비밀번호"></v-text-field>
                                <v-text-field v-model="newPassword" placeholder="새 비밀번호"></v-text-field>
                                <v-text-field v-model="newPasswordCfm" placeholder="새 비밀번호 확인"></v-text-field>
                            </v-list-item-title>
                        </v-list-item>
                    </v-list>
                    <v-list style="padding:20px 100px;">
                        <v-btn depressed="depressed" color="primary" @click="ValidationForm()">
                            확인
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
                originNickname: ""
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
                    this.item.profile = "selenaTestImg.jpg";
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
                    })
                    .catch((error) => {
                        console.dir(error)
                    })
                    . finally(() => {})
                this.$refs.file.value = ''
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
                        "password": this.item.newPassword
                    })
                    .then(({data}) => {
                        alert("수정되었습니다.");
                        this
                            .$router
                            .push("/");
                    })
            }
        }
    }
</script>

<style></style>