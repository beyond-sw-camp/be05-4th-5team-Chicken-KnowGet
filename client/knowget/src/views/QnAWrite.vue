<template>
  <div class="qna-write">
    <h1>{{ isEdit ? 'Edit Post' : '무엇이든 물어보세요' }}</h1>
    <form @submit.prevent="submitPost">
      <input type="text" v-model="title" placeholder="제목" required>
      <textarea v-model="content" placeholder="내용" required></textarea>
      <button type="submit">{{ isEdit ? 'Update' : '질문등록' }}</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      title: '',
      content: '',
      isEdit: false
    };
  },
  methods: {
    async submitPost() {
      const now = new Date(); // 현재 시간 생성
      const post = {
        title: this.title,
        content: this.content,
        createdAt: now.toISOString() // ISO 문자열로 작성 시간 설정
      };
      try {
        const url = this.isEdit ? `/qna/update/${this.$route.params.id}` : '/qna/save';
        const method = this.isEdit ? 'patch' : 'post';

        const response = await axios({
          method: method,
          url: url,
          data: post
        });

        console.log('Response:', response.data);
        this.$router.push('/qna');
      } catch (error) {
        console.error('Error submitting post:', error);
        alert('Error submitting post: ' + error.message);
      }
    }
  },
  created() {
    if (this.$route.params.id) {
      this.isEdit = true;
      this.fetchPost(this.$route.params.id);
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
