<template>
<div class="comment_box">
    <h5 class="content">Hello everyone!!</h5>
    <div class="author_info">
        <div class="author">by Sue</div>
        <div class="authored_time">2021. 12. 10</div>
    </div>
    <div class="options">
        <i class="fas fa-reply reply"></i>
        <i class="fas fa-pen mm"></i>
        <i class="fas fa-trash-alt mm"></i>
    </div>
</div>
</template>

<script>
export default {

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
}

.options {
    position: absolute;
    right: 15px;
    top: 30px;
    font-size: 20px;
}

.options>i:hover {
    color: var(--main-color);
    cursor: pointer;
}
.mm{
    margin-left: 15px;
}
</style>
