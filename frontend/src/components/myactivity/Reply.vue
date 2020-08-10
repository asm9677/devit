<template>
    <div style="; margin-top:20px;">
        <v-card tile="tile" flat="flat">
            <v-layout wrap="wrap">
                <div
                    ref="question"
                    :style="{'height': questionHeight+'px'}"
                    style="width:100%; overflow-y:auto; margin-top:20px;">
                    <v-list v-if="true" link="link">
                        <div
                            class="boardContent"
                            style="width:100%; border:1px solid #1976d2; border-radius:5px; margin:10px 0px;"
                            v-for="i in 10"
                            :key="i+'_div'"
                            @click="$emit('showDetail', 1)">
                            <v-list-item style="width:100%;">
                                <v-list-item-content style="padding-bottom:0px;">
                                    <div class="v-list-item__title">파이썬에서 실수 계산을 오차없이 할 수 있는 방법이 있나요?</div>
                                    <div class="v-list-item__subtitle">파이썬에서 실수 계산을 오차없이 할 수 있는 방법이 있나요?</div>
                                </v-list-item-content>
                            </v-list-item>
                            <v-list-item>
                                <v-list-item-content style="padding-top:8px;">
                                    <v-list-item-subtitle>
                                        <v-avatar class="profile" size="15">
                                            <v-img :src="'https://picsum.photos/500/300?image=15'"></v-img>
                                        </v-avatar>
                                        <span style="margin-left:3px; font-size:12px;">미용쓰기</span>
                                        &nbsp;&nbsp;
                                        <span style="font-size:12px;">
                                            <v-icon small="small">mdi-comment-text</v-icon>
                                            0</span>&nbsp;
                                        <span style="font-size:12px;">조회 0</span>

                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>
                        </div>
                    </v-list>
                </div>
            </v-layout>
        </v-card>
        <v-snackbar v-model="snackbar" timeout="1500" color="primary">
            {{msg}}
        </v-snackbar>
    </div>
</template>

<script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
<script>
    import http from "@/util/http_common.js"
    import axios from "axios"
    import store from "@/store/index.js"
    export default {
        props: [],
        data() {
            return {}
        },
        watch: {},
        created() {},
        methods: {}
    }
</script>

<style scoped="scoped">
    .v-list-item__title {
        font-size: 14px;
    }
    .v-list-item__subtitle {
        font-size: 12px;
    }
</style>