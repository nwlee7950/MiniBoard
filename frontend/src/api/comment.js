import { apiInstance } from "./index.js";

const api = apiInstance();

//현재 글 댓글 조회
function getComment(commentno, success, fail){
    api.get(`/comments/${commentno}`).then(success).catch(fail);
}

function deleteComment(commentno, success, fail){
    api.delete(`/comments/${commentno}`).then(success).catch(fail);
}

function writeComment(comment, success, fail){
    console.log(JSON.stringify(comment));
    api.post(`/comments`, JSON.stringify(comment)).then(success).catch(fail);
}
export { getComment, deleteComment, writeComment };