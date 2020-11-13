import Vue from 'vue'
import Router from 'vue-router'

// Vue Component
import Login from '@/components/Login/Login'
import UserManagement from '@/components/UserManagement/UserManagement'
import Notification from '@/components/Notification/Notification'
import ChangePassword from '@/components/ChangePassword/ChangePassword'
import NotificationManagement from '@/components/Notification/NotificationManagement'
import EditNotification from '@/components/Notification/EditNotification'
import Work from '@/components/Work/Work'
import EditWork from '@/components/Work/EditWork'
import WorkRequest from '@/components/Work/WorkRequest'
import WorkAssign from '@/components/Work/WorkAssign'
import AccountManagement from '@/components/AccountManagement/AccountManagement'
import NewAccount from '@/components/AccountManagement/NewAccount'
import AddingEmployee from '@/components/UserManagement/AddingEmployee'

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
      path: '/accountManagement',
      name: 'AccountManagement',
      component: AccountManagement
    },
    {
      path: '/newAccount',
      name: 'NewAccount',
      component: NewAccount
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
      path: '/work',
      name: 'Work',
      component: Work
    },
    {
      path: '/edit-work',
      name: 'EditWork',
      component: EditWork
    },
    {
      path: '/work-request',
      name: 'WorkRequest',
      component: WorkRequest
    },
    {
      path: '/work-assign',
      name: 'WorkAssign',
      component: WorkAssign
    },
    {
      path: '/addNewEmployee',
      name: 'AddingEmployee',
      component: AddingEmployee
    }
  ]
})
