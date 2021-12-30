<template>
<div class="login">
    <form>
        <label for="id">ID</label>
        <input type="text" name="id" placeholder="아이디 입력..." v-model="user.id">
        <label for="pw">Password</label>
        <input type="text" name="pw" placeholder="비밀번호 입력..." v-model="user.password">
        <button @click.prevent="confirm">로그인</button>
    </form>
</div>
</template>

<script>
import {
    mapState,
    mapActions
} from "vuex";
export default {
    data() {
        return {
            user: {
                id: "",
                password: ""
            }
        }
    },
    computed: {
        ...mapState("memberStore", ["isLogin"]),
    },
    methods: {
        ...mapActions("memberStore", ["userConfirm", "getUserInfo"]),
        async confirm() {
            await this.userConfirm(this.user);
        },
    }
}
</script>

<style scoped>
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}

.login>form {
    display: flex;
    flex-direction: column;
}

input {
    outline: none;
    width: 300px;
}
</style>
