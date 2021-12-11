<template>
<tr class="tr">
    <td><input type="checkbox" class="checkbox"></td>
    <td>{{id}}</td>
    <td @click="goDetail" class="title">{{title}}</td>
    <td>{{userId}}</td>
    <td>{{date}}</td>
    <td>{{hits}}</td>
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
    },
    props: {
        id: Number,
        title: String,
        userId: String,
        date: String,
        hits: Number,
    },
}
</script>

<style scoped>
.title {
    color: var(--main-color);
}

.title:hover {
    opacity: 0.7;
    cursor: pointer;
}
</style>
