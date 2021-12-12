import { apiInstance } from "./index.js";

const api = apiInstance();

//현재 글 댓글 조회
function getComment(commentno, success, fail){
    api.get(`/comments/${commentno}`).then(success).catch(fail);
}

export { getComment };