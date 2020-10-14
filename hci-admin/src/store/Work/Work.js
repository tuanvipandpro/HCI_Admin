import axios from 'axios'

const work = {
  namespaced: true,
  state: {
    _workList: []
  },
  getters: {

  },
  mutations: {
    _setWorkList (state, _workList) {
      state._workList = _workList
    }
  },
  actions: {
    /**
     * Get worklist for management
     * @param context
     * @param params
     */
    _getWorksList (context, params) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/api/work/get-work-management'
      return new Promise((resolve, reject) => {
        axios.get(url, {params}).then(res => {
          console.log(res)
          // context.commit('_setWorkList', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    }
  }
}

export default work
