<template>
  <div class="signup-container">
    <h1>회원가입</h1>
    <form @submit.prevent="signUp">
      <input type="text" v-model="name" placeholder="이름" required>
      <br>
      <input type="text" v-model="id" placeholder="ID" required>
      <br>
      <input type="password" v-model="password" @input="validatePassword" placeholder="비밀번호" required>
      <div v-if="password && !validPassword">
        <p>비밀번호는 4글자 이상이며, 숫자, 소문자, 특수문자를 포함해야 합니다.</p>
      </div>
      <br>
      <input type="password" v-model="confirmPassword" placeholder="비밀번호 확인" required>
      <br>
      <input type="email" v-model="email" placeholder="이메일" required>
      <br>
      <input type="text" v-model="phone" placeholder="연락처 (선택)">
      <br>
      <button type="submit" :disabled="!canSubmit">회원가입</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: '',
      name: '',
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
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }
      const userData = {
        id: this.id,
        name: this.name,
        password: this.password,
        email: this.email,
        phone: this.phone
      };
      this.$store.dispatch('signUp', userData)
          .then(() => {
            this.$router.push('/');  //성공 시 로그인 창으로~
          })
          .catch(error => {
            alert('회원가입 실패: ' + error.message);
          });
    }
  },
  computed: {
    canSubmit() {
      return this.id && this.name && this.email && this.validPassword && this.password === this.confirmPassword;
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
