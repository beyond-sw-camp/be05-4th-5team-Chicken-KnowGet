import { createRouter, createWebHistory } from 'vue-router';
import UserLogin from '../views/UserLogin.vue';
import SignUp from '../views/SignUp.vue';
import QnAList from '../views/QnAList.vue';
import QnAWrite from '../views/QnAWrite.vue';
import QnADetail from '../views/QnADetail.vue';
import store from '../store'; // Ensure this import path matches your file structure

const routes = [
  { path: '/', component: UserLogin, meta: { requiresGuest: true } },
  { path: '/signup', component: SignUp, meta: { requiresGuest: true } },
  { path: '/qna', component: QnAList, meta: { requiresAuth: true } },
  { path: '/qna/:postIdx', component: QnADetail, props: true, meta: { requiresAuth: true } }, // Changed ":id" to ":postIdx"
  { path: '/write', component: QnAWrite, meta: { requiresAuth: true } },
  { path: '/edit/:id', component: QnAWrite, props: true, meta: { requiresAuth: true } },
  { path: '/login', redirect: '/' },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth);
  const requiresGuest = to.matched.some(record => record.meta.requiresGuest);
  const isAuthenticated = store.state.isAuthenticated;

  if (requiresAuth && !isAuthenticated) {
    next('/');
  } else if (requiresGuest && isAuthenticated) {
    next('/qna');
  } else {
    next();
  }
});

export default router;
