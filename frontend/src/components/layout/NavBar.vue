<template>
<div class="navbar">
    <router-link :to="{name:'Home'}" class="logo link"><i class="fas fa-stream"></i>Mini Board</router-link>
    <div class="menus">
        <router-link :to="{name:'Board'}" class="board link"><i class="far fa-keyboard"></i>Board</router-link>
        <div class="admin" v-if="isAdmin">
            <router-link :to="{name:'AdminPage'}" class=" admin link"><i class="far fa-address-book"></i>회원관리</router-link>
        </div>
        <div class="in" v-if="isLogin">
            <p class="welcome">{{userInfo.name}}님 환영합니다 :)</p>
            <router-link :to="{name:'MyPage'}" class="mypage link">
                <img :src="require(`@/assets/profileImg/${userInfo.profileImage}`)" alt="" class="profileImg">
                My Page
            </router-link>
            <button @click.prevent="logoutUser" class="logoutBtn"><i class="fas fa-sign-out-alt"></i>Logout</button>
        </div>
        <div class="out" v-else>
            <router-link :to="{name:'MemberForm'}" class="dropdown link"><i class="far fa-user-circle"></i>Login / Join</router-link>
        </div>
    </div>
</div>
</template>

<script>
import {
    mapState,
    mapActions,
} from 'vuex';
export default {
    name: 'NavBar',
    computed: {
        ...mapState("memberStore", ["isLogin", "isAdmin", "userInfo"]),
    },
    methods: {
        ...mapActions("memberStore", ["logout"]),
        async logoutUser() {
            console.log("one");
            let data = {
                "isLogin" : false,
                "userInfo" : null
            }
            await this.logout(data);
            console.log("six");
            this.$router.push({name:"Home"});
            console.log("seven");
        },
    }
}
</script>

<style>
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 20px;
    box-shadow: 0 0 5px var(--shadow-color);
    background-color: var(--white-color);
    height: 90px;
}

.logo {
    font-size: 30px;
}

.menus {
    display: flex;
    align-items: center;
}

.link {
    margin-right: 20px;
    text-decoration: none;
    color: var(--font-color);
}

.link:hover {
    color: var(--main-color);
}

.dropdown {
    position: relative;
}
.in{
    display: flex;
    align-items: center;
}
.welcome{
    padding-right: 20px;
    padding-top: 12px;
}
.logoutBtn{
    border: none;
    outline: none;
    background-color: white;
}
.logoutBtn:hover{
    color: var(--main-color);
}
.fas,
.far{
    margin-right: 10px;
}
.profileImg{
    width: 30px;
    height: 30px;
    border-radius: 50%;
}
</style>
