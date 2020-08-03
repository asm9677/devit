<template>
  <v-app >
    <Header></Header>
    <Navigation ></Navigation>
    <Content></Content>
  </v-app>
</template>

<script>
import Header from '@/components/common/Header.vue';
import Navigation from '@/components/common/Navigation.vue';
import Content from '@/components/common/Content.vue';
export default {
  name: 'App',

  components: {
    Header,
    Navigation,
    Content,
  },

  data() {
    return {

    }
  },
  
  methods: {
    
  }

};
</script>
<style>
    @import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
    * {
        font-family: "Noto Sans KR", sans-serif;
    }
</style>
