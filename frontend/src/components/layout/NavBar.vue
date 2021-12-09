<template>
  <div class="navbar">
      <div class="logo"><i class="fas fa-stream"></i> Mini Board</div>
      <div class="menus">
          <router-link :to="{name:'Board'}" class="board link"><i class="far fa-keyboard"></i> Board</router-link>
          <router-link :to="{name:'MyPage'}" class="mypage link"><i class="fas fa-user-cog"></i> My Page</router-link>
          <router-link :to="{name:'MemberForm'}" class="dropdown link"><i class="far fa-user-circle"></i></router-link>
      </div>
  </div>
</template>

<script>
export default {
    name:'NavBar',
}
</script>

<style>
.navbar{
    padding: 20px 200px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 20px;
}
.logo{
    font-size: 30px;
}
.menus{
    display: flex;
}
.link{
    margin-right: 50px;
    text-decoration: none;
    color: var(--font-color);
}
.dropdown{
    position: relative;
}
</style>