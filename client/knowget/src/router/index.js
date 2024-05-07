import { createRouter, createWebHistory } from 'vue-router'
import UserLogin from '../views/UserLogin.vue'
import SignUp from '../views/SignUp.vue'
import QnAList from '../views/QnAList.vue'
import QnAWrite from '../views/QnAWrite.vue'
import QnADetail from '../views/QnADetail.vue'
import store from '../store' // Vuex 스토어를 임포트

const routes = [
  { path: '/', component: UserLogin, meta: { requiresGuest: true } },
  { path: '/signup', component: SignUp, meta: { requiresGuest: true } },
  { path: '/qna', component: QnAList, meta: { requiresAuth: true } },
  { path: '/qna/:id', component: QnADetail, meta: { requiresAuth: true } },
  { path: '/write', component: QnAWrite, meta: { requiresAuth: true } },
  { path: '/edit/:id', component: QnAWrite, meta: { requiresAuth: true } },
  { path: '/login', redirect: '/' } , // Home 경로를 로그인 페이지로 리다이렉트
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 네비게이션 가드 추가
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  const requiresGuest = to.matched.some(record => record.meta.requiresGuest);
  const isAuthenticated = store.state.isAuthenticated;

  if (requiresAuth && !isAuthenticated) {
    next('/');
  } else if (requiresGuest && isAuthenticated) {
    next('/qna'); // 이미 인증된 사용자는 QnA 페이지로 리다이렉션
  } else {
    next();
  }
});

export default router;
