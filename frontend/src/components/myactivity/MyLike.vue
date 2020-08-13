<template>
    <div style="margin:20px 50px; padding:10px 60px">

        <v-layout row="row" wrap="wrap">
            <div style="width:100%; margin:0 10px;">
                <span style="font-size:20px; font-weight:600; color:#1976d2 !important">내가 좋아요한 강의</span>
                <span style="float:right; font-size:14px; color:#585858; cursor:pointer" @click="goToLectureList">더보기</span>
            </div>
            <v-flex
                v-for="(item,i) in letureItems"
                :key="`4${i}`"
                xs12="xs12"
                sm6="sm6"
                md4="md4"
                lg3="lg3"
                xl2="xl2">
                <v-card
                    tile="tile"
                    flat="flat"
                    style="margin-left:10px; margin-top:20px;cursor:pointer;">
                    <v-img
                        :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                        :lazy-src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                        aspect-ratio="1.7"
                        @click="move(`/lecture/detail/${item.lectureId}`)"></v-img>

                    <v-list>
                        <div @click="move(`/lecture/detail/${item.lectureId}`)">
                            <v-list-item-title>
                                <h3>{{item.title}}</h3>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                조회수
                                {{item.viewCount | convertView}}&nbsp;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}
                            </v-list-item-subtitle>

                            <v-list-item-subtitle>
                                총
                                {{item.lectureCount}}강의
                            </v-list-item-subtitle>
                        </div>
                        <v-list-item-subtitle>
                            #
                            <v-chip
                                :color="`primary lighten-4`"
                                class="ma-1"
                                v-for="(tag,index) in item.tagName ? item.tagName.split(',') : ''"
                                :key="i+'_'+index+'_tag'"
                                small="small"
                                label="label"
                                @click="move(`/search?keyword=${tag}`)">
                                <span style="color:black">
                                    {{tag}}
                                </span>
                            </v-chip>
                        </v-list-item-subtitle>
                        <v-avatar class="profile" size="20">
                            <v-img :src="'http://i3a101.p.ssafy.io/images/' + item.profile"></v-img>
                        </v-avatar>
                        <span style="margin-left:5px;font-size:12px">{{item.nickname}}</span>

                    </v-list>
                </v-card>

            </v-flex>
            <div style="width:100%; margin:20px 10px 0 10px; padding-top:20px; border-top:1px #c8c8c8 solid;">
                <span style="font-size:20px; font-weight:600; color:#1976d2 !important">내가 좋아요한 동영상</span>
                <span style="float:right; font-size:14px; color:#585858; cursor:pointer" @click="goToVideoList">더보기</span>
            </div>
            <v-flex
                v-for="(item,i) in videoItems"
                :key="`4${i}`"
                xs12="xs12"
                sm6="sm6"
                md4="md4"
                lg3="lg3"
                xl2="xl2">
                <v-card
                    tile="tile"
                    flat="flat"
                    style="margin-left:10px; margin-top:20px;cursor:pointer;">
                    <v-img
                        :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                        :lazy-src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                        aspect-ratio="1.7"
                        @click="move(`/lecture/detail/${item.lectureId}`)"></v-img>

                    <v-list>
                        <div @click="move(`/lecture/detail/${item.lectureId}`)">
                            <v-list-item-title>
                                <h3>{{item.title}}</h3>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                조회수
                                {{item.viewCount | convertView}}&nbsp;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}
                            </v-list-item-subtitle>

                            <v-list-item-subtitle>
                                총
                                {{item.lectureCount}}강의
                            </v-list-item-subtitle>
                        </div>
                        <!-- <v-list-item-subtitle>
                            #
                            <v-chip
                                :color="`primary lighten-4`"
                                class="ma-1"
                                v-for="(tag,index) in item.tagName ? item.tagName.split(',') : ''"
                                :key="i+'_'+index+'_tag'"
                                small="small"
                                label="label"
                                @click="move(`/search?keyword=${tag}`)">
                                <span style="color:black">
                                    {{tag}}
                                </span>
                            </v-chip>
                        </v-list-item-subtitle> -->
                        <v-avatar class="profile" size="20">
                            <v-img :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"></v-img>
                        </v-avatar>
                        <span style="margin-left:5px;font-size:12px">{{item.nickname}}</span>

                    </v-list>
                </v-card>

            </v-flex>
        </v-layout>
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
            return {letureItems: [], videoItems: [], level: this.$route.query.level, page: 1, loading: false}
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
        created(){
            if(!store.state.token) {
                this.$router.push('/')
            }
        },
        mounted() {
            http
                .axios
                .get(`/api/v1/myLikeLecture?page=1&itemsperpage=4`)
                .then(({data}) => {
                    this.letureItems = data.result;
                })
            http
                .axios
                .get(`/api/v1/myLikeVideo?page=1&itemsperpage=4`)
                .then(({data}) => {
                    this.videoItems = data.result;
                })
        },
        beforeDestroy() {
        },
        methods: {
            move(url) {
                this
                    .$router
                    .push(url)
            },
            goToLectureList(){
                this.move('/mylike/lecture')
            },
            goToVideoList(){
                this.move('/mylike/video')
            }
        }
    }
</script>

<style></style>