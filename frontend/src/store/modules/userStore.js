import {
//   getUsers,
  getUser,
//   registUser,
//   modifyUser,
//   deleteUser,
  loginUser,
} from "@/api/user.js";

const userStore = {
    namespaced: true,
    state:{
        users:[],
        userInfo:{},
    },
    getter:{},
    mutations:{
        SET_USERINFO(state, userInfo){
            state.userInfo = userInfo;
        }
    },
    actions:{
        login({commit}, user){
            loginUser(
                user,
                ()=>{
                    let msg = "로그인 성공!!";
                    console.log(msg);
                    getUser(
                        user.id,
                        (res) => {
                            let userInfo = res.data;
                            commit("SET_USERINFO", userInfo);
                        },
                        (err) => {
                            console.log("로그인 후 회원정보 불러오기 실패!!", err);
                        }
                    );
                },
                (err) => {
                    console.log("로그인 실패!!", err);
                }
            );
        },
    }
}

export default userStore;