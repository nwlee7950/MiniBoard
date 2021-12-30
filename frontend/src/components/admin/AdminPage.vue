<template>
<div class="admin">
    <div class="container">
        <h1 class="title">관리자 페이지입니다.</h1>
        <div class="user" v-for="(user, index) in userList" :key="index">
            <span class="center"> No. {{index}}</span>
            <span>아이디 : {{user.id}}</span>
            <span>이름 : {{user.name}}</span>
            <div class="checkBox center">
            <input :value="user.id" type="checkbox" v-model="checked" class="center">
            </div>
        </div>
        <button @click.prevent="deleteMember" class="btn">탈퇴</button>
    </div>
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
        deleteMember() {
            for (let i = 0; i < this.checked.length; i++)
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
    height: 75vh;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 200px;
}

.title {
    margin-bottom: 50px;
    font-size: 50px;
}

.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    font-size: 20px;
}

.user {
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 3fr 3fr 1fr;
    padding-bottom: 5px;
    margin-bottom: 20px;
    border-bottom: 1px solid var(--shadow-color);
}

.btn {
    width: 100px;
    border: none;
    border-radius: 3px;
    background-color: var(--main-color);
    color: white;
    margin-top: 30px;
    padding: 3px 0;
    align-self: flex-end;
}
.center{
    text-align: center;
}
</style>
