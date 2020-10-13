!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"4-1"' />
      </el-col>
      <el-col offset="5" :span="19">
          <h1>Quản lý ca</h1>
          <el-table
            stripe
            :data="subAccountData"
            style="width: 95%; margin-left: 2.5%"
            empty-text="Không có dữ liệu"
          >
                <el-table-column label="Mã ca" prop="username"/>
                <el-table-column label="Nhân viên" prop="fullname"/>
                <el-table-column label="Giờ bắt đầu" prop="start"/>
                <el-table-column label="Giờ kết thúc" prop="end"/>
                <el-table-column label="Giờ kết thúc" prop="end"/>
                <el-table-column label="Giờ kết thúc" prop="end"/>
                <el-table-column label="Giờ kết thúc" prop="end"/>
                <!-- <el-table-column label="Điểm danh" prop="present"/> -->
                <!-- <el-table-column fixed="right" label="Xử lý" >
                    <template slot-scope="scope">
                        <el-button
                            size="mini"
                            style="background-color: #82FA58;"
                            :disabled="scope.row.status === 'Active'"
                            @click="handleClicked(scope.$index, scope.row, 'Active')">
                                Active
                        </el-button>
                        <el-button
                            size="mini"
                            type="danger"
                            :disabled="scope.row.status === 'Inactive'"
                            @click="handleClicked(scope.$index, scope.row, 'Inactive')">
                                Inactive
                        </el-button>
                    </template>
                </el-table-column> -->
                    <el-table-column
                        fixed="right"
                        label="Operations"
                        width="120">
                        <template slot-scope="scope">
                            <el-button @click="handleClick" type="text" size="small">Detail</el-button>
                            <el-button type="text" size="small">Edit</el-button>
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
                :total="numOfPage * 10"
            />
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
      accountData: [ {
        username: '1',
        fullname: 'TuanLM',
        start: '2020-10-01 08:00:00',
        end: '2020-10-01 08:00:00',
        present: 'Có',
        status: 'Inactive'
      } ],
      search: ''
    }
  },
  components: {
    'hci-menu': Menu
  },
  watch: {
    search () {
      this.searchList = this.accountData.filter(data => !this.search || data.fullname.toLowerCase().includes(this.search.toLowerCase()))
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
    this.numOfPage = this.accountData.length / this.pageSize
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
