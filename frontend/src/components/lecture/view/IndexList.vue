<template>
    <v-layout wrap style="width:100%;">
        <v-list>
            <v-list-item>
                <v-list-item-content>
                    프로그래밍 시작하기 : 파이썬 입문
                    <v-list-item-subtitle>
                        {{order}}강 / {{totalCount}}강
                    </v-list-item-subtitle>  
                    <div style="margin-top:15px" />
                    <v-list-item-subtitle>    
                        <v-progress-linear :value="order/totalCount*100"></v-progress-linear>                                    
                    </v-list-item-subtitle>    
                </v-list-item-content>                                
            </v-list-item>                            
        </v-list>
        <v-list ref="contents" style="overflow-y:auto; font-size:14px; width:95%" :style="{'height':contentHeight+'px'}" :dark="darkOption">
            <v-list-item 
                v-for="(item,index) in chapter" :key="`${index}_contents`"
                link
                @click="move(`/lecture/player/index/${lectureId}?order=${index+1}`)"
            >
                <v-list-item-avatar>
                    <v-icon>
                        mdi-play-circle-outline
                    </v-icon>
                </v-list-item-avatar>
                <v-list-item-content>    
                    {{item.title}}
                </v-list-item-content>
                <v-list-item-action>
                    {{item.playTime}}
                </v-list-item-action>
            </v-list-item>
        </v-list>
    </v-layout>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
    import http from "@/util/http_common.js"
    export default {
        props: ['darkOption', 'lectureId', 'order'],
        data() {
            return {                
                contentHeight: 768,

                keyword: '',
                height: 20,
                loading: false,
                page: 1,
                tags: [],
                scrollPos:0,

                chapter: [],
                totalCount: 0,  
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
        created(){        
            this.getIndexList();
        },
        mounted() {
            if(this.darkOption){
                $('.LecturePlayer .v-list-item__title').css('color', '#d4d4d4')
                $('.LecturePlayer .nickname').css('color', '#d4d4d4')
            }else{            
                $('.LecturePlayer .v-list-item__title').css('color', '#000000DE')
                $('.LecturePlayer .nickname').css('color', '#000000DE')
            }
            this.handleResize2()
            window.addEventListener('resize', this.handleResize2);
        },
        beforeDestroy(){
            window.removeEventListener('resize', this.handleResize2);
        },
        methods: {    
            handleResize2() {
                this.contentHeight = $('body').prop("clientHeight")-this.$refs.contents.$el.offsetTop
            },   
            move(url){
                this.$router.push(url).catch(()=>{location.reload(true);});
            },
            getIndexList() {
                http.axios.get(`/api/v1/lectures/subs/${this.lectureId}`).then(({data}) => {
                    this.chapter = [];
                    this.totalCount = data.result.length;

                    for(let i in data.result){                    
                        this.chapter.push({
                            "commonId": data.result[i].commonId,
                            "order": parseInt(data.result[i].order),
                            "subId": data.result[i].subId,
                            "tags": data.result[i].tags ? data.result[i].tags.split(',') : [],
                            "title": data.result[i].title,
                            "videoYn": data.result[i].videoYn,
                            "wikiYn": data.result[i].wikiYn,
                            "playTime": data.result[i].playTime,                        
                        })
                    }
                })
            },
        }
    }
</script>

<style scoped>
* {
    font-weight: 400;
}
</style>