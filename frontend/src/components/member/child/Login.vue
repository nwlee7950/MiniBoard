<template>
<div class="login">
    <form @submit="onLogin">
        <label for="id">ID</label>
        <input type="text" name="id" placeholder="아이디 입력..." v-model="id">
        <label for="pw">Password</label>
        <input type="text" name="pw" placeholder="비밀번호 입력..." v-model="pw">
        <button type="submit">로그인</button>
    </form>
</div>
</template>

<script>
import {
    mapActions
} from "vuex";
export default {
    data(){
        return{
            id:"",
            pw:"",
        }
    },
methods:{
    ...mapActions("userStore", ["login"]),

    //LOGIN
    onLogin(event){
        event.preventDefault();
        this.loginUser();
    },
    loginUser(){
        let user = {
            id: this.id,
            password: this.pw,
        }
        this.login(user);
    },
}
}
</script>

<style scoped>
.login{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}
.login>form{
    display: flex;
    flex-direction: column;
}
input{
    outline: none;
    width: 300px;
}
</style>
