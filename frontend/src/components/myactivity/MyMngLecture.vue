<template>
    <div style="margin:50px">
        <v-container justify-center="justify-center">
            <v-layout row="row" wrap="wrap">
                <div style="width:100%; margin:0 auto;">
                    <span style="font-size:26px; font-weight:600; color:#1976d2 !important;">관리중인 프로젝트</span>
                    <div style="width:100%; margin-bottom:30px;"></div>

                    <v-data-table :headers="headers" :items="items" class="elevation-1">
                        <template v-slot:item.thumbnailUrl="{ item }">
                            <v-img
                                max-width="200"
                                :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                :lazy-src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                aspect-ratio="1.7"
                                @click="move(`/lecture/detail/${item.lectureId}`)"></v-img>
                        </template>
                        <template v-slot:item.btn="{item}">
                            <v-btn depressed="depressed" small="small" text color="success">관리하기</v-btn>
                            <!-- <div style="margin:5px"></div> -->
                            <v-btn depressed="depressed" small="small" text color="error">삭제하기</v-btn>
                        </template>
                    </v-data-table>
                </div>

            </v-layout>
        </v-container>
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
                headers: [
                    {
                        text: '이미지',
                        value: 'thumbnailUrl',
                        align: 'center',
                        width: '200'
                    }, {
                        text: '강좌명',
                        value: 'title',
                        align: 'start'
                    }, {
                        text: '강의 수',
                        value: 'lectureCount',
                        align: 'center',
                        width: '100'
                    }, {
                        text: '조회 수',
                        value: 'viewCount',
                        align: 'center',
                        width: '100'
                    }, {
                        text: '관리하기',
                        value: 'btn',
                        align: 'center',
                        width: '100'
                    }
                ],
                items: [],
                level: this.$route.query.level,
                page: 1,
                itemsperpage: 1000,
                loading: false
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
            }
        }
    }
</script>

<style></style>