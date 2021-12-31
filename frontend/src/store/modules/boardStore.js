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
    SET_ADMIN_ARTICLES(state, articles) {
      let adminArticles = articles.filter(
        (article) => article.userId === "admin"
      );
      console.log(adminArticles);
      state.articles = adminArticles;
    },
    SET_USER_ARTICLES(state, articles) {
      let userArticles = articles.filter(
        (article) => article.userId !== "admin"
      );
      console.log(userArticles);
      for (let i = 0; i < userArticles.length; i++) {
        state.articles.push(userArticles[i]);
      }
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
        (res) => {
          commit("SET_ADMIN_ARTICLES", res.data);
          commit("SET_USER_ARTICLES", res.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getOneArticle({ commit }, boardId) {
      getArticle(
        boardId,
        (res) => {
          let msg = "해당 글을 불러오는 데 성공했습니다.";
          console.log(msg);
          commit("SET_CURRENT_ARTICLE", res.data);
        },
        (error) => {
          console.log("에러발생!!", error);
          alert("글을 불러오는 데 실패했습니다.");
        }
      );
    },
    addArticle({ commit }, article) {
      writeArticle(
        article,
        (res) => {
          let msg = "글 등록이 완료되었습니다.";
          alert(msg);
          console.log(res);
          commit("SET_MSG", msg);
        },
        (error) => {
          console.log(error);
          alert("글 등록에 실패했습니다.");
        }
      );
    },
    modArticle({ commit }, article) {
      modifyArticle(
        article,
        (res) => {
          let msg = "글 수정이 완료되었습니다.";
          alert(msg);
          console.log(res);
          commit("SET_MSG", msg);
        },
        (error) => {
          console.log(error);
          alert("글 수정에 실패했습니다.");
        }
      );
    },
    delArticle({ commit }, boardId) {
      deleteArticle(
        boardId,
        (res) => {
          let msg = "글 삭제가 완료되었습니다.";
          alert(msg);
          console.log(res);
          commit("SET_MSG", msg);
        },
        (error) => {
          console.log(error);
          alert("글 삭제에 실패했습니다.");
        }
      );
    },
  },
};

export default boardStore;
