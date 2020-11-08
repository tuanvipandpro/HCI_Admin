import Vue from 'vue'
import Router from 'vue-router'

// Vue Component
import Login from '@/components/Login/Login'
import ForgetPassword from '@/components/Login/ForgetPassword'
import UserManagement from '@/components/Admin/UserManagement'
import Notification from '@/components/Notification/Notification'
import ChangePassword from '@/components/ChangePassword/ChangePassword'
import NewPitch from '@/components/Pitch/NewPitch'
import ManagePitch from '@/components/Pitch/ManagePitch'
import HistoryPitch from '@/components/Pitch/HistoryPitch'
import Profile from '@/components/ChangePassword/Profile'
import Statistic from '@/components/Statistic/Statistic'
import AdminHome from '@/components/Admin/AdminHome'
import PitchOwner from '@/components/Admin/PitchOwner'
import BlackList from '@/components/Admin/BlackList'
import CreatePitchOwner from '@/components/Admin/CreatePitchOwner'
import OrderPitchManagement from '@/components/Pitch/OrderPitchManagement'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/user-management',
      name: 'UserManagement',
      component: UserManagement
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
    },
    {
      path: '/manage-pitch',
      name: 'ManagePitch',
      component: ManagePitch
    },
    {
      path: '/pitch-history',
      name: 'HistoryPitch',
      component: HistoryPitch
    },
    {
      path: '/profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/statistic',
      name: 'Statistic',
      component: Statistic
    },
    {
      path: '/forget',
      name: 'ForgetPassword',
      component: ForgetPassword
    },
    {
      path: '/admin-home',
      name: 'AdminHome',
      component: AdminHome
    },
    {
      path: '/admin-pitch-owner',
      name: 'PitchOwner',
      component: PitchOwner
    },
    {
      path: '/admin-black-list',
      name: 'BlackList',
      component: BlackList
    },
    {
      path: '/admin-create-pitch-owner',
      name: 'CreatePitchOwner',
      component: CreatePitchOwner
    },
    {
      path: '/orderPitchManagement',
      name: 'OrderPitchManagement',
      component: OrderPitchManagement
    }
  ]
})
