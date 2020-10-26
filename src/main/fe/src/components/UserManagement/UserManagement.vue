!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"1-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <h1>Quản lý người dùng</h1>
          <el-table
            stripe
            :data="subAccountData"
            style="width: 95%; margin-left: 2.5%"
            empty-text="Không có dữ liệu">
          <el-table-column
            label="Tài Khoản"
            width="230px"
            prop="email">
          </el-table-column>
          <el-table-column
            label="Họ tên"
            width="200px"
            prop="name">
          </el-table-column>
          <el-table-column
            label="Giới tính"
            width="90px"
          >
            <template slot-scope="scope">
              <span>{{ scope.row.gender ? 'Nam' : 'Nữ'}}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Địa chỉ"
            width="260px"
            prop="address">
          </el-table-column>
          <el-table-column
            label="Số điện thoại"
            prop="phone">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="mini"
                placeholder="Tìm tên"/>
            </template>
            <template slot-scope="scope">
              <el-button
                size="mini"
                style="background-color: #82FA58;"
                :disabled="scope.row.active"
                @click="handleClicked(scope.$index, scope.row, 'Active')">Active</el-button>
              <el-button
                size="mini"
                type="danger"
                :disabled="!scope.row.active"
                @click="handleClicked(scope.$index, scope.row, 'Inactive')">Inactive</el-button>
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
          :total="searchList.length === 0 ? accountData.length : searchList.length">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import { mapActions } from 'vuex'

export default {
  data () {
    return {
      numOfPage: 0,
      currentPage: 1,
      pageSize: 3,
      subAccountData: [],
      searchList: [],
      accountData: [],
      search: '',
      loader: {}
    }
  },
  components: {
    'hci-menu': Menu
  },
  watch: {
    search () {
      this.searchList = this.accountData.filter(data => !this.search || data.name.toLowerCase().includes(this.search.toLowerCase()))
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
    this.checkAuthen()
    this.loader = this.getLoader()
    this.init()
  },
  methods: {
    ...mapActions('workAssign', ['_getEmployeeList']),
    ...mapActions('employee', ['updateStatusEmployee']),
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
    init () {
      let _this = this
      new Promise((resolve, reject) => {
        resolve()
      }).then(() => {
        return _this._getEmployeeList(0)
      }).then(res => {
        if (res.data) {
          _this.accountData = res.data
        }
        _this.numOfPage = Math.ceil(_this.accountData.length / _this.pageSize)
        _this.changePage()
        _this.closeLoader(_this.loader)
      })
    },
    handleClicked (index, row, typeButton) {
      this.$confirm('Do you want to ' + String(typeButton).toLowerCase() + ' ' + row.name + ' ?', 'Warning', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'warning'
      }).then(() => {
        let empDataset = row
        empDataset.active = typeButton === 'Active'
        this.loader = this.getLoader()
        return this.updateStatusEmployee(empDataset)
      }).then(res => {
        if (res) {
          row.active = typeButton === 'Active'
          this.showMessage(typeButton + ' user successful !!!', 'success')
        }
        this.closeLoader(this.loader)
      })
    },
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
