import axios from 'axios'

const account = {
  namespaced: true,
  state: {
    _username: '',
    _accountData: []
  },
  getters: {

  },
  mutations: {
    /**
     * setter for _username
     */
    _setUsername (state, _username) {
      state._username = _username
    },
    /**
     * setter for _accountData
     */
    _setAccountData (state, _accountData) {
      state._accountData = _accountData
    }
  },
  actions: {
    /**
     * get all accounts function
     */
    _getAllAccounts (context) {
      let url = '/api/account/get-all-account'
      return new Promise((resolve, reject) => {
        axios.get(url, {
          params: {},
          headers: {Authorization: sessionStorage.getItem('token')}
        })
          .then((res) => {
            context.commit('_setAccountData', res.data)
            resolve(res)
          })
          .catch((e) => {
            reject(e)
          })
      })
    },
    /**
     * delete an account by username function
     */
    _deleteAccountByUsername (context) {
      let url = '/api/account/update-active-account/' + context.state._username + '/0'
      return new Promise((resolve, reject) => {
        axios.put(url,
          {},
          {headers: {Authorization: sessionStorage.getItem('token')}}
        )
          .then((res) => {
            resolve(res)
          })
          .catch((e) => {
            reject(e)
          })
      })
    },
    /**
     * active an account by username function
     */
    _activeAccountByUsername (context) {
      let url = '/api/account/update-active-account/' + context.state._username + '/1'
      return new Promise((resolve, reject) => {
        axios.put(url,
          {},
          {headers: {Authorization: sessionStorage.getItem('token')}}
        )
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

export default account
