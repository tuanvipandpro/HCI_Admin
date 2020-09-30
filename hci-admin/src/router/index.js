import Vue from 'vue'
import Router from 'vue-router'

// Vue Component
import Login from '@/components/Login/Login'
import Home from '@/components/Home/Home'
import Notification from '@/components/Notification/Notification'
import ChangePassword from '@/components/ChangePassword/ChangePassword'
import NewPitch from '@/components/Pitch/NewPitch'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/change-password',
      name: 'ChangePassword',
      component: ChangePassword
    },
    {
      path: '/new-notification',
      name: 'Notification',
      component: Notification
    },
    {
      path: '/new-pitch',
      name: 'NewPitch',
      component: NewPitch
    }
  ]
})
