<template>
<div class="join">
    <form @submit="onRegist">
        <label for="id">ID</label>
        <input type="text" name="id" placeholder="아이디 입력..." v-model="user.id">
        <label for="pw">Password</label>
        <input type="text" name="pw" placeholder="비밀번호 입력..." v-model="user.password">
        <label for="name">Name</label>
        <input type="text" name="name" placeholder="이름 입력..." v-model="user.name">
        <button type="submit">회원가입</button>
    </form>
</div>
</template>

<script>
import {
    mapState,
    mapActions
} from "vuex";

export default {
    name: "Join",
    data() {
        return {
            user: {
                id: "",
                name: "",
                password: "",
                profileImage: "",
            }
        }
    },
    computed: {
        ...mapState("memberStore", ["isRegister"])
    },
    methods: {
        ...mapActions("memberStore", ["userRegister"]),
        onRegist(event) {
            event.preventDefault();
            this.register();
        },
        register() {
            this.userRegister(this.user);
            this.$router.push({
                name: "Login"
            })
        }
    }

}
</script>

<style scoped>

</style>
