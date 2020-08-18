<template>
  <div style="margin:50px">
      
    <v-layout row wrap>
      <v-flex v-for="(item,i) in items" :key="`4${i}`" xs12 sm6 md4 lg3 xl2 >        
            <v-card tile flat style="margin-left:10px; margin-top:20px;cursor:pointer;">
                <v-img 
                    :src="'http://i3a101.p.ssafy.io/images/' + item.thumbnailUrl"
                    lazy-src="@/assets/images/empty.png"
                    contain
                    position="left top"
                    aspect-ratio="1.77"
                    @click="move(`/lecture/detail/${item.lectureId}`)"
                >
                    <template v-slot:placeholder>
                        <v-row
                        class="fill-height ma-0"
                        align="center"
                        justify="center"
                        >
                            <v-progress-circular indeterminate color="primary lighten-4"></v-progress-circular>
                        </v-row>
                    </template>
                </v-img>
                
                    
                <!-- <v-card-actions> -->
                                    <v-list>
                                            <div @click="move(`/lecture/detail/${item.lectureId}`)">
                                            <v-list-item-title style="font-size: 1.17em; font-weight: bold;">
                                                {{item.title}}
                                            </v-list-item-title>
                                            <v-list-item-subtitle>
                                                조회수 {{item.viewCount | convertView}}&nbsp;<v-icon size="16" :color="item.userLikeYn ? 'pink' : 'gray'">mdi-heart</v-icon>{{item.likeCount | convertLike}}
                                            </v-list-item-subtitle>
                                            
                                            <v-list-item-subtitle>
                                                총 {{item.lectureCount}}강의
                                            </v-list-item-subtitle>
                                            </div>
                                            <v-list-item-subtitle>
                                                #
                                                <v-chip                                            
                                                    :color="`primary lighten-4`"                                            
                                                    class="ma-1"
                                                    v-for="(tag,index) in item.tagName ? item.tagName.split(',') : ''"   
                                                    :key="i+'_'+index+'_tag'"
                                                    small
                                                    label
                                                    @click="move(`/search?keyword=${tag}`)"
                                                >  
                                                    <span style="color:black">
                                                        {{tag}}
                                                    </span>
                                                </v-chip>                                                                                                   
                                            </v-list-item-subtitle>
                                            <v-avatar
                                                class="profile"
                                                size=20
                                            >
                                                <v-img 
                                                    :src="'http://i3a101.p.ssafy.io/images/' + item.profile"
                                                ></v-img>
                                            </v-avatar>
                                            <span style="margin-left:5px;font-size:12px">{{item.nickname}}</span>

                                    </v-list>   
                <!-- </v-card-actions> -->                
            </v-card>            
      </v-flex>
    </v-layout>
  </div>
</template>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script>
import http from "@/util/http_common.js"

export default {
    data() {
        return {
            items: [

            ],
            level: this.$route.query.level,
            page: 1,
            offset: 0,
        }
    },
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
        },
    },
    created(){        
        this.$router.app.$store.commit('startLoading')
        http.axios.get(`/api/v1/lectures?page=${this.page}&type=${this.level}`).then(({data}) => {
            this.page++;
            this.items = data.result;
        }).finally(() => {
            this.$router.app.$store.commit('endLoading')
        })

        document.addEventListener('scroll', this.handleScroll);
    },
    beforeDestroy(){
        document.removeEventListener('scroll', this.handleScroll);
    },
    methods: {
        handleScroll(){
            if($(document).scrollTop() + $(document)[0].scrollingElement.clientHeight + 10 >= $(document).height()){
                if(this.offset >= $(document).scrollTop()) {
                    this.offset = $(document).scrollTop();
                    return;
                }
                this.offset = $(document).scrollTop();
                if(!this.$router.app.$store.state.loading){
                    this.$router.app.$store.commit('startLoading', true)
                    http.axios.get(`/api/v1/lectures?page=${this.page}&type=${this.level}`)
                        .then(({data}) => {
                            this.page++;
                            
                            for(let i in data.result)
                                this.items.push(data.result[i]);
                        }).finally(() => {
                            this.$router.app.$store.commit('endLoading', false)
                        })
                }
            }          
        },
        move(url){            
            this.$router.push(url)
        }
    }
}
</script>

<style>

</style>