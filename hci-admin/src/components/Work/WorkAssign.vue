!<template>
  <div id="work-assign">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"4-3"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <el-form
            :model="formData"
            :rules="formRules"
            style="margin-left: 1%"
            ref="assignForm"
            status-icon>
                <h2>Phân công ca</h2>
                <el-form-item label="Chọn ngày" required prop="date">
                    <el-date-picker
                      style="width: 30%; margin-left: 2.8%"
                      v-model="formData.date"
                      type="date"
                      placeholder="Chọn ngày cần giao ca"
                      :picker-options="pickerOptions"
                      @change="abc"/>
                </el-form-item>
                <el-form-item label="Chọn cửa hàng" required prop="store">
                    <el-select
                      style="width: 30%; margin-left: 0.1%"
                      v-model="formData.store"
                      placeholder="Chọn cửa hàng cần phân công"
                      @change="changeStore">
                        <el-option v-for="item in storeList" :key="item.id" :label="item.name" :value="item.id"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="Chọn ca làm" required prop="shift">
                    <el-select
                      style="width: 30%; margin-left: 1.8%"
                      v-model="formData.shift"
                      placeholder="Chọn ca cần giao việc"
                      @change="changeShift">
                        <el-option v-for="item in shiftList" :key="item.id" :label="item.name" :value="item.id"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="Chọn nhân viên" required prop="employee">
                    <el-select style="width: 30%" v-model="formData.employee" placeholder="Chọn ca cần giao việc">
                        <el-option label="Nhân viên 1" value="1"/>
                        <el-option label="Nhân viên 2" value="2"/>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm">Xác nhận</el-button>
                    <el-button @click="resetForm">Làm mới</el-button>
                </el-form-item>
          </el-form>
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
      storeList: [],
      shiftList: [],
      employeeList: [],
      formData: {
        date: '',
        store: '',
        shift: '',
        employee: ''
      },
      formRules: {
        date: [
          {required: true, message: 'Vui lòng chọn ngày cần giao ca', trigger: 'blur'}
        ],
        store: [
          {required: true, message: 'Vui lòng chọn cửa hàng cần giao ca', trigger: 'blur'}
        ],
        shift: [
          {required: true, message: 'Vui lòng chọn ca cần giao', trigger: 'blur'}
        ],
        employee: [
          {required: true, message: 'Vui lòng chọn nhân viên cần giao', trigger: 'blur'}
        ]
      },
      pickerOptions: {
        disabledDate (time) {
          let limit = new Date()
          limit.setDate(limit.getDate() + 7)
          return time.getTime() <= limit
        }
      }
      // end
    }
  },
  computed: {
    ...mapState('workAssign', ['_storeList', '_shiftList', '_employeeList'])
  },
  mounted () {
    if (!sessionStorage.getItem('username')) {
      this.transitTo('Login', undefined)
    } else {
      const loader = this.getLoader()
      this._getStoreList().then(res => {
        this.closeLoader(loader)
        this.storeList = [...this._storeList]
      }).catch(e => {
        this.closeLoader(loader)
        console.error(e)
      })
    }
  },
  methods: {
    ...mapActions('workAssign', ['_getStoreList', '_getShiftList', '_getEmployeeList', '_assignWork']),
    abc () {
      console.log(moment(this.formData.date).format('yyyy-MM-DD'))
    },
    /**
     * Event when change store
     */
    changeStore () {
      const loader = this.getLoader()
      this._getShiftList(this.formData.store).then(res => {
        this.closeLoader(loader)
        this.shiftList = [...this._shiftList]
      }).catch(e => {
        this.closeLoader(loader)
        console.error(e)
      })
    },
    /**
     * Event when change shift
     */
    changeShift () {
      const shift = this.shiftList.find(s => s.id === this.formData.shift)
      console.log(shift)
    },
    /**
     * Submit Form
     */
    submitForm () {
      this.$refs['assignForm'].validate(res => {
        if (res) {
          // alert('ok')
          this.resetForm()
        } else {
          // alert('not ok')
        }
      })
    },
    /**
     * Reset Form
     */
    resetForm () {
      this.$refs['assignForm'].resetFields()
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
