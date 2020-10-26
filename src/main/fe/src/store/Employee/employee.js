import axios from 'axios'

const employee = {
  namespaced: true,
  state: {
    email: '',
    name: '',
    address: '',
    phone: '',
    gender: true,
    storeId: '',
    bankNumber: '',
    salary: '',
    active: true
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
       * setter for active
       */
    setActive (state, active) {
      state.active = active
    },
    /**
       * setter for salary
       */
    setSalary (state, salary) {
      state.salary = salary
    },
    /**
       * setter for bankNumber
       */
    setBankNumber (state, bankNumber) {
      state.bankNumber = bankNumber
    },
    /**
       * setter for storeId
       */
    setStoreId (state, storeId) {
      state.storeId = storeId
    }
  },
  actions: {
  /**
     * api create new employee
     */
    createNewEmployee (context, employeeInfo) {
      let url = '/api/employee/add-new-employee'
      return new Promise((resolve, reject) => {
        axios.post(url,
          employeeInfo,
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
     * api create new employee
     */
    updateStatusEmployee (context, employeeDataset) {
      let url = '/api/employee/change-status'
      return new Promise((resolve, reject) => {
        axios.post(url,
          employeeDataset,
          {headers: {Authorization: sessionStorage.getItem('token')}}
        )
          .then((res) => {
            resolve(res.data)
          })
          .catch((e) => {
            reject(e)
          })
      })
    }
  }
}

export default employee
