<template>
    <div style="; margin-top:20px;">
        <v-card tile="tile" flat="flat">
            <v-layout wrap="wrap">
                <div
                    ref="question"
                    :style="{'height': questionHeight+'px'}"
                    style="width:100%; overflow-y:auto; margin-top:0px;">
                    <v-list v-if="true" link="link">
                        <div
                            class="boardContent"
                            style="width:100%; border-bottom:1px solid #c8c8c8; "
                            v-for="item in items"
                            :key="item.boardId"
                            @click="$emit('showDetail', 1)">
                            <v-list-item style="width:100%;">
                                <v-list-item-content style="padding-bottom:0px;">
                                    <div class="v-list-item__title">{{ item.boardTitle }}</div>
                                    <div class="v-list-item__subtitle">원문제목 : {{ item.boardContent }}</div>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content style="padding-top:0px;">
                                    <v-list-item-subtitle>
                                        <v-avatar class="profile" size="15">
                                            <v-img :src="'https://picsum.photos/500/300?image=15'"></v-img>
                                        </v-avatar>
                                        <span style="margin-left:3px; font-size:12px;">{{ item.userName }}</span>
                                        &nbsp;&nbsp;
                                        <span style="font-size:12px;">
                                            <v-icon small="small">mdi-comment-text</v-icon>
                                            {{item.replyCount}}</span>&nbsp;
                                        <span style="font-size:12px;">조회
                                            {{item.boardCount}}</span>

                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </div>
                    </v-list>

                    <v-container>
                        <v-row justify="center">
                            <v-col cols="8">
                                <v-pagination v-model="page" class="my-4" :length="pageCnt"></v-pagination>
                            </v-col>
                        </v-row>
                    </v-container>
                </div>
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
        props: [],
        watch: {
            page() {
                this.searchByPage()
            }
        },
        data() {
            return {page: 1, pageCnt: 0, itemsPerPage: 4, items: [{}]}
        },
        watch: {},
        created() {},
        mounted() {
            this.searchByPage();
        },
        methods: {
            searchByPage() {

                http
                    .axios
                    .get(`/api/v1/myreply?page=${this.page}&itemsperpage=${this.itemsPerPage}`, {})
                    .then(({data}) => {
                        this.items = data.result;
                        this.pageCnt = data
                            .result[0]
                            .pageCnt;
                        console.log("data.result", data.result)
                    })
                    .catch((error) => {
                        console.dir(error)
                    })
                }
        }
    }
</script>

<style scoped="scoped">
    .v-list-item__title {
        font-size: 14px;
    }
    .v-list-item__subtitle {
        margin-top: 10px;
        font-size: 12px;
    }
</style>