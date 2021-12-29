// import jwt_decode from "jwt-decode";
import {
  //   getUsers,
  //   getUser,
  registUser,
  //   modifyUser,
  //   deleteUser,
  loginUser,
} from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isRegister: false,
    isUpdate: false,
    isDeleted: false,
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_REGISTER: (state, isRegister) => {
      state.isRegister = isRegister;
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
    async userRegister({ commit }, user) {
      await registUser(
        user,
        (response) => {
          console.log(response);
          commit("SET_IS_REGISTER", true);
        },
        (error) => {
          console.log(error);
          commit("SET_IS_REGISTER", false);
        }
      );
    },
  },
};

export default memberStore;
