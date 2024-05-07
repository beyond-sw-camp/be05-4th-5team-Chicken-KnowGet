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
      localStorage.setItem('user', JSON.stringify(user));
      localStorage.setItem('token', user.token); // 토큰 저장
    },
    logout(state) {
      state.isAuthenticated = false;
      state.user = null;
      localStorage.removeItem('user');
      localStorage.removeItem('token'); // 토큰 삭제
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
    setPage(state, page) {
      state.currentPage = page;
    }
  },
  actions: {
    checkLogin({ commit }) {
      const user = localStorage.getItem('user');
      if (user) {
        try {
          const parsedUser = JSON.parse(user);
          commit('authenticate', parsedUser);
        } catch (e) {
          console.error("Failed to parse user data:", e);
        }
      }
    },
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
    fetchPosts({ commit }) {
      axios.get('/qna/all')
          .then(response => {
            commit('setPosts', response.data);
          })
          .catch(error => {
            console.error('Error fetching posts:', error);
          });
    },

    signUp(_, userData) { // Use an underscore or remove the parameter if not using `commit`
      return axios.post('/user/signup', userData)
          .then(response => {
            // No commit here since we are not logging the user in automatically
            return response.data;
          });
    },
    submitPost({ commit }, post) {
      return axios.post('/qna/save', post)
          .then(response => {
            commit('addPost', response.data);
            return response.data;
          })
          .catch(error => {
            console.error('Error submitting post:', error);
            throw error;
          });
    }
  }
});
