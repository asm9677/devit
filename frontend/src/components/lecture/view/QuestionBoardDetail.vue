<template>
    <div>
        <v-layout wrap>            
            <v-list style dense>
                <v-list-item>
                    <v-list-item-content>
                    </v-list-item-content>
                    <v-list-item-action style="cursor:pointer;">
                        <span style="font-size:12px;">수정</span>                        
                    </v-list-item-action>
                    <v-list-item-action  style="margin-left:5px; cursor:pointer;">
                        <span style="font-size:12px;">삭제</span>
                    </v-list-item-action>
                </v-list-item>
                <v-list-item>
                    <v-list-item-content class="wrap-text">
                        파이썬에서 실수 계산을 오차없이 할 수 있는 방법이 있나요?
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-avatar size="30" style="margin-right:10px;">
                            <v-img 
                                :src="'https://picsum.photos/500/300?image=15'"
                            ></v-img>
                    </v-list-item-avatar >
                    <v-list-item-content>
                        <v-list-item-title>
                            미용쓰기
                        </v-list-item-title>
                        <v-list-item-subtitle>
                            2020.08.09. 15:59 조회 0
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
                            파이썬에서 실수 계산을 오차없이 할 수 있는 방법이 있나요?
                        </v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>                    
                    <v-divider />
                </v-list-item>
                <template v-for="i in 2">
                    <v-list-item  :key="`${i}_reply`" three-line="">
                        <v-list-item-avatar size="30" style="margin-right:10px;">
                            <v-img 
                                :src="'https://picsum.photos/500/300?image=15'"
                            ></v-img>
                        </v-list-item-avatar >
                        <v-list-item-content>
                            <v-list-item-title>
                                미용쓰기
                            </v-list-item-title>
                            <v-list-item-subtitle class="wrap-text">
                                좋은 방법이 있습니다 <br>
                                그것은 바로
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                2020-08-09 15:59 &middot; <span style="font-size:12px;">수정</span>  <span style="font-size:12px;">삭제</span>  
                            </v-list-item-subtitle>
                        </v-list-item-content>                    
                    </v-list-item>
                    <v-list-item :key="`${i}_divider`" >
                        <v-divider />
                    </v-list-item>
                </template>
                <v-list-item>
                    <v-textarea dense outlined auto-grow placeholder="댓글을 입력해보세요!" color="success" hide-details style="margin:10px 0px"></v-textarea>                          
                </v-list-item>
                <v-list-item>
                    <v-list-item-content /> 
                    <v-list-item-action>
                        <v-btn color="success" outlined>
                            댓글 입력
                        </v-btn>
                    </v-list-item-action>
                </v-list-item>
            </v-list>
        </v-layout>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.wrap-text {
  -webkit-line-clamp: unset !important;
  white-space: normal;  
}

.v-list {
  height: 550px;
  overflow-y: auto;
  padding-right:15px;
}

</style>