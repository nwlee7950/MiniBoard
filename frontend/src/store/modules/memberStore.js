import jwt_decode from "jwt-decode";
import {
    getUsers,
  getUser,
  registUser,
  modifyUser,
  deleteUser,
  loginUser,
} from "@/api/member.js";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isRegister: false,
    userInfo: null,
    isUpdate: false,
    isDeleted: false,
    isAdmin:false,
    userList:null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_REGISTER: (state, isRegister) => {
      state.isRegister = isRegister;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
      if(userInfo.role === 'ADMIN'){
          state.isAdmin = true;
      }else{
          state.isAdmin = false;
      }
    },
    SET_IS_DELETED: (state, isDeleted) => {
      state.isDeleted = isDeleted;
    },
    SET_IS_UPDATE: (state, isUpdate) => {
      state.isUpdate = isUpdate;
    },
    SET_USER_LIST: (state, userList) => {
        state.userList = userList;
    }
  },
  actions: {
    async userConfirm({ commit, dispatch }, user) {
      await loginUser(
        user,
        (res) => {
          let token = res.data.token;
          commit("SET_IS_LOGIN", true);
          sessionStorage.setItem("access-token", token);
          console.log("로그인 스토어 성공");
          dispatch("getUserInfo", token);
        },
        () => {
          console.log("로그인 스토어 실패");
          commit("SET_IS_LOGIN", false);
        }
      );
    },
    async userRegister({ commit }, user, file) {
      let formData = new FormData();
      formData.append("userRegisterDto", JSON.stringify(user));
      formData.append("file", file);
      console.log(formData);
      await registUser(
        formData,
        (res) => {
          console.log(res);
          commit("SET_IS_REGISTER", true);
        },
        (error) => {
          console.log(error);
          commit("SET_IS_REGISTER", false);
        }
      );
    },
    getUserInfo({ commit }, token) {
      console.log("겟유저인포 들어옴");
      let decode_token = jwt_decode(token);
      console.log(decode_token);
      getUser(
        decode_token.sub,
        (res) => {
          console.log("유저 정보 불러오기 성공");
          console.log(res.data);
          commit("SET_USER_INFO", res.data);
        },
        (error) => {
          console.log("유저 정보 불러오기 에러");
          console.log(error);
        }
      );
    },
    async userDelete({ commit }, userid) {
      await deleteUser(
        userid,
        (res) => {
          console.log("memberstore");
          commit("SET_IS_DELETED", true);
          console.log(res);
        },
        () => {}
      );
    },
    async userUpdate({ commit }, user) {
      await modifyUser(
        user,
        (res) => {
          commit("SET_IS_UPDATE", true);
          console.log(res);
        },
        () => {}
      );
    },
    async getUserList({commit}){
        await getUsers(
            (res) => {
                commit("SET_USER_LIST", res.data);
            },
            () => {}
        )
    }
  },
};

export default memberStore;
