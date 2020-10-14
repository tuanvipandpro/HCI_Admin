import Vue from 'vue'
import Router from 'vue-router'

// Vue Component
import Login from '@/components/Login/Login'
import UserManagement from '@/components/UserManagement/UserManagement'
import Notification from '@/components/Notification/Notification'
import ChangePassword from '@/components/ChangePassword/ChangePassword'
import NewPitch from '@/components/Pitch/NewPitch'
import PitchManagement from '@/components/Pitch/PitchManagement'
import NotificationManagement from '@/components/Notification/NotificationManagement'
import EditNotification from '@/components/Notification/EditNotification'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/userManagement',
      name: 'UserManagement',
      component: UserManagement
    },
    {
      path: '/change-password',
      name: 'ChangePassword',
      component: ChangePassword
    },
    {
      path: '/get-notification',
      name: 'NotificationManagement',
      component: NotificationManagement
    },
    {
      path: '/edit-notification',
      name: 'EditNotification',
      component: EditNotification
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
    },
    {
      path: '/pitch-management',
      name: 'PitchManagement',
      component: PitchManagement
    }
  ]
})
