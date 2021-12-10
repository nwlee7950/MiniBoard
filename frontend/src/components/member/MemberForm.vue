<template>
<div id="form">
    <div class="form">
        <router-link :to="{name:'Login'}" class="link login">Login</router-link>
        <router-link :to="{name:'Join'}" class="link join">Join</router-link>
    </div>
    <router-view></router-view>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>
.form{
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 100px;
    font-size: 30px;
}
.link{
    color: var(--font-color);
    text-decoration: underline;
    margin: 0 50px;
}
</style>
