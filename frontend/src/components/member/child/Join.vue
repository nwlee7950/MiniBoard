<template>
<div class="join">
    <form>
        <label for="id">ID</label>
        <input type="text" name="id" placeholder="아이디 입력..." v-model="user.id">
        <label for="pw">Password</label>
        <input type="text" name="pw" placeholder="비밀번호 입력..." v-model="user.password">
        <label for="name">Name</label>
        <input type="text" name="name" placeholder="이름 입력..." v-model="user.name">
        <input type="file" @change="previewFiles">
        <button @click="registUser" class="joinBtn">회원가입</button>
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
            },
            file: null,
        }
    },
    computed: {
        ...mapState("memberStore", ["isRegister"])
    },
    methods: {
        ...mapActions("memberStore", ["userRegister"]),
        async registUser() {
            await this.userRegister(this.user, this.file);
            this.$router.push({
                name: "Login"
            })
        },
        previewFiles(event) {
            let file = event.target.files[0];
            this.file = file;
        }
    }

}
</script>

<style scoped>
.join {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 60vh;
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
.joinBtn{
    width: 100px;
    border: none;
    border-radius: 3px;
    background-color: var(--main-color);
    color: white;
    margin-top: 15px;
    padding: 3px 0;
}
</style>
