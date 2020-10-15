!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"4-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <h1>Quản lý ca</h1>
          <el-table :data="tableData" stripe>
            <el-table-column label="Mã Ca" fixed prop="id" align="center"/>
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
            <el-table-column label="Tên ca" align="center">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>Giờ bắt đầu: {{ scope.row.start }}</p>
                  <p>Giờ kết thúc: {{ scope.row.end }}</p>
                  <div slot="reference">
                    <el-tag size="medium">{{ scope.row.shiftNm }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="Cửa hàng" align="center">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>Mã cửa hàng: {{ scope.row.storeId }}</p>
                  <p>Tên cửa hàng: {{ scope.row.storeNm }}</p>
                  <div slot="reference">
                    <el-tag size="medium">{{ scope.row.storeNm }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="Ngày làm việc" prop="date" align="center"/>
            <el-table-column label="Điểm danh" prop="present" align="center"/>
            <el-table-column label="Thao tác" fixed="right" align="center">
              <template slot-scope="scope">
                  <!-- <el-button type="text" size="small" @click="editWork(scope)">Chỉnh sửa</el-button> -->
                  <el-button v-if="scope.row.active" type="text" size="small" @click="updateActiveWork(scope, 0)">Hủy ca</el-button>
                  <el-button v-else type="text" size="small" @click="updateActiveWork(scope, 1)">Mở ca</el-button>
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
import { mapActions, mapState } from 'vuex'
import Menu from '../Common/Menu'

export default {
  data () {
    return {
      currentPage: 1,
      pageSize: 5,
      rootData:
      [
        {
          id: 0,
          shiftNm: '',
          active: true,
          present: true,
          start: '',
          end: '',
          storeId: 0,
          storeNm: '',
          employeeId: 0,
          employeeNm: ''
        }
      ],
      tableData: []
    }
  },
  components: {
    'hci-menu': Menu
  },
  computed: {
    ...mapState('work', ['_workList'])
  },
  mounted () {
    // Authenticate
    if (!sessionStorage.getItem('username')) {
      this.transitTo('Login', undefined)
    } else {
      const loader = this.getLoader()
      // Get Worklist
      this._getWorksList()
        .then(res => {
          this.closeLoader(loader)
          this.rootData = this._workList
          this.changePage()
        })
        .catch(e => {
          this.closeLoader(loader)
          console.error(e)
        })
    }
  },
  methods: {
    ...mapActions('work', ['_getWorksList', '_updateActiveWork']),
    /**
     * Go to edit work
     */
    editWork (scope) {
      this.transitTo('EditWork', {id: scope.row.id})
    },
    /**
     * Delete Work
     */
    updateActiveWork (scope, mode) {
      let row = scope.row
      const loader = this.getLoader()
      let params = {
        id: row.id,
        mode: mode
      }

      this._updateActiveWork(params).then(res => {
        this.closeLoader(loader)
        row.active = !row.active
      })
        .catch(e => {
          this.closeLoader(loader)
          console.error(e)
        })
    },
    /**
     * Handle when paging
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
<style>
  .el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
    color: white;
  }
  .el-button {
    color: black;
  }
</style>
