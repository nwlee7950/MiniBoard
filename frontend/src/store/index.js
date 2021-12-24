import Vue from "vue";
import Vuex from "vuex";
import userStore from "@/store/modules/userStore.js";
import boardStore from "@/store/modules/boardStore.js";
import commentStore from "@/store/modules/commentStore.js";
import replyStore from "@/store/modules/replyStore.js";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    userStore,
    boardStore,
    commentStore,
    replyStore,
  },
});

export default store;
