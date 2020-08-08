<template>
<div class="LecturePlayer">
    <layout wrap ref="main">
        <v-navigation-drawer
            fixed
            right
            mini-variant
            dark
            permanent
            style="background-image: linear-gradient(-45deg, rgba(0, 160, 255, 0.86), rgb(0, 72, 162)); min-height:100%; z-index:101"
            ref="nav"
        >
          <v-list
            nav
            dense
          >
            <v-list-item-group v-model="menu">
                <v-tooltip left color="primary" open-on-hover open-on-focus open-on-click>
                    <template v-slot:activator="{ on, attrs }">
                        <v-list-item 
                            v-bind="attrs"
                            v-on="on" 
                            link 
                            @click="menu == 0 && list? closeList() : openList(); tabs=0"
                        >
                            <v-list-item-icon>
                                <v-icon>mdi-format-list-bulleted</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title></v-list-item-title>
                        </v-list-item>
                    </template>
                    <span>목차</span>
                </v-tooltip>

                <v-tooltip left color="primary" open-on-hover open-on-focus open-on-click>
                    <template v-slot:activator="{ on, attrs }">
                        <v-list-item 
                            v-bind="attrs"
                            v-on="on" 
                            link 
                            @click="menu == 1 ? closeList() : openList(); tabs=1"
                        >
                            <v-list-item-icon>
                                <v-icon>mdi-play-circle</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title></v-list-item-title>
                        </v-list-item>
                    </template>
                    <span>관련 영상</span>
                </v-tooltip>

                <v-tooltip left color="primary" open-on-hover open-on-focus open-on-click>
                    <template v-slot:activator="{ on, attrs }">
                        <v-list-item 
                            v-bind="attrs"
                            v-on="on" 
                            link 
                            @click="menu == 2 ? closeList() : openList(); tabs=2"
                        >
                            <v-list-item-icon>
                                <v-icon>mdi-comment-processing</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title></v-list-item-title>
                        </v-list-item>
                    </template>
                    <span>질문</span>
                </v-tooltip>

                <v-tooltip left color="primary" open-on-hover open-on-focus open-on-click>
                    <template v-slot:activator="{ on, attrs }">
                        <v-list-item 
                            v-bind="attrs"
                            v-on="on" 
                            link             
                            @click="menu == 3 ? closeList() : openList(); tabs=3"                
                        >
                            <v-list-item-icon>
                                <v-icon>mdi-upload</v-icon>
                            </v-list-item-icon>
                            <v-list-item-title></v-list-item-title>
                        </v-list-item>
                    </template>
                    <span>기여하기</span>
                </v-tooltip>  

                <v-tooltip left color="primary" open-on-hover open-on-focus open-on-click>
                    <template v-slot:activator="{ on, attrs }">
                        <v-list-item 
                            v-bind="attrs"
                            v-on="on" 
                            link         
                            @click="menu == 4 ? closeList() : openList(); tabs=4"                    
                        >
                            <v-list-item-icon>
                                <i class="fas fa-cog fa-lg" style="margin-left:1px; margin-top:4px"></i>
                            </v-list-item-icon>
                            <v-list-item-title></v-list-item-title>
                        </v-list-item>
                    </template>
                    <span>설정</span>
                </v-tooltip>  
            </v-list-item-group>
          </v-list>
        </v-navigation-drawer>
        <v-expand-x-transition>
            <v-card :width="listWidth" v-show="list" ref="list" tile flat style="z-index:100; position:fixed; width:400px; top:0px; height:100%;" :style="{'right':navWidth+'px'}">
                <v-tabs v-model="tabs" hide-slider height=0>
                    <v-tab-item>
                        <v-list :dark="darkOption">
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        목차
                                    </v-list-item-title>                                                            
                                </v-list-item-content>
                                <v-list-item-action @click="closeList">
                                    <v-icon>
                                        mdi-close
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content>
                                        프로그래밍 시작하기 : 파이썬 입문
                                        <v-list-item-subtitle>
                                            1강 / 20강 &middot; 총 836분
                                        </v-list-item-subtitle>  
                                        <div style="margin-top:15px" />
                                        <v-list-item-subtitle>    
                                            <v-progress-linear value="5"></v-progress-linear>                                    
                                        </v-list-item-subtitle>    
                                </v-list-item-content>                                
                            </v-list-item>                            
                        </v-list>
                        <v-list ref="contents" style="overflow-y:auto; font-size:14px" :style="{'height':contentHeight+'px'}" :dark="darkOption">
                            <v-list-item 
                                v-for="(item,index) in items" :key="`${index}_contents`"
                                link
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
                                    {{item.len}}분
                                </v-list-item-action>
                            </v-list-item>
                        </v-list>
                    </v-tab-item>
                    <v-tab-item>
                        <v-list :dark="darkOption">
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        관련 영상
                                    </v-list-item-title>                        
                                </v-list-item-content>
                                <v-list-item-action @click="closeList">
                                    <v-icon>
                                        mdi-close
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                            <v-list>
                                <v-list-item>                                
                                    <another-video :darkOption="darkOption"></another-video>
                                </v-list-item>
                            </v-list>
                            
                        </v-list>
                    </v-tab-item>
                    <v-tab-item>
                        <v-list>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        질문 게시판
                                    </v-list-item-title>                        
                                </v-list-item-content>
                                <v-list-item-action @click="closeList">
                                    <v-icon>
                                        mdi-close
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                        </v-list>
                        <v-list>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        <QuestionBoard :darkOption="darkOption"> </QuestionBoard>
                                    </v-list-item-title>                        
                                </v-list-item-content>
                            </v-list-item>
                            
                        </v-list>
                    </v-tab-item>
                    <v-tab-item>
                        <v-list :dark="darkOption">
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        기여하기
                                    </v-list-item-title>                        
                                </v-list-item-content>
                                <v-list-item-action @click="closeList">
                                    <v-icon>
                                        mdi-close
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                            <v-list>
                                <v-list-item>                                
                                    <Contribute :darkOption="darkOption"></Contribute>
                                </v-list-item>
                            </v-list>
                        </v-list>                        
                    </v-tab-item>
                    <v-tab-item>
                        <v-list :dark="darkOption" style="height:1500px;">
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        설정
                                    </v-list-item-title>                                                            
                                </v-list-item-content>
                                <v-list-item-action @click="closeList">
                                    <v-icon>
                                        mdi-close
                                    </v-icon>
                                </v-list-item-action>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-subtitle>
                                        테마
                                    </v-list-item-subtitle>
                                    <v-list-item-subtitle>     
                                        <v-radio-group v-model="darkOption">
                                            <v-layout>
                                            <v-flex xs6 sm6 md6 lg6 xl6 style="padding:7px;">
                                                <v-card style="cursor:pointer" :style="{'border': (!darkOption ? '2px solid #00D27A' : 'none')}" link @click="darkOption=false">
                                                    <v-img src="@/assets/images/light.png" ></v-img>                                                
                                                </v-card>                                                
                                                <v-row justify="space-around" style="margin-top:10px;">                                                    
                                                    <v-radio
                                                        label="Light"
                                                        color="#00D27A"
                                                        :value="false"
                                                    ></v-radio>                                                    
                                                </v-row>
                                            </v-flex>

                                            <v-flex xs6 sm6 md6 lg6 xl6 style="padding:7px;">
                                                <v-card style="cursor:pointer" :style="{'border': (darkOption ? '2px solid #00D27A' : 'none')}" link @click="darkOption=true">
                                                    <v-img src="@/assets/images/dark.png" ></v-img>
                                                </v-card>
                                                <v-row justify="space-around" style="margin-top:10px;">                                                    
                                                    <v-radio
                                                        label="Dark"
                                                        color="#00D27A"
                                                        :value="true"
                                                    ></v-radio>                                                    
                                                </v-row>

                                            </v-flex>
                                            </v-layout>
                                        </v-radio-group>

                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                            <v-divider />
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-subtitle>
                                        자동 재생
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-action>
                                    <v-switch v-model="autoPlay" color="#00D27A"></v-switch>
                                </v-list-item-action>
                            </v-list-item>   
                        </v-list>
                    </v-tab-item>
                    
                </v-tabs>
            </v-card>
        </v-expand-x-transition>
        
        

            <div style="position:absolute; top:0px; left:0px; width:100%; height:100%; z-index:10; ">            
                    <div style="border-right:1px solid #d2d2d2" :style="{'width' : videoWidth+'px', 'border-right': (darkOption ? '1px solid #1e1e1e' : '1px solid #d2d2d2')}">
                        <video
                            ref="video"
                            id="livestation-player"
                            class="video-js vjs-default-skin vjs-big-play-centered"
                            controls
                            preload="auto"
                            data-setup='{}'
                            style="width:100%;min-height:500px;"   
                            poster="http://i3a101.p.ssafy.io/images/2659d2f2-f9c6-4074-8921-fe6d1a70a122.jpg"
                        >
                            <source src="http://i3a101.p.ssafy.io/images/example2.mp4"></source>
                        </video>        
                        <v-layout style="position:fixed; z-index:99; bottom:0px; left:0px;" :style="{'width': videoWidth + 'px'}">               
                            <v-bottom-navigation
                                absolute 
                                :background-color="darkOption ? '#373838' : '#88CBF8'"                                
                            >
                                <v-btn @click="move(`/lecture/detail/${$route.params.id}`)">
                                    <v-icon :color="darkOption ? '#d4d4d4' : '#2981CF'">mdi-exit-to-app mdi-rotate-180</v-icon>
                                </v-btn>
                                <v-tabs :background-color="darkOption ? '#373838' : '#88CBF8'">                                
                                </v-tabs>
                                <v-btn>
                                    <v-icon :color="darkOption ? '#d4d4d4' : '#2981CF'">mdi-skip-previous</v-icon>
                                </v-btn>
                                <v-btn>
                                    <v-icon :color="darkOption ? '#d4d4d4' : '#2981CF'">mdi-skip-next</v-icon>
                                </v-btn>
                                
                            </v-bottom-navigation>
                        </v-layout>

                        <v-list :dark="darkOption">
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title>
                                        프로그래밍 시작하기 : 파이썬 입문
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        조회수 1,234,567 &middot; <v-icon small>mdi-heart</v-icon> 999
                                    </v-list-item-subtitle>
                                    <v-list-item-subtitle>
                                    #
                                        <v-chip                                            
                                            :color="`primary lighten-4`"                                            
                                            class="ma-1"
                                            v-for="(tag,index) in ['파이썬', '프로그래밍', '초급']"   
                                            :key="index+'_tag'"
                                            small
                                            label
                                            @click="move(`/search?keyword=${tag}`)"
                                        >  
                                            <span style="color:black">
                                                {{tag}}
                                            </span>
                                        </v-chip>                                                                                                   
                                    </v-list-item-subtitle>
                                    <v-list-item-subtitle>
                                        <v-avatar
                                                class="profile"
                                                size=20
                                            >
                                                <v-img 
                                                    :src="'https://picsum.photos/500/300?image=15'"
                                                ></v-img>
                                        </v-avatar>
                                        <span style="margin-left:5px;font-size:12px">미용쓰기</span>
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                                <v-list-item-action>
                                    <v-btn small class="mx-2" fab light color="white">
                                        <v-icon color='grey'>mdi-heart</v-icon>
                                    </v-btn>
                                </v-list-item-action>
                                <v-list-item-action>
                                    <v-btn small class="mx-2" fab color="white">
                                        <v-icon class="grey--text">mdi-share-variant</v-icon>
                                    </v-btn>
                                </v-list-item-action>
                            </v-list-item>
                            <v-divider />
                            <div style="margin-top:50px" />
                            <v-list-item>
                                <div class="wiki-paragraph">코드에서 중괄호는 모아놓고 보면 굉장히 지저분할 뿐더러, 당장 입력해야 하는 괄호가 2개나 되고,<a class="wiki-fn-content" href="#fn-3"><span class="target" id="rfn-3"></span>[3]</a> 괄호 하나라도 잘못 넣으면 블럭이 다 꼬여버리는 귀찮은 존재이지만(begin 과 end 혹은 end. 으로 구분해야 하는 파스칼에서 보면 중괄호만 해도 엄청나게 간결해 보이지만), 대신 블럭들을 이리저리 만질때는 매우 편리한 존재이다. 특히 C 코드를 종이에 출력해보면 중괄호 있고 없고의 가독성 차이가 의외로 크다. 파이썬에서는 이 중괄호를 들여쓰기로 대체하는데, 이게 블럭의 시작과 끝 지점을 찾는 부분에서 꽤 불편하다. 대신 괄호를 2개나 일일이 다 넣고, 잘못 넣지 않게 점검도 해줘야하는 수고가 없다는 엄청난 장점이 있다. <br>
                                이 들여쓰기 의무 규칙 때문에 소스 코드 들여쓰기에 탭(<a class="wiki-link-internal" href="/w/Tab" title="Tab">Tab</a>) 문자를 사용하지 <strong>않을 것을 매우 강력히 요구한다.</strong><a class="wiki-fn-content" href="#fn-4"><span class="target" id="rfn-4"></span>[4]</a>(들여쓰기에 최적화된 python을 쓰기싫으면 <del>지저분한</del> 세미콜론과 중괄호, int char등을 수시로 많이 사용하게 될 것이다.) <a class="wiki-link-internal" href="/w/%ED%83%AD" title="탭">탭</a> 문자는 사용자나 시스템의 설정에 따라 서로 다른 폭의 공백이 생긴다. 문제는 파이썬 인터프리터가 탭 문자 하나를 공백 1문자로 처리한다는 것이다. 탭 문자로 들여쓰기를 처리하면 자신과 다른 탭 설정을 가진 에디터에서 코드를 열어 볼 경우 코드 들여쓰기가 높은 확률로 망가져 버리고, 그걸 교정해보겠다고 들여쓰기에 공백 문자를 혼용하는 순간 해당 소스 코드는 사람이 고칠 수 없는 형태로(시각적으로는 블록이 맞지만 파이썬 인터프리터는 다른 블록으로 인식) 망가져 버린다. PEP-8에 명시된 공식 코딩 가이드에서는 <a class="wiki-link-internal" href="/w/%EC%8A%A4%ED%8E%98%EC%9D%B4%EC%8A%A4%20%EB%B0%94" title="스페이스 바">스페이스 바</a>로 <strong>공백 4문자</strong>를 넣기를 권장하고 있다. 물론 이렇게 스페이스 바를 네 번이나 치는 귀찮은 행위를 프로그래머들이 가만 놔둘 리가 없다. PyCharm, VSCode 등 Python 개발자들이 애용하는 최신 에디터는 Tab을 누르면 공백 4개로 자동 변환하여 입력되는 기능이 지원되므로 이들 에디터를 이용하면 공백 4개를 번거롭게 칠 필요가 없어진다.<br><br>웃긴건 Python 2는 <a class="wiki-link-internal" href="/w/Tab" title="Tab">Tab</a> 키를 권장한다. 탭 키를 사용하지 말 것을 요구하는 건 Python 3에서 해당하는 이야기. 물론 Python 2에서도 "Tab 키를 쓸 경우에는 잊어버리지 말 것"을 이야기하긴 하나, 그렇다면 처음부터 Tab 키를 막았어야 말이 된다.  <del>첨에는 탭이 그렇게 똥인줄 몰랐어....</del> <a class="wiki-link-internal" href="/w/PyCharm" title="PyCharm">PyCharm</a> 등의 주요 에디터에는 탭 키를 썼는지 스페이스 바를 썼는지 시각적으로 구분하여 보여주는 기능이 있으므로 참고하자.<br><br>참고로 위의 예시인 팩토리얼 함수는 (람다식과 ternary operator를 이용해) 다음과 같이 간결히 나타낼 수 있다.<br>C와 Java에서 쓰이는 ternary operator <code>?:</code>에 비해 훨씬 직관적으로 읽혀, '실행할 수 있는 의사코드'라는 별명을 여기서도 느낄 수 있다.</div>
                            </v-list-item>
                            <div style="height:100px;"></div>                            
                        </v-list>
                        
                    </div>
            </div>
            
            <div style="position:fixed; top:0px; left:0px;width:100%; height:100%; z-index:9;" :style="{'background-color': (darkOption ? '#1e1e1e' : '#FFFFFF')}">
                
            </div>
        </layout>
    </div>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
import AnotherVideo from "@/components/lecture/view/AnotherVideo.vue"
import QuestionBoard from "@/components/lecture/view/QuestionBoard.vue"
import Contribute from "@/components/lecture/view/Contribute.vue"
export default {
    components: {
        AnotherVideo,
        QuestionBoard,
        Contribute,        
    },
    data(){
        return {
            darkOption: false,
            theme:'',
            autoPlay: true,
            height:500,
            contentHeight: 768,

            navWidth:0,
            listWidth:400,
            videoWidth:500,
            list: true,
            tabs: 0,
            menu: 0,

            myPlayer: null,

            items: [
                {
                    title: '구름IDE에서 파이썬 코딩 시작하기',
                    len: 14
                },
                {
                    title: '실습용 소스 코드 다운로드 하기.',
                    len: 1
                },
                {
                    title: '수와 연산',
                    len: 28
                },
                {
                    title: '변수: 대입과 비교 연산',
                    len: 30
                },
                {
                    title: '논리 연산과 조건문',
                    len: 31
                },
                {
                    title: '리스트와 반복문',
                    len: 38
                },
                {
                    title: '열린 사물함의 갯수 문제',
                    len: 31
                },
                {
                    title: '함수의 활용과 소수의 판별',
                    len: 43
                },
                {
                    title: '에라토스테네스의 체',
                    len: 27
                },
                {
                    title: '소인수 분해',
                    len: 28
                },
                {
                    title: '최대공약수 구하기',
                    len: 23
                },
                {
                    title: '최소 공배수 구하기',
                    len: 24
                },
                
            ]

        }
    },
    watch: {
        darkOption(){
            if(this.darkOption){
                $('.LecturePlayer .v-list-item__content').css('color', '#d4d4d4')
                $('.LecturePlayer .wiki-paragraph').css('color', '#d4d4d4')
            }else{            
                $('.LecturePlayer .v-list-item__content').css('color', '#000000DE')
                $('.LecturePlayer .wiki-paragraph').css('color', '#000000DE')
            }
            localStorage.setItem('darkOption', this.darkOption ? 'dark' : 'light');
        },
        autoPlay() {
            localStorage.setItem('autoPlay', this.autoPlay ? 'true' : 'false');
        }
    },
    created(){
        this.darkOption = localStorage.getItem('darkOption')        
        if(this.darkOption == null)
            this.darkOption = false;
        else
            this.darkOption= this.darkOption == 'dark';
        
        this.autoPlay = localStorage.getItem('autoPlay')
        if(this.autoPlay == null)
            this.autoPlay = true;
        else
            this.autoPlay= this.autoPlay == 'true';

        
    },
    mounted() {
        if(this.darkOption){
            $('.LecturePlayer .v-list-item__content').css('color', '#d4d4d4')
            $('.LecturePlayer .wiki-paragraph').css('color', '#d4d4d4')
        }else{            
            $('.LecturePlayer .v-list-item__content').css('color', '#000000DE')
            $('.LecturePlayer .wiki-paragraph').css('color', '#000000DE')
        }
        
        this.handleResize()
        window.addEventListener('resize', this.handleResize)     
        // 동영상 길이 구하는 법
        // var myPlayer = videojs('livestation-player');  
        // myPlayer.ready(function() {
        //     var key = setInterval(() => {
        //         if(myPlayer.duration()){
        //             console.dir(myPlayer.duration())
        //             clearInterval(key);
        //         }
        //     }, 100)
        // });

        // 종료 시 다음 강의로 넘어가기
        // myPlayer.on('ended', function() {
        // //    alert("끝!")
        //  });

    },
    beforeDestroy(){
        window.removeEventListener('resize', this.handleResize);
    },
    methods: {
        handleResize() {
            this.navWidth = this.$refs.nav.miniVariantWidth;
            this.videoWidth = document.body.scrollWidth - this.listWidth - this.navWidth;
            this.height = this.$refs.video.clientHeight;
            this.contentHeight = $('body').prop("clientHeight")-this.$refs.contents.$el.offsetTop
        },   

        closeList(){
            this.navWidth = this.$refs.nav.miniVariantWidth;
            this.listWidth = 0;
            this.videoWidth = document.body.scrollWidth - this.listWidth - this.navWidth;
            this.list = false;
            this.menu = -1;
        },

        openList(){
            this.navWidth = this.$refs.nav.miniVariantWidth;
            this.listWidth = 400;
            this.videoWidth = document.body.scrollWidth - this.listWidth - this.navWidth;
            this.list = true;
        },

        move(url){
            this.$router.push(url)
        }
    }
}
</script>

<style scoped>
</style>