<template>
<tr class="tr">
    <td>{{area}}</td>
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
            "area":String,
        }
    },
    created(){
        if(this.userId === "admin")
            this.area = "공지"
        else
            this.area = "일반"
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
