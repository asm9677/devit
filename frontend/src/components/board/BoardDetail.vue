<template>
    <div id="app">
        <v-container>
            <v-container fluid="fluid">
                <template>
                    <v-col :key="item.boardId">
                        <div class="board_detail">
                            <v-list-item style="margin-bottom:5px;">
                                <v-hover v-slot:default="{ hover }">
                                    <v-btn 
                                        v-show="isBtnShow"
                                        outlined="outlined"
                                        depressed="depressed"
                                        text="text"
                                        small="small"
                                        @click="modifyBoard()"
                                        style="margin-right:5px">
                                        <font :color="hover ? '' : 'gray'" size="2">수정</font>
                                    </v-btn>
                                </v-hover>
                                <v-hover v-slot:default="{ hover }">
                                    <v-btn
                                        v-show="isBtnShow"
                                        outlined="outlined"
                                        depressed="depressed"
                                        text="text"
                                        small="small"
                                        @click="deleteBoard()">
                                        <font :color="hover ? '' : 'gray'" size="2">삭제</font>
                                    </v-btn>
                                </v-hover>
                                <v-spacer></v-spacer>
                                <!-- <v-hover v-slot:default="{ hover }">
                                    <v-btn
                                        outlined="outlined"
                                        depressed="depressed"
                                        text="text"
                                        small="small"
                                        style="margin-right:5px"
                                        @click="move('/lecture/create')">
                                        <font :color="hover ? '' : 'gray'" size="2">이전글</font>
                                    </v-btn>
                                </v-hover>
                                <v-hover v-slot:default="{ hover }">
                                    <v-btn
                                        outlined="outlined"
                                        depressed="depressed"
                                        text="text"
                                        small="small"
                                        style="margin-right:5px"
                                        @click="move('/lecture/create')">
                                        <font :color="hover ? '' : 'gray'" size="2">다음글</font>
                                    </v-btn>
                                </v-hover> -->
                                <v-hover v-slot:default="{ hover }">
                                    <v-btn
                                        outlined="outlined"
                                        depressed="depressed"
                                        text="text"
                                        small="small"
                                        @click="goToList()">
                                        <font :color="hover ? '' : 'gray'" size="2">목록</font>
                                    </v-btn>
                                </v-hover>
                            </v-list-item>
                            <v-list-item class="board_title">
                                {{ item.boardTitle }}
                            </v-list-item>
                            <v-list-item>
                                <v-icon size="40">mdi-account-circle</v-icon>
                                <!--<v-list-item-avatar color="grey"><img src=""
                                alt="John"></v-list-item-avatar>-->
                                <v-list-item-content>
                                    <v-list-item class="board_writer">{{ item.userName }}</v-list-item>
                                    <v-list-item class="board_info">
                                        {{item.boardModified ? item.boardModified : item.boardCreated | moment('YYYY-MM-DD HH:mm')}}
                                        <!-- <v-icon color="red" size="20" style="margin:5px 3px 0 5px;">mdi-heart-outline</v-icon>
                                        {{item.likes}} -->
                                        <v-icon size="20" style="margin:5px 3px 0 5px;">mdi-eye</v-icon>
                                        {{item.boardCount}}
                                        <!--<v-icon size="15">mdi-comment-processing-outline</v-icon>-->
                                        <v-spacer></v-spacer>
                                        <v-icon size="20" style="margin:5px 3px 0 5px;">mdi-comment-processing</v-icon>
                                        {{item.replyCount}}
                                    </v-list-item>
                                </v-list-item-content>
                            </v-list-item>

                            <v-divider></v-divider>

                            <v-list dense="dense">

                                <v-list-item class="board_content">
                                    {{ item.boardContent }}
                                </v-list-item>

                            </v-list>
                        </div>

                        <div class="comment_list">
                            <v-divider></v-divider>
                            <v-list-item class="comment_title">
                                댓글
                                <v-icon size="20" style="margin:5px 3px 0 20px;">mdi-comment-processing</v-icon>
                                <span style="font-size:15px; font-weight:normal;">{{item.replyCount}}</span>
                                <v-spacer></v-spacer>
                                <v-hover v-slot:default="{ hover }">
                                    <v-btn
                                        depressed="depressed"
                                        text="text"
                                        small="small"
                                        @click="move('/lecture/create')">
                                        <font :color="hover ? '' : 'gray'" size="2">등록</font>
                                    </v-btn>
                                </v-hover>
                            </v-list-item>

                            <v-list-item>
                                <v-list-item-title>
                                    <div>
                                        <v-textarea
                                            no-resize="no-resize"
                                            outlined="outlined"
                                            auto-grow="auto-grow"
                                            rows="3"
                                            placeholder="미구현 기능입니다"></v-textarea>
                                    </div>
                                </v-list-item-title>
                            </v-list-item>

                            <v-data-iterator
                                class="board_list"
                                :items="items"
                                :items-per-page.sync="itemsPerPage"
                                hide-default-footer="hide-default-footer"
                                no-data-text=""
                            >
                                <template v-slot:default="props">
                                    <v-col v-for="item in props.items" :key="item.board_id">
                                        <v-list-item>
                                            <v-icon size="40">mdi-account-circle</v-icon>
                                            <!--<v-list-item-avatar color="grey"><img src=""
                                            alt="John"></v-list-item-avatar>-->
                                            <v-list-item-content>
                                                <v-list-item class="comment_writer">{{ item.name }}</v-list-item>
                                                <v-list-item class="comment_info">
                                                    {{item.date}}
                                                </v-list-item>
                                            </v-list-item-content>
                                            <v-spacer></v-spacer>
                                            <v-hover v-slot:default="{ hover }">
                                                <v-btn
                                                    depressed="depressed"
                                                    text="text"
                                                    small="small"
                                                    @click="move('/lecture/create')">
                                                    <font :color="hover ? '' : 'gray'" size="2">답글</font>
                                                </v-btn>
                                            </v-hover>
                                        </v-list-item>
                                        <v-list-item class="comment_content">{{ item.content }}</v-list-item>

                                        <v-divider></v-divider>
                                    </v-col>
                                </template>
                            </v-data-iterator>
                        </div>
                    </v-col>
                </template>
            </v-container>
            <v-snackbar v-model="snackbar" bottom="bottom" timeout="1500">
                {{ text }}
                <template v-slot:action="{ attrs }">
                    <v-btn text="text" v-bind="attrs" color="success" @click="snackbar = false">닫기</v-btn>
                </template>
            </v-snackbar>
        </v-container>
    </div>
</template>

<script>
    import http from "@/util/http_common.js"
    import eventBus from "@/lib/EventBus.js"

    export default {
        name: 'app',
        data() {
            return {
                isBtnShow: false,
                itemsPerPage: 10,
                item:{},
                items: [
                    /*{
                        comment_id: 1,
                        name: '홍길동',
                        content: '글내용2',
                        date: '2020.07.06. 15:00',
                        is_reply: 'N'
                    }, {
                        comment_id: 2,
                        name: '홍길동',
                        content: '글내용2',
                        date: '2020.07.06. 15:00',
                        is_reply: 'N'
                    }, {
                        comment_id: 3,
                        name: '홍길동',
                        content: '글내용2',
                        date: '2020.07.06. 15:00',
                        is_reply: 'N'
                    }*/
                ],
                snackbar: false,
                text: ""
            }
        },
        mounted() {
            if(this.$route.params.showMsg == true){
                
                //this.text = "작성이 완료되었습니다.";
                this.text = this.$route.params.msgText;
                this.snackbar = true;
            }
            http
                .axios
                .get("/api/v1/board/" + this.$route.query.boardId, {
                })
                .then(({data}) => {
                    console.log("여기!!!!!!!!!!!!!11", data);
                    this.item = data.result;

                    if(this.item.isMine == 'Y'){ //수정/삭제 버튼
                        this.isBtnShow = true;
                    }else{
                        this.isBtnShow = false;
                    }
                })
                .catch((error) => {
                    console.dir(error)
                })
            },
        methods: {
            modifyBoard() {
                if(this.$router.app.$store.state.token){
                    var boardId = this.$route.query.boardId;
                    this
                        .$router
                        .push({
                            name: 'BoardEdit',
                            query: {
                                'boardtype': this.$route.query.boardtype,
                                'boardId': boardId,
                                'edittype': 'modify'
                            }
                        });
                }else{
                    eventBus.$emit('doLogin');
                }
                
            },
            deleteBoard() {
                if(this.$router.app.$store.state.token){
                    var boardId = this.$route.query.boardId;
                    if (confirm("삭제하시겠습니까?")) {
                        http
                            .axios
                            .delete("/api/v1/board/" + boardId, {
                                /*nickname: this.nickname,
                                email: this.email,
                                password: this.password*/
                            })
                            .then(({data}) => {
                                //alert("삭제가 완료되었습니다.");
                                //data;
                                this.text = "삭제가 완료되었습니다.";
                                this.snackbar = true;
                                /*this
                                    .$router
                                    .push("/board?type="+boardType);*/
                                this.goToList();
                            })
                            .catch((error) => {
                                console.dir(error)
                            })
                        }
                }else{
                    eventBus.$emit('doLogin');
                }
                
            },
            goToList() {
                this
                    .$router
                    .push("/board?type=" + this.$route.query.boardtype);
            }
        }
    }
</script>

<style>
    .board_detail {
        padding: 20px;
        min-height: 250px;
    }
    .board_detail .v-list-item {
        min-height: 10px;
    }

    .board_detail .board_title {
        font-size: 30px;
        /*font-weight: bold;*/
    }
    .board_detail .board_writer {
        /*font-weight: bold;*/
    }

    .board_detail .board_info {
        font-size: 12px;
        color: #585858 !important;
    }

    .board_detail .board_content {
        font-size: 15px;
        margin: 10px 5px;
    }

    .comment_list {
        padding: 20px;
        margin-top: 20px;
    }
    .comment_list .comment_title {
        font-size: 18px;
        font-weight: bold;
    }
    .comment_list .comment_writer {
        font-size: 16px;
    }
    .comment_list .comment_info {
        font-size: 12px;
        color: #c8c8c8 !important;
        /**이거 안먹네..? */
    }
    .comment_list .comment_content {
        font-size: 16px;
        margin: 10px;
        color: #c8c8c8 !important;
        /**이거 안먹네..? */
    }
</style>