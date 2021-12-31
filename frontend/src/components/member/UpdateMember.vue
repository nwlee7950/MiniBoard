<template>
<div class="modifyContainer">
    <h1>정보수정<i class="fas fa-user-edit"></i></h1>
    <form class="modifyForm">
        <div class="inputBox">
            <label for="userid">아이디</label>
            <input type="text" id="userid" v-model="userInfo.id" required placeholder="아이디 입력...." disabled ref="userid" />
            <label for="userpwd">비밀번호</label>
            <input type="password" id="userpwd" v-model="user.password" required placeholder="비밀번호 입력...." />
            <label for="userpwd">이름</label>
            <input type="text" id="username" v-model="user.name" required placeholder="이름 입력...." />
            <label for="file">프로필 사진</label>
            <input type="file" name="file" @change="previewFiles">
        </div>
        <div class="btnBox">
            <button type="button" @click="updateMember" class="updateBtn">수정</button>
        </div>
    </form>
</div>
</template>

<script>
import {
    mapState,
    mapActions,
} from "vuex";

export default {
    name: "UpdateUser",
    data() {
        return {
            user: {
                id: null,
                name: null,
                password: null,
                profileImage: "",
            },
            file: null,
        }
    },
    computed: {
        ...mapState("memberStore", ["isUpdate", "userInfo"])
    },
    methods: {
        ...mapActions("memberStore", ["userUpdate", "getUserInfo"]),
        async updateMember() {
            let data = {
                "user": this.user,
                "file": this.file,
            }
            await this.userUpdate(data);
            if (this.isUpdate) {
                let token = sessionStorage.getItem("access-token");
                await this.getUserInfo(token);
                this.$router.push({
                    name: "MyPage",
                })
            }
        },
        previewFiles(event) {
            let file = event.target.files[0];
            this.file = file;
        }
    },
    created() {
        this.user.id = this.userInfo.id;
        this.user.profileImage = this.userInfo.profileImage;
    }
};
</script>

<style scoped>
.modifyContainer {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 200px;
}

h1 {
    font-size: 50px;
    margin-bottom: 50px;
}

form {
    display: flex;
    flex-direction: column;
    background-color: rgb(226, 224, 226);
    border-radius: 10px;
    padding: 50px 50px;
    font-size: 20px;
    justify-content: center;
    align-items: center;
    margin: 0 200px;
}

.inputBox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-self: center;
    text-align: center;
}

input {
    outline: none;
    width: 300px;
    border: none;
    margin: 15px 0;
    padding: 5px;
    border-radius: 3px;
}

.updateBtn {
    width: 100px;
    border: none;
    border-radius: 3px;
    background-color: var(--main-color);
    color: white;
    margin-top: 15px;
    padding: 3px 0;
}
</style>
