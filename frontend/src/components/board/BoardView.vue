<template>
<div id="view" class="view">
    <div class="title">Detail</div>
    <div class="container">
        <form @submit="onSubmit">
            <div class="form">
                <label for="author">작성자</label>
                <input type="text" name="author" v-model="article.userId">
                <label for="title">제목</label>
                <input type="text" name="title" v-model="article.title">
                <label for="content">내용</label>
                <textarea name="content" class="content_box" v-model="article.content"></textarea>
            </div>
            <div class="btn_box">
                <button @click="goModify" class="modify_btn">Modify</button>
                <button type="submit" class="modify_btn">Delete</button>
                <button @click="goList">Back</button>
            </div>
        </form>
    </div>
    <div class="comments">
        <h3 class="comment_title">New Comment</h3>
        <div class="type">
            <div class="user">
                <div class="icon"></div>
                <label for="author" class="author">{{curUser}}</label>
            </div>
            <form class="input_area" @submit="createComment">
                <input type="text" class="type_input" placeholder="comment..." v-model="newComment">
                <button type="submit">Upload</button>
            </form>
        </div>
        <div class="comments_list">
            <h3>Comments</h3>
            <div id="my-table" :per-page="perPage" :current-page="currentPage">
                <board-comment v-for="(comment, index) in itemsForList" :key="index" v-bind="comment" class="commentItem" />
            </div>
        </div>
        <b-pagination class="page" v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="my-table">
        </b-pagination>
    </div>
</div>
</template>

<script>
import BoardComment from "./child/BoardComment.vue";
import {
    getArticle,
    deleteArticle,
} from "@/api/board";
import {
    getComment,
    writeComment,
} from "@/api/comment.js";
export default {
    data() {
        return {
            article: {},
            comments: [],
            currentPage: 1,
            perPage: 6,
            newComment: "",
            curUser: "jisu",
        };
    },
    components: {
        BoardComment,
    },
    computed: {
        rows() {
            return this.comments.length;
        },
        itemsForList() {
            return this.comments.slice(
                (this.currentPage - 1) * this.perPage,
                this.currentPage * this.perPage,
            );
        },
    },
    methods: {
        goModify() {
            this.$router.push({
                name: 'BoardModify',
                params: {
                    articleno: this.article.id,
                }
            });
        },
        goList() {
            this.$router.push({
                name: 'Board'
            });
        },
        onSubmit(event) {
            event.preventDefault();
            //현재 접속 중인 유저가 글 작성자라면
            this.removeArticle();
        },
        removeArticle() {
            deleteArticle(
                this.$route.params.articleno,
                () => {
                    let msg = "삭제가 완료되었습니다.";
                    alert(msg);
                    this.moveList();
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        moveList() {
            this.$router.push({
                name: "Board"
            });
        },
        createComment(event){
            event.preventDefault();
            this.registComment();
        },
        registComment() {
            writeComment({
                    userId: this.curUser,
                    boardId: this.article.id,
                    content: this.newComment,
                },
                () => {
                    let msg = "댓글 등록이 완료되었습니다.";
                    alert(msg);
                },
                (error) => {
                    console.log(error);
                }
            );
            this.newComment = "";
        },
    },
    created() {
        getArticle(
            this.$route.params.articleno,
            (response) => {
                this.article = response.data;
            },
            (error) => {
                console.log("에러발생!!", error);
            }
        );
        getComment(
            this.$route.params.articleno,
            (response) => {
                this.comments = response.data;
            },
            (error) => {
                console.log("에러발생!!", error);
            }
        )
    },

}
</script>

<style scoped>
.view {
    padding-top: 100px;
}

.title {
    font-size: 70px;
    width: 100%;
    margin: 60px 0;
    text-decoration: underline;
    text-align: center;
}

.container {
    background-color: var(--white-color);
    padding: 50px;
    border-radius: 5px;
    box-shadow: 2px 2px 10px var(--shadow-color);
    margin-bottom: 30px;
}

.form {
    padding: 0 200px;
    display: flex;
    flex-direction: column;
}

label {
    font-size: 20px;
}

input,
textarea {
    padding: 5px 10px;
    margin-bottom: 30px;
}

input:focus,
textarea:focus {
    outline: none;
}

.content_box {
    height: 200px;
}

.btn_box {
    display: flex;
    justify-content: flex-end;
    padding-right: 200px;
}

button {
    border: none;
    background-color: var(--main-color);
    border-radius: 5px;
    color: var(--white-color);
    padding: 5px 15px;
}

.modify_btn {
    margin-right: 5px;
}

.comments {
    padding: 100px 220px 50px 250px;
}

h3 {
    margin-bottom: 30px;
}

.type {
    display: flex;
    flex-direction: column;
}

.user {
    display: flex;
    align-items: center;
}

.icon {
    width: 30px;
    height: 30px;
    margin-right: 10px;
    background-color: var(--main-color);
    border-radius: 50%;
    color: var(--white-color);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 17px;
}

.author {
    font-size: 25px;
}

.type_input {
    width: 90%;
    border: none;
    border-bottom: 1px solid black;
    background: none;
    margin-right: 10px;
}
</style>
