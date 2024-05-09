<!-- UserLogin.vue -->
<template>
  <div class="knowget">
    <h1>KnowGet</h1>
    <h3>QnA service</h3>
    <h5>로그인 후 너겟의 모든 서비스를 이용할 수 있습니다.</h5>
    <div class="login-container">
      <h1>Login</h1>
      <form @submit.prevent="login">
        <input type="text" v-model="id" placeholder="아이디" required>
        <br>
        <input type="password" v-model="password" placeholder="패스워드" required>
        <br>
        <button type="submit">로그인</button>
      </form>
      <button @click="$router.push('/signup')">회원가입</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserLogin",
  data() {
    return {
      id: '',
      password: ''
    }
  },
  methods: {
    login() {
      this.$store.dispatch('login', {
        id: this.id,
        password: this.password
      }).then(() => {
        // 로그인 성공 시 페이지 이동
        this.$router.push('/qna');
      }).catch((error) => {
        console.error("Login failed:", error);
        alert("로그인 실패: 아이디 또는 패스워드를 확인하세요.");
      });
    }
  }
}
</script>


<style>
  .login-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 30vh;
  }
  input, button {
    margin: 10px;
    padding: 10px;
    width: 200px;
  }
  </style>
  