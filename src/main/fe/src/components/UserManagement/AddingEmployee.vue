!<template>
  <div id="addNewEmployee">
    <el-row style="text-align: left">
      <el-col :span="5">
        <hci-menu :activeIndex='"1-2"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <div id="new-notification-form">
          <h1>Thêm mới nhân viên</h1>
          <el-form
            :model="employeeForm"
            ref="employeeForm"
            label-width="150px"
            :rules="rules"
            label-position="left"
            class="demo-dynamic"
          >
            <el-form-item
              prop="email"
              label="Email"
            >
              <el-input v-model="employeeForm.email" style="width: 40%"/>
            </el-form-item>
            <el-form-item
              prop="name"
              label="Họ tên"
            >
              <el-input v-model="employeeForm.name" style="width: 40%"/>
            </el-form-item>
            <el-form-item label="Giới tính" prop="gender">
              <el-radio-group v-model="employeeForm.gender">
                <el-radio :label="true">Nam</el-radio>
                <el-radio :label="false">Nữ</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="Chi nhánh" required prop="storeId">
                    <el-select
                      style="width: 30%; margin-left: 0.1%"
                      v-model="employeeForm.storeId"
                      placeholder="---Chọn chi nhánh cửa hàng---"
                      @change="changeStore">
                        <el-option v-for="item in storeList" :key="item.id" :label="item.name" :value="item.id"/>
                    </el-select>
                </el-form-item>
            <el-form-item
              prop="address"
              label="Địa chỉ"
            >
              <el-input v-model="employeeForm.address" style="width: 40%"/>
            </el-form-item>
            <el-form-item
              prop="bankNumber"
              label="Tài khoản ngân hàng"
            >
              <el-input v-model="employeeForm.bankNumber" style="width: 40%"/>
            </el-form-item>
            <el-form-item
              prop="phone"
              label="Số điện thoại"
            >
              <el-input
                v-model="employeeForm.phone"
                maxlength="10"
                style="width: 40%"
                :rules="rulePhone"
                @input="checkPhoneFormat(employeeForm.phone)"
              />
            </el-form-item>
            <el-form-item label="Lương (VNĐ)" prop="salary">
              <el-input-number v-model="employeeForm.salary" :min="100000" :step="1000"/>
            </el-form-item>
            <el-form-item>
              <el-button
                type="primary"
                @click="submitForm('employeeForm')"
                >Submit</el-button
              >
              <el-button @click="resetForm('employeeForm')"
                >Reset</el-button
              >
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import { mapMutations, mapActions } from 'vuex'
export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      loader: {},
      employeeForm: {
        email: '',
        name: '',
        address: '',
        phone: '',
        gender: true,
        storeId: '',
        bankNumber: '',
        salary: '',
        active: true
      },
      rulePhone: [],
      storeList: [],
      tempForm: {},
      rules: {
        email: [
          {
            required: true,
            message: 'Please input email address',
            trigger: 'blur'
          },
          {
            type: 'email',
            message: 'Please input correct email address',
            trigger: ['blur', 'change']
          }
        ],
        name: [
          {
            required: true,
            message: 'Please input name',
            trigger: 'blur'
          }
        ],
        address: [
          {
            required: true,
            message: 'Please input address',
            trigger: 'blur'
          }
        ],
        storeId: [
          {
            required: true,
            message: 'Please input store',
            trigger: 'change'
          }
        ]
      }
    }
  },
  computed: {
  },
  mounted () {
    this.checkAuthen()
    this.init()
  },
  methods: {
    ...mapMutations('createAccount', ['_setAccountFormData']),
    ...mapActions('workAssign', ['_getStoreList']),
    ...mapActions('employee', ['createNewEmployee']),
    /**
     * Run when page initialization
     */
    init () {
      let _this = this
      new Promise((resolve, reject) => {
        resolve()
      }).then(() => {
        return _this._getStoreList()
      }).then(res => {
        _this.storeList = res.data
      })
    },
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
    },
    checkPhoneFormat (val) {
      this.employeeForm.phone = val.replace(/[^0-9]/g, '')
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$confirm('Bạn có muốn thêm nhân viên này ?', 'Warning', {
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Hủy bỏ',
            type: 'warning'
          }).then(() => {
            this.loader = this.getLoader()
            return this.createNewEmployee(this.employeeForm)
          }).then(() => {
            this.closeLoader(this.loader)
            this.showMessage('Thêm mới nhân viên thành công !!!', 'success')
            this.$refs['employeeForm'].resetFields()
          })
        } else {
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
#new-notification-form {
  margin-left: 5%;
}
.txt-content {
  width: 50%;
}

.txt-content .el-textarea__inner {
  min-height: 15vh !important;
}
</style>
