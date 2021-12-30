import { apiInstance } from "./index.js";

const api = apiInstance();

function setAuthToken() {
  let token = sessionStorage.getItem("access-token");
  api.defaults.headers.Authorization = "Bearer " + token;
  console.log("헤더 토큰 셋팅 성공");
}

//모든 글 조회
function listArticle(param, success, fail) {
  api.get(`/boards`, { params: param }).then(success).catch(fail);
}

//새로운 글 작성
function writeArticle(article, success, fail) {
  console.log(article);
  setAuthToken();
  api.post(`/boards`, JSON.stringify(article)).then(success).catch(fail);
}

//해당 글 조회
function getArticle(articleno, success, fail) {
  api.get(`/boards/${articleno}`).then(success).catch(fail);
}

//기존 글 수정
function modifyArticle(article, success, fail) {
  setAuthToken();
  api.put(`/boards`, JSON.stringify(article)).then(success).catch(fail);
}

//기존 글 삭제
function deleteArticle(articleno, success, fail) {
  setAuthToken();
  api.delete(`/boards/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
