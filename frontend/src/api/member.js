import { apiInstance } from "./index.js";

const api = apiInstance();

function setAuthToken() {
  let token = sessionStorage.getItem("access-token");
//   console.log(api.defaults.headers.Authorization);
  api.defaults.headers.Authorization = "Bearer " + token;
//   console.log(api.defaults.headers.Authorization);
//   console.log("헤더 토큰 셋팅 성공");
}

function getUsers(success, fail) {
  setAuthToken();
  api.get(`/users`).then(success).catch(fail);
}

function getUser(userid, success, fail) {
  setAuthToken();
  api.get(`/users/${userid}`).then(success).catch(fail);
}

function registUser(data, success, fail) {
  api.post(`/users`, data).then(success).catch(fail);
}

function modifyUser(data, success, fail) {
  setAuthToken();
  api.put(`/users`, data).then(success).catch(fail);
}

function deleteUser(userid, success, fail) {
  console.log("member");
  setAuthToken();
  api.delete(`/users/${userid}`).then(success).catch(fail);
}

function loginUser(user, success, fail) {
  api.post(`/users/login`, JSON.stringify(user)).then(success).catch(fail);
}

export { getUsers, getUser, registUser, modifyUser, deleteUser, loginUser };
