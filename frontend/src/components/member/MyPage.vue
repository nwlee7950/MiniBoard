<template>
<div class="mypage">
    <div class="title">My Page</div>
    <div class="container">
        <div class="memberInfo" v-if="userInfo">
            <div class="infoDetail">
                <span></span>
                <img :src="require(`@/assets/profileImg/${userInfo.profileImage}`)" alt="" class="profileImg">
                <div class="idName">
                <p>아이디</p>
                <p>{{ userInfo.id }}</p>
                <p>이름</p>
                <p>{{ userInfo.name }}</p>
                </div>
            </div>
        </div>
        <div class="btnBox">
            <button class="modifyBtn btn" @click="moveUpdate">정보수정</button>
            <button class="quitBtn btn" @click="deleteMember">회원탈퇴</button>
        </div>
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
    data() {
        return {}
    },
    computed: {
        ...mapState("memberStore", ["userInfo", "isDeleted"]),
    },
    methods: {
        ...mapActions("memberStore", ["userDelete", "getUserInfo"]),
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
    },
    created() {
        this.getUserInfo(sessionStorage.getItem("access-token"));
    }
}
</script>

<style scoped>
.mypage {
    height: 50vh;
    padding-top: 100px;
}

.title {
    font-size: 70px;
    width: 100%;
    margin: 60px 0;
    text-decoration: underline;
    text-align: center;
}

.container {
    display: flex;
    flex-direction: column;
    background-color: rgb(226, 224, 226);
    border-radius: 10px;
    padding: 40px 50px;
    font-size: 20px;
    justify-content: center;
    align-items: center;
    width: 600px;
}

.infoDetail {
    display: flex;
    flex-direction: column;
    }
.idName{
    display: grid;
    grid-template-columns: repeat(2, 1fr);

}
.idName>p:nth-child(odd) {
    margin-right: 10px;
    text-align: right;
}

.idName>p:nth-child(even) {
    margin-left: 10px;
}

.btn {
    width: 100px;
    border: none;
    border-radius: 3px;
    background-color: var(--main-color);
    color: white;
    margin-top: 15px;
    padding: 3px 0;
}

.btn:first-child {
    margin-right: 10px;
}

.btn:last-child {
    margin-left: 10px;
}
.profileImg{
    width: 250px;
    height: 250px;
    border-radius: 50%;
    margin: 30px 0;
    margin-bottom: 50px;
    padding: 0;
}
</style>
