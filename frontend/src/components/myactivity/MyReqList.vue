<template>
    <div style="margin:50px">
        <v-container justify-center="justify-center">
            <v-layout row="row" wrap="wrap">
                    <div style="width:100%; margin:0 auto;">
                        <span style="font-size:26px; font-weight:600; color:#1976d2 !important;">요청 목록</span>
                    </div>
                    <v-flex xs12 sm12 md8 lg8 xl8 style="margin-top:30px; margin-left:0px;">                         
                            <v-list style="padding:10px 0px;" dense>
                                <template v-for="(item,index) in items">
                                    <v-divider :key="`${index}_divider`"/>
                                    <v-list-item :key="`${index}_memberList`" link @click="curItem=item">
                                        <v-list-item-avatar size="30" rounded="false">
                                            <v-img :src="'http://i3a101.p.ssafy.io/images/' + item.lectureThumbnail"></v-img>
                                        </v-list-item-avatar>
                                        <v-list-item-content>
                                            <v-list-item-title>
                                                <b>{{item.subTitle}}</b>&nbsp;      
                                            </v-list-item-title>                                               
                                            <v-list-item-subtitle >
                                                <span> {{item.nickname}} {{item.created | diffDate}}</span>
                                            </v-list-item-subtitle>                                                
                                        </v-list-item-content>
                                        
                                        <v-list-item-action>
                                            <v-icon color="green" v-if="item.acceptYn=='Y'">
                                                mdi-check-circle-outline
                                            </v-icon>
                                            <v-icon color="red" v-else-if="item.acceptYn=='N'">
                                                mdi-close-circle-outline
                                            </v-icon>
                                        </v-list-item-action>
                                        <v-list-item-action>
                                            <v-icon v-if="item.reqType == 'video'">
                                                mdi-play-circle-outline
                                            </v-icon>
                                            <v-icon v-else-if="item.reqType == 'wiki'">
                                                mdi-script-text-outline
                                            </v-icon>
                                        </v-list-item-action>
                                    </v-list-item>                                    
                                </template>
                            </v-list>    
                    </v-flex>                    
                    <v-flex v-show="option"  md4 lg4 xl4> 
                        <v-list style="position: sticky; top:60px;" dense>
                            <div style="
                                    width:100%; 
                                    height:30em;    
                                    padding:30px 50px;
                                    padding-right:10px;
                                " 
                                v-if="curItem"
                            >           
                                <div v-if="curItem.reqType == 'video'">
                                    {{curItem.subTitle}} - {{curItem.hisTitle}}<br>
                                    <v-avatar size=20>
                                        <v-img :src="'http://i3a101.p.ssafy.io/images/' + curItem.profile"></v-img>
                                    </v-avatar> 
                                    <span> {{curItem.nickname}} {{curItem.created | diffDate}} </span>
                                </div>
                                <div v-else style="overflow: hidden; height:85%;">                                    
                                    {{curItem.subtitle}}<br>
                                    <v-avatar size=20>
                                        <v-img :src="'http://i3a101.p.ssafy.io/images/' + curItem.profile"></v-img>
                                    </v-avatar> 
                                    <span> {{curItem.nickname}} {{curItem.created | diffDate}} </span><p />
                                    <v-list-item-content>
                                    <span v-html="curItem.wikiContentHtml"> </span>                                        
                                    </v-list-item-content>
                                </div>
                                <div  style="
                                    width:100%; display: -webkit-box;
                                    display: flex;
                                    -webkit-box-pack: center;
                                    justify-content: center;
                                    -webkit-box-align: center;
                                    align-items: center;
                                    margin:30px 0px;
                                ">                              
                                
                                        <v-img v-if="curItem.reqType == 'video'" :src="'http://i3a101.p.ssafy.io/images/' + curItem.thumbnailUrl">
                                            <template v-slot:placeholder>
                                                <v-row
                                                class="fill-height ma-0"
                                                align="center"
                                                justify="center"
                                                >
                                                <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                                                </v-row>
                                            </template>
                                        </v-img>
                                </div>     
                                <!-- <v-btn color="primary" block depressed style="margin:10px 0px;" @click="requestProcess(curItem,'Y')">적용</v-btn>  
                                <v-btn color="primary" block depressed style="margin:10px 0px;" @click="requestProcess(curItem,'N')">취소</v-btn>   -->
                                <v-btn color="primary" block depressed style="margin:10px 0px;">상세보기</v-btn>  
                            </div>
                        </v-list>
                    </v-flex>
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
        //props: ['option'],
        data() {
            return {items: [], level: this.$route.query.level, page: 1, itemsperpage: 20, loading: false,
            curItem: null, option:true}
        },
    filters: {
        idOfEmail(val) {
            let id = val.substring(0,val.indexOf('@'))
            return '@' + (id ? id : val)
        },
        diffDate(val) {
            let diff = (new Date() - new Date(val)) / 1000;
            if(diff < 60)
                return '방금 전'
            diff /= 60;
            if(diff < 60)
                return parseInt(diff) + '분 전'

            diff /= 60;
            if(diff < 24)
                return parseInt(diff) + '시간 전'

            diff /= 24;
            if(diff < 7)
                return parseInt(diff) + '일 전'
            if (diff < 30)
                return parseInt(diff/7) + '주 전'
            if (diff < 365)
                return parseInt(diff/30) + '달 전'
            return parseInt(diff/365) + '년 전'
            return val
        }
    },
        created(){
            if(!store.state.token) {
                this.$router.push('/')
            }
        },
        mounted() {
            this.loading = true;
            http
                .axios
                .get(
                    `/api/v1/myReqList?page=${this.page}&itemsperpage=${this.itemsperpage}`
                )
                .then(({data}) => {
                    this.page++;
                    this.items = data.result;
                    console.log(this.items)
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
                                `/api/v1/myReqList?page=${this.page}&itemsperpage=${this.itemsperpage}`
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