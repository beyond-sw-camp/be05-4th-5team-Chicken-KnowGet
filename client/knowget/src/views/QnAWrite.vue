<template>
    <div class="qna-write">
      <h1>{{ isEdit ? 'Edit Post' : '무엇이든 물어보세요' }}</h1>
      <form @submit.prevent="submitPost">
        <input type="text" v-model="title" placeholder="제목" required>
        <br>
        <textarea v-model="content" placeholder="내용" required></textarea>
        <br>
        <button type="submit">{{ isEdit ? 'Update' : '질문등록' }}</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        title: '',
        content: '',
        isEdit: false
      }
    },
    methods: {
      submitPost() {
        const post = {
          title: this.title,
          content: this.content,
          author: this.$store.state.user.userId,
          date: new Date().toISOString().substring(0, 10),
          views: 0,
          comments: []
        };
        if (this.isEdit) {
          // 게시글 업데이트 로직
          this.$store.commit('updatePost', post);
        } else {
          // 새 게시글 추가 로직
          this.$store.commit('addPost', post);
        }
        this.$router.push('/qna');
      }
    },
    created() {
      if (this.$route.params.id) {
        this.isEdit = true;
        const post = this.$store.state.posts.find(p => p.id === this.$route.params.id);
        this.title = post.title;
        this.content = post.content;
      }
    }
  }
  </script>
  
  <style>
  .qna-write {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
  }
  input, textarea, button {
    margin: 10px;
    width: 300px;
  }
  textarea {
    height: 150px;
  }
  </style>
  