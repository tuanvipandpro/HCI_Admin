!<template>
  <div id="work-request">
    <el-row>
      <!-- Menu -->
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"4-2"' />
      </el-col>
      <!-- Content -->
      <el-col :offset="5" :span="19">
          <h1>Ca làm được đề xuất</h1>
          <el-table :data="tableData" stripe>
                <el-table-column label="Code" fixed prop="id" align="center"/>
                <el-table-column label="Nhân viên" align="center">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>Mã Nhân Viên: {{ scope.row.employeeId }}</p>
                            <p>Tên: {{ scope.row.employeeNm }}</p>
                            <div slot="reference">
                                <el-tag size="medium">{{ scope.row.employeeNm }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column label="Ca làm" align="center">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>Mã Ca Làm: {{ scope.row.shiftId }}</p>
                            <p>Tên: {{ scope.row.shiftNm }}</p>
                            <div slot="reference">
                                <el-tag size="medium">{{ scope.row.shiftNm }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column label="Giờ bắt đầu" prop="start" align="center"/>
                <el-table-column label="Giờ kết thúc" prop="end" align="center"/>
                <el-table-column label="Thao tác" fixed="right" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="acceptWork(scope)">Duyệt</el-button>
                    </template>
                </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="prev, pager, next"
            style="margin-top: 10px"
            :hide-on-single-page="true"
            :current-page.sync="currentPage"
            :page-size="pageSize"
            :total="rootData.length"
            @current-change="changePage()">
          </el-pagination>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import { mapActions, mapState } from 'vuex'
import moment from 'moment'

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      currentPage: 1,
      pageSize: 5,
      tableData: [],
      rootData: []
    }
  },
  computed: {
    ...mapState('workRequest', ['_workRequestList'])
  },
  mounted () {
    if (!sessionStorage.getItem('username')) {
      this.transitTo('Login', undefined)
    } else {
      const loader = this.getLoader()

      this._getRequestWorkList(this.convertDateToDateTime()).then(res => {
        this.closeLoader(loader)
        this.rootData = [...this._workRequestList]
        this.changePage()
      })
        .catch(e => {
          this.closeLoader(loader)
          console.error(e)
        })
    }
  },
  methods: {
    ...mapActions('workRequest', ['_getRequestWorkList', '_acceptWork']),
    /**
     * Convert Date to yyyy-MM-ddTHH:mm:ss
     * @param now
     */
    convertDateToDateTime () {
      const now = new Date()
      return moment(now.toString()).format('yyyy-MM-DD') + 'T' + moment(now.toString()).format('HH:mm:ss')
    },
    /**
     * Accept Work
     */
    acceptWork (scope) {
      const row = scope.row
      this.$confirm('Bạn có chắc chắn duyệt ca làm này ?', 'Xác nhận ?', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy bỏ',
        type: 'warning'
      }).then(() => {
        const loader = this.getLoader()
        this._acceptWork(row).then(res => {
        // Thông báo update thành công
          this.closeLoader(loader)
          this.rootData = this.rootData.filter(item => item.id !== row.id)
          this.changePage()
        })
          .catch(e => {
            this.closeLoader(loader)
            console.error(e)
          })
      })
    },
    /**
     * Handle when change page
     */
    changePage () {
      let firstIndex = (this.currentPage - 1) * this.pageSize
      let lastIndex = (this.currentPage * this.pageSize - 1)
      this.tableData = this.rootData.filter((item, index) => index >= firstIndex && index <= lastIndex)
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
    }
  }
}
</script>
