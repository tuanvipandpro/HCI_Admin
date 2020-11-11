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
              prop="date">
            </el-table-column>
            <el-table-column
              label="Sân"
              align="center"
              prop="pitch">
            </el-table-column>
            <el-table-column
              label="Thời gian"
              align="center"
              prop="startTime">
            </el-table-column>
            <el-table-column
              label="Tổng tiền"
              align="center"
              prop="totalPrice">
            </el-table-column>
            <el-table-column
              label="Trạng thái"
              align="center"
              prop="status">
            </el-table-column>
            <el-table-column
              label="Xử lý"
              align="center">
              <template slot-scope="scope">
                <el-button
                  @click.native.prevent="acceptRow(scope.$index, subDataTable)"
                  :disabled="scope.row.status !== 'Chờ xét duyệt'"
                  type="text"
                  >
                  Chấp nhận
                </el-button>
                <el-button
                  @click.native.prevent="deleteRow(scope.$index, subDataTable)"
                  :disabled="scope.row.status !== 'Chờ xét duyệt'"
                  type="text"
                  >
                  Hủy
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-dialog title="Xác nhận" :visible.sync="dialogAcceptFormVisible" :close-on-click-modal="false" >
            <el-form v-if="optionForm === 0" ref="acceptForm" status-icon>
                <el-form-item>
                  <div style="width: 60%" class="accept-dropdown">
                    Chọn sân:
                      <el-select v-model="selectPitch" slot="prepend" placeholder="Select" style="width: 60%">
                        <el-option label="Sân 01" value="1"></el-option>
                        <el-option label="Sân 02" value="2"></el-option>
                        <el-option label="Sân 03" value="3"></el-option>
                      </el-select>
                  </div>
                  <div style="margin-top: 2vh">
                    <el-button type="primary" @click="acceptOrder">Xác nhận</el-button>
                    <el-button type="primary" @click="closeAcceptForm">Hủy</el-button>
                  </div>
                </el-form-item>
            </el-form>
        </el-dialog>
        <el-dialog title="Lý do hủy" :visible.sync="dialogRemoveFormVisible" :close-on-click-modal="false">
          <el-form v-if="optionForm === 0" ref="reasonForm" status-icon :rules="rules" :model="formData">
              <h4>Tips: Nhập lý do hủy đặt sân</h4>
              <el-form-item  prop="reason">
                  <el-input v-model="formData.reason" placeholder="Lý do" type="textarea"/>
              </el-form-item>
              <el-form-item>
                  <el-button type="primary" @click="removeOrder">Xác nhận</el-button>
              </el-form-item>
          </el-form>
        </el-dialog>
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
      formData: {
        reason: ''
      },
      rules: {
        reason: [{required: true, message: 'Vui lòng nhập lý do !', trigger: 'blur'}]
      },
      optionForm: 0,
      dialogRemoveFormVisible: false,
      dialogAcceptFormVisible: false,
      indexAccept: -1,
      indexDetele: -1,
      selectPitch: null,
      tableData: [{
        date: '2020-12-01',
        startTime: '06:00 - 07:30',
        name: 'Anh Dũng',
        pitch: 'Sân 5',
        address: 'Dĩ An, Bình Dương',
        phone: '0902348880',
        totalPrice: '200.000 VNĐ',
        status: 'Chờ xét duyệt'
      }, {
        date: '2020-11-20',
        startTime: '14:30 - 15:30',
        name: 'Minh Tuấn',
        pitch: 'Sân 7',
        address: 'An Phú, Thuận An, Bình Dương',
        phone: '0984348981',
        totalPrice: '100.000 VNĐ',
        status: 'Chờ xét duyệt'
      }, {
        date: '2020-11-20',
        startTime: '15:30 - 17:00',
        name: 'Phương Đạt',
        pitch: 'Sân 5',
        address: 'Q7, TP.HCM',
        phone: '0347238681',
        totalPrice: '250.000 VNĐ',
        status: 'Chờ xét duyệt'
      }, {
        date: '2020-11-20',
        startTime: '17:30 - 18:30',
        name: 'Thanh Tú',
        pitch: 'Sân 5',
        address: 'Gò Vấp, TP.HCM',
        phone: '0168276819',
        totalPrice: '200.000 VNĐ',
        status: 'Chờ xét duyệt'
      },
      {
        date: '2020-11-19',
        startTime: '19:00 - 20:30',
        pitch: 'Sân 5',
        address: 'Q5, TP.HCM',
        phone: '025474119',
        totalPrice: '200.000 VNĐ',
        status: 'Chờ xét duyệt'
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
    acceptRow (index, rows) {
      this.dialogAcceptFormVisible = true
      this.indexAccept = index
    },
    acceptOrder () {
      const loader = this.getLoader()
      this.subDataTable[this.indexAccept].status = 'Đã duyệt'
      this.dialogAcceptFormVisible = false
      this.indexAccept = -1
      this.closeLoader(loader)
    },
    closeAcceptForm () {
      this.dialogAcceptFormVisible = false
      this.indexAccept = -1
    },
    deleteRow (index, rows) {
      this.dialogRemoveFormVisible = true
      this.indexDetele = index
    },
    removeOrder () {
      this.$refs['reasonForm'].validate((valid) => {
        if (valid) {
          const loader = this.getLoader()
          setTimeout(() => {
            this.dialogRemoveFormVisible = false
            this.subDataTable[this.indexDetele].status = 'Đã hủy'
            this.formData.reason = ''
            this.indexDetele = -1
            this.closeLoader(loader)
          }, 1000)
        } else return false
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
  .el-dialog__body {
    padding-top: 1vh;
  }
</style>
