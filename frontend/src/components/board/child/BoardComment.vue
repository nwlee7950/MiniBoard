<template>
<div class="comment_box">
    <h5 class="content">{{content}}</h5>
    <div class="author_info">
        <div class="author">by {{userId}}</div>
        <div class="authored_time">{{date}}</div>
    </div>
    <div class="options">
        <form @submit="onSubmit">
            <button><i class="fas fa-reply reply"></i></button>
            <button><i class="fas fa-pen mm"></i></button>
            <button type="submit"><i class="fas fa-trash-alt mm"></i></button>
        </form>
    </div>
</div>
</template>

<script>
import {
    deleteComment
} from "@/api/comment.js";
export default {
    props: {
        boardId: Number,
        id: Number,
        userId: String,
        content: String,
        date: String,
    },
    methods: {
        onSubmit(event) {
            event.preventDefault();
            //현재 접속 중인 유저가 댓글 작성자라면
            this.removeComment();
        },
        removeComment() {
            let id = this.id;
            deleteComment(
                id,
                () => {
                    let msg = "삭제가 완료되었습니다.";
                    alert(msg);
                },
                (error) => {
                    console.log(error);
                }
            )
        },
    }
}
</script>

<style scoped>
.comment_box {
    border-radius: 5px;
    background-color: var(--white-color);
    box-shadow: 2px 2px 10px var(--shadow-color);
    padding: 15px;
    margin-bottom: 15px;
    margin-right: 35px;
    position: relative;
}

.author_info {
    display: flex;
}

.author {
    margin-right: 10px;
    width: 100px;
}

.options {
    position: absolute;
    right: 15px;
    top: 30px;
    font-size: 20px;
}

button>i:hover {
    color: var(--main-color);
    cursor: pointer;
}

.mm {
    margin-left: 15px;
}

button {
    background-color: var(--white-color);
    border: none;
    outline: none;
}
</style>
