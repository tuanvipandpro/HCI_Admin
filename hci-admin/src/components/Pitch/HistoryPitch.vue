!<template>
  <div id="new-pitch">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"1-2"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <h1 style="text-align: center">Lịch sử đặt sân</h1>
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
              label="Thời gian"
              align="center"
              prop="startTime">
            </el-table-column>
            <!-- <el-table-column
              label="Thời gian kết thúc"
              align="center"
              prop="endTime">
            </el-table-column> -->
            <el-table-column
              label="Tổng tiền"
              prop="totalPrice">
            </el-table-column>
            <el-table-column
              label="Trạng thái"
              prop="status">
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
        date: '2020-09-01',
        startTime: '6:00 - 7:30',
        // endTime: '7:30',
        name: 'Anh Dũng',
        pitch: 'Sân 5',
        address: 'Dĩ An, Bình Dương',
        // addressPitch: '442 Lê Văn Việt, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0123456789',
        totalPrice: '200.000 VNĐ',
        status: 'Hoàn thành'
      }, {
        date: '2020-11-02',
        startTime: '14:30 - 15:30',
        // endTime: '15:30',
        name: 'Minh Tuấn',
        pitch: 'Sân 7',
        address: 'An Phú, Thuận An, Bình Dương',
        // addressPitch: '5 Đường số 447, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0123456789',
        totalPrice: '100.000 VNĐ',
        status: 'Hoàn thành'
      }, {
        date: '2020-11-20',
        startTime: '15:00 - 17:00',
        // endTime: '17:00',
        name: 'Phương Đạt',
        pitch: 'Sân 5',
        address: 'Q7, TP.HCM',
        // addressPitch: ' 8 Đường số 51, Hiệp Bình Chánh, Thủ Đức, TP.HCM',
        phone: '0123456789',
        totalPrice: '250.000 VNĐ',
        status: 'Hủy'
      }, {
        date: '2020-11-20',
        startTime: '17:15 - 18:45',
        // endTime: '18:45',
        name: 'Thanh Tú',
        pitch: 'Sân 5',
        address: 'Gò Vấp, TP.HCM',
        // addressPitch: '8 Đường số 51, Hiệp Bình Chánh, Thủ Đức, TP.HCM',
        phone: '0123456789',
        totalPrice: '200.000 VNĐ',
        status: 'Đang chờ'
      }, {
        date: '2020-11-18',
        startTime: '18:00 - 20:00',
        // endTime: '20:00',
        name: 'Hoàng Tâm',
        pitch: 'Sân 7',
        address: 'Q9, TP.HCM',
        // addressPitch: '5 Đường số 447, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0123456789',
        totalPrice: '250.000 VNĐ',
        status: 'Đang chờ'
      }, {
        date: '2020-11-20',
        startTime: '19:00 - 20:00',
        // endTime: '20:00',
        pitch: 'Sân 5',
        address: 'Dĩ An, Bình Dương',
        // addressPitch: '442 Lê Văn Việt, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0123456789',
        totalPrice: '180.000 VNĐ',
        status: 'Hủy'
      }, {
        date: '2020-11-19',
        startTime: '19:00 -20:30',
        // endTime: '20:30',
        pitch: 'Sân 5',
        address: 'Q5, TP.HCM',
        // addressPitch: '442 Lê Văn Việt, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0123456789',
        totalPrice: '200.000 VNĐ',
        status: 'Đang chờ'
      }],
      startTime: '',
      endTime: '',
      pickerOptions: {
        // disabledDate (time) {
        //   let yesterday = new Date()
        //   yesterday.setDate(yesterday.getDate() - 1)
        //   return time.getTime() <= yesterday
        // }
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
        day = day < 10 ? '0' + day : day
        let month = date.getMonth()
        month = month < 10 ? '0' + (month + 1) : (month + 1)
        let year = date.getFullYear()
        this.subDataTable = this.tableData.filter(data => !this.dateSearch || data.date === year + '-' + month + '-' + day)
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
