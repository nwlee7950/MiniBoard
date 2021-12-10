<template>
  <div id="modify" class="modify">
      <div class="title">modify</div>
      <board-form></board-form>
  </div>
</template>

<script>
import BoardForm from './child/BoardForm.vue';
export default {
    components:{
        BoardForm,
    }
}
</script>

<style scoped>
.modify {
    padding-top: 100px;
    display: flex;
    flex-direction: column;
}

.title {
    font-size: 70px;
    width: 100%;
    margin: 60px 0;
    text-decoration: underline;
    text-align: center;
}
</style>