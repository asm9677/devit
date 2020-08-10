<template>
  <div style="; margin-top:20px;">
        <v-card tile flat >
                <v-layout wrap>
                    <v-flex xs12 sm12 md8 lg8 xl8 style="margin-left:0px;">                         
                            <v-list style="padding:10px 0px;" dense>
                                <v-list-item>
                                    요청 목록
                                </v-list-item>  
                                <template v-for="(item,index) in items">
                                    <v-divider :key="`${index}_divider`"/>
                                    <v-list-item :key="`${index}_memberList`" link @click="curItem=item">
                                        <v-list-item-avatar size="30">
                                            <v-img :src="item.thumbnailUrl" :lazy-src="item.thumbnailUrl"></v-img>
                                        </v-list-item-avatar>
                                        <v-list-item-content>
                                            <v-list-item-title>
                                                <b>{{item.title}}</b>&nbsp;      
                                            </v-list-item-title>                                               
                                            <v-list-item-subtitle >
                                                <span> {{item.nickname}} {{item.requestDate | diffDate}}</span>
                                            </v-list-item-subtitle>                                                
                                        </v-list-item-content>
                                        
                                        <v-list-item-action>
                                            <v-icon color="green" v-if="item.state==1">
                                                mdi-check-circle-outline
                                            </v-icon>
                                            <v-icon color="red" v-else-if="item.state==2">
                                                mdi-close-circle-outline
                                            </v-icon>
                                        </v-list-item-action>
                                        <v-list-item-action>
                                            <v-icon v-if="item.type == 1">
                                                mdi-movie
                                            </v-icon>
                                            <v-icon v-else>
                                                mdi-wikipedia
                                            </v-icon>
                                        </v-list-item-action>
                                    </v-list-item>                                    
                                </template>
                            </v-list>    
                    </v-flex>                    
                    <v-flex v-show="option"  md4 lg4 xl4> 
                        <v-list style="position: sticky; top:60px;" dense>
                            <v-list-item >                                                                  
                                    <!-- 미리보기                                 -->
                            </v-list-item>
                            <div style="
                                    width:100%; 
                                    height:20em;    
                                    margin-left:50px;
                                    margin-top:30px;
                                " 
                                v-if="curItem"
                            >           
                                <div v-if="curItem.type==1">
                                    {{curItem.title}} - {{curItem.videoTitle}}<br>
                                    <v-avatar size=20>
                                        <v-img :src="curItem.thumbnailUrl" :lazy-src="curItem.thumbnailUrl"></v-img>
                                    </v-avatar> 
                                    <span> {{curItem.nickname}} {{curItem.requestDate | diffDate}} </span>
                                </div>
                                <div v-else style="overflow: hidden; height:500px;">                                    
                                    {{curItem.title}}<br>
                                    <v-avatar size=20>
                                        <v-img :src="curItem.thumbnailUrl" :lazy-src="curItem.thumbnailUrl"></v-img>
                                    </v-avatar> 
                                    <span> {{curItem.nickname}} {{curItem.requestDate | diffDate}} </span><p />
                                    <v-list-item-content>
                                    <span v-html="curItem.content"> </span>
                                        
                                    </v-list-item-content>
                                </div>
                                <div  style="
                                    width:100%;height:100%; display: -webkit-box;
                                    display: flex;
                                    -webkit-box-pack: center;
                                    justify-content: center;
                                    -webkit-box-align: center;
                                    align-items: center;
                                ">                              
                                        <v-img v-if="curItem.type==1" :src="curItem.videothumbnailUrl">
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
                            </div>
                        </v-list>
                    </v-flex>
                </v-layout>
        </v-card>
  </div>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
import http from "@/util/http_common.js"
import axios from "axios"
import store from "@/store/index.js"
export default {
    props: ['option'],
    watch: {
    },
    data() {
        return {         
            items: [],
            curItem: null
        }
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
    created() {
        for(let i = 1; i <= 20; i++)
            this.addItem(i);
    },
    methods: {
        addItem(i){
            this.items.push({
                "lectureId": i,
                "subIndexId": i,
                "type": i%3 ? 1 : 2 ,
                "title": '다 같이 배우는 파이썬',
                "thumbnailUrl": `https://picsum.photos/500/300?image=${i*3}`,
                "nickname": "미용쓰기",
                "videoTitle": 'IDE 설치 및 Hello World 출력',
                "videothumbnailUrl": `https://picsum.photos/500/300?image=${i*3 + 2}`,
                "content": `<h3 style="box-sizing: inherit; margin: 2rem 0px 0.6666em; padding: 0px; font-size: 1.5rem; color: #333333; line-height: 1.2;">눈앞에 다가온 AI 시대,<br style="box-sizing: inherit;">우리는 어떻게 준비해야 할까요?</h3><p style="font-size: medium; font-weight: 400;">1. 엔지니어링 리터러시: 최소한의 알고리즘(파이썬)은 반드시 알아야 한다.&nbsp;<br>2. 데이터 활용능력: 눈앞에 데이터가 있으면, 어떤 모델을 사용해서 어떤 결과를 낼 수 있는지 끊임없이 생각하는 능력이 중요하다.&nbsp;<br>3. 자동화 도전 습관: 내가 하는 일에서 어떤 작업을 자동화할 수 있는지 시도해보는 습관 기르기.&nbsp;&nbsp;</p><p style="font-size: medium; font-weight: 400;">문과 전공자도 엑셀 배우듯 파이썬 배우길 추천합니다.&nbsp;<br>앞으로 파이썬은 모든 일의 필요조건이 될 거니까요.&nbsp;</p><p style="font-size: medium; font-weight: 400;">“어떤 데이터를 모아서 어떻게 축적하고, AI를 활용할지 생각하는 능력이야말로 사람이 할 일이다.”<br><br><span style="color: #3598db;"><a href="https://news.sbs.co.kr/news/endPage.do?news_id=N1005538602%20출처 : SBS 뉴스  원본 링크 : https://news.sbs.co.kr/news/endPage.do?news_id=N1005538602&amp;plink=COPYPASTE&amp;cooper=SBSNEWSEND" target="_blank" rel="noopener noreferrer"><span style="text-decoration: underline;"><em>(출처 : AI 모른 척하다간 역사상 가장 큰 소외 경험하게 될 것)</em></span></a></span></p><h3 style="box-sizing: inherit; margin: 2rem 0px 0.6666em; padding: 0px; font-size: 1.5rem; color: #333333; line-height: 1.2;">눈앞에 다가온 AI 시대,<br style="box-sizing: inherit;">우리는 어떻게 준비해야 할까요?</h3><p style="font-size: medium; font-weight: 400;">1. 엔지니어링 리터러시: 최소한의 알고리즘(파이썬)은 반드시 알아야 한다.&nbsp;<br>2. 데이터 활용능력: 눈앞에 데이터가 있으면, 어떤 모델을 사용해서 어떤 결과를 낼 수 있는지 끊임없이 생각하는 능력이 중요하다.&nbsp;<br>3. 자동화 도전 습관: 내가 하는 일에서 어떤 작업을 자동화할 수 있는지 시도해보는 습관 기르기.&nbsp;&nbsp;</p><p style="font-size: medium; font-weight: 400;">문과 전공자도 엑셀 배우듯 파이썬 배우길 추천합니다.&nbsp;<br>앞으로 파이썬은 모든 일의 필요조건이 될 거니까요.&nbsp;</p><p style="font-size: medium; font-weight: 400;">“어떤 데이터를 모아서 어떻게 축적하고, AI를 활용할지 생각하는 능력이야말로 사람이 할 일이다.”<br><br><span style="color: #3598db;"><a href="https://news.sbs.co.kr/news/endPage.do?news_id=N1005538602%20출처 : SBS 뉴스  원본 링크 : https://news.sbs.co.kr/news/endPage.do?news_id=N1005538602&amp;plink=COPYPASTE&amp;cooper=SBSNEWSEND" target="_blank" rel="noopener noreferrer"><span style="text-decoration: underline;"><em>(출처 : AI 모른 척하다간 역사상 가장 큰 소외 경험하게 될 것)</em></span></a></span></p><h3 style="box-sizing: inherit; margin: 2rem 0px 0.6666em; padding: 0px; font-size: 1.5rem; color: #333333; line-height: 1.2;">눈앞에 다가온 AI 시대,<br style="box-sizing: inherit;">우리는 어떻게 준비해야 할까요?</h3><p style="font-size: medium; font-weight: 400;">1. 엔지니어링 리터러시: 최소한의 알고리즘(파이썬)은 반드시 알아야 한다.&nbsp;<br>2. 데이터 활용능력: 눈앞에 데이터가 있으면, 어떤 모델을 사용해서 어떤 결과를 낼 수 있는지 끊임없이 생각하는 능력이 중요하다.&nbsp;<br>3. 자동화 도전 습관: 내가 하는 일에서 어떤 작업을 자동화할 수 있는지 시도해보는 습관 기르기.&nbsp;&nbsp;</p><p style="font-size: medium; font-weight: 400;">문과 전공자도 엑셀 배우듯 파이썬 배우길 추천합니다.&nbsp;<br>앞으로 파이썬은 모든 일의 필요조건이 될 거니까요.&nbsp;</p><p style="font-size: medium; font-weight: 400;">“어떤 데이터를 모아서 어떻게 축적하고, AI를 활용할지 생각하는 능력이야말로 사람이 할 일이다.”<br><br><span style="color: #3598db;"><a href="https://news.sbs.co.kr/news/endPage.do?news_id=N1005538602%20출처 : SBS 뉴스  원본 링크 : https://news.sbs.co.kr/news/endPage.do?news_id=N1005538602&amp;plink=COPYPASTE&amp;cooper=SBSNEWSEND" target="_blank" rel="noopener noreferrer"><span style="text-decoration: underline;"><em>(출처 : AI 모른 척하다간 역사상 가장 큰 소외 경험하게 될 것)</em></span></a></span></p>`,
                "requestDate": '2020-08-06',
                "state": i%3 + 1
            })
        },
    }
}
</script>

<style scoped>
    * {
        font-weight: 400;
    }
    h1 {
        font-size: 26px;
        font-weight: 500;
        margin-bottom:30px;
    }
    .v-text-field{        
      font-size:16px;
      width: 120px;
      height:40px;
      padding:0px auto;
    }

    b{
        font-size:13px;
        
    }
    span{
        font-size:11px;
    }
</style>