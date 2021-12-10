<template>
<div id="list" class="list">
    <div class="title">Board</div>
    <div class="container">
        <div class="top_bar">
            <div class="search">
                <b-form-select v-model="selected" class="select_form">
                    <b-form-select-option :value="null" disabled>-- 검색 조건을 선택해주세요 -- </b-form-select-option>
                    <b-form-select-option :value="title">제목</b-form-select-option>
                    <b-form-select-option :value="content">내용</b-form-select-option>
                    <b-form-select-option :value="author">작성자</b-form-select-option>
                </b-form-select>
                <button class="search_btn"><i class="fas fa-search"></i></button>
            </div>
            <div class="create_delete">
                <button class="create option" @click="goWrite"><i class="fas fa-plus"></i>새로운 글 작성</button>
                <button class="delete option"><i class="fas fa-trash"></i>선택 글 삭제</button>
            </div>
        </div>
        <div class="table">
            <table>
                <tr class="thead">
                    <th>삭제</th>
                    <th>번호</th>
                    <th class="table_title">제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
                <board-list-row></board-list-row>
                <!-- <tbody class="tbody" id="my-table" :per-page="perPage" :current-page="currentPage">
                    <board-list-row v-for="(article, index) in itemsForList" :key="index" v-bind="article" class="boardItem" />
                </tbody> -->
            </table>
        </div>
        <div class="paging">
            <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" aria-controls="table"></b-pagination>
        </div>
    </div>
</div>
</template>

<script>
import BoardListRow from './child/BoardListRow';
export default {
    components: {
        BoardListRow,
    },
    data() {
        return {
            selected: null,
            articles: [],
            currentPage: 1,
            perPage: 5,
        };
    },
    computed: {
        rows() {
            return this.articles.length;
        },
        itemsForList() {
            return this.articles.slice(
                (this.currentPage - 1) * this.perPage,
                this.currentPage * this.perPage,
            );
        }
    },
    methods: {
        goWrite() {
            this.$router.push({
                name: 'BoardWrite'
            });
        }
    }
}
</script>

<style scoped>
.list {
    padding-top: 100px;
    display: flex;
    flex-direction: column;
    width: 100%;
}

.title {
    font-size: 70px;
    margin: 60px 0;
    text-decoration: underline;
    text-align: center;
}

.container {
    padding: 30px;
    border-radius: 5px;
    box-shadow: 2px 2px 10px var(--shadow-color);
    background-color: var(--white-color);
    height: 60vh;
}

.top_bar {
    display: flex;
    justify-content: space-between;
}

.select_form {
    border-radius: 5px;
    padding: 5px 15px;
}

.select_form:focus {
    outline: none;
}

.search {
    display: flex;
}

.search_btn {
    margin-left: 5px;
    border: none;
    background-color: var(--main-color);
    color: var(--white-color);
    padding: 3px 10px;
    border-radius: 5px;
}

.create_delete {
    display: flex;
    justify-content: center;
    align-items: center;
}

.create {
    margin-right: 10px;
}

.option {
    border: 1px solid var(--main-color);
    background-color: var(--main-color);
    font-size: 15px;
    color: var(--white-color);
    border-radius: 5px;
    padding: 5px 15px;
    cursor: pointer;
    width: 150px;
}

.option>i {
    margin-right: 5px;
}

.table {
    margin-top: 40px;
}

table {
    width: 100%;
    text-align: center;
    border-spacing: 0 10px;
}

.thead {
    font-size: 20px;
    font-weight: 700;
    color: var(--main-color);
}

.table_title {
    width: 40%;
}

.paging {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
