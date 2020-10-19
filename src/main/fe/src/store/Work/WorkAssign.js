import axios from 'axios'

const workAssign = {
  namespaced: true,
  state: {
    _storeList: [],
    _shiftList: [],
    _employeeList: []
  },
  getters: {

  },
  mutations: {
    /**
     * Set Store List
     * @param state
     * @param _storeList
     */
    _setStoreList (state, _storeList) {
      state._storeList = _storeList
    },
    /**
     * Set Shift List
     * @param state
     * @param _shiftList
     */
    _setShiftList (state, _shiftList) {
      state._shiftList = _shiftList
    },
    /**
     * Set Employee List
     * @param state
     * @param _employeeList
     */
    _setEmployeeList (state, _employeeList) {
      state._employeeList = _employeeList
    }
  },
  actions: {
    /**
     * Get store list avalable
     * @param context
     * @param params
     */
    _getStoreList (context) {
      let url = '/api/store/get-all'
      return new Promise((resolve, reject) => {
        axios.get(url, {
          headers: {Authorization: sessionStorage.getItem('token')}
        }).then(res => {
          context.commit('_setStoreList', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    },
    /**
     * Get shift list avalable of selected store
     * @param context
     * @param params
     */
    _getShiftList (context, params) {
      let url = '/api/shift/get-all/' + params
      return new Promise((resolve, reject) => {
        axios.get(url, {
          headers: {Authorization: sessionStorage.getItem('token')}
        }).then(res => {
          context.commit('_setShiftList', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    },
    /**
     * Get employee list avalable
     * @param context
     * @param params
     */
    _getEmployeeList (context, params) {
      let url = '/api/employee/get-all/' + params
      return new Promise((resolve, reject) => {
        axios.get(url, {
          headers: {Authorization: sessionStorage.getItem('token')}
        }).then(res => {
          context.commit('_setEmployeeList', res.data)
          resolve(res)
        }).catch(e => reject(e))
      })
    },
    /**
     * Assign work
     * @param context
     * @param params
     */
    _assignWork (context, params) {
      let url = '/api/work/assign-work'
      return new Promise((resolve, reject) => {
        axios.post(url, params, {headers: {Authorization: sessionStorage.getItem('token')}})
          .then(res => resolve(res))
          .catch(e => reject(e))
      })
    }
  }
}

export default workAssign
