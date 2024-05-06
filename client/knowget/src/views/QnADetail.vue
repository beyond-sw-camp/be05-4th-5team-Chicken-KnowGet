<template>
    <div class="qna-detail">
      <h1>{{ post.title }}</h1>
      <p>Written by {{ post.author }} on {{ post.date }}</p>
      <p>Views: {{ post.views }}</p>
      <div v-html="post.content"></div>
      <button @click="editPost(post.id)">Edit</button>
      <button @click="deletePost(post.id)">Delete</button>
      <button @click="$router.push('/qna')">Back to List</button>
      <h3>Comments</h3>
      <div v-for="comment in post.comments" :key="comment.id">
        <p>{{ comment.text }} - {{ comment.author }} on {{ comment.date }}</p>
        <button v-if="comment.author === $store.state.user.userId" @click="deleteComment(comment.id)">Delete</button>
      </div>
      <form @submit.prevent="addComment">
        <textarea v-model="newComment"></textarea>
        <button type="submit">Add Comment</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        post: null,
        newComment: ''
      }
    },
    methods: {
      fetchPost() {
        // 게시글 ID로 게시글 정보 가져오기
        const postId = this.$route.params.id;
        this.post = this.$store.state.posts.find(post => post.id === postId);
      },
      editPost(postId) {
        this.$router.push(`/edit/${postId}`);
      },
      deletePost(postId) {
        // 게시글 삭제 로직
        this.$store.commit('deletePost', postId);
        this.$router.push('/qna');
      },
      addComment() {
        const comment = {
          text: this.newComment,
          author: this.$store.state.user.userId,
          date: new Date().toISOString().substring(0, 10)
        };
        // 댓글 추가 로직
        this.post.comments.push(comment);
        this.newComment = '';
      },
      deleteComment(commentId) {
        // 댓글 삭제 로직
        const index = this.post.comments.findIndex(c => c.id === commentId);
        this.post.comments.splice(index, 1);
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
  