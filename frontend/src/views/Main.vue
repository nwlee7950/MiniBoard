<template>
  <div class="main">
      <nav-bar class="nav"></nav-bar>
      <router-view/>
  </div>
</template>

<script>
import NavBar from "@/components/layout/NavBar.vue"
export default {
    components:{
        NavBar,
    }
}
</script>

<style scoped>
.main{
    background-image: url("../assets/board.jpg");
    height: 100vh;
    overflow-y: scroll;
}
.nav{
    position: fixed;
    top: 0;
    width: 100%;
    padding: 20px 200px;
}
</style>