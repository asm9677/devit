<template>
<v-card tile @mouseenter="enterNav" @mouseleave="leaveNav">
  <v-navigation-drawer
    permanent
    :expand-on-hover="expandOnHover"
    fixed    
    ref = "nav"
    dark
    style="background-image: linear-gradient(-45deg, rgba(0, 160, 255, 0.86), rgb(0, 72, 162)); min-height:100%;"
  >
        
      <!-- <v-img src="https://cdn.vuetifyjs.com/images/parallax/material.jpg" min-height="100%"> -->
      <div>
        <v-list 
          dense
          nav
        >
        <v-list-item>
            <v-list-item-icon>              
                <v-icon @click="fixedNav">{{expandOnHover ? 'mdi-sort-variant' : 'mdi-menu'}}</v-icon>              
            </v-list-item-icon>
          </v-list-item>
          <v-list-group
            v-for="item in items"
            :key="item.title"
            :v-model="item.active"
            :prepend-icon="item.icon"            
            no-action
            color="white"
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title v-text="item.title" color="white"></v-list-item-title>
              </v-list-item-content>
            </template>

              <v-list-item
                v-for="subItem in item.items"
                :key="subItem.title"
                link
                @click="changeCategory(subItem);" 
              >
                
                <v-list-item-content>
                  <v-list-item-title v-text="subItem.title"></v-list-item-title>
                </v-list-item-content>
                
              </v-list-item>

          </v-list-group>
        </v-list>
      <!-- </v-img> -->
      </div>
  </v-navigation-drawer>
</v-card>
</template>

<script>
import eventBus from "@/lib/EventBus.js"
export default {
  mounted(){
    this.leaveNav();
  },
  data () {
      return {        
        items: [
          {
            icon: 'mdi-view-dashboard',
            title: '커뮤니티',
            active: true,
            items: [
              { title: '공지사항', active: false, path: '/board?type=1'},
              { title: '자유게시판', active: false, path: '/board?type=2' },
              { title: '질문게시판', active: false, path: '/board?type=3' },
              { title: '신고게시판', active: false, path: '/board?type=4' },
            ],
          },
          {
            icon: 'mdi-play-circle-outline',
            title: '개발자 강의',
            active: true,
            items: [
              { title: '초급', active: false, path: '/lecture?level=1' },
              { title: '중급', active: false, path: '/lecture?level=2' },
              { title: '고급', active: false, path: '/lecture?level=3' },
            ],
          },
          {
            icon: 'mdi-account',
            title: '나의 활동',
            active: true,
            items: [
              { title: '내가 쓴 글/댓글', active: false, path: '/' },
              { title: '참여중인 프로젝트', active: false, path: '/' },
              { title: '수강한 강의', active: false, path: '/' },
              { title: '기여해본 프로젝트', active: false, path: '/' },
              { title: '내가 좋아요한 강의', active: false, path: '/' },
            ],
          },
        ],        
        expandOnHover: true,
      }
  },
  methods: {
    changeCategory(subItem){
      for(let i in this.items){
        for(let j in this.items[i].items){
          this.items[i].items[j].active = false;
        }
      }
      subItem.active = true;
      this.$router.push(subItem.path).catch(() => {location.reload(true)});
    },
    enterNav(){
      if(this.expandOnHover){
        eventBus.$emit("modifyNavForHeader", this.$refs.nav.width);
        eventBus.$emit("modifyNavForContent", this.$refs.nav.width);
      }
    },
    leaveNav(){
      if(this.expandOnHover){
        eventBus.$emit("modifyNavForHeader", this.$refs.nav.miniVariantWidth);
        eventBus.$emit("modifyNavForContent", this.$refs.nav.miniVariantWidth);
      }
    },
    fixedNav(){
      this.expandOnHover = !this.expandOnHover;
    }
  }
}
</script>

<style>

</style>