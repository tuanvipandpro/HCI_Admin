!<template>
  <div id="account-management">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"2-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <h1>Quản lý tài khoản nhân viên</h1>
          <el-table
            stripe
            :data="subAccountData"
            style="width: 95%; margin-left: 2.5%"
            empty-text="Không có dữ liệu">
          <el-table-column
            label="Tên tài khoản"
            prop="username">
          </el-table-column>
          <el-table-column
            label="Vai trò"
            prop="role">
          </el-table-column>
          <el-table-column
            label="Mã nhân viên"
            prop="employeeId">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="mini"
                placeholder="Tìm theo mã nhân viên"/>
            </template>
            <template slot-scope="scope">
              <el-button
                v-if="scope.row.active"
                type="danger"
                icon="el-icon-delete"
                @click="onClickDeleteBtn(scope.row)"
                circle></el-button>
              <el-button
                v-if="!scope.row.active"
                style="background-color: #82FA58;"
                icon="el-icon-check"
                @click="onClickActiveBtn(scope.row)"
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
      subAccountData: [],
      searchList: [],
      accountData: [],
      search: ''
    }
  },
  computed: {
    ...mapState('account', [
      '_username',
      '_accountData'
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
      this.searchList = this.accountData.filter(data => !this.search || data.employeeId.toString() === this.search.toString())
      if (this.search.length > 0) {
        this.currentPage = 1
        this.numOfPage = this.searchList.length / this.pageSize
      } else {
        this.numOfPage = this.accountData.length / this.pageSize
      }
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      this.subAccountData = this.searchList.filter((item, index) => {
        return index >= firstIndex && index <= lastIndex
      })
    }
  },
  mounted () {
    this.initPage()
  },
  methods: {
    ...mapMutations('account', [
      '_setUsername',
      '_setAccountData'
    ]),
    ...mapActions('account', [
      '_getAllAccounts',
      '_deleteAccountByUsername',
      '_activeAccountByUsername'
    ]),
    /**
     * Run when page initialization
     */
    initPage () {
      this.loader = this.getLoader()
      this.checkAuthen()
      this._getAllAccounts().then(() => {
        this.closeLoader(this.loader)
        this.accountData = this._accountData
        this.numOfPage = this.accountData.length / this.pageSize
        this.changePage()
      })
    },
    /**
     * on click button delete
     */
    onClickDeleteBtn (row) {
      this.$confirm('Bạn có muốn xóa tài khoản này không ?', 'Warning', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy bỏ',
        type: 'warning'
      }).then(() => {
        this.loader = this.getLoader()
        this._setUsername(row.username)
        return this._deleteAccountByUsername()
      }).then(() => {
        this.closeLoader(this.loader)
        this.loader = this.getLoader()
        return this._getAllAccounts()
      }).then(() => {
        this.closeLoader(this.loader)
        this.accountData = this._accountData
        this.numOfPage = this.accountData.length / this.pageSize
        this.changePage()
        this.showMessage('Xóa tài khoản thành công !!!', 'success')
      })
    },
    /**
     * on click button active
     */
    onClickActiveBtn (row) {
      this.$confirm('Bạn có muốn kích hoạt tài khoản này không ?', 'Warning', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy bỏ',
        type: 'warning'
      }).then(() => {
        this.loader = this.getLoader()
        this._setUsername(row.username)
        return this._activeAccountByUsername()
      }).then(() => {
        this.closeLoader(this.loader)
        this.loader = this.getLoader()
        return this._getAllAccounts()
      }).then(() => {
        this.closeLoader(this.loader)
        this.accountData = this._accountData
        this.numOfPage = this.accountData.length / this.pageSize
        this.changePage()
        this.showMessage('Kích hoạt tài khoản thành công !!!', 'success')
      })
    },
    /**
     * change page function
     */
    changePage () {
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      if (this.searchList.length > 0) {
        this.subAccountData = this.searchList.filter((item, index) => {
          return index >= firstIndex && index <= lastIndex
        })
      } else {
        this.subAccountData = this.accountData.filter((item, index) => {
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
