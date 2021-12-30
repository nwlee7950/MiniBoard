<template>
<div class="admin">
    관리자 페이지입니다.
    <div class="user" v-for="(user, index) in userList" :key="index">
        <span>아이디 : {{user.id}}</span>
        <span>이름 : {{user.name}}</span>
        <input :value="user.id" type="checkbox" v-model="checked">
    </div>
    <button @click.prevent="deleteMember">강퇴</button>
</div>
</template>

<script>
import {
    mapActions,
    mapState
} from "vuex";
export default {
    name: "adminPage",
    data() {
        return {
            checked: [],
        }
    },
    computed: {
        ...mapState("memberStore", ["userList"]),
    },
    methods: {
        ...mapActions("memberStore", ["getUserList", "userDelete"]),
        deleteMember(){
            for(let i = 0; i < this.checked.length; i++)
                this.userDelete(this.checked[i]);

            this.checked = [];
        }
    },
    created() {
        this.getUserList();
    }
}
</script>

<style scoped>
.admin {
    padding-top: 100px;
}
</style>
