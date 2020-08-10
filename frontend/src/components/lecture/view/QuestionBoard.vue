<template>
    <v-layout wrap ref="main">
        <v-spacer></v-spacer>
        <v-btn v-if="true" light color="success" outlined>
            질문하기
        </v-btn>
        <v-list v-else style="width:100%;">
            <v-list-item>
                <v-text-field dense outlined placeholder="제목을 입력하세요." color="success" hide-details style="margin-bottom:10px"></v-text-field>                
            </v-list-item>
            <v-list-item>                
                <v-textarea dense outlined auto-grow placeholder="" color="success" hide-details style="margin-bottom:4px"></v-textarea>                                
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                </v-list-item-content>
                <v-list-item-action style="margin-top:0px;margin-bottom:0px;">
                    <v-btn light color="success" outlined>
                        질문
                    </v-btn>                    
                </v-list-item-action>
                <v-list-item-action style="margin-top:0px;margin-bottom:0px;">
                    <v-btn light color="success" outlined>
                        취소
                    </v-btn>                    
                </v-list-item-action>
            </v-list-item>
        </v-list>
        <div ref="question" :style="{'height': questionHeight+'px'}" style="width:100%; overflow-y:auto; margin-top:20px;">
            <v-list v-if="true" link>
                <div class="boardContent" style="width:100%; border:1px solid #4CAF50; border-radius:5px; margin:10px 0px;" v-for="i in 10" :key="i+'_div'" @click="$emit('showDetail', 1)">
                    <v-list-item style="width:100%;">
                        <v-list-item-content style="padding-bottom:0px;">                            
                            <div class="v-list-item__title">파이썬에서 실수 계산을 오차없이 할 수 있는 방법이 있나요?</div>
                            <div class="v-list-item__subtitle">파이썬에서 실수 계산을 오차없이 할 수 있는 방법이 있나요?</div>
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item>
                        <v-list-item-content style="padding-top:8px;">
                            <v-list-item-subtitle>                          
                                <v-avatar
                                    class="profile"
                                    size=15
                                >
                                    <v-img 
                                        :src="'https://picsum.photos/500/300?image=15'"
                                    ></v-img>
                                </v-avatar>
                                <span style="margin-left:3px; font-size:12px;">미용쓰기</span> &nbsp;&nbsp;
                                <span style="font-size:12px;"><v-icon small>mdi-comment-text</v-icon> 0</span>&nbsp;
                                <span style="font-size:12px;">조회 0</span>
                                 
                            </v-list-item-subtitle>
                        </v-list-item-content>
                    </v-list-item>
                </div>
            </v-list>
            <div
                v-else 
                style="
                    width:100%;height:100%; display: -webkit-box;
                    display: flex;
                    -webkit-box-pack: center;
                    justify-content: center;
                    -webkit-box-align: center;
                    align-items: center;
                    font-size:14px;
                    font-weight:400;
                "
            > 
                첫번째 질문을 남겨보세요!
            </div>  
        </div>           
    </v-layout>
</template>

<script>
export default {
    data(){
        return {
            questionHeight: 768,
            dialog:true,
        }
    },
    mounted(){
        this.questionHeight = $('body').prop("clientHeight") - this.$refs.question.offsetTop - 120;
    },
    methods:{
    }
}
</script>

<style scoped>
    * {
        /* font-weight: 500; */
        
    }

    .boardContent:hover {
        transform: translateY(-4px);
        box-shadow: 0 3px 8px 0 rgba(0,0,0,.08), 0 0 1px 0 rgba(0,0,0,.44);
        cursor:pointer;
    }

    /* .v-text-field__details{
        display: none !important;
        margin-bottom: 100px !important;
        height:0px !important;        
    } */
</style>