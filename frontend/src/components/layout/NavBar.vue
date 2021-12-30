<template>
<div class="navbar">
    <router-link :to="{name:'Home'}" class="logo link"><i class="fas fa-stream"></i> Mini Board</router-link>
    <div class="menus">
        <router-link :to="{name:'Board'}" class="board link"><i class="far fa-keyboard"></i> Board</router-link>
        <div class="in" v-if="isLogin">
            <router-link :to="{name:'MyPage'}" class="mypage link"><i class="far fa-user-circle"></i> My Page</router-link>
            <button @click.prevent="logoutUser"><i class="far fa-user-circle"></i>로그아웃</button>
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
    mapMutations,
} from 'vuex';
export default {
    name: 'NavBar',
    computed: {
        ...mapState("memberStore", ["isLogin"]),
    },
    methods: {
        ...mapMutations("memberStore", ["SET_IS_LOGIN", "SET_USER_INFO"]),
        logoutUser() {
            this.SET_IS_LOGIN(false);
            this.SET_USER_INFO(null);
            sessionStorage.removeItem("access-token");
            
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
}

.link {
    margin-right: 50px;
    text-decoration: none;
    color: var(--font-color);
}

.link:hover {
    color: var(--main-color);
}

.dropdown {
    position: relative;
}
</style>
