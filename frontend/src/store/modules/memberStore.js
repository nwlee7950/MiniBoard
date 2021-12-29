// import jwt_decode from "jwt-decode";
import {
  //   getUsers,
//   getUser,
//   registUser,
//   modifyUser,
//   deleteUser,
  loginUser,
} from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await loginUser(
        user,
        (response) => {
          console.log("로그인 스토어 성공");
          console.log(response);
          commit("SET_IS_LOGIN", true);
        },
        () => {
          console.log("로그인 스토어 실패");
          commit("SET_IS_LOGIN", false);
        }
      );
    },
  },
};

export default memberStore;
