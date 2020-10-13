!<template>
  <div id="notificationManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"3-1"' />
      </el-col>
      <el-col :span="19">
          <h1>Quản lý tin tức</h1>
          <el-table
            stripe
            :data="subNotificationData"
            style="width: 95%; margin-left: 2.5%"
            empty-text="Không có dữ liệu">
          <el-table-column
            label="Ngày tạo"
            prop="username">
          </el-table-column>
          <el-table-column
            label="Tiêu đề"
            prop="fullname">
          </el-table-column>
          <el-table-column
            label="Tóm tắt"
            prop="address">
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
                @click="handleClicked(scope.$index, scope.row, 'Active')"
                circle></el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                @click="handleClicked(scope.$index, scope.row, 'Inactive')"
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

export default {
  data () {
    return {
      numOfPage: 0,
      currentPage: 1,
      pageSize: 10,
      subNotificationData: [],
      searchList: [],
      notificationData: [{
        username: 'Tom@123',
        fullname: 'Tom',
        address: 'No. 100, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'John@abc',
        fullname: 'John',
        address: 'No. 101, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Morgan@xyz',
        fullname: 'Morgan',
        address: 'No. 102, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Jessy@hihi',
        fullname: 'Jessy',
        address: 'No. 103, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Jerry@ccc',
        fullname: 'Jerry',
        address: 'No. 104, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Suri@sri',
        fullname: 'Suri',
        address: 'No. 105, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Mama@ma',
        fullname: 'Mama',
        address: 'No. 106, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Mimi@mi',
        fullname: 'Mimi',
        address: 'No. 107, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Chichi@chi',
        fullname: 'Chichi',
        address: 'No. 108, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 109, Grove St, Los Angeles',
        status: 'Active'
      },
      {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 110, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'John@abc',
        fullname: 'John',
        address: 'No. 111, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Morgan@xyz',
        fullname: 'Morgan',
        address: 'No. 112, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Jessy@hihi',
        fullname: 'Jessy',
        address: 'No. 113, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Jerry@ccc',
        fullname: 'Jerry',
        address: 'No. 114, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Suri@sri',
        fullname: 'Suri',
        address: 'No. 115, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Mama@ma',
        fullname: 'Mama',
        address: 'No. 116, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Mimi@mi',
        fullname: 'Mimi',
        address: 'No. 117, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Chichi@chi',
        fullname: 'Chichi',
        address: 'No. 118, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 119, Grove St, Los Angeles',
        status: 'Active'
      },
      {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 120, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'John@abc',
        fullname: 'John',
        address: 'No. 121, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Morgan@xyz',
        fullname: 'Morgan',
        address: 'No. 122, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Jessy@hihi',
        fullname: 'Jessy',
        address: 'No. 123, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Jerry@ccc',
        fullname: 'Jerry',
        address: 'No. 124, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Suri@sri',
        fullname: 'Suri',
        address: 'No. 125, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Mama@ma',
        fullname: 'Mama',
        address: 'No. 126, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Mimi@mi',
        fullname: 'Mimi',
        address: 'No. 127, Grove St, Los Angeles',
        status: 'Inactive'
      }, {
        username: 'Chichi@chi',
        fullname: 'Chichi',
        address: 'No. 128, Grove St, Los Angeles',
        status: 'Active'
      }, {
        username: 'Siro@sr',
        fullname: 'Siro',
        address: 'No. 129, Grove St, Los Angeles',
        status: 'Active'
      },
      {
        username: 'Sirom@srm',
        fullname: 'Sirom',
        address: 'No. 130, Grove St, Los Angeles',
        status: 'Active'
      }
      ],
      search: ''
    }
  },
  components: {
    'hci-menu': Menu
  },
  watch: {
    search () {
      this.searchList = this.notificationData.filter(data => !this.search || data.fullname.toLowerCase().includes(this.search.toLowerCase()))
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
    /**
     * Run when page initialization
     */
    initPage () {
      this.checkAuthen()
      this.numOfPage = this.notificationData.length / this.pageSize
      this.changePage()
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
