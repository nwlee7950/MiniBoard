<template>
<div class="home">
    <div class="context">
        <p class="title">
            <strong class="bold">Mini Board,</strong><br>
            Share yours and take other's
        </p>
        <div class="joinLink">
            <router-link :to="{name:'Join'}" class="move_join" v-if="logout">Join Mini Board</router-link>
        </div>
        <button type="button" class="move_board" @click="move_board">Go to the Board</button>
    </div>
    <div class="icons">
        <img src="../assets/yellow.png" alt="yellow">
        <div></div>
        <img src="../assets/emerald.png" alt="emerald">
        <div></div>
        <div></div>
        <div></div>
        <img src="../assets/charcoal.png" alt="charcoal">
        <div></div>
        <img src="../assets/red.png" alt="red">
    </div>
</div>
</template>

<script>
import {
    mapState
} from 'vuex'
export default {
    name: 'Home',
    data() {
        return {
            "logout": true,
        }
    },
    computed: {
        ...mapState("memberStore", ["isLogin"]),
    },
    methods: {
        move_board() {
            this.$router.push({
                name: 'Main'
            });
        }
    },
    created() {
        this.logout = !this.isLogin;
    }
}
</script>

<style scoped>
.home {
    background-image: url("../assets/home.jpg");
    height: 100vh;
    display: flex;
    justify-content: space-around;
    align-items: center;
}

.context {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.title {
    font-size: 40px;
    margin-bottom: 150px;
}

.bold {
    font-size: 65px;
    color: var(--main-color);
}

.move_join {
    font-size: 15px;
    margin: 100px 0 30px 0;
    text-decoration: underline;
    color: var(--font-color);
}

.move_join:hover {
    color: var(--main-color);
}

.move_board {
    font-size: 30px;
    color: var(--white-color);
    background-color: var(--main-color);
    border: none;
    border-radius: 5px;
    padding: 10px 15px;
}

.move_board:hover {
    transform: scale(1.1);
    transition: transform 0.2s ease;
    cursor: pointer;
}

.icons {
    width: 600px;
    height: 600px;
    display: grid;
    grid-template-rows: 4fr 1fr 4fr;
    grid-template-columns: 4fr 1fr 4fr;
    margin-right: 130px;
}

img {
    width: 100%;
    height: 100%;
}

.joinLink {
    margin-bottom: 20px;
    font-size: 30px;
}
</style>
