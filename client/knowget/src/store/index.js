import { createStore } from 'vuex'

export default createStore({
  state: {
    isAuthenticated: false,
    user: null,
    posts: [],
    currentPage: 1,
    postsPerPage: 10
  },
  mutations: {
    authenticate(state, user) {
      state.isAuthenticated = true;
      state.user = user;
    },
    logout(state) {
      state.isAuthenticated = false;
      state.user = null;
    },
    setPosts(state, posts) {
      state.posts = posts;
    },
    addPost(state, post) {
      state.posts.push(post);
    },
    updatePost(state, updatedPost) {
      const index = state.posts.findIndex(post => post.id === updatedPost.id);
      if (index !== -1) {
        state.posts.splice(index, 1, updatedPost);
      }
    },
    deletePost(state, postId) {
      state.posts = state.posts.filter(post => post.id !== postId);
    },
    addComment(state, { postId, comment }) {
      const post = state.posts.find(post => post.id === postId);
      if (post) {
        post.comments.push(comment);
      }
    },
    deleteComment(state, { postId, commentId }) {
      const post = state.posts.find(post => post.id === postId);
      if (post) {
        const index = post.comments.findIndex(comment => comment.id === commentId);
        if (index !== -1) {
          post.comments.splice(index, 1);
        }
      }
    },
    setPage(state, page) {
      state.currentPage = page;
    }
  },
  actions: {
    fetchPosts({ commit }) {
      // 여기에서 API를 통해 게시글을 가져오는 로직을 구현할 수 있습니다.
      // 예시 데이터 로딩
      commit('setPosts', [
        // 게시글 예시 데이터
      ]);
    },
    submitPost({ commit }, post) {
      // API로 게시글을 추가하는 로직
      commit('addPost', post);
    },
    updatePostDetails({ commit }, post) {
      // API로 게시글을 업데이트하는 로직
      commit('updatePost', post);
    },
    removePost({ commit }, postId) {
      // API로 게시글을 삭제하는 로직
      commit('deletePost', postId);
    },
    addNewComment({ commit }, { postId, comment }) {
      // API로 댓글을 추가하는 로직
      commit('addComment', { postId, comment });
    },
    removeComment({ commit }, { postId, commentId }) {
      // API로 댓글을 삭제하는 로직
      commit('deleteComment', { postId, commentId });
    }
  },
  getters: {
    paginatedPosts: state => {
      const start = (state.currentPage - 1) * state.postsPerPage;
      const end = start + state.postsPerPage;
      return state.posts.slice(start, end);
    }
  }
})
