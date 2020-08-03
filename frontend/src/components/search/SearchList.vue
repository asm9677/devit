<template>
  <div>
        <v-container>
            <div style="margin-top:10px;"></div>        
        <v-card tile flat>
                <v-layout wrap ref="main">
                    <v-flex xs12 sm12 md8 lg8 xl8 ref="left" style="margin-left:0px; padding:20px;">       
                        <v-list>
                            <h1 style="margin-left:10px;"><span class="primary--text">{{keyword}}</span> 검색 결과</h1>
                            <v-container fluid v-for="(item, i) in items" :key="i + '_item'">
                                <v-row style="cursor:pointer;" @click="move(`/lecture/detail/${item.lectureId}`)">
                                    <v-col :cols="4">
                                        <div style="margin: 0px auto;">
                                            <v-avatar
                                                class="profile"
                                                size="100%"
                                                tile>
                                                <v-img 
                                                    :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                                    :lazy-src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                                    aspect-ratio="1.7"
                                                    :ref="'img'+i"
                                                    min-height="100"
                                                    min-width="170"
                                                    style="border-radius:5px;"
                                                ></v-img>
                                            </v-avatar>
                                        </div>
                                    </v-col>
                                    <v-col :cols="7">
                                        <v-row>
                                        <v-card
                                            tile
                                            flat
                                            :ref="'content'+i"
                                        >
                                            <v-list-item-title>
                                                <h3>{{item.title}}</h3>
                                            </v-list-item-title>

                                            <v-list-item-subtitle>
                                                총 {{item.lectureCount}}강의 &middot; 조회수 {{item.viewCount | convertView}}&nbsp;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}
                                            </v-list-item-subtitle>
                                            <v-list-item-subtitle>
                                                #
                                                <v-chip                                            
                                                    :color="`primary lighten-4`"                                            
                                                    class="ma-1"
                                                    v-for="(tag,index) in item.tagName.split(',')"   
                                                    :key="i+'_'+index+'_tag'"
                                                    small
                                                    label
                                                    @click.stop="reSearch(tag)"
                                                >  
                                                    <span style="color:black">
                                                        {{tag}}
                                                    </span>
                                                </v-chip>                                                                                                   
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
                                                            :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                                                            
                                                        ></v-img>
                                                    </v-avatar>
                                                <span style="margin-left:5px;font-size:12px">{{item.nickname}}</span>
                                        </v-row>

                                    </v-col>
                                </v-row>
                                <v-divider :key="i + '_divider'"></v-divider>
                            </v-container>
                        </v-list>
                        
                    </v-flex>                    
                    <v-flex v-show="option" style="border-left:1px solid #e2e2e2" md4 lg4 xl4> 
                        <div style="position: sticky; top:130px;">
                            <v-container>
                                <span style="normal--text; margin-left:10px;"> 추천 키워드 </span>
                                <p/>
                                <div class="chips" v-for="(tag,i) in tags" :key="i+'_chips'" @click="reSearch(tag.tagName)">
                                    <span class="chips_title">
                                        {{tag.tagName}}
                                    </span>
                                    <span class="chips_number">
                                        {{tag.tagCount}}
                                    </span>
                                </div>
                                
                            </v-container>
                        </div>
                    </v-flex>
                </v-layout>
        </v-card>
      </v-container>
  </div>
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
    data() {
        return {            
            items:[],
            option: true,
            keyword: '',
            height: 20,
            loading: false,
            page: 1,
            tags: [],
        }
    },
    mounted() {
        window.addEventListener('resize', this.handleResize)
        this.option = this.$refs.main.clientWidth != this.$refs.left.clientWidth;
        if(this.$refs.img0){
                this.height = this.$refs.img0[0].$el.offsetHeight - this.$refs.content0[0].$el.offsetHeight-25
                if(this.height < 0)
                    this.height = 0;
            }
    },
    created(){
        this.keyword = this.$route.query.keyword;        

        this.search(this.keyword);
        document.addEventListener('scroll', this.handleScroll);
    },
    beforeDestroy(){
        document.removeEventListener('scroll', this.handleScroll);
        document.removeEventListener('resize', this.handleResize);
    },
    methods: {
        handleScroll(){
            if($(document).scrollTop() + $(document)[0].scrollingElement.clientHeight + 100 >= $(document).height()){
                if(!this.loading){
                    this.loading = true;
                    http.axios.get(`/api/v1/commons/${this.keyword}/${this.page}`)
                        .then(({data}) => {
                            this.page++;                            
                            for(let i in data.result)
                                this.items.push(data.result[i]);
                        }).finally(() => {
                            this.loading = false;
                        })
                }
            }          
        },
        handleResize() {
            this.option = this.$refs.main.clientWidth != this.$refs.left.clientWidth;
            if(this.$refs.img0){
                this.height = this.$refs.img0[0].$el.offsetHeight - this.$refs.content0[0].$el.offsetHeight-25
                if(this.height < 0)
                    this.height = 0;
            }
        },    
        goto(target){
            this.$vuetify.goTo(target, {
                duration: 300,
                offset: 100,
                easing: 'easeInOutCubic'
            })
        },        
        reSearch(keyword){
            history.pushState('', '', `/search?keyword=${keyword}`);
            this.keyword = keyword;
            this.search(keyword);
         
        },
        search(keyword){
            http.axios.get('/api/v1/lectures/tags').then(({data}) => {
                this.tags = data.result
            })        
            http.axios.get(`/api/v1/commons/${keyword}/1`).then(({data}) => {
                this.items = data.result                
                this.page++;
            })
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
    h1 {
        font-size: 24px;
        font-weight: 400;
        margin-bottom:20px;
    }
    div.content {
        font-size:16px;
        margin-top:20px;
    }

    div.chips{
        display: inline-block;
        background-color:#ededed; 
        border-radius: 5px;
        padding: 5px 0px;
        padding-left: 5px;
        margin:10px;
        font-size:12px;
        opacity: 0.6;
    }

    .chips_title{
        padding: 5px;
    }

    .chips_number{
        height:100%;
        width:100%;
        background-color:#cdcdcd;
        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
        padding: 5px;
    }

    div.chips:hover{
        opacity: 1.0;
        cursor:pointer
    }
</style>