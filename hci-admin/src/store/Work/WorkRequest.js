import axios from 'axios'

const workRequest = {
  namespaced: true,
  state: {
    _workRequestList: []
  },
  getters: {

  },
  mutations: {
    _setWorkRequestList (state, _workRequestList) {
      state._workRequestList = _workRequestList
    }
  },
  actions: {
    /**
     * Get request work list
     * @param context
     * @param params
     */
    _getRequestWorkList (context, params) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/work/get-work-managements'
      return new Promise((resolve, reject) => {
        axios.get(url, {headers: {Authorization: sessionStorage.getItem('token')}}).then(res => {
          context.commit('_setWorkRequestList', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    },
    /**
     * Accept work by id
     * @param context
     * @param params
     */
    _acceptWork (context, params) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/work/active-works/' + params.id + '/' + params.mode
      return new Promise((resolve, reject) => {
        axios.put(url, {}, {headers: {Authorization: sessionStorage.getItem('token')}})
          .then(res => resolve(res))
          .catch(e => reject(e))
      })
    }
  }
}

export default workRequest
