!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"4-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <h1>Quản lý ca</h1>
          <el-table :data="tableData" stripe>
            <el-table-column label="Mã Ca" fixed prop="workId" align="center"/>
            <el-table-column label="Nhân viên" align="center">
              <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                  <p>Mã Nhân Viên: 1</p>
                  <p>Tên: Lê Minh Tuấn</p>
                  <div slot="reference">
                    <el-tag size="medium">{{ scope.row.employeeNm }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="Tên ca" prop="workNm" align="center"/>
            <el-table-column label="Giờ bắt đầu" prop="start" align="center"/>
            <el-table-column label="Giờ kết thúc" prop="end" align="center"/>
            <el-table-column label="Điểm danh" prop="present" align="center"/>
            <el-table-column label="Thao tác" fixed="right" align="center">
              <template slot-scope="scope">
                  <el-button type="text" size="small">Edit</el-button>
                  <el-button type="text" size="small">Delete</el-button>
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

export default {
  data () {
    return {
      currentPage: 1,
      pageSize: 5,
      rootData:
      [
        {
          workId: '1',
          employeeNm: 'Lê Minh Tuấn',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        },
        {
          workId: '2',
          employeeNm: 'TuanLM',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        },
        {
          workId: '3',
          employeeNm: 'TuanLM',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        },
        {
          workId: '4',
          employeeNm: 'TuanLM',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        },
        {
          workId: '5',
          employeeNm: 'TuanLM',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        },
        {
          workId: '6',
          employeeNm: 'TuanLM',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        },
        {
          workId: '7',
          employeeNm: 'TuanLM',
          workNm: 'Ca Sáng',
          start: '2020-10-01 08:00:00',
          end: '2020-10-01 08:00:00',
          present: 'Có',
          active: true
        }
      ],
      tableData: []
    }
  },
  components: {
    'hci-menu': Menu
  },
  mounted () {
    this.checkAuthen()
    this.changePage()
  },
  methods: {
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
