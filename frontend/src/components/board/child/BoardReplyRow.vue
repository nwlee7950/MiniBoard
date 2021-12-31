<template>
<div class="reply_box">
    <div class="reply_contents">
        <h6 class="content">{{content}}</h6>
        <div class="author_info">
            <div class="author">by {{userId}}</div>
            <div class="authored_time">{{date}}</div>
        </div>
    </div>
    <div class="options">
        <div class="option">
            <button @click.prevent="updateOrNot" class="modify_btn btn"><i class="fas fa-pen mm"></i></button>
            <form @submit="onUpdate">
                <Modal v-if="showModal" @close="showModal = false">
                    <h3 slot="header" class="modal_header">
                        <span>Update reply</span>
                        <i class="fas fa-times" @click="removeModal"></i>
                    </h3>
                    <input slot="body" type="text" v-model="rewrite" placeholder="new reply.." class="modal_body" />
                    <button slot="footer" type="submit" class="modal_footer">Update</button>
                </Modal>
            </form>
        </div>
        <div class="option">
            <form @submit="onDelete">
                <button type="submit" class="delete_btn btn"><i class="fas fa-trash-alt mm"></i></button>
            </form>
        </div>
    </div>
</div>
</template>

<script>
import Modal from "./ModalComment.vue";
import {
    mapState,
    mapActions
} from "vuex";
export default {
    props: {
        userId: String,
        content: String,
        date: String,
        id: Number,
        boardId: Number,
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
    computed: {
        ...mapState("memberStore", ["userInfo"]),
    },
    methods: {
        ...mapActions("replyStore", ["modReply", "delReply"]),

        //UPDATE REPLY
        updateOrNot() {
            if (this.userId === this.userInfo.id) {
                this.showModal = true;
            } else {
                console.log("대댓글 수정 권한이 없습니다.");
            }
        },
        onUpdate(event) {
            event.preventDefault();
            this.updateReply();
        },
        updateReply() {
            let reply = {
                userId: this.userInfo.id,
                content: this.rewrite,
                id: this.id,
                boardId: this.boardId,
            };
            this.modReply(reply);
            this.rewrite = "";
            this.removeModal();
            this.$emit("changeReplys");
        },

        //DELETE REPLY
        onDelete(event) {
            event.preventDefault();
            if (this.userId === this.userInfo.id) {
                this.removeReply();
            } else {
                console.log("대댓글 삭제 권한이 없습니다.");
            }
        },
        removeReply() {
            let reply = {
                id: this.id,
                boardId: this.boardId,
            };
            this.delReply(reply);
            this.$emit("changeReplys");
        },

        //MODAL
        toggleModal(event) {
            event.preventDefault();
            this.removeModal();
        },
        removeModal() {
            this.showModal = !this.showModal;
        },
    }
}
</script>

<style scoped>
.reply_box {
    padding: 15px;
    position: relative;
    display: flex;
    justify-content: space-between;
}

.reply_contents {
    display: flex;
    flex-direction: column;
}

.options {
    display: flex;
    align-items: center;
}

.author_info {
    display: flex;
    font-size: 13px;
}

.author {
    margin-right: 10px;
    width: 100px;
}

.fa-times {
    margin-left: 150px;
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

.modal_footer {
    background-color: var(--main-color);
    border-radius: 5px;
    outline: none;
    border: none;
    color: var(--white-color);
    padding: 5px 15px;
}

.btn:hover {
    color: var(--main-color);
}
</style>
