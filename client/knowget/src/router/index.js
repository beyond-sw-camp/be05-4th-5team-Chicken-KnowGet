import { createRouter, createWebHistory } from 'vue-router'
import UserLogin from '../views/UserLogin.vue' 
import SignUp from '../views/SignUp.vue'

const routes = [
  { path: '/', component: UserLogin },
  { path: '/signup', component: SignUp },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
