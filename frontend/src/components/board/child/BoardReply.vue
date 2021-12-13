<template>
<div class="reply">
    hi
    <reply-row v-for="(reply, index) in replys" :key="index" v-bind="reply"></reply-row>
</div>
</template>

<script>
import ReplyRow from "./BoardReplyRow.vue";
import {
    getComment,
} from "@/api/comment.js";
export default {
    data() {
        return {
            replys: [],
        }
    },
    components: {
        ReplyRow,
    },
    props:{
        id:Number,
    },
    created() {
        getComment(
            this.$route.params.articleno,
            (response) => {
                let replys = response.data.filter(reply => reply.parentId === this.id);
                this.replys = replys;
                console.log(JSON.stringify(replys));
            },
            (error) => {
                console.log("에러발생!!", error);
            }
        )
    }
}
</script>

<style scoped>

</style>
