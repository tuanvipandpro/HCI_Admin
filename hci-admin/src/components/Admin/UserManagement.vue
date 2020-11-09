!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"2-2"' />
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
            prop="username">
          </el-table-column>
          <el-table-column
            label="Họ tên"
            prop="fullname">
          </el-table-column>
          <el-table-column
            label="Địa chỉ"
            prop="address">
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
                :disabled="scope.row.status === 'Kích hoạt'"
                @click="handleClicked(scope.$index, scope.row, 'Kích hoạt')">Kích hoạt</el-button>
              <el-button
                size="mini"
                type="danger"
                :disabled="scope.row.status === 'Hủy'"
                @click="handleInactiveClicked(scope)">Hủy</el-button>
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
          <el-dialog title="Lý do hủy" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
            <el-form>
                <h4>Nhập lý do hủy tài khoản này</h4>
                <el-form-item  prop="reason">
                    <el-input v-model="reason" placeholder="Lý do" type="textarea"/>
                </el-form-item>
                <el-divider/>
                <el-form-item>
                    <el-button type="primary" @click="disableAccount">Hủy</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
  </div>
</template>

<script>
import Menu from '../Common/MenuAdmin'

export default {
  data () {
    return {
      reason: '',
      dialogFormVisible: false,
      numOfPage: 0,
      currentPage: 1,
      pageSize: 7,
      subAccountData: [],
      searchList: [],
      accountData: [{
        username: 'AnhDung1@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'MinhTuan1@gmail.com',
        fullname: 'Minh Tuấn',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'PhuongDat1@gmail.com',
        fullname: 'Phương Đạt',
        address: 'Quận 7, TP.HCM',
        status: 'Hủy'
      }, {
        username: 'ThanhTu1@gmail.com',
        fullname: 'Thanh Tú',
        address: 'Gò Vấp, TP.HCM',
        status: 'Kích hoạt'
      }, {
        username: 'GiangLuu1@gmail.com',
        fullname: 'Giang Lưu',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'VanThanh1@gmail.com',
        fullname: 'Văn Thành',
        address: 'Quận 7, TP.HCM',
        status: 'Kích hoạt'
      }, {
        username: 'LeMinh1@gmail.com',
        fullname: 'Lê Minh',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'AnhDung2@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'MinhTuan2@gmail.com',
        fullname: 'Minh Tuấn',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'PhuongDat2@gmail.com',
        fullname: 'Phương Đạt',
        address: 'Quận 7, TP.HCM',
        status: 'Kích hoạt'
      },
      {
        username: 'ThanhTu2@gmail.com',
        fullname: 'Thanh Tú',
        address: 'Gò Vấp, TP.HCM',
        status: 'Kích hoạt'
      }, {
        username: 'GiangLuu2@gmail.com',
        fullname: 'Giang Lưu',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'VanThanh2@gmail.com',
        fullname: 'Văn Thành',
        address: 'Quận 7, TP.HCM',
        status: 'Hủy'
      }, {
        username: 'LeMinh2@gmail.com',
        fullname: 'Lê Minh',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'AnhDung3@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'MinhTuan3@gmail.com',
        fullname: 'Minh Tuấn',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'PhuongDat3@gmail.com',
        fullname: 'Phương Đạt',
        address: 'Quận 7, TP.HCM',
        status: 'Hủy'
      }, {
        username: 'ThanhTu3@gmail.com',
        fullname: 'Thanh Tú',
        address: 'Gò Vấp, TP.HCM',
        status: 'Hủy'
      }, {
        username: 'GiangLuu3@gmail.com',
        fullname: 'Giang Lưu',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'AnhDung4@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      },
      {
        username: 'PhuongDat4@gmail.com',
        fullname: 'Phương Đạt',
        address: 'Quận 7, TP.HCM',
        status: 'Kích hoạt'
      }, {
        username: 'MinhTuan4@gmail.com',
        fullname: 'Minh Tuấn',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'AnhDung5@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'ThanhTu4@gmail.com',
        fullname: 'Thanh Tú',
        address: 'Gò Vấp, TP.HCM',
        status: 'Kích hoạt'
      }, {
        username: 'LeMinh3@gmail.com',
        fullname: 'Lê Minh',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'PhuongDat5@gmail.com',
        fullname: 'Phương Đạt',
        address: 'Quận 7, TP.HCM',
        status: 'Kích hoạt'
      }, {
        username: 'AnhDung6@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'MinhTuan5@gmail.com',
        fullname: 'Minh Tuấn',
        address: 'An Phú, Thuận An, Bình Dương',
        status: 'Hủy'
      }, {
        username: 'AnhDung7@gmail.com',
        fullname: 'Anh Dũng',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      }, {
        username: 'ThanhTu5@gmail.com',
        fullname: 'Thanh Tú',
        address: 'Gò Vấp, TP.HCM',
        status: 'Kích hoạt'
      },
      {
        username: 'GiangLuu4@gmail.com',
        fullname: 'Giang Lưu',
        address: 'Đông Hòa, Dĩ An, Bình Dương',
        status: 'Kích hoạt'
      }
      ],
      tempScope: '',
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
    this.numOfPage = Math.ceil(this.accountData.length / this.pageSize)
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
      this.$confirm('Bạn có muốn ' + String(typeButton).toLowerCase() + ' ' + row.fullname + ' ?', 'Cảnh báo', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'warning'
      }).then(() => {
        row.status = typeButton
        this.showMessage(typeButton + ' người dùng thành công !!!', 'success')
      })
    },
    handleInactiveClicked (scope) {
      this.tempScope = scope
      this.dialogFormVisible = true
    },
    disableAccount () {
      this.tempScope.row.status = 'Hủy'
      this.dialogFormVisible = false
      this.reason = ''
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
