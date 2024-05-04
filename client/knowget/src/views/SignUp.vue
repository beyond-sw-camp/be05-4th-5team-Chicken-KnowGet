<template>
    <div class="signup-container">
      <h1>회원가입</h1>
      <form @submit.prevent="signUp">
        <input type="text" v-model="userName" placeholder="이름" required>
        <br>
        <input type="text" v-model="userId" placeholder="ID" required>
        <br>
        <input type="password" v-model="password" @input="validatePassword" placeholder="비밀번호" required>
        <div v-if="password && !validPassword">
          <p>4글자 이상 , 숫자, 소문자, 특수문자를 포함한 비밀번호 입력</p>
        </div>
        <br>
        <input type="password" v-model="confirmPassword" placeholder="비밀번호 확인" required>
        <br>
        <input type="email" v-model="email" placeholder="이메일" required>
        <br>
        <input type="text" v-model="phone" placeholder="연락처 (선택)">
        <br>
        <button type="submit" :disabled="!canSubmit">Sign Up</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        userId: '',
        password: '',
        confirmPassword: '',
        email: '',
        phone: '',
        validPassword: false
      }
    },
    methods: {
      validatePassword() {
        const regex = /^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z]).{4,}$/;
        this.validPassword = regex.test(this.password);
      },
      signUp() {
        if (this.password !== this.confirmPassword) {
          alert('Passwords do not match');
          return;
        }
        // 회원가입 로직 처리
        this.$store.dispatch('signUp', { userId: this.userId, password: this.password, email: this.email, phone: this.phone });
        this.$router.push('/');
      }
    },
    computed: {
      canSubmit() {
        return this.userId && this.email && this.validPassword && this.password === this.confirmPassword;
      }
    }
  }
  </script>
  
  <style>
  .signup-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 50vh;
  }
  input, button {
    margin: 10px;
    padding: 10px;
    width: 200px;
  }
  </style>
  