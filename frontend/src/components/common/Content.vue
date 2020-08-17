<template>
    <div id="mainContent" style="" ref="empty">        
        <router-view :key="$route.fullPath"></router-view>
        <v-fab-transition>
            <v-btn
                v-show="button"
                color="primary"
                small
                dark
                fab
                bottom
                right
                fixed
                @click="goto(0)"
                style="z-index:10002"                
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
            this.$refs.empty.style.marginLeft = width + "px";
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