<template>
<tr class="tr">
    <td>{{id}}</td>
    <td @click="goDetail" class="title">{{title}}</td>
    <td>{{userId}}</td>
    <td>{{date}}</td>
    <td>{{hits}}</td>
</tr>
</template>

<script>
export default {
    data(){
        return{
        }
    },
    methods: {
        goDetail() {
            this.$router.push({
                name: 'BoardView',
                params: {
                    articleno: this.id,
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
