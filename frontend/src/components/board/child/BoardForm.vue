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
    writeArticle,
    getArticle,
    // modifyArticle
} from "@/api/board";
export default {
    data() {
        return {
            article: {
                userId: "",
                title: "",
                content: "",
                notice: false,
            }
        }
    },
    methods: {
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
        registArticle() {
            writeArticle({
                    userId: this.article.userId,
                    title: this.article.title,
                    content: this.article.content,
                    notice: this.article.notice,
                },
                () => {
                    let msg = "등록이 완료되었습니다.";
                    alert(msg);
                    this.moveList();
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        // updateArticle() {
        //     modifyArticle({
        //             userId: this.article.userId,
        //             title: this.article.title,
        //             content: this.article.content,
        //             notice: this.article.notice,
        //         },
        //         ({
        //             data
        //         }) => {
        //             let msg = "수정 처리시 문제가 발생했습니다.";
        //             if (data === "success") {
        //                 msg = "수정이 완료되었습니다.";
        //             }
        //             alert(msg);
        //             this.$router.push({
        //                 name: "Board"
        //             });
        //         },
        //         (error) => {
        //             console.log(error);
        //         }
        //     );
        // },
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
        if (this.type === "modify") {
            getArticle(
                this.$route.params.articleno,
                (response) => {
                    this.article.userId = response.data.userId;
                    this.article.title = response.data.title;
                    this.article.content = response.data.content;
                    this.article.notice = response.data.notice;
                },
                (error) => {
                    console.log("에러발생!!", error);
                }
            );
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
