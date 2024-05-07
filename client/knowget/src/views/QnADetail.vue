<template>
  <div class="qna-detail" v-if="post">
    <h1>{{ post.title }}</h1>
    <p>{{ post.content }}</p>
    <p>작성자: {{ post.user.name || '익명' }}</p>
    <p>작성 시간: {{ formatDateTime(post.createdAt) }}</p>
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
    };
  },
  methods: {
    fetchPost() {
      const postIdx = this.$route.params.postIdx;
      axios.post(`/qna/${postIdx}`)
          .then(response => {
            this.post = response.data;
          })
          .catch(error => {
            console.error('Error fetching post:', error);
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
  flexDirection: column;
  alignItems: center;
  marginTop: 20px;
}
</style>
