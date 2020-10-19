import axios from 'axios'

const login = {
  namespaced: true,
  state: {
    _formData: {
      username: '',
      password: ''
    }
  },
  getters: {

  },
  mutations: {
    _setFormData (state, _formData) {
      state._formData = _formData
    }
  },
  actions: {
    _checkLogin (context) {
      let url = '/api/authenticate/login'
      return new Promise((resolve, reject) => {
        axios.post(url, {
          username: context.state._formData.username,
          password: context.state._formData.password
        })
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

export default login
