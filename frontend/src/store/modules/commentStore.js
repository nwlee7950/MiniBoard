import {
  getComment,
  writeComment,
  modifyComment,
  deleteComment,
} from "@/api/comment.js";

const commentStore = {
  namespaced: true,
  state: {
    comments: [],
  },
  getter: {},
  mutations: {
    SET_COMMENTS: (state, comments) => {
      state.comments = comments.reverse();
    },
  },
  actions: {
    getComments({ commit }, no) {
      getComment(
        no,
        (response) => {
          let comments = response.data.filter(
            (comment) => comment.parentId === 0
          );
          commit("SET_COMMENTS", comments);
        },
        (error) => {
          console.log("댓글 불러오기 에러!!", error);
        }
      );
    },
    addComment({ commit }, comment) {
      writeComment(
        comment,
        () => {
          let msg = "댓글 등록이 완료되었습니다.";
          console.log(msg);
          getComment(
            comment.boardId,
            (response) => {
              let comments = response.data.filter(
                (comment) => comment.parentId === 0
              );
              commit("SET_COMMENTS", comments);
            },
            (error) => {
              console.log("추가 후 댓글 불러오기 에러!!", error);
            }
          );
        },
        (error) => {
          console.log("댓글 추가 에러!!", error);
        }
      );
    },
    modComment: ({ commit }, comment) => {
        console.log(comment);
      modifyComment(
        comment,
        (res) => {
          let msg = "댓글 수정이 완료되었습니다.";
          console.log(msg);
          console.log(res);
          getComment(
            comment.boardId,
            (response) => {
              let comments = response.data.filter(
                (comment) => comment.parentId === 0
              );
              commit("SET_COMMENTS", comments);
            },
            (error) => {
              console.log("수정 후 댓글 불러오기 에러!!", error);
            }
          );
        },
        (error) => {
          console.log("댓글 수정 에러!!", error);
        }
      );
    },
    delComment: ({ commit }, comment) => {
      deleteComment(
        comment.id,
        (res) => {
          let msg = "삭제가 완료되었습니다.";
          console.log(msg);
          console.log(res);
          getComment(
            comment.boardId,
            (response) => {
              let comments = response.data.filter(
                (comment) => comment.parentId === 0
              );
              commit("SET_COMMENTS", comments);
            },
            (error) => {
              console.log("삭제 후 댓글 불러오기 에러!!", error);
            }
          );
        },
        (error) => {
          console.log("댓글 삭제 에러!!", error);
        }
      );
    },
  },
};

export default commentStore;
