import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8081'; // 서버 구성에 따라 이 URL을 업데이트하세요.

createApp(App).use(store).use(router).provide('axios', axios).mount('#app');
