import { apiInstance } from "./index.js";

const api = apiInstance();

function setAuthToken() {
  let token = sessionStorage.getItem("access-token");
  console.log(api.defaults.headers.Authorization);
  api.defaults.headers.Authorization = "Bearer " + token;
  console.log(api.defaults.headers.Authorization);
  console.log("헤더 토큰 셋팅 성공");
}

//현재 글 댓글 조회
function getComment(commentno, success, fail) {
  api.get(`/comments/${commentno}`).then(success).catch(fail);
}

function deleteComment(commentno, success, fail) {
  setAuthToken();
  api.delete(`/comments/${commentno}`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  setAuthToken();
  api.post(`/comments`, JSON.stringify(comment)).then(success).catch(fail);
}

function modifyComment(comment, success, fail) {
  setAuthToken();
  api.put(`/comments`, JSON.stringify(comment)).then(success).catch(fail);
}

export { getComment, deleteComment, writeComment, modifyComment };
