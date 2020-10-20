import axios from 'axios'

const changePassword = {
  namespaced: true,
  state: {

  },
  getters: {

  },
  mutations: {

  },
  /**
   * Change password
   * @param context
   * @param params
   */
  actions: {
    _changePassword (context, params) {
      let url = '/api/account/change-password'
      return new Promise((resolve, reject) => {
        axios.put(url, {
          username: sessionStorage.getItem('username'),
          password: params
        },
        {headers: {Authorization: sessionStorage.getItem('token')}})
          .then((res) => {
            resolve(res)
          })
          .catch((e) => {
            reject(e)
          })
      })
    }
  }
}

export default changePassword
