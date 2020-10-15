import axios from 'axios'

const workEdit = {
  namespaced: true,
  state: {
    _workObj: {}
  },
  getters: {

  },
  mutations: {
    _setWorkObj (state, _workObj) {
      state._workObj = _workObj
    }
  },
  actions: {
    /**
     * Get worklist for management
     * @param context
     * @param params
     */
    _getWorkById (context, params) {
      // let url = 'https://tuanlm-hr.herokuapp.com/api/work/get-work-management'
      let url = 'http://localhost:8084/api/work/get-work-managementsss'
      return new Promise((resolve, reject) => {
        axios.get(url, {params}).then(res => {
          context.commit('_setWorkObj', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    },
    /**
     * Delete work by id
     * @param context
     * @param params
     */
    _updateWork (context, params) {
      // let url = 'https://tuanlm-hr.herokuapp.com/api/work/active-work/' + params.id + '/' + params.mode
      let url = 'http://localhost:8084/api/work/active-workssss/' + params.id + '/' + params.mode
      return new Promise((resolve, reject) => {
        axios.put(url)
          .then(res => resolve(res))
          .catch(e => reject(e))
      })
    }
  }
}

export default workEdit
