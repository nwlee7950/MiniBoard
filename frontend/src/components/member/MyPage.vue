<template>
<div class="mypage">
    <div class="title">My Page</div>
    <div class="container">
        <span>이름</span>
        <span></span>
        <span>등급</span>
        <span></span>
        <span>아이디</span>
        <span></span>
        <span>이메일</span>
        <span></span>
    </div>
</div>
</template>

<script>
export default {

}
</script>

<style scoped>
.mypage {
    padding-top: 100px;
    display: flex;
    flex-direction: column;
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
    height: 60vh;
}
</style>
