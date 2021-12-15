<template>
<div id="list" class="list">
    <div class="title">Board</div>
    <div class="container">
        <div class="top_bar">
            <div class="search">
                <b-form-select v-model="selected" :options="options" class="select_form">
                </b-form-select>
                <input type="text" v-model="keyword" class="keyword" placeholder="검색어..">
                <button class="search_btn" @click="searchByConditions"><i class="fas fa-search"></i></button>
            </div>
            <div class="create_delete">
                <button class="create option" @click="goWrite"><i class="fas fa-plus"></i>새로운 글 작성</button>
            </div>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr class="thead">
                        <th>번호</th>
                        <th class="table_title">제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody class="tbody" id="my-table" :per-page="perPage" :current-page="currentPage">
                    <board-list-row v-for="(article, index) in itemsForList" :key="index" v-bind="article" class="boardItem" />
                </tbody>
            </table>
            <b-pagination v-model="currentPage" :per-page="perPage" :total-rows="rows" align="center" class="pagination"></b-pagination>
        </div>
    </div>
</div>
</template>

<script>
import BoardListRow from './child/BoardListRow';
import {
    mapState,
    mapActions
} from "vuex";
export default {
    components: {
        BoardListRow,
    },
    data() {
        return {
            selected: null,
            keyword: "",
            currentPage: 1,
            perPage: 15,
            options: [{
                    value: null,
                    text: '-- 검색 조건을 선택해주세요 --'
                },
                {
                    value: 'author',
                    text: '작성자'
                },
                {
                    value: 'title',
                    text: '제목'
                },
                {
                    value: 'content',
                    text: '내용'
                }
            ]
        };
    },
    computed: {
        ...mapState("boardStore", ["articles"]),
        rows() {
            return this.articles.length;
        },
        itemsForList() {
            return this.articles.slice(
                (this.currentPage - 1) * this.perPage,
                this.currentPage * this.perPage,
            );
        },
    },
    methods: {
        ...mapActions("boardStore", ["getAllArticles"]),
        goWrite() {
            this.$router.push({
                name: 'BoardWrite'
            });
        },
        searchByConditions() {
            console.log(this.selected, this.keyword);
            let options = {
                key: this.selected,
                word: this.keyword,
            };
            this.getAllArticles(options);
        },
    },
    created() {
        let options = {
            key: this.selected,
            word: this.keyword,
        };
        this.getAllArticles(options);
    },

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
    height: 62vh;
    position: relative;
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
    width: 50%;
}

.keyword {
    margin: 0 5px;
    border-radius: 5px;
    outline: none;
    border: 1px solid var(--font-color);
    padding: 0 10px;
}
.pagination{
    position: absolute;
    bottom: 0;
    left: 43%;
}
</style>
