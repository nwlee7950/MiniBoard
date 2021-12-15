<template>
<div class="comment_box">
    <div class="box_content">
        <h5 class="content">{{content}}</h5>
        <div class="author_info">
            <div class="author">by {{userId}}</div>
            <div class="authored_time">{{date}}</div>
        </div>
    </div>
    <div class="options">
        <button @click="toggleReply"><i class="fas fa-reply reply"></i></button>
        <button @click="showModal = true"><i class="fas fa-pen mm"></i></button>
        <form @submit="onUpdate">
            <Modal v-if="showModal" @close="showModal = false">
                <h3 slot="header" class="modal_header">
                    <span>Update comment</span>
                    <i class="fas fa-times" @click="toggleModal"></i>
                </h3>
                <input slot="body" type="text" v-model="rewrite" placeholder="new comment.." class="modal_body" />
                <button slot="footer" type="submit" class="modal_footer">Update</button>
            </Modal>
        </form>
        <form @submit="onDelete">
            <button type="submit"><i class="fas fa-trash-alt mm"></i></button>
        </form>
    </div>
    <form class="reply_box" @submit="onRegist" v-if="showReply">
        <div class="new_reply">
            <input type="text" placeholder="reply.." v-model="newReply" class="reply_input">
            <button type="submit" class="reply_btn">Upload</button>
        </div>
        <div class="reply">
            <reply-row class="one-reply" v-for="(reply, index) in replys" :key="index" v-bind="reply" @changeReplys="toggletwice"></reply-row>
        </div>
    </form>
</div>
</template>

<script>
import Modal from "./ModalComment.vue";
import ReplyRow from "./BoardReplyRow.vue";
import {
    mapActions
} from "vuex";
import {
    getComment,
} from "@/api/comment.js";
export default {
    data() {
        return {
            showModal: false,
            rewrite: "",
            showReply: false,
            newReply: "",
            replys: [],
        }
    },
    components: {
        Modal,
        ReplyRow,
    },
    props: {
        boardId: Number,
        id: Number,
        userId: String,
        content: String,
        date: String,
    },
    methods: {
        ...mapActions("commentStore", ["modComment", "delComment"]),
        ...mapActions("replyStore", ["addReply"]),

        //UPDATE COMMENT
        onUpdate(event) {
            event.preventDefault();
            //현재 접속 중인 유저가 댓글 작성자라면
            this.updateComment();
        },
        updateComment() {
            let comment = {
                userId: this.userId,
                content: this.rewrite,
                id: this.id,
                boardId: this.boardId,
            }
            this.modComment(comment);
            this.toggleModal();
        },

        //DELETE COMMENT
        onDelete(event) {
            event.preventDefault();
            //현재 접속 중인 유저가 댓글 작성자라면
            this.removeComment();
        },
        removeComment() {
            let comment = {
                id: this.id,
                boardId: this.boardId,
            }
            this.delComment(comment);
        },

        //CREATE REPLY
        onRegist(event) {
            event.preventDefault();
            this.registReply();
            this.toggletwice();
        },
        registReply() {
            let info = {
                userId: this.userId,
                content: this.newReply,
                parentId: this.id,
                boardId: this.$route.params.articleno
            };
            this.addReply(info);
            this.newReply = "";
        },

        //MODAL
        toggleModal() {
            this.showModal = !this.showModal;
        },
        toggleReply() {
            this.showReply = !this.showReply;
            this.getReplys();
        },
        getReplys() {
            getComment(
                this.boardId,
                (response) => {
                    let replys = response.data.filter(
                        (obj) => obj.parentId === this.id
                    );
                    this.replys = replys;
                },
                (error) => {
                    console.log("대댓글 불러오기 에러!!", error);
                }
            );
        },
        toggletwice() {
            this.toggleReply();
            this.toggleReply();
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

.fa-times {
    margin-left: 100px;
}

.fa-times:hover {
    cursor: pointer;
    color: var(--main-color);
}

.modal_header {
    display: flex;
    justify-content: space-between;
}

.modal_body {
    width: 100%;
    padding: 5px;
}

.modal_footer,
.reply_btn {
    background-color: var(--main-color);
    border-radius: 5px;
    outline: none;
    border: none;
    color: var(--white-color);
    padding: 5px 15px;
}
.new_reply{
    display: flex;
}
.reply_input {
    width: 90%;
    padding: 10px;
    border: none;
    border-bottom: 1px solid var(--font-color);
    margin-right: 15px;
}

.reply_input:focus{
    outline: none;
}
.reply_box{
    margin-top: 20px;
}
.one-reply{
    border-bottom: 1px solid var(--shadow-color);
}
.one-reply:last-child{
    border:none;
}
</style>
