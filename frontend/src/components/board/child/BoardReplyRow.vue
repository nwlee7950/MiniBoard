<template>
<div class="reply_box">
    <h5 class="content">{{content}}</h5>
    <div class="author_info">
        <div class="author">by {{userId}}</div>
        <div class="authored_time">{{date}}</div>
    </div>
    <div class="options">
        <form @submit="onUpdate">
            <Modal v-if="showModal" @close="showModal = false">
                <h3 slot="header">
                    Modify this reply<i class="fas fa-times" @click="removeModal"></i>
                </h3>
                <input slot="body" type="text" v-model="rewrite" />
                <button slot="footer" type="submit">Update</button>
            </Modal>
        </form>
        <form @submit="onDelete">
            <button type="submit"><i class="fas fa-trash-alt mm"></i></button>
        </form>
    </div>
</div>
</template>

<script>
import Modal from "./ModalComment.vue";
import {
    deleteComment,
} from "@/api/comment.js";
export default {
    props: {
        userId: String,
        content: String,
        date: String,
        id: Number,
    },
    components: {
        Modal,
    },
    data() {
        return {
            showModal: false,
            rewrite: "",
        }
    },
    methods:{
        onDelete(event) {
            event.preventDefault();
            //현재 접속 중인 유저가 댓글 작성자라면
            this.removeReply();
        },
        removeReply() {
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
    }
}
</script>

<style scoped>
.reply_box {
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
</style>
