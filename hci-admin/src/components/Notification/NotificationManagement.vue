!<template>
  <div id="notificationManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"3-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <h1>Quản lý tin tức</h1>
          <el-table
            stripe
            :data="subNotificationData"
            style="width: 95%; margin-left: 2.5%"
            empty-text="Không có dữ liệu">
          <el-table-column
            label="Ngày tạo"
            prop="createDate">
          </el-table-column>
          <el-table-column
            label="Tiêu đề"
            prop="title">
          </el-table-column>
          <el-table-column
            label="Tóm tắt"
            prop="shortContent">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="mini"
                placeholder="Tìm theo tiêu đề"/>
            </template>
            <template slot-scope="scope">
              <el-button
                style="background-color: #82FA58;"
                icon="el-icon-edit"
                @click="onClickEditBtn(scope.row)"
                circle></el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                @click="onClickDeleteBtn(scope.row)"
                circle></el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          :page-size="pageSize"
          :current-page.sync="currentPage"
          style="margin-top: 10px"
          layout="prev, pager, next"
          :hide-on-single-page="true"
          @current-change="changePage()"
          :total="numOfPage * 10">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import { mapState, mapMutations, mapActions } from 'vuex'
export default {
  data () {
    return {
      loader: {},
      numOfPage: 0,
      currentPage: 1,
      pageSize: 10,
      subNotificationData: [],
      searchList: [],
      notificationData: [],
      search: ''
    }
  },
  computed: {
    ...mapState('notification', [
      '_articleID',
      '_notificationData'
    ])
  },
  components: {
    'hci-menu': Menu
  },
  watch: {
    /**
     * Load list data
     */
    search () {
      this.searchList = this.notificationData.filter(data => !this.search || data.title.toLowerCase().includes(this.search.toLowerCase()))
      if (this.search.length > 0) {
        this.currentPage = 1
        this.numOfPage = this.searchList.length / this.pageSize
      } else {
        this.numOfPage = this.notificationData.length / this.pageSize
      }
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      this.subNotificationData = this.searchList.filter((item, index) => {
        return index >= firstIndex && index <= lastIndex
      })
    }
  },
  mounted () {
    this.initPage()
  },
  methods: {
    ...mapMutations('notification', [
      '_setArticleID',
      '_setNotificationData'
    ]),
    ...mapActions('notification', [
      '_getAllNotifications',
      '_deleteNotificationByID'
    ]),
    /**
     * Run when page initialization
     */
    initPage () {
      this.loader = this.getLoader()
      this.checkAuthen()
      this._getAllNotifications().then(() => {
        this.closeLoader(this.loader)
        console.log(this._notificationData)
        this.notificationData = this._notificationData
        this.numOfPage = this.notificationData.length / this.pageSize
        this.changePage()
      })
    },
    /**
     * on click button edit
     */
    onClickEditBtn (row) {
      this.transitTo('EditNotification', {articleID: row.id})
    },
    /**
     * on click button delete
     */
    onClickDeleteBtn (row) {
      this.$confirm('Bạn có muốn xóa thông báo này không ?', 'Warning', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy bỏ',
        type: 'warning'
      }).then(() => {
        this.loader = this.getLoader()
        this._setArticleID(row.id)
        return this._deleteNotificationByID()
      }).then(() => {
        this.closeLoader(this.loader)
        this.showMessage('Xóa thông báo thành công !!!', 'success')
      })
    },
    /**
     * change page function
     */
    changePage () {
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      if (this.searchList.length > 0) {
        this.subNotificationData = this.searchList.filter((item, index) => {
          return index >= firstIndex && index <= lastIndex
        })
      } else {
        this.subNotificationData = this.notificationData.filter((item, index) => {
          return index >= firstIndex && index <= lastIndex
        })
      }
    },
    /**
     * Show Loader
     */
    getLoader () {
      return this.$loading({
        lock: true,
        spinner: 'el-icon-loading',
        text: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
    },
    /**
     * Close Loader
     * @param loader
     */
    closeLoader (loader) {
      loader.close()
    },
    /**
     * Transit to another component
     * @param name
     * @param data
     */
    transitTo (name, data) {
      const param = {
        name: name,
        params: data
      }
      this.$router.push(param)
    },
    /**
     * Show default message
     * @param message
     * @param type
     */
    showMessage (message, type) {
      this.$message({
        message: message,
        type: type
      })
    },
    /**
     * Check Authen
     */
    checkAuthen () {
      if (!sessionStorage.getItem('username')) {
        this.transitTo('Login', undefined)
      }
    }
  }
}
</script>
<style>
  .el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
    color: white;
  }
  .el-button {
    color: black;
  }
</style>
