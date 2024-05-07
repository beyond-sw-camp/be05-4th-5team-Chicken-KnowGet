<template>
  <div class="qna-list">
    <h1>너겟에게 물어봐</h1>
    <input type="text" v-model="search" placeholder="질문 찾기">
    <div class="post-container" v-for="post in filteredPosts" :key="post.id">
      <div class="post-left">
        <!-- 여기에 클릭 이벤트를 추가합니다 -->
        <h3 @click="navigateToPost(post.id)" class="post-title">{{ post.title }}</h3>
        <p>작성자: {{ post.user.name || '익명' }}</p>
      </div>
      <div class="post-right">
        <p>작성 시간: {{ formatDateTime(post.createdAt) }}</p>
        <p>조회 수: {{ post.views }}</p>
        <p>답변 수: {{ post.comments?.length || 0 }}</p>
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
    async navigateToPost(postId) {
      console.log('Post ID:', postId);  // 디버깅을 위해 postId 출력
      try {
        await axios.post(`/qna/increase-views/${postId}`);
        this.$router.push(`/qna/${postId}`);
      } catch (error) {
        console.error('Error navigating to post:', error);
      }
    }
    ,
    async fetchPosts() {
      try {
        const response = await axios.get('/qna/findall');
        this.posts = response.data;
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
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
