<template>
    <div style="width:100%">
        <v-tabs v-model="tabs" icons-and-text grow hide-slider color="green">
            <v-tab>
                <span>비디오</span>
                <v-icon>mdi-movie</v-icon>                
            </v-tab>
            <v-tab @click="dialog=true">
                <span>위키</span>
                <v-icon>mdi-wikipedia</v-icon>
            </v-tab>
            <v-tab-item> 
                <v-list :dark="darkOption" style="height:2000px">               
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>
                                <v-text-field dense outlined placeholder="제목">
                                </v-text-field>
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>                    
                    <v-list-item>
                        <v-list-item-content>
                            <v-list-item-title>
                                <div style="width:100%; height:16em; border:2px dashed gray; cursor:pointer;" @click="clickVideo($event)" id="video">                            
                                    <input type="file"
                                        v-show="false"				                                          
                                        accept="video/mp4,video/x-m4v,video/*"
                                        @change="changeVideo"
                                        ref="file" 
                                        id="file"
                                    />
                                    <div v-show="!thumbnailUrl" style="
                                        width:100%;height:100%; display: -webkit-box;
                                        display: flex;
                                        -webkit-box-pack: center;
                                        justify-content: center;
                                        -webkit-box-align: center;
                                        align-items: center;
                                    ">                                            
                                        <v-icon style="font-size:40px;" color='success'> mdi-movie-open</v-icon>
                                        <div>                                        
                                            <div class="success--text" sytle="display:block;font-size:14px;">비디오 업로드</div>
                                            <div style="font-size:14px;">클릭해서 비디오를 선택하세요.</div>
                                        </div>
                                    </div>
                                    <v-img v-show="thumbnailUrl" :src="`http://i3a101.p.ssafy.io/images/${thumbnailUrl}`" min-height="100%" min-width="100%"/>                                        
                                </div>
                            </v-list-item-title> 
                        </v-list-item-content>
                    </v-list-item>
                    <v-list-item style="margin-top:15px;">
                        <v-list-item-content>
                        </v-list-item-content>
                        <v-list-item-action>
                            <v-btn outlined color="success">
                                요청하기
                            </v-btn>
                        </v-list-item-action>
                    </v-list-item>
                </v-list>
            </v-tab-item>       
            <v-tab-item>                
                    <v-list :dark="darkOption" style="height:2000px">
                    </v-list>
                    <v-dialog v-model="dialog" persistent max-width="960" overlay-opacity="0">
                        <v-card tile>
                            <v-list :dark="darkOption" >
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-list-item-title>
                                            위키
                                        </v-list-item-title>                                                            
                                    </v-list-item-content>
                                    <v-list-item-action style="cursor:pointer" @click="dialog=false">
                                        <v-icon>
                                            mdi-close
                                        </v-icon>
                                    </v-list-item-action>
                                </v-list-item>     
                                <v-list-item>
                                    <v-list-item-content>
                                        <v-textarea outlined auto-grow placeholder="텍스트 에디터 적용 예정입니다.">

                                        </v-textarea>
                                    </v-list-item-content>
                                </v-list-item>
                                <v-list-item style="margin-top:15px;">
                                    <v-list-item-content>
                                    </v-list-item-content>
                                    <v-list-item-action>
                                        <v-btn outlined color="success">
                                            요청하기
                                        </v-btn>
                                    </v-list-item-action>
                                    <v-list-item-action>
                                        <v-btn outlined color="success">
                                            미리보기
                                        </v-btn>
                                    </v-list-item-action>
                                </v-list-item>
                            </v-list>
                        </v-card>
                        
                    </v-dialog>
            </v-tab-item> 
        </v-tabs>
    </div>
</template>

<script>
export default {
    props:['darkOption'],
    data(){
        return {
            tabs: 0,
            thumbnailUrl: '',
            videoUrl: '',
            dialog: false,
        }
    },
    methods: {
        clickVideo(){
            $("#file").click();
        },
        changeVideo(e){	
            
        },
    }
}
</script>

<style>

</style>