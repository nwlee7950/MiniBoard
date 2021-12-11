import { apiInstance } from "./index.js";

const api = apiInstance();

//모든 글 조회
function listArticle(param, success, fail) {
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

//새로운 글 작성
function writeArticle(article, success, fail) {
  api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

//검색 조건 하 조회
function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

//기존 글 수정
function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

//기존 글 삭제
function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
