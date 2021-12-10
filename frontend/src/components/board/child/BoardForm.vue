<template>
<div id="form" class="container">
    <form class="form">
        <label for="author">작성자</label>
        <input type="text" name="author">
        <label for="title">제목</label>
        <input type="text" name="title">
        <label for="content">내용</label>
        <textarea name="content" class="content_box"></textarea>
    </form>
    <div class="btn_box">
        <button @click="submitAndGoList" class="submit_btn">submit</button>
        <button @click="goList">back</button>
    </div>
</div>
</template>

<script>
export default {
methods:{
    submitAndGoList(){
        // 새글 저장 
        this.$router.push({name:'Board'});
    },
    goList(){
        this.$router.push({name:'Board'});
    }
}
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
.btn_box{
    display: flex;
    justify-content: flex-end;
    padding-right: 200px;
}
.btn_box>button{
    border: none;
    background-color: var(--main-color);
    border-radius: 5px;
    color: var(--white-color);
    padding: 5px 15px;
}
.submit_btn{
    margin-right: 5px;
}
</style>
