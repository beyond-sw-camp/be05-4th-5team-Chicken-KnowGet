<template>
  <div class="qna-detail" v-if="post">
    <h1>{{ post.title }}</h1>
    <p>{{ post.content }}</p>
    <p>작성자: {{ post.user.name || '익명' }}</p>
    <p>작성 시간: {{ formatDateTime(post.createdAt) }}</p>
    <div class="actions">
      <button @click="editPost(post.id)">수정</button>
      <button @click="deletePost(post.id)">삭제</button>
    </div>
    <div class="comments">
      <h3>댓글</h3>
      <div v-for="comment in post.comments" :key="comment.id">
        <p>{{ comment.text }}</p>
        <button @click="deleteComment(comment.id)">댓글 삭제</button>
      </div>
      <textarea v-model="newComment" placeholder="댓글 추가..."></textarea>
      <button @click="addComment">댓글 달기</button>
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
      newComment: ''
    };
  },
  methods: {
    async fetchPost() {
      const postIdx = this.$route.params.id;
      try {
        const response = await axios.post(`/qna/find/${postIdx}`);
        this.post = response.data;
      } catch (error) {
        console.error('Error fetching post:', error);
      }
    },
    async editPost(postIdx) {
      this.$router.push(`/edit/${postIdx}`);
    },
    async deletePost(postIdx) {
      try {
        await axios.delete(`/qna/delete/${postIdx}`);
        this.$router.push('/qna');
      } catch (error) {
        console.error('Error deleting post:', error);
      }
    },
    async addComment() {
      // 백엔드에 댓글을 추가하는 로직
      try {
        await axios.post(`/comments/add`, {
          postId: this.post.id,
          text: this.newComment,
          author: this.$store.state.user.userId
        });
        this.newComment = '';
        this.fetchPost();  // 댓글 추가 후 게시글 새로고침
      } catch (error) {
        console.error('Error adding comment:', error);
      }
    },
    async deleteComment(commentId) {
      try {
        await axios.delete(`/comments/delete/${commentId}`);
        this.fetchPost();  // 댓글 삭제 후 게시글 새로고침
      } catch (error) {
        console.error('Error deleting comment:', error);
      }
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
  textarea, button {
    margin: 10px;
  }
  </style>
