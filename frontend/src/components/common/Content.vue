<template>
    <div id="mainContent" style="width:100%" ref="empty">        
        <router-view :key="$route.fullPath"></router-view>
        <v-fab-transition>
            <v-btn
                v-show="button && !$router.app.$store.state.smallMode"
                color="primary"
                small
                dark
                fab
                bottom
                right
                fixed
                @click="goto(0)"
                style="z-index:1234"                                
            >
                <v-icon>mdi-chevron-up</v-icon>
            </v-btn>
        </v-fab-transition>
        <v-overlay :value="$router.app.$store.state.loading" opacity="0" style="z-index:9999;">
            <v-progress-circular  indeterminate color="primary lighten-4" width="10" size="128"></v-progress-circular>
        </v-overlay>
    </div>
</template>

<script>
import eventBus from "@/lib/EventBus.js"
import http from "@/util/http_common.js"

export default {
    watch:{
        $route(val){
            window.scrollTo(0, 0)
            this.getNoticeCount();
        }
    },
    data(){
        return {
            button: false,
        }
    },
    created(){
        window.onload = this.getNoticeCount;

        eventBus.$on("modifyNavForHeader", (width) => {
            if(!this.$router.app.$store.state.smallMode) {
                this.$refs.empty.style.paddingLeft = width + "px";                
            }else{
                this.$refs.empty.style.paddingLeft = 56 + "px";
            }
        });
        eventBus.$on("updateHeader", (height) => {
            this.$refs.empty.style.marginTop = height + "px";
        })
        document.addEventListener("scroll", this.handleScroll)
    },
    beforeDestroy(){
        document.removeEventListener("scroll", this.handleScroll)
    },
    methods:{
        getNoticeCount() {
            if(this.$router.app.$store.state.token) {
                http.axios.get('/api/v1/notice/getInfoCnt').then(({data}) => {
                    if(data.msg == 'success')
                        eventBus.$emit('setNotice', parseInt(data.result));
                })
            }
        },
        handleScroll(){
            this.button = window.scrollY > 40
        },
        goto(target){
            this.$vuetify.goTo(target, {
                duration: 300,
                offset: 0,
                easing: 'easeInOutCubic'
            })
        },
    }
}
</script>

<style scoped>
</style>