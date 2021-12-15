import { writeComment, modifyComment, deleteComment } from "@/api/comment.js";

const replyStore = {
  namespaced: true,
  state: {
    msg: String,
  },
  getter: {},
  mutations: {
    SUCCESS(state, msg) {
      state.msg = msg;
      console.log(msg);
    },
  },
  actions: {
    addReply({ commit }, reply) {
      commit("SUCCESS", "성공");
      console.log("gpdl");
      writeComment(
        reply,
        () => {
          let msg = "대댓글 등록이 완료되었습니다.";
          alert(msg);
        },
        (error) => {
          console.log("대댓글 추가 에러!!", error);
        }
      );
    },
    modReply: ({ commit }, reply) => {
      commit("SUCCESS", "성공");
      modifyComment(
        reply,
        () => {
          let msg = "대댓글 수정이 완료되었습니다.";
          alert(msg);
        },
        (error) => {
          console.log("대댓글 수정 에러!!", error);
        }
      );
    },
    delReply: ({ commit }, reply) => {
      commit("SUCCESS", "성공");
      deleteComment(
        reply.id,
        () => {
          let msg = "삭제가 완료되었습니다.";
          alert(msg);
        },
        (error) => {
          console.log("대댓글 삭제 에러!!", error);
        }
      );
    },
  },
};

export default replyStore;
