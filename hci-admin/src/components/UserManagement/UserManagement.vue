!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"1"' />
      </el-col>
      <el-col :span="19">
          <h1>User Management</h1>
          <el-table
            stripe
            :data="subAccountData"
            style="width: 95%; margin-left: 2.5%"
            empty-text="No Data">
          <el-table-column
            label="Username"
            prop="username">
          </el-table-column>
          <el-table-column
            label="Fullname"
            prop="fullname">
          </el-table-column>
          <el-table-column
            label="Address"
            prop="address">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header">
              <el-input
                v-model="search"
                size="mini"
                :change="changeTextSearch"
                placeholder="Type to search"/>
            </template>
            <template slot-scope="scope">
              <el-button
                size="mini"
                style="background-color: #82FA58"
                :disabled="scope.row.status === 'Active'"
                @click="handleClicked(scope.$index, scope.row, 'Active')">Active</el-button>
              <el-button
                size="mini"
                type="danger"
                :disabled="scope.row.status === 'Inactive'"
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
          :total="numOfPage * 10">
        </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'

export default {
  data () {
    return {
      numOfPage: 0,
      currentPage: 1,
      pageSize: 10,
      subAccountData: [],
      searchList: [],
      accountData: [{
        username: 'Tom@123',
        fullname: 'Tom',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'John@abc',
        fullname: 'John',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Morgan@xyz',
        fullname: 'Morgan',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Jessy@hihi',
        fullname: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Jerry@ccc',
        fullname: 'Jerry',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Suri@sri',
        fullname: 'Suri',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Mama@ma',
        fullname: 'Mama',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Mimi@mi',
        fullname: 'Mimi',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Chichi@chi',
        fullname: 'Chichi',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      },
      {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 189, Grove St, Los Angeles',
        status: 'Active'
      }
      ],
      search: ''
    }
  },
  components: {
    'hci-menu': Menu
  },
  mounted () {
    this.checkAuthen()
    this.numOfPage = this.accountData.length % 10 + 1
    this.changePage()
  },
  methods: {
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
    handleClicked (index, row, typeButton) {
      this.$confirm('Do you want to ' + String(typeButton).toLowerCase() + ' ' + row.fullname + ' ?', 'Warning', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'warning'
      }).then(() => {
        row.status = typeButton
        this.showMessage(typeButton + ' user Successful !!!', 'success')
      })
    },
    handleInactiveClicked (index, row) {
      row.status = 'Inactive'
    },
    changePage () {
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      this.subAccountData = this.accountData.filter((item, index) => {
        return index >= firstIndex && index <= lastIndex
      })
    },
    changeTextSearch () {
      this.searchList = this.accountData.filter(data => !this.search || data.fullname.toLowerCase().includes(this.search.toLowerCase()))
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      this.subAccountData = this.searchList.filter((item, index) => {
        return index >= firstIndex && index <= lastIndex
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
</style>
