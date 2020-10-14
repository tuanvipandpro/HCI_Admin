import axios from 'axios'

const notification = {
  namespaced: true,
  state: {
    _articleID: '',
    _notificationData: []
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
     * setter for _notificationData
     */
    _setNotificationData (state, _notificationData) {
      state._notificationData = _notificationData
    }
  },
  actions: {
    /**
     * get all notifications function
     */
    _getAllNotifications (context) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/article/0'
      return new Promise((resolve, reject) => {
        axios.get(url, {
          params: {}
        })
          .then((res) => {
            context.commit('_setNotificationData', res.data)
            resolve(res)
          })
          .catch((e) => {
            reject(e)
          })
      })
    },
    /**
     * delete a notification by id function
     */
    _deleteNotificationByID (context) {
      let url = 'https://tuanlm-hr.herokuapp.com/api/article/delete-by-id/' + context.state._articleID
      return new Promise((resolve, reject) => {
        axios.delete(url, {
          data: {}
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

export default notification
