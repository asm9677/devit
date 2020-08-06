<template>
    <v-list ref="another" class="LecturePlayer" style="width:100%; overflow-y:auto; font-size:14px" :dark="darkOption" :style="{'height': anotherHeight+'px'}">
        <div fluid v-for="(item, i) in items" :key="i + '_item'" style="width:100%">
            <v-row style="cursor:pointer;" >
                <v-col :cols="4">
                    <div style="margin: 0px auto;">
                        <v-avatar
                            class="profile"
                            size="100%"
                            tile
                        >                        
                            <v-img 
                                :src="'https://picsum.photos/500/300?image=20'"
                                :lazy-src="'https://picsum.photos/500/300?image=20'"
                                aspect-ratio="1.7"
                                :ref="'img'+i"
                                
                            ></v-img>
                        </v-avatar>
                    </div>
                </v-col>
                <v-col :cols="8">
                    <v-row>
                        <v-card
                            tile
                            flat
                            :ref="'content'+i"
                        >
                            <v-list-item-title>
                                {{item.title}}
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                조회수 {{item.viewCount | convertView}}&middot;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}
                            </v-list-item-subtitle>                            
                        </v-card>
                    </v-row>
                    <v-card :height="height" tile flat >
                    </v-card>
                    <v-row>
                        <v-avatar
                            class="profile"
                            size=20
                        >
                            <v-img 
                                :src="'https://picsum.photos/500/300?image=5'"
                            ></v-img>
                        </v-avatar>
                        <span class="nickname" style="margin-left:5px;font-size:12px">{{item.nickname}}</span>
                    </v-row>
                </v-col>
            </v-row>
        </div>
        <div style="height:50px" />
    </v-list>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
    import http from "@/util/http_common.js"
    export default {
        filters: {
            convertView(num) {
                if(num < 1000){
                    return num + '회'
                }

                if(num >= 100000000){
                    num /= 100000000;
                    return parseFloat(num).toFixed(2) + '억회'
                }
                if(num >= 10000){
                    num /= 10000;
                    return parseFloat(num).toFixed(0) + '만회'
                }
                if(num >= 1000){
                    num /= 1000;
                    return parseFloat(num).toFixed(1) + '천회'
                }
            },
            convertLike(num){
                return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
            }
        },
        props: ['darkOption'],
        data() {
            return {                
                anotherHeight: 768,

                items:[],
                keyword: '',
                height: 20,
                loading: false,
                page: 1,
                tags: [],
                scrollPos:0,
            }
        },
        watch: {
            darkOption(){
                if(this.darkOption){
                    $('.LecturePlayer .v-list-item__title').css('color', '#d4d4d4')
                    $('.LecturePlayer .nickname').css('color', '#d4d4d4')
                }else{            
                    $('.LecturePlayer .v-list-item__title').css('color', '#000000DE')
                    $('.LecturePlayer .nickname').css('color', '#000000DE')
                }                
            },
        },
        mounted() {
            if(this.darkOption){
                $('.LecturePlayer .v-list-item__title').css('color', '#d4d4d4')
                $('.LecturePlayer .nickname').css('color', '#d4d4d4')
            }else{            
                $('.LecturePlayer .v-list-item__title').css('color', '#000000DE')
                $('.LecturePlayer .nickname').css('color', '#000000DE')
            }
            if(this.$refs.img0){
                this.height = this.$refs.img0[0].$el.offsetHeight - this.$refs.content0[0].$el.offsetHeight-25
                if(this.height < 0)
                    this.height = 0;
            }
            this.handleResize2()
            window.addEventListener('resize', this.handleResize2);
        },
        created(){            
            for(let i = 0; i < 20; i++)
                this.addItem(i);
        },
        beforeDestroy(){
            window.removeEventListener('resize', this.handleResize2);
        },
        methods: {    
            handleResize2() {
                this.anotherHeight = $('body').prop("clientHeight")-this.$refs.another.$el.offsetTop
            },   
            move(url){
                this.$router.push(url).catch(()=>{location.reload(true);});
            },
            addItem(i){
                this.items.push({
                    "lectureId": i,
                    "title": '다 같이 배우는 파이썬',
                    "thumbnailUrl": `https://picsum.photos/500/300?image=${i*5}`,
                    "nickname": "미용쓰기",
                    "lectureCount": 20,
                    "viewCount": 9900000,
                    "likeCount": 999,
                    "tagName": 'python,프로그래밍 언어,GUI',
                    "userLikeYn": i%3 == 0
                })
            }

        }
    }
</script>

<style scoped>
* {
    font-weight: 400;
}
</style>