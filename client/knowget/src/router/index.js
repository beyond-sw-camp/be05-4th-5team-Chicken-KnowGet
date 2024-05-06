import { createRouter, createWebHistory } from 'vue-router'
import UserLogin from '../views/UserLogin.vue'
import SignUp from '../views/SignUp.vue'
import QnAList from '../views/QnAList.vue'
import QnAWrite from '../views/QnAWrite.vue'
import QnADetail from '../views/QnADetail.vue'
import store from '../store' // Vuex 스토어를 임포트

const routes = [
  { path: '/', component: UserLogin },
  { path: '/signup', component: SignUp },
  { path: '/qna', component: QnAList, meta: { requiresAuth: true } },
  { path: '/qna/:id', component: QnADetail, meta: { requiresAuth: true } },
  { path: '/write', component: QnAWrite, meta: { requiresAuth: true } },
  { path: '/edit/:id', component: QnAWrite, meta: { requiresAuth: true } },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 네비게이션 가드 추가
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  const isAuthenticated = store.state.isAuthenticated;

  if (requiresAuth && !isAuthenticated) {
    next('/');
  } else {
    next();
  }
});

export default router;
