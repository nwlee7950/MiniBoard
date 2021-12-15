import {
  listArticle,
  writeArticle,
  modifyArticle,
  getArticle,
  deleteArticle,
} from "@/api/board";
const boardStore = {
  namespaced: true,
  state: {
    msg: String,
    articles: [],
    article: {},
  },
  getters: {},
  mutations: {
    SET_MSG(state, msg) {
      state.msg = msg;
      console.log(msg);
    },
    SET_ALL_ARTICLES(state, articles) {
      state.articles = articles;
    },
    SET_CURRENT_ARTICLE(state, article) {
      state.article = article;
    },
    RESET_ARTICLE(state, blank) {
      state.article = blank;
    },
  },
  actions: {
    resetArticle({ commit }) {
      commit("RESET_ARTICLE", {});
    },
    getAllArticles({ commit }, options) {
      listArticle(
        options,
        (response) => {
          commit("SET_ALL_ARTICLES", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getOneArticle({ commit }, boardId) {
      getArticle(
        boardId,
        (response) => {
          let msg = "해당 글 불러오기 성공!!";
          console.log(msg);
          commit("SET_CURRENT_ARTICLE", response.data);
        },
        (error) => {
          console.log("에러발생!!", error);
        }
      );
    },
    addArticle({ commit }, article) {
      writeArticle(
        article,
        () => {
          let msg = "글 등록이 완료되었습니다.";
          console.log(msg);
          commit("SUCCESS", msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modArticle({ commit }, article) {
      modifyArticle(
        article,
        () => {
          let msg = "글 수정이 완료되었습니다.";
          console.log(msg);
          commit("SUCCESS", msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    delArticle({ commit }, boardId) {
      deleteArticle(
        boardId,
        () => {
          let msg = "글 삭제가 완료되었습니다.";
          console.log(msg);
          commit("SUCCESS", msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default boardStore;
