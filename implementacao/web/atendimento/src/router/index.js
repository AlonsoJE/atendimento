import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home'
import Cadastro from '../views/Cadastro'
import Consulta from '../views/Consulta'

Vue.use(VueRouter)

const routes = [
  {
    path: '/Home',
    name: 'Home',
    component: Home
  },
  {
    path: '/Cadastro',
    name: 'Cadastro',
    component: Cadastro
  },
  {
    path: '/Consulta',
    name: 'Consulta',
    component: Consulta
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
