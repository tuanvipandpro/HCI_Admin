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
      _workList = _workList.map(e => {
        e.present = (e.present) ? 'Có mặt' : 'Chưa có mặt'
        e.date = e.start.substring(0, 10)
        return e
      })
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
      let url = 'https://tuanlm-hr.herokuapp.com/api/work/get-work-management'
      return new Promise((resolve, reject) => {
        axios.get(url, {headers: {Authorization: sessionStorage.getItem('token')}}).then(res => {
          context.commit('_setWorkList', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    },
    /**
     * Delete work by id
     * @param context
     * @param params
     */
    _updateActiveWork (context, params) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/work/active-work/' + params.id + '/' + params.mode
      return new Promise((resolve, reject) => {
        axios.put(url, {}, {headers: {Authorization: sessionStorage.getItem('token')}})
          .then(res => resolve(res))
          .catch(e => reject(e))
      })
    }
  }
}

export default work
