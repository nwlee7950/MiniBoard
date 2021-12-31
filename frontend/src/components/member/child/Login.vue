<template>
<div class="login">
    <form>
        <label for="id">ID</label>
        <input type="text" name="id" placeholder="아이디 입력..." v-model="user.id">
        <label for="pw">Password</label>
        <input type="text" name="pw" placeholder="비밀번호 입력..." v-model="user.password">
        <button @click.prevent="confirm" class="loginBtn">로그인</button>
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
            this.$router.push({
                name: "Board"
            });
        },
    }
}
</script>

<style scoped>
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50vh;
}

form {
    display: flex;
    flex-direction: column;
    background-color: rgb(226, 224, 226);
    border-radius: 10px;
    padding: 20px 50px;
    font-size: 20px;
    justify-content: center;
    align-items: center;
}

input {
    outline: none;
    width: 300px;
    border: none;
    margin: 15px 0;
    padding: 5px;
    border-radius: 3px;
}

.loginBtn {
    width: 100px;
    border: none;
    border-radius: 3px;
    background-color: var(--main-color);
    color: white;
    margin-top: 15px;
    padding: 3px 0;
}
</style>
