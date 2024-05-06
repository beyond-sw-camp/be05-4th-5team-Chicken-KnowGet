import { createStore } from 'vuex';
import axios from 'axios';

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
    login({ commit }, credentials) {
      return axios.post('/user/signin', credentials)
        .then(({ data }) => {
          commit('authenticate', data);
          return data;
        });
    },
    logout({ commit }) {
      commit('logout');
    },
    signUp({ commit }, userData) {
      return axios.post('/user/signup', userData)
        .then(response => {
          commit('authenticate', response.data);
          return response.data;
        });
    },
    fetchPosts({ commit }) {
      axios.get('/posts')
        .then(response => {
          commit('setPosts', response.data);
        });
    },
    submitPost({ commit }, post) {
      axios.post('/posts', post)
        .then(response => {
          commit('addPost', response.data);
        });
    },
    updatePostDetails({ commit }, post) {
      axios.put(`/posts/${post.id}`, post)
        .then(response => {
          commit('updatePost', response.data);
        });
    },
    removePost({ commit }, postId) {
      axios.delete(`/posts/${postId}`)
        .then(() => {
          commit('deletePost', postId);
        });
    },
    addNewComment({ commit }, { postId, comment }) {
      axios.post(`/posts/${postId}/comments`, comment)
        .then(() => {
          commit('addComment', { postId, comment });
        });
    },
    removeComment({ commit }, { postId, commentId }) {
      axios.delete(`/posts/${postId}/comments/${commentId}`)
        .then(() => {
          commit('deleteComment', { postId, commentId });
        });
    }
  },
  getters: {
    paginatedPosts: state => {
      const start = (state.currentPage - 1) * state.postsPerPage;
      const end = start + state.postsPerPage;
      return state.posts.slice(start, end);
    }
  }
});
