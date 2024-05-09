<template>
  <div class="qna-list">
    <h1>너겟에게 물어봐</h1>
    <input type="text" v-model="search" placeholder="질문 찾기">
    <div class="post-container" v-for="post in filteredPosts" :key="post.idx">
      <div class="post-left">
        <h3 @click="navigateToPost(post.idx)" class="post-title">{{ post.title }}</h3>
        <p>작성자: {{ post.user.name || '익명' }}</p>
      </div>
      <div class="post-right">
        <p>작성 시간: {{ formatDateTime(post.writtenTime) }}</p>
      </div>
    </div>
    <div class="pagination">
      <button @click="setPage(p)" v-for="p in totalPages" :key="p" class="page-button">{{ p }}</button>
    </div>
    <button @click="$router.push('/write')">질문하러 가기</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      search: '',
      posts: [],
      currentPage: 1,
      postsPerPage: 10
    };
  },
  computed: {
    filteredPosts() {
      let start = (this.currentPage - 1) * this.postsPerPage;
      let end = start + this.postsPerPage;
      return this.posts.filter(post => post.title.includes(this.search)).slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.posts.length / this.postsPerPage);
    }
  },
  methods: {
    setPage(page) {
      this.currentPage = page;
    },
    navigateToPost(postIdx) {
      this.$router.push(`/qna/${postIdx}`);
    },
    fetchPosts() {
      axios.get('/qna/all')
          .then(response => {
            this.posts = response.data;
          })
          .catch(error => {
            console.error('Error fetching posts:', error);
          });
    },
    formatDateTime(dateTime) {
      if (!dateTime) return '날짜 정보 없음';
      const date = new Date(dateTime);
      return date.toLocaleString();
    }
  },
  created() {
    this.fetchPosts();
  }
}
</script>

<style>
.qna-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}
.post-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 90%;
  border-bottom: 1px solid #ccc;
  padding: 10px;
  margin: 5px;
}
.post-left {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.post-right {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
.post-title {
  cursor: pointer;
  color: blue;
}
.pagination {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
.page-button {
  border: none;
  background-color: white;
  color: black;
  cursor: pointer;
  padding: 5px 10px;
  margin: 0 5px;
  border-radius: 5px;
  transition: background-color 0.3s, color 0.3s;
}
.page-button:hover {
  background-color: #000000;
  color: white;
}
button {
  margin-top: 5px;
}
</style>
