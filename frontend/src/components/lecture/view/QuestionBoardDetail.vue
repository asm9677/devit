<template>
    <div>
        <v-layout wrap style="width:100%;">            
            <v-list style="width:100%" dense>
                <v-list-item>
                    <v-list-item-content class="wrap-text">
                        {{item.boardTitle}}
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-avatar size="30" style="margin-right:10px;">
                            <v-img 
                                :src="'http://i3a101.p.ssafy.io/images/' + item.profile"
                            ></v-img>
                    </v-list-item-avatar >
                    <v-list-item-content>
                        <v-list-item-title>
                            {{item.nickName}}
                        </v-list-item-title>
                        <v-list-item-subtitle>
                            {{item.boardModified | moment('YYYY.MM.DD. HH:mm')}} 조회 {{item.boardCount}}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                    <v-list-item-action>
                        <v-icon>
                            mdi-dots-vertical
                        </v-icon>
                    </v-list-item-action>
                </v-list-item>
                <v-list-item>                    
                    <v-divider />
                </v-list-item>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-subtitle class="wrap-text">
                            {{item.boardContent}}
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item style="min-height:0px; margin-top:20px;">                    
                    <v-divider />
                </v-list-item>
                <v-list-item class="nomargin">
                    <v-list-item-subtitle class="nomargin">
                    답글 {{item.replyCount}}
                    </v-list-item-subtitle>
                </v-list-item>
                <template v-for="(replyItem,i) in reply">
                    <v-list-item  :key="`${i}_reply`" three-line="">
                        <v-list-item-avatar size="30" style="margin-right:10px;">
                            <v-img 
                                :src="'http://i3a101.p.ssafy.io/images/' + replyItem.profile"
                            ></v-img>
                        </v-list-item-avatar >
                        <v-list-item-content>
                            <v-list-item-title style="margin-bottom:4px;">
                                <span style="font-weight:700; font-family:'Malgun Gothic';">{{replyItem.userName}}</span>
                            </v-list-item-title>
                            <v-list-item-subtitle class="wrap-text" v-html="replyItem.replyContent" style="color:black; font-weight:400; font-family:'Malgun Gothic';">
                            </v-list-item-subtitle>
                            <v-list-item-subtitle style="color:#979797; font-size:12px; font-weight:400; font-family:'Malgun Gothic'; margin-top:7px;">
                                {{replyItem.replyModified | moment('YYYY.MM.DD. HH:mm')}} <template v-if="replyItem.isMine=='Y'">&middot; <span style="font-size:12px;">수정</span>  <span style="font-size:12px;">삭제</span>  </template>
                            </v-list-item-subtitle> 
                        </v-list-item-content>                    
                    </v-list-item>
                    <v-list-item :key="`${i}_divider`" style="min-height:0px;">
                        <v-divider />
                    </v-list-item>
                </template>
                <v-list-item>
                    <v-textarea dense outlined auto-grow placeholder="답글을 입력해보세요!" color="success" hide-details style="margin:10px 0px" v-model="replyContent"></v-textarea>                          
                </v-list-item>
                <v-list-item>
                    <v-list-item-content /> 
                    <v-list-item-action>
                        <v-btn color="success" outlined @click="writeReply">
                            댓글 입력
                        </v-btn>
                    </v-list-item-action>
                </v-list-item>
            </v-list>
        </v-layout>
    </div>
</template>

<script>
import http from "@/util/http_common.js"
export default {
    props: ['boardId'],
    data() {
        return {
            item: {},
            reply: {},
            replyContent: ''
        }
    },
    watch: {
        boardId() {
            this.initBoard();
        }
    },
    created(){
        this.initBoard()       
    },
    methods: {
        writeReply() {
            http.axios.post(`/api/v1/reply`,{
                "boardId": this.boardId,
                "parentReplyId": 0,
                "replyContent": this.replyContent,
            }).then(({data}) => {
                console.dir(data.result)
                this.reply = data.result
            }).finally(() => {
                this.replyContent = '';
                this.initBoard();
            })
        },
        initBoard(){
            http.axios.get(`/api/v1/board/${this.boardId}`).then(({data}) => {
                console.dir(data.result)
                this.item = data.result
            })
            http.axios.get(`/api/v1/reply/${this.boardId}`).then(({data}) => {
                console.dir(data.result)
                this.reply = data.result
            }) 
        }
    }
}
</script>

<style scoped>
.wrap-text {
  -webkit-line-clamp: unset !important;
  white-space: normal;  
}

.v-list {
  height: 650px;
  overflow-y: auto;
  padding-right:15px;
}

    .nomargin{
        margin:0px;

    }
    .nopadding{
        padding:0px;         
    }

</style>