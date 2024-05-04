import { createRouter, createWebHistory } from 'vue-router'
import UserLogin from '../views/UserLogin.vue' 
import SignUp from '../views/SignUp.vue'
import QnAList from '../views/QnAList.vue'
import QnAWrite from '../views/QnAWrite.vue'
import QnADetail from '../views/QnADetail.vue'



const routes = [
  { path: '/', component: UserLogin },
  { path: '/signup', component: SignUp },
  { path: '/qna', component: QnAList },
  { path: '/qna/:id', component: QnADetail },
  { path: '/write', component: QnAWrite },
  { path: '/edit/:id', component: QnAWrite },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
