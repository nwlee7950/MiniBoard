<template>
<div class="comment_box">
    <h5 class="content">{{content}}</h5>
    <div class="author_info">
        <div class="author">by {{userId}}</div>
        <div class="authored_time">{{date}}</div>
    </div>
    <div class="options">
        <button @click="toggleReply"><i class="fas fa-reply reply"></i></button>
        <button id="show-modal" @click="showModal = true"><i class="fas fa-pen mm"></i></button>
        <form @submit="onUpdate">
            <Modal v-if="showModal" @close="showModal = false">
                <h3 slot="header">
                    댓글 수정 <i class="fas fa-times" @click="removeModal"></i>
                </h3>
                <input slot="body" type="text" v-model="rewrite" />
                <button slot="footer" type="submit">Update</button>
            </Modal>
        </form>
        <form @submit="onSubmit">
            <button type="submit"><i class="fas fa-trash-alt mm"></i></button>
        </form>
    </div>
    <Reply v-if="showReply" v-bind:id="id"></Reply>
</div>
</template>

<script>
import Modal from "./ModalComment.vue";
import Reply from "./BoardReply.vue";
import {
    deleteComment,
    modifyComment,
} from "@/api/comment.js";
export default {
    data() {
        return {
            showModal: false,
            rewrite: "",
            showReply: false,
        }
    },
    components: {
        Modal,
        Reply,
    },
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
        removeModal() {
            this.showModal = !this.showModal;
        },
        onUpdate(event) {
            event.preventDefault();
            //현재 접속 중인 유저가 댓글 작성자라면
            this.updateComment();
        },
        updateComment() {
            let comment = {
                userId: this.userId,
                content: this.rewrite,
                id: this.id
            }
            modifyComment(
                comment,
                () => {
                    let msg = "댓글 수정이 완료되었습니다.";
                    alert(msg);
                    this.removeModal();
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        toggleReply() {
            this.showReply = !this.showReply;
        }
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
    display: flex;
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
