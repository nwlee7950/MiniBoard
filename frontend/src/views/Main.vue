<template>
  <div class="main">
      <nav-bar></nav-bar>
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
}
nav-bar{
    position: fixed;
    top: 0;
}
</style>