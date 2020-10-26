import Vuex from 'vuex'
import Vue from 'vue'

import login from './Login/Login'
import work from './Work/Work'
import workEdit from './Work/WorkEdit'
import workRequest from './Work/WorkRequest'
import workAssign from './Work/WorkAssign'
import notification from './Notification/Notification'
import editNotification from './Notification/EditNotification'
import createNotification from './Notification/CreateNotification'
import account from './Account/AccountManagement'
import createAccount from './Account/NewAccount'
import changePassword from './ChangePassword/ChangePassword'
import employee from './Employee/employee'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    login: login,
    notification: notification,
    editNotification: editNotification,
    createNotification: createNotification,
    work: work,
    workEdit: workEdit,
    workRequest: workRequest,
    account: account,
    createAccount: createAccount,
    workAssign: workAssign,
    changePassword: changePassword,
    employee: employee
  }
})
