import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Main from '../views/Main.vue'
import Member from '../components/member/Member.vue'
import MemberForm from '../components/member/MemberForm.vue'
import Login from '../components/member/loginAndJoin/Login.vue'
import Join from '../components/member/loginAndJoin/Join.vue'
import MyPage from '../components/member/MyPage.vue'
import Board from '../components/board/Board.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/main',
    name: 'Main',
    component: Main,
    redirect: '/main/board',
    children:[
        {
          path: '/main/member',
          name: 'Member',
          component: Member,
          redirect:'main/member/login',
          children:[
              {
                  path: '/main/member/form',
                  name: 'MemberForm',
                  component: MemberForm,
                  redirect: '/main/member/form/login',
                  children:[
                    {
                        path: '/main/member/form/login',
                        name: 'Login',
                        component: Login
                    },
                    {
                        path: '/main/member/form/join',
                        name: 'Join',
                        component: Join
                    },
                  ]
              },
              {
                  path: '/main/member/mypage',
                  name: 'MyPage',
                  component: MyPage
              },
          ]
        },
        {
          path: '/main/board',
          name: 'Board',
          component: Board
        },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
