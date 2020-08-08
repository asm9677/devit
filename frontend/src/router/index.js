import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '@/components/common/Main.vue'
import Join from '@/components/user/Join.vue'
import UserModify from '@/components/user/Modify.vue'

import Board from '@/components/board/Board.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardEdit from '@/components/board/BoardEdit.vue'
import TempBoard from "@/components/board/TempBoard.vue";

import LectureList from '@/components/lecture/view/LectureList.vue'
import LectureManagement from '@/components/lecture/manager/LectureManagement.vue'
import LectureDetail from '@/components/lecture/view/LectureDetail.vue'

import LecturePlayer from '@/components/lecture/view/LecturePlayer.vue'

import SearchList from '@/components/lecture/view/SearchList.vue'

Vue.use(VueRouter)


const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main
  },
  {
    path: '/join',
    name: 'Join',
    component: Join
  },
  {
    path: '/user/modify',
    name: 'UserModify',
    component: UserModify
  },
  {
    path: '/board',
    name: 'Board',
    component: Board
  },
  {
    path: '/board/detail',
    name: 'BoardDetail',
    component: BoardDetail
  },
  {
    path: '/board/edit',
    name: 'BoardEdit',
    component: BoardEdit
  },
  {
    path: '/lecture',
    name: 'Lecture',
    component: LectureList
  },
  {
    path: '/lecture/management/:id/default',
    name: 'LectureManagement',
    component: LectureManagement
  },
  {
    path: '/lecture/detail/:id',
    name: 'LectureDetail',
    component: LectureDetail
  },

  {
    path: '/search',
    name: 'Search',
    component: SearchList
  },

  {
    path: '/tempboard',
    name: 'TempBoard',
    component: TempBoard
  },

  {
    path: '/lecture/player/:id',
    name: 'LecturePlayer',
    component: LecturePlayer
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
