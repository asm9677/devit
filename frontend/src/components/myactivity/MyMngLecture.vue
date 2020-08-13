<template>
    <div style="margin:50px">
        <v-container justify-center="justify-center">
            <v-layout row="row" wrap="wrap">
                <div style="width:100%; margin:0 auto;">
                    <span style="font-size:26px; font-weight:600; color:#1976d2 !important;">관리중인 프로젝트</span>
                    <div style="width:100%; margin-bottom:30px;"></div>

                    <v-list>
                        <div
                            class=""
                            style="width:100%; border-bottom:1px solid #c8c8c8; cursor:pointer;"
                            v-for="item in items"
                            :key="item.lectureId">
                            <v-list-item style="width:100%;">
                                <v-list-item-content>
                                    <div>
                                        <v-img
                                            max-width="170"
                                            max-height="100"
                                            :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                            :lazy-src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                            aspect-ratio="1.7"
                                            @click="move(`/lecture/detail/${item.lectureId}`)"></v-img>
                                    </div>
                                </v-list-item-content>
                                <v-list-item-content @click="move(`/lecture/detail/${item.lectureId}`)">
                                    <div style="width:500px;">{{item.title}}</div>
                                </v-list-item-content>
                                <v-list-item-content @click="move(`/lecture/detail/${item.lectureId}`)">
                                    <div style="text-align:right; width:50px;">{{item.lectureCount}}</div>
                                </v-list-item-content>
                                <v-list-item-content @click="move(`/lecture/detail/${item.lectureId}`)">
                                    <div style="text-align:right; width:50px;">{{item.viewCount}}</div>
                                </v-list-item-content>
                                <v-list-item-content>
                                    <v-btn
                                        depressed="depressed"
                                        small="small"
                                        text="text"
                                        color="success"
                                        @click="move(`/lecture/management/default/${item.lectureId}`)">관리하기</v-btn>
                                    <v-btn
                                        depressed="depressed"
                                        small="small"
                                        text="text"
                                        color="error"
                                        @click="remove(`${item.lectureId}`)">삭제하기</v-btn>
                                </v-list-item-content>
                            </v-list-item>
                        </div>
                    </v-list>
                </div>

            </v-layout>
        </v-container>
        <v-snackbar v-model="snackbar" timeout="1500" color="primary">
            {{msg}}
        </v-snackbar>
        <v-snackbar v-model="errorSnackbar" timeout="1500" color="error">
            {{errorMsg}}
        </v-snackbar>
    </div>
</template>

<script
    src="https://code.jquery.com/jquery-3.5.1.min.js"
    integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
    crossorigin="anonymous"></script>
<script>
    import http from "@/util/http_common.js"
    import store from "@/store/index.js"

    export default {
        data() {
            return {
                items: [],
                level: this.$route.query.level,
                page: 1,
                itemsperpage: 10,
                loading: false,
                errorMsg: "",
                errorSnackbar: false,
                msg: "",
                snackbar: false
            }
        },
        filters: {
            convertView(num) {
                if (num < 1000) {
                    return num + '회'
                }

                if (num >= 100000000) {
                    num /= 100000000;
                    return parseFloat(num).toFixed(2) + '억회'
                }
                if (num >= 10000) {
                    num /= 10000;
                    return parseFloat(num).toFixed(0) + '만회'
                }
                if (num >= 1000) {
                    num /= 1000;
                    return parseFloat(num).toFixed(1) + '천회'
                }
            },
            convertLike(num) {
                return num
                    .toString()
                    .replace(/\B(?=(\d{3})+(?!\d))/g, ',');
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
            this.loading = true;
            http
                .axios
                .get(
                    `/api/v1/myMngLecture?page=${this.page}&itemsperpage=${this.itemsperpage}`
                )
                .then(({data}) => {
                    this.page++;
                    this.items = data.result;
                })
                . finally(() => {
                    this.loading = false;
                })

            document.addEventListener('scroll', this.handleScroll);
        },
        beforeDestroy() {
            document.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
            handleScroll() {
                if ($(document).scrollTop() + $(document)[0].scrollingElement.clientHeight + 100 >= $(
                    document
                ).height()) {
                    if (!this.loading) {
                        this.loading = true;
                        http
                            .axios
                            .get(
                                `/api/v1/myMngLecture?page=${this.page}&itemsperpage=${this.itemsperpage}`
                            )
                            .then(({data}) => {
                                this.page++;

                                for (let i in data.result) 
                                    this
                                        .items
                                        .push(data.result[i]);
                                }
                            )
                            . finally(() => {
                                this.loading = false;
                            })
                    }
                }
            },
            move(url) {
                this
                    .$router
                    .push(url)
            },
            remove(lectureId) {
                if (confirm("삭제하시겠습니까?")) {
                    http
                        .axios
                        .put('/api/v1/deleteLecture', {"lectureId": this.item.lectureId})
                        .then(({data}) => {
                            if (data.msg == "noauth") {
                                this.errorMsg = "프로젝트 관리 권한이 없습니다.";
                                this.errorSnackbar = true;
                            } else {

                                this.msg = "삭제되었습니다.";
                                this.snackbar = true;

                                this.page = 1;
                                this.loading = true;
                                http
                                    .axios
                                    .get(
                                        `/api/v1/myMngLecture?page=${this.page}&itemsperpage=${this.itemsperpage}`
                                    )
                                    .then(({data}) => {
                                        this.page++;
                                        this.items = data.result;
                                    })
                                    . finally(() => {
                                        this.loading = false;
                                    })

                                document.addEventListener('scroll', this.handleScroll);
                            }
                        })
                        .catch((error) => {
                            console.dir(error)
                        })
                    }
            }
        }
    }
</script>

<style></style>