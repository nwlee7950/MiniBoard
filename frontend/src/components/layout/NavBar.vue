<template>
  <div class="navbar">
      <router-link :to="{name:'Home'}" class="logo link"><i class="fas fa-stream"></i> Mini Board</router-link>
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
    box-shadow: 0 0 5px var(--shadow-color);
    background-color: var(--white-color);
    height: 90px;
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
.link:hover{
    color: var(--main-color);
}
.dropdown{
    position: relative;
}
</style>