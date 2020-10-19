import axios from 'axios'

const editNotification = {
  namespaced: true,
  state: {
    _articleID: '',
    _employeeID: '',
    _notificationFormData: {}
  },
  getters: {

  },
  mutations: {
    /**
     * setter for _articleID
     */
    _setArticleID (state, _articleID) {
      state._articleID = _articleID
    },
    /**
     * setter for _employeeID
     */
    _setEmployeeID (state, _employeeID) {
      state._employeeID = _employeeID
    },
    /**
     * setter for _notificationFormData
     */
    _setNotificationFormData (state, _notificationFormData) {
      state._notificationFormData = _notificationFormData
    }
  },
  actions: {
    /**
     * get notifications by ID function
     */
    _getNotificationByID (context) {
      let url = '/api/article/get-by-id/' + context.state._articleID
      return new Promise((resolve, reject) => {
        axios.get(url, {
          params: {},
          headers: {Authorization: sessionStorage.getItem('token')}
        })
          .then((res) => {
            context.commit('_setNotificationFormData', res.data)
            resolve(res)
          })
          .catch((e) => {
            reject(e)
          })
      })
    },
    /**
     * update a notification by id function
     */
    _updateNotificationByID (context) {
      let url = '/api/article/upsert-article'
      return new Promise((resolve, reject) => {
        axios.put(url,
          {
            mode: 1,
            employeeId: context.state._employeeID,
            articleId: context.state._articleID,
            title: context.state._notificationFormData.title,
            shortContent: context.state._notificationFormData.shortContent,
            content: context.state._notificationFormData.content
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

export default editNotification
