<template>
<div id="form" class="container">
    <form @submit="onSubmit" @reset="onReset">
        <div class="form">
            <label for="userId">작성자</label>
            <input type="text" name="userId" v-model="article.userId" ref="userId">
            <label for="title">제목</label>
            <input type="text" name="title" v-model="article.title" ref="title">
            <label for="content">내용</label>
            <textarea name="content" class="content_box" v-model="article.content" ref="content"></textarea>
        </div>
        <div class="btn_box">
            <label for="notice">공지</label>
            <input type="checkbox" name="notice" v-model="article.notice">
            <button type="submit" class="submit_btn" v-if="this.type === 'register'">Register</button>
            <button type="submit" class="submit_btn" v-else>Update</button>
            <button type="reset" @click="moveList">Back</button>
        </div>
    </form>
</div>
</template>

<script>
import {
    mapState, mapActions
} from "vuex";
export default {
    computed:{
        ...mapState("boardStore", ["article"]),
    },
    methods: {
        ...mapActions("boardStore", ["resetArticle", "getOneArticle", "addArticle", "modArticle"]),
        onSubmit(event) {
            event.preventDefault();

            let err = true;
            let msg = "";
            !this.article.userId &&
                ((msg = "작성자 입력해주세요"),
                    (err = false),
                    this.$refs.userId.focus());
            err &&
                !this.article.title &&
                ((msg = "제목 입력해주세요"),
                    (err = false),
                    this.$refs.title.focus());
            err &&
                !this.article.content &&
                ((msg = "내용 입력해주세요"),
                    (err = false),
                    this.$refs.content.focus());

            if (!err) alert(msg);
            else
                this.type === "register" ? this.registArticle() : this.updateArticle();
        },
        onReset(event) {
            event.preventDefault();
            this.article.userId = "";
            this.article.subject = "";
            this.article.content = "";
            this.article.notice = false;
            this.$router.push({
                name: "Board"
            });
        },

        //CREATE_ARTICLE
        registArticle() {
            let article = {
                userId: this.article.userId,
                title: this.article.title,
                content: this.article.content,
                notice: this.article.notice,
            }
            this.addArticle(article);
            this.moveList();
        },
        updateArticle() {
            let article = {
                userId: this.article.userId,
                title: this.article.title,
                content: this.article.content,
                notice: this.article.notice,
                id: this.article.id,
            };
            this.modArticle(article);
            this.moveList();
        },
        moveList() {
            this.$router.push({
                name: "Board"
            });
        },
    },
    props: {
        type: {
            type: String
        },
    },
    created() {
        this.resetArticle();
        if (this.type === "modify") {
            this.getOneArticle(this.$route.params.articleno);
        }
    },

}
</script>

<style scoped>
.container {
    background-color: var(--white-color);
    padding: 50px;
    border-radius: 5px;
    box-shadow: 2px 2px 10px var(--shadow-color);
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

.btn_box>button {
    border: none;
    background-color: var(--main-color);
    border-radius: 5px;
    color: var(--white-color);
    padding: 5px 15px;
}

.submit_btn {
    margin-right: 5px;
}
</style>
