import Vue from "vue";
import Vuex from "vuex";
import memberStore from "@/store/modules/memberStore.js";
import boardStore from "@/store/modules/boardStore.js";
import commentStore from "@/store/modules/commentStore.js";
import replyStore from "@/store/modules/replyStore.js";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    memberStore,
    boardStore,
    commentStore,
    replyStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],

});

export default store;
