<template>
    <div id="app">

        <!-- <div> <video id="livestation-player" class="video-js vjs-big-play-button
        vjs-big-play-centered" controls preload="auto" data-setup='{}'> <source
        src="./example2.mp4" type="video/mp4"></source> </video> </div> <div> <video
        id="livestation-player" class="video-js vjs-default-skin" controls
        preload="auto" data-setup='{}'> <source src="./example3.mp4"
        type="video/mp4"></source> </video> </div> -->

        <v-container fluid="fluid" grid-list-xl="grid-list-xl" class="devit_status">
            <v-layout row="row" justify-center="justify-center" style="margin:auto 0">
                <v-flex class="status_wrap" xs2="xs2" style="min-width:200px">
                    <v-layout row="row" wrap="wrap">
                        <div class="status_img">
                            <v-img src="@/assets/images/members.png" max-width="30px"></v-img>
                        </div>
                        <v-flex>
                            <v-layout column="column" wrap="wrap">
                                <div class="status_title">총 회원</div>
                                <div class="status_content">{{this.totalUsers}}</div>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <span class="bar" aria-hidden="true">|</span>
                <v-flex class="status_wrap" xs2="xs2" style="min-width:200px">
                    <v-layout row="row" wrap="wrap">
                        <div class="status_img">
                            <v-img src="@/assets/images/elearning.png" max-width="30px"></v-img>
                        </div>
                        <v-flex>
                            <v-layout column="column" wrap="wrap">
                                <div class="status_title">강의에 기여한 회원</div>
                                <div class="status_content">{{this.totalDeviters}}</div>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <span class="bar" aria-hidden="true">|</span>
                <v-flex class="status_wrap" xs2="xs2" style="min-width:200px">
                    <v-layout row="row" wrap="wrap">
                        <div class="status_img">
                            <v-img src="@/assets/images/lecture.png" max-width="30px"></v-img>
                        </div>
                        <v-flex>
                            <v-layout column="column" wrap="wrap">
                                <div class="status_title">누적 강의 수</div>
                                <div class="status_content">{{this.totalLectures}}</div>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-container>
        <v-container fluid="fluid" class="devit_info">
            <v-layout justify-center="justify-center">
                <div style="width:30px; vertical-align:middle; margin:auto 2px">
                    <v-img src="@/assets/images/logo.png"></v-img>
                </div>
                <div class="info_title">데빗이 뭔가요?</div>
            </v-layout>
            <v-container>
                <v-layout row="row" wrap="wrap" justify-center="justify-center">
                    <v-flex class="info_wrap" xs12="xs12" sm6="sm6" md4="md4" lg3="lg3" xl2="xl2">
                        <v-layout column="column">
                            <v-flex class="info_img">
                                <v-img src="@/assets/images/participation.png" max-width="80px"></v-img>
                            </v-flex>
                            <v-flex class="info_sub_title">참여형 강의 플랫폼</v-flex>
                            <v-flex class="info_content">
                                누구나 만들고 수정할 수 있는 참여형 강의 플랫폼입니다. 다른 사람의 강의에 부족한 점이 있거나 잘못된 정보가 있다면 자유롭게 추가/수정하여
                                강의의 품질을 높여보세요.
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex class="info_wrap" xs12="xs12" sm6="sm6" md4="md4" lg3="lg3" xl2="xl2">
                        <v-layout column="column">
                            <v-flex class="info_img">
                                <v-img src="@/assets/images/choose.png" max-width="80px"></v-img>
                            </v-flex>
                            <v-flex class="info_sub_title">원하는 강의만 쏙쏙 골라 듣기</v-flex>
                            <v-flex class="info_content">
                                아직도 강의를 처음부터 순서대로 들으시나요? 필요한 챕터만 골라서 듣고 시간을 절약해보세요.
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex class="info_wrap" xs12="xs12" sm6="sm6" md4="md4" lg3="lg3" xl2="xl2">
                        <v-layout column="column">
                            <v-flex class="info_img">
                                <v-img src="@/assets/images/communication.png" max-width="80px"></v-img>
                            </v-flex>
                            <v-flex class="info_sub_title">개발자들의 자유로운 소통 공간</v-flex>
                            <v-flex class="info_content">
                                강의를 수강하며 이해가 되지 않는다면, 혹은 홀로 개발을 하다 막힌다면 바로 질문게시판에 질문을 올려보세요. 강의를 올린 사람 뿐만 아니라
                                다양한 분야의 베테랑 개발자님들께 답변을 받을 수 있답니다.
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-container>
        <v-container fluid="fluid" class="devit_rank">
            <v-layout row="row" wrap="wrap">
                <v-flex
                    class="rank_wrap_30"
                    xs12="xs12"
                    sm6="sm6"
                    md4="md4"
                    lg3="lg3"
                    xl2="xl2">
                    <div class="rank_title">활동 랭킹</div>
                </v-flex>
                <v-flex
                    class="rank_wrap_40"
                    xs12="xs12"
                    sm6="sm6"
                    md4="md4"
                    lg3="lg3"
                    xl2="xl2">
                    <v-layout justify-center="justify-center" row="row" wrap="wrap">
                        <v-flex class="rank_top_wrap">
                            <v-layout column="column">
                                <v-flex class="rank_img">
                                    <v-img src="@/assets/images/silver.png" max-width="60px"></v-img>
                                </v-flex>
                                <v-flex class="rank_top_ranker">
                                    <span class="rank">2위</span>
                                    <span class="ranker">{{this.items[1].nickname}}</span>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex class="rank_top_wrap">
                            <v-layout column="column">
                                <v-flex class="rank_img">
                                    <v-img src="@/assets/images/gold.png" max-width="60px"></v-img>
                                </v-flex>
                                <v-flex class="rank_top_ranker">
                                    <span class="rank">1위</span>
                                    <span class="ranker">{{this.items[0].nickname}}</span>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex class="rank_top_wrap">
                            <v-layout column="column">
                                <v-flex class="rank_img">
                                    <v-img src="@/assets/images/bronze.png" max-width="60px"></v-img>
                                </v-flex>
                                <v-flex class="rank_top_ranker">
                                    <span class="rank">3위</span>
                                    <span class="ranker">{{this.items[2].nickname}}</span>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex
                    class="rank_wrap_30"
                    xs12="xs12"
                    sm6="sm6"
                    md4="md4"
                    lg3="lg3"
                    xl2="xl2">
                    <v-layout column="column" wrap="wrap">
                        <v-flex>
                            <span class="rank">4위</span>
                            <span class="ranker">{{this.items[3].nickname}}</span>
                        </v-flex>
                        <v-flex>
                            <span class="rank">5위</span>
                            <span class="ranker">{{this.items[4].nickname}}</span>
                        </v-flex>
                        <v-flex>
                            <span class="rank">6위</span>
                            <span class="ranker">{{this.items[5].nickname}}</span>
                        </v-flex>
                        <v-flex>
                            <span class="rank">7위</span>
                            <span class="ranker">{{this.items[6].nickname}}</span>
                        </v-flex>
                        <v-flex>
                            <span class="rank">8위</span>
                            <span class="ranker">{{this.items[7].nickname}}</span>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-container>

    </div>
</template>
<script>
    import http from "@/util/http_common.js";

    export default {
        data() {
            return {
                totalUsers: "",
                totalDeviters: "",
                totalLectures: "",
                items: [
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                    {
                        "nickname": ""
                    }, 
                ]
            }
        },
        created() {
            http
                .axios
                .get(`/api/v1/commons/main/status`, {})
                .then(({data}) => {
                    this.totalUsers = data.result.totalUsers;
                    this.totalDeviters = data.result.totalDeviters;
                    this.totalLectures = data.result.totalLectures;
                })
                .catch((error) => {
                    console.dir(error)
                });
                http
                .axios
                .get(`/api/v1/commons/main/rank`, {})
                .then(({data}) => {
                    this.items = data.result;
                })
                .catch((error) => {
                    console.dir(error)
                });
            }
    }
</script>

<style scoped="scoped">

    .devit_status {
        /*background-color:#f2f2f2;*/
        min-height: 100px;
        /*vertical-align:middle;*/
        margin: auto;
    }
    .devit_status .status_wrap {
        margin: auto 0;
    }
    .devit_status .status_wrap .status_img {
        margin: auto 20px;
    }
    .devit_status .status_wrap .status_title {
        font-size: 12px;
        color: #4294BF;
        font-weight: bolder;
    }
    .devit_status .status_wrap .status_content {
        font-size: 18px;
        font-weight: bold;
    }
    .devit_status .bar {
        display: inline-block;
        width: 1px;
        height: 60px;
        text-indent: -999em;
        background: #e4e4e5;
        vertical-align: middle;
        margin: 0 2px;
    }

    .devit_info {
        /*background-color:#f2f2f2;*/
        margin: 20px 0;
    }

    .devit_info .info_title {
        font-size: 30px;
        font-weight: bolder;
        color: #274259;
    }

    .devit_info .info_wrap {
        width: 33%;
        max-width: 300px;
        margin: 2px 20px;
    }

    .devit_info .info_wrap .info_img {
        height: 100px;
        margin: auto;
    }
    .devit_info .info_wrap .info_sub_title {
        font-weight: bold;
        font-size: 16px;
        margin: 10px auto 20px;
    }
    .devit_info .info_wrap .info_content {
        margin: auto;
        font-size: 14px;
    }

    .devit_rank {
        /*background-color:#f2f2f2;*/
        margin: 20px 0;
    }

    .devit_rank .rank_title {
        color: #274259;
        font-size: 30px;
        font-weight: bolder;
        text-align: right;
        margin: auto 0;
    }
    .devit_rank .rank_wrap_40 {
        width: 40%;
        padding: 0 10px;
        margin: auto;
    }
    .devit_rank .rank_wrap_30 {
        width: 30%;
        padding: 0 30px;
        margin: auto;
    }

    .devit_rank .rank_top_wrap {
        width: 33%;
    }

    .devit_rank .rank_top_ranker {
        text-align: center;
    }
    .devit_rank .rank_img {
        /*width:100%;*/
        margin: auto;
    }

    .devit_rank .rank {
        color: #4294BF;
        font-weight: bolder;
        font-size: 11px;
        margin: 0 3px;
    }

    .devit_rank .ranker {
        color: #274259;
        font-weight: bolder;
        font-size: 11px;
    }
</style>