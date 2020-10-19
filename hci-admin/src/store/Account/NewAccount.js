import axios from 'axios'

const createAccount = {
  namespaced: true,
  state: {
    _accountFormData: {}
  },
  getters: {

  },
  mutations: {
    /**
     * setter for _accountFormData
     */
    _setAccountFormData (state, _accountFormData) {
      state._accountFormData = _accountFormData
    }
  },
  actions: {
    /**
     * create a notification function
     */
    _createAccount (context) {
      let url = '/api/account/insert-account'
      return new Promise((resolve, reject) => {
        axios.post(url,
          {
            username: context.state._accountFormData.username,
            password: context.state._accountFormData.password,
            employeeId: context.state._accountFormData.employeeId
          },
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

export default createAccount
