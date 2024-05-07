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
      // 백엔드에서 게시글 ID로 정보를 가져옵니다.
      const postId = this.$route.params.id;
      try {
        const response = await axios.get(`/qna/find/${postId}`);
        this.post = response.data;
      } catch (error) {
        console.error('Error fetching post:', error);
      }
    },
    async editPost(postId) {
      this.$router.push(`/edit/${postId}`);
    },
    async deletePost(postId) {
      try {
        await axios.delete(`/qna/delete/${postId}`);
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
