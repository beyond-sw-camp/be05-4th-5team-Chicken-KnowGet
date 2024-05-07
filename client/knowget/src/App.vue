<template>
  <div id="app">
    <nav>
      <router-link to="/qna">HOME</router-link> |
      <router-link to="/about">About</router-link>
      <div v-if="user" class="user-info">
        {{ user.id }} 회원님 환영합니다. |
        <button @click="logout">로그아웃</button>
      </div>
      <div v-else>
        <router-link to="/login">Login</router-link> |  <!-- 업데이트된 로그인 링크 -->
        <router-link to="/signup">Sign Up</router-link>
      </div>
    </nav>
    <router-view/>
  </div>
</template>


<script>
import { mapState } from 'vuex';

export default {
  computed: {
    ...mapState(['user'])
  },
  methods: {
    logout() {
      this.$store.dispatch('logout').then(() => {
        this.$router.push('/login'); // 로그아웃 후 로그인 페이지로 리다이렉트
      });
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
  display: flex;
  justify-content: space-between;
}

nav a, nav button {
  font-weight: bold;
  color: #2c3e50;
  background: none;
  border: none;
  padding: 0;
  cursor: pointer;
}

nav a.router-link-exact-active, .user-info {
  color: #42b983;
}

.user-info {
  margin-left: auto;
}
</style>
