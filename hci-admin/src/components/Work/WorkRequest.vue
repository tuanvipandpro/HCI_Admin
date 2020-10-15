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

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      currentPage: 1,
      pageSize: 1,
      tableData: [],
      rootData: [
        {
          id: '1',
          employeeId: '1',
          employeeNm: 'TuanLM',
          shiftId: '1',
          shiftNm: 'Ca Sáng',
          start: '2020-10-15 20:52:00',
          end: '2020-10-15 20:52:00',
          accept: false
        },
        {
          id: '2',
          employeeId: '1',
          employeeNm: 'TuanLM',
          shiftId: '1',
          shiftNm: 'Ca Sáng',
          start: '2020-10-15 20:52:00',
          end: '2020-10-15 20:52:00',
          accept: false
        }
      ]
    }
  },
  computed: {
    ...mapState('workRequest', ['_workRequestList'])
  },
  mounted () {
    if (!sessionStorage.getItem('username')) {
      this.transitTo('Login', undefined)
    } else {
      this.tableData = [...this.rootData]
      this.changePage()
    }
  },
  methods: {
    ...mapActions('workRequest', ['_getRequestWorkList', '_acceptWork']),
    /**
     * Accept Work
     */
    acceptWork (scope) {

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
