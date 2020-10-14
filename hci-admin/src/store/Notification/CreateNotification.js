import axios from 'axios'

const createNotification = {
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
     * create a notification function
     */
    _createNotification (context) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/article/upsert-article'
      return new Promise((resolve, reject) => {
        axios.put(url, {
          mode: 2,
          employeeId: context.state._employeeID,
          articleId: context.state._articleID,
          title: context.state._notificationFormData.title,
          shortContent: context.state._notificationFormData.shortContent,
          content: context.state._notificationFormData.content
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

export default createNotification
