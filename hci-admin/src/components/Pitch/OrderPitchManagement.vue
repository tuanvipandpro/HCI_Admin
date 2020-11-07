!<template>
  <div id="OrderPitchManagement">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-3"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <h1 style="text-align: center">Danh sách đặt sân</h1>
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
              width="230%"
              prop="date">
            </el-table-column>
            <el-table-column
              label="Sân"
              align="center"
              width="100%"
              prop="pitch">
            </el-table-column>
            <el-table-column
              label="Thời gian"
              align="center"
              width="380%"
              prop="startTime">
            </el-table-column>
            <el-table-column
              label="Tổng tiền"
              width="150%"
              align="center"
              prop="totalPrice">
            </el-table-column>
            <el-table-column
              label="Hủy sân"
              align="center"
              width="300%">
              <template slot-scope="scope">
                <el-button
                  @click.native.prevent="deleteRow(scope.$index, subDataTable)"
                  type="text"
                  >
                  Hủy
                </el-button>
              </template>
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
        startTime: '06:00 - 07:30',
        // endTime: '7:30',
        name: 'Anh Dũng',
        pitch: 'Sân 5',
        address: 'Dĩ An, Bình Dương',
        // addressPitch: '442 Lê Văn Việt, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0902348880',
        totalPrice: '200.000 VNĐ',
        status: 'Đặt thành công'
      }, {
        date: '2020-11-02',
        startTime: '14:30 - 15:30',
        // endTime: '15:30',
        name: 'Minh Tuấn',
        pitch: 'Sân 7',
        address: 'An Phú, Thuận An, Bình Dương',
        // addressPitch: '5 Đường số 447, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0984348981',
        totalPrice: '100.000 VNĐ',
        status: 'Đặt thành công'
      }, {
        date: '2020-11-20',
        startTime: '15:00 - 17:00',
        // endTime: '17:00',
        name: 'Phương Đạt',
        pitch: 'Sân 5',
        address: 'Q7, TP.HCM',
        // addressPitch: ' 8 Đường số 51, Hiệp Bình Chánh, Thủ Đức, TP.HCM',
        phone: '0347238681',
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
        phone: '0168276819',
        totalPrice: '200.000 VNĐ',
        status: 'Đặt thành công'
      }, {
        date: '2020-11-18',
        startTime: '18:00 - 20:00',
        // endTime: '20:00',
        name: 'Hoàng Tâm',
        pitch: 'Sân 7',
        address: 'Q9, TP.HCM',
        // addressPitch: '5 Đường số 447, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '0212574119',
        totalPrice: '250.000 VNĐ',
        status: 'Đặt thành công'
      }, {
        date: '2020-11-20',
        startTime: '19:00 - 20:00',
        // endTime: '20:00',
        pitch: 'Sân 5',
        address: 'Dĩ An, Bình Dương',
        // addressPitch: '442 Lê Văn Việt, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '034574119',
        totalPrice: '180.000 VNĐ',
        status: 'Hủy'
      }, {
        date: '2020-11-19',
        startTime: '19:00 - 20:30',
        // endTime: '20:30',
        pitch: 'Sân 5',
        address: 'Q5, TP.HCM',
        // addressPitch: '442 Lê Văn Việt, Tăng Nhơn Phú A, Q9, TP.HCM',
        phone: '025474119',
        totalPrice: '200.000 VNĐ',
        status: 'Đặt thành công'
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
    deleteRow (index, rows) {
      rows.splice(index, 1)
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
