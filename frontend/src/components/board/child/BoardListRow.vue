<template>
<tr class="tr" :class="notice">
    <td>{{area}}</td>
    <td @click="goDetail" class="title">{{title}}</td>
    <td>{{userId}}</td>
    <td>{{date}}</td>
    <td>{{hits}}</td>
</tr>
</template>

<script>
export default {
    data() {
        return {
            "area": String,
            "notice": String,
        }
    },
    created() {
        if (this.userId === "admin") {
            this.area = "공지"
            this.notice = "yes"
        } else {
            this.area = "일반"
            this.notice = "no"
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

.yes {
    background-color: rgb(227, 241, 227);
}
</style>
