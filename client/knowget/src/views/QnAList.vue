<template>
  <div class="qna-list">
    <h1>너겟에게 물어봐</h1>
    <input type="text" v-model="search" placeholder="질문 찾기">
    <div class="post-container" v-for="post in filteredPosts" :key="post.id">
      <div class="post-left">
        <h3 @click="navigateToPost(post.id)" class="post-title">{{ post.title }}</h3>
        <p>작성자 : {{ post.author }}</p>
      </div>
      <div class="post-right">
        <p>{{ post.date }}</p>
        <p>조회 수: {{ post.views }}</p>
        <p>답변 수: {{ post.comments.length }}</p>
      </div>
    </div>
    <div class="pagination">
      <button @click="setPage(p)" v-for="p in totalPages" :key="p" class="page-button">{{ p }}</button>
    </div>
    <button @click="$router.push('/write')">질문하러 가기</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: '',
      posts: [
      { id: 1, title: "너겟 파는 곳인가요?", author: "작성자 A", date: "2024-05-01", views: 100, comments: [] },
        { id: 2, title: "질문있어요", author: "작성자 B", date: "2024-05-02", views: 150, comments: [{}, {}, {}] },
        { id: 3, title: "담당자 나와", author: "작성자 C", date: "2024-05-03", views: 200, comments: [{}] },
        { id: 4, title: "삶이 힘들어요", author: "작성자 D", date: "2024-05-04", views: 250, comments: [{}, {}] },
        { id: 5, title: "이럴 땐 어떡하죠", author: "작성자 E", date: "2024-05-05", views: 300, comments: [] },
        { id: 6, title: "답변 빠르게", author: "작성자 F", date: "2024-05-06", views: 350, comments: [{}, {}, {}, {}] },
        { id: 7, title: "언제 답변해주시나요", author: "작성자 G", date: "2024-05-07", views: 400, comments: [] },
        { id: 8, title: "배송 관련", author: "작성자 H", date: "2024-05-08", views: 450, comments: [{}] },
        { id: 9, title: "문의 드립니다.", author: "작성자 I", date: "2024-05-09", views: 500, comments: [{}, {}] },
        { id: 10, title: "너겟 최고", author: "작성자 J", date: "2024-05-10", views: 550, comments: [] },
        { id: 11, title: "치킨 너겟", author: "작성자 K", date: "2024-05-11", views: 600, comments: [{}, {}, {}] },
        { id: 12, title: "이름 누가 지었냐", author: "작성자 L", date: "2024-05-12", views: 650, comments: [] },
        { id: 13, title: "크크 내 안의 흑염룡", author: "작성자 M", date: "2024-05-13", views: 700, comments: [{}] },
        { id: 14, title: "물건을 구입했는데요", author: "작성자 N", date: "2024-05-14", views: 750, comments: [{}, {}] },
        { id: 15, title: "저 좀 도와주세요", author: "작성자 O", date: "2024-05-15", views: 800, comments: [] }
      ],
      currentPage: 1,
      postsPerPage: 10
    }
  },
  computed: {
    filteredPosts() {
      let start = (this.currentPage - 1) * this.postsPerPage;
      let end = start + this.postsPerPage;
      return this.posts.filter(post => post.title.includes(this.search)).slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.posts.filter(post => post.title.includes(this.search)).length / this.postsPerPage);
    }
  },
  methods: {
    setPage(page) {
      this.currentPage = page;
    },
    navigateToPost(postId) {
      this.$router.push(`/qna/${postId}`);
    }
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
