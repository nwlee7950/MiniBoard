<template>
<tr class="tr">
    <th><input type="checkbox" class="checkbox"></th>
    <th>1</th>
    <th @click="goDetail" class="title">goDetail</th>
    <th>헬로</th>
    <th>헬로</th>
    <th>1</th>
</tr>
</template>

<script>
export default {
    methods: {
        goDetail() {
            this.$router.push({
                name: 'BoardView',
                params: {
                    articleno: 1,
                }
            })
        }
    }
}
</script>

<style scoped>
.title{
    color: var(--main-color);
}
.title:hover{
    opacity: 0.7;
    cursor: pointer;
}
</style>
