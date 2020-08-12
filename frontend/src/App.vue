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

    @media only screen and (-webkit-min-device-pixel-ratio: 0) and (min-device-width: 1025px) {
         ::-webkit-scrollbar {
            width: 6px;
            height: 6px;
        }
        ::-webkit-scrollbar-button:start:decrement,
        ::-webkit-scrollbar-button:end:increment {
            display: block;
            height: 0px;
        }


        ::-webkit-scrollbar-button:vertical:increment {
            /* background-color: #fff;
             */
             height:0px;
        } 

        ::-webkit-scrollbar-track-piece {
            background-color: transparent;
            -webkit-border-radius: 3px;
        }

        ::-webkit-scrollbar-thumb:vertical {
            height: 50px;
            background-color:rgb(167, 164, 164);
            opacity: 0.5;
            
             /* background-image: linear-gradient(-45deg, rgba(0, 160, 255, 0.86), rgb(0, 72, 162)); */
        }

        ::-webkit-scrollbar-thumb:horizontal {
            width: 50px;
            background-color:transparent;
            -webkit-border-radius: 3px;
        } 
    }
    .v-application p{
        margin: 0px;
    } 

    blockquote {
        color: #7f8fa4;
        font-size: inherit;
        padding: 8px 24px;
        margin: 16px 0;
        border-left: 3px solid #eaeaea;
    }

    hr {
        border-top: 1px solid #e1e1e1;
    }

    pre {
        margin-bottom: 16px;
        font-size: 13px;
        line-height: 1.6em;
        overflow-x: auto;
        border-radius: 2px;
    }

    a {
        text-decoration:none
    }
</style>
