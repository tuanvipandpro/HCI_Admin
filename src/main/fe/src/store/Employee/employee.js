import axios from 'axios'

const employee = {
  namespaced: true,
  state: {
    email: '',
    name: '',
    address: '',
    phone: '',
    gender: '',
    status: ''
  },
  getters: {

  },
  mutations: {
    /**
     * setter for email
     */
    setEmail (state, email) {
      state.email = email
    },
    /**
     * setter for name
     */
    setName (state, name) {
      state.name = name
    },
    /**
     * setter for address
     */
    setAddress (state, address) {
      state.address = address
    },
      /**
       * setter for phone
       */
    setPhone (state, phone) {
      state.phone = phone
    },
      /**
     * setter for gender
     */
    setGender (state, gender) {
      state.gender = gender
    },
      /**
       * setter for status
       */
    setStatus (state, status) {
        state.status = status
    }
  },
  actions: {
    
    }
  }
}

export default employee
