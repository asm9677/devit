<template>
    <div style="" ref="empty">        
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
    </div>
</template>

<script>
import eventBus from "@/lib/EventBus.js"

export default {
    watch:{
        
    },
    data(){
        return {
            button: false,
        }
    },
    created(){
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