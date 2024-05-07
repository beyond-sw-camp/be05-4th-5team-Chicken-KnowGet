<template>
  <div class="qna-detail" v-if="post">
    <div class="post-header">
      <h1 class="post-title">{{ post.title }}</h1>
      <p class="post-info">작성자: {{ post.user.id || '익명' }}</p>
      <p class="post-info">작성 시간: {{ formatDateTime(post.writtenTime) }}</p>
    </div>
    <div class="post-content">
      <p>{{ post.content }}</p>
    </div>
    <div class="comment-section">
      <h2>댓글</h2>
      <ul class="comment-list">
        <li v-for="comment in comments" :key="comment.idx" class="comment-item">
          <p class="comment-content">{{ comment.content }}</p>
          <p class="comment-info">작성자: {{ comment.user.id || '익명' }}</p>
          <p class="comment-info">작성 시간: {{ formatDateTime(comment.writtenTime) }}</p>
        </li>
      </ul>
      <form @submit.prevent="submitComment" class="comment-form">
        <textarea v-model="newComment" placeholder="댓글을 입력하세요"></textarea>
        <button type="submit">댓글 작성</button>
      </form>
    </div>
  </div>
  <div v-else>
    <p>Loading post...</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      post: null,
      comments: [],
      newComment: ''
    };
  },
  methods: {
    fetchPost() {
      const postIdx = this.$route.params.postIdx;
      axios.get(`/qna/${postIdx}`)
          .then(response => {
            this.post = response.data;
          })
          .catch(error => {
            console.error('Error fetching post:', error);
          });
      axios.post(`/qna/${postIdx}/comments`)
          .then(response => {
            this.comments = response.data;
          })
          .catch(error => {
            console.error('Error fetching comments:', error);
          });
    },
    submitComment() {
      const postIdx = this.$route.params.postIdx;
      axios.post(`/comment/create`, { content: this.newComment , postIdx : postIdx })
          .then(response => {
            this.comments.push(response.data);
            this.newComment = ''; // Clear the input field after submitting comment
          })
          .catch(error => {
            console.error('Error submitting comment:', error);
          });
    },
    formatDateTime(dateTime) {
      if (!dateTime) return '날짜 정보 없음';
      const date = new Date(dateTime);
      return date.toLocaleString();
    }
  },
  created() {
    this.fetchPost();
  }
}
</script>

<style>
.qna-detail {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.post-header {
  text-align: center;
}

.post-title {
  font-size: 24px;
  margin-bottom: 10px;
}

.post-info {
  font-size: 14px;
  color: gray;
}

.post-content {
  margin-top: 20px;
  width: 80%;
  border-top: 1px solid #ccc;
  padding-top: 20px;
}

.comment-section {
  margin-top: 20px;
  width: 80%;
}

.comment-section h2 {
  font-size: 20px;
  margin-bottom: 10px;
}

.comment-list {
  list-style: none;
  padding: 0;
}

.comment-item {
  margin-bottom: 20px;
}

.comment-content {
  font-size: 16px;
}

.comment-info {
  font-size: 14px;
  color: gray;
}

.comment-form {
  margin-top: 20px;
}

.comment-form textarea {
  width: 100%;
  height: 100px;
  margin-bottom: 10px;
}

.comment-form button {
  padding: 8px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

.comment-form button:hover {
  background-color: #0056b3;
}
</style>
