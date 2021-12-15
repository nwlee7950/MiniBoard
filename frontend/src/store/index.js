import Vue from 'vue';
import Vuex from 'vuex';
import memberStore from "@/store/modules/memberStore.js";
import boardStore from "@/store/modules/boardStore.js";
import commentStore from "@/store/modules/commentStore.js";


Vue.use(Vuex);

const store =  new Vuex.Store({
    modules: {
        memberStore,
        boardStore,
        commentStore,
    },
});

export default store;