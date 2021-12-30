import Vue from "vue";
import VueRouter from "vue-router";

import Home from "../views/Home.vue";
import Main from "../views/Main.vue";

import Member from "../components/member/Member.vue";
import MemberForm from "../components/member/MemberForm.vue";
import Login from "../components/member/child/Login.vue";
import Join from "../components/member/child/Join.vue";
import MyPage from "../components/member/MyPage.vue";
import UpdateMember from "../components/member/UpdateMember.vue";

import Board from "../components/board/Board.vue";
import BoardWrite from "../components/board/BoardWrite.vue";
import BoardView from "../components/board/BoardView.vue";
import BoardModify from "../components/board/BoardModify.vue";

import AdminPage from '@/components/admin/AdminPage.vue';

import store from "@/store/index.js";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  console.log(checkUserInfo);
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    router.push({ name: "Login" });
  } else {
    console.log("로그인 이미 함");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/main",
    name: "Main",
    component: Main,
    redirect: "/main/board",
    children: [
      // Board
      {
        path: "/main/board",
        name: "Board",
        component: Board,
      },
      {
        path: "/main/board/write",
        name: "BoardWrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "/main/board/detail/:articleno",
        name: "BoardView",
        component: BoardView,
      },
      {
        path: "/main/board/update/:articleno",
        name: "BoardModify",
        beforeEnter: onlyAuthUser,
        component: BoardModify,
      },
      // Member
      {
        path: "/main/member",
        name: "Member",
        component: Member,
        redirect: "main/member/login",
        children: [
          {
            path: "/main/member/form",
            name: "MemberForm",
            component: MemberForm,
            redirect: "/main/member/login",
            children: [
              {
                path: "/main/member/login",
                name: "Login",
                component: Login,
              },
              {
                path: "/main/member/join",
                name: "Join",
                component: Join,
              },
            ],
          },
          {
            path: "/main/member/mypage",
            name: "MyPage",
            beforeEnter: onlyAuthUser,
            component: MyPage,
          },
          {
            path: "/main/member/update",
            name: "UpdateMember",
            beforeEnter: onlyAuthUser,
            component: UpdateMember,
          },
        ],
    },
    //ADMIN
    {
      path: "/main/admin",
      name: "AdminPage",
      beforeEnter: onlyAuthUser,
      component: AdminPage,
    },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
