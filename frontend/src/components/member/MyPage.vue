<template>
<div class="mypage">
    <div class="title">My Page</div>
    <div class="memberInfo" v-if="userInfo">
        <h1>My Page<i class="fas fa-user-tag"></i></h1>
        <div class="infoDetail">
            <p>아이디</p>
            <p>{{ userInfo.id }}</p>
            <p>이름</p>
            <p>{{ userInfo.name }}</p>
        </div>
    </div>
    <div class="btnBox">
        <button class="modifyBtn btn" @click="moveUpdate">정보수정</button>
        <button class="quitBtn btn" @click="deleteMember">회원탈퇴</button>
    </div>

</div>
</template>

<script>
import {
    mapState,
    mapMutations,
    mapActions,
} from "vuex";

export default {
    name: "MyPage",
    computed: {
        ...mapState("memberStore", ["userInfo", "isDeleted"]),
    },
    methods: {
        ...mapActions("memberStore", ["userDelete"]),
        ...mapMutations("memberStore", ["SET_IS_LOGIN", "SET_USER_INFO"]),
        moveUpdate() {
            this.$router.push({
                name: "UpdateMember",
                param: {
                    id: this.userInfo.id,
                }
            })
        },
        async deleteMember() {
            console.log(this.userInfo.id);
            await this.userDelete(this.userInfo.id);
            console.log(this.isDeleted);
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            if (this.$route.path != "/")
                this.$router.push({
                    name: "Home",
                });
        }
    }
}
</script>

<style scoped>
.mypage {
    padding-top: 100px;
    display: flex;
    flex-direction: column;
}

.title {
    font-size: 70px;
    width: 100%;
    margin: 60px 0;
    text-decoration: underline;
    text-align: center;
}

.container {
    background-color: var(--white-color);
    padding: 50px;
    border-radius: 5px;
    box-shadow: 2px 2px 10px var(--shadow-color);
    height: 60vh;
}
</style>
