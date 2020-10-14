import Vuex from 'vuex'
import Vue from 'vue'

import login from './Login/Login'
import work from './Work/Work'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    login: login,
    work: work
  }
})
