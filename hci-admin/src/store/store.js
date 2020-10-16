import Vuex from 'vuex'
import Vue from 'vue'

import login from './Login/Login'
import work from './Work/Work'
import workEdit from './Work/WorkEdit'
import workRequest from './Work/WorkRequest'
import notification from './Notification/Notification'
import editNotification from './Notification/EditNotification'
import createNotification from './Notification/CreateNotification'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    login: login,
    notification: notification,
    editNotification: editNotification,
    createNotification: createNotification,
    work: work,
    workEdit: workEdit,
    workRequest: workRequest
  }
})
