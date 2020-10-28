!<template>
  <div id="new-pitch">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-3"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <h1 style="text-align: center">Quản lý sân</h1>
        <div style="float: right; margin-right: 10px">
          <el-date-picker
            v-model="dateSearch"
            type="date"
            :picker-options="pickerOptions"
            style="margin-right: 20px"
            placeholder="Chọn ngày">
          </el-date-picker>
        </div>
        <div>
          <el-table
            :data="subDataTable"
            stripe
            style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <p>* Thông tin người đặt: </p>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;- Tên người đặt: {{ props.row.name }}</p>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;- Địa chỉ: {{ props.row.address }}</p>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;- Số điện thoại: {{ props.row.phone }}</p>
              </template>
            </el-table-column>
            <el-table-column
              label="Ngày"
              prop="date">
            </el-table-column>
            <el-table-column
              label="Sân"
              prop="pitch">
            </el-table-column>
            <el-table-column
              label="Địa chỉ"
              prop="addressPitch">
            </el-table-column>
            <el-table-column
              label="Thời gian bắt đầu"
              prop="startTime">
            </el-table-column>
            <el-table-column
              label="Thời gian kết thúc"
              prop="endTime">
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      subDataTable: [],
      tableData: [{
        date: '2020-10-20',
        startTime: '6:00',
        endTime: '7:30',
        name: 'Tom',
        pitch: 'Sân 1',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'PG, BD, VN',
        phone: '0123456789'
      }, {
        date: '2020-10-20',
        startTime: '14:30',
        endTime: '15:30',
        name: 'Tom',
        pitch: 'Sân 2',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'BB, BD, VN',
        phone: '0123456789'
      }, {
        date: '2020-10-20',
        startTime: '15:00',
        endTime: '17:00',
        name: 'Tom',
        pitch: 'Sân 3',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'BC, BD, VN',
        phone: '0123456789'
      }, {
        date: '2020-10-20',
        startTime: '17:15',
        endTime: '18:45',
        name: 'Tom',
        pitch: 'Sân 1',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'DT, BD, VN',
        phone: '0123456789'
      }, {
        date: '2020-10-18',
        startTime: '18:00',
        endTime: '20:00',
        name: 'Tom',
        pitch: 'Sân 2',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'TA, BD, VN',
        phone: '0123456789'
      }, {
        date: '2020-10-20',
        startTime: '19:00',
        endTime: '20:00',
        name: 'Tom',
        pitch: 'Sân 3',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'DA, BD, VN',
        phone: '0123456789'
      }, {
        date: '2020-10-19',
        startTime: '19:00',
        endTime: '20:30',
        name: 'Tom',
        pitch: 'Sân 2',
        address: 'No. 189, Grove St, Los Angeles',
        addressPitch: 'TDM, BD, VN',
        phone: '0123456789'
      }],
      startTime: '',
      endTime: '',
      pickerOptions: {
        disabledDate (time) {
          let yesterday = new Date()
          yesterday.setDate(yesterday.getDate() - 1)
          return time.getTime() <= yesterday
        }
      },
      dateSearch: '',
      value2: ''
    }
  },
  watch: {
    dateSearch () {
      let date = new Date()
      date = this.dateSearch
      if (date) {
        let day = date.getDate()
        let month = date.getMonth()
        let year = date.getFullYear()
        this.subDataTable = this.tableData.filter(data => !this.dateSearch || data.date === year + '-' + (month + 1) + '-' + day)
      } else {
        this.subDataTable = this.tableData
      }
    }
  },
  mounted () {
    /**
     * Check Authentication in session storage
     * If false => redirect to login page
     */
    this.checkAuthen()
    this.subDataTable = this.tableData
  },
  methods: {
    /**
     * Validate and Submit Form
     */
    submitForm () {
      this.tempForm = {...this.pitchForm}
      this.$refs['pitchForm'].validate((valid) => {
        if (valid) {
          this.$confirm('Create this notification?. Continue?', 'Warning', {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            const loader = this.getLoader()
            setTimeout(() => {
              this.resetForm()
              this.showMessage('Create Pitch Successful !!!', 'success')
              this.closeLoader(loader)
            }, 1000)
          })
        } else {
          return false
        }
      })
    },
    /**
     * Reset Form to empty
     */
    resetForm () {
      this.$refs['pitchForm'].resetFields()
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
     * Check Authen in Session Storage
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
