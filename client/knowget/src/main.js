import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';

// 기본 URL 설정
axios.defaults.baseURL = 'http://localhost:8080';

// 요청 인터셉터를 추가하여 모든 요청에 인증 토큰 포함
axios.interceptors.request.use(
    config => {
        const user = localStorage.getItem('user');
        if (user) {
            const { token } = JSON.parse(user);
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);



const app = createApp(App);
app.use(store);
app.use(router);
store.dispatch('checkLogin'); // 앱 시작 시 로그인 상태 확인
app.mount('#app');