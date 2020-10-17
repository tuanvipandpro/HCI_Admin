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
                <el-form-item label="Chọn cửa hàng" required prop="store">
                    <el-select style="width: 30%; margin-left: 0.1%" v-model="formData.store" placeholder="Chọn cửa hàng cần phân công">
                        <el-option label="Cửa hàng 1" value="1"/>
                        <el-option label="Cửa hàng 2" value="2"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="Chọn ca làm" required prop="shift">
                    <el-select style="width: 30%; margin-left: 1.8%" v-model="formData.shift" placeholder="Chọn ca cần giao việc">
                        <el-option label="Ca Sáng" value="1"/>
                        <el-option label="Ca Chiều" value="2"/>
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

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      formData: {
        store: '',
        shift: '',
        employee: ''
      },
      formRules: {
        store: [
          {required: true, message: 'Vui lòng chọn cửa hàng cần giao ca', trigger: 'blur'}
        ],
        shift: [
          {required: true, message: 'Vui lòng chọn ca cần giao', trigger: 'blur'}
        ],
        employee: [
          {required: true, message: 'Vui lòng chọn nhân viên cần giao', trigger: 'blur'}
        ]
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
    }
  },
  methods: {
    ...mapActions('workAssign', ['_getStoreList', '_getShiftList', '_getEmployeeList', '_assignWork']),
    /**
     * Submit Form
     */
    submitForm () {
      this.$refs['assignForm'].validate(res => {
        console.log(res)
        if (res) {
          alert('ok')
          this.resetForm()
        } else {
          alert('not ok')
        }
      })
    },
    /**
     * Reset Form
     */
    resetForm () {
      this.formData.store = ''
      this.formData.shift = ''
      this.formData.employee = ''
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
