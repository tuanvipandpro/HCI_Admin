!<template>
  <div id="change-password">
    <el-row style="text-align: left">
      <!-- Menu -->
      <el-col :span='5'>
        <hci-menu :activeIndex='"5-1"' />
      </el-col>
      <!-- Content -->
      <el-col :offset="5" :span="19">
        <div id="forget-form">
          <el-form
            ref="forgetForm"
            status-icon
            :model="forgetForm"
            :rules="rules"
          >
            <h2>Đổi mật khẩu</h2>
            <el-form-item label="Mật khẩu mới" prop="newPassword">
              <el-input type="password" v-model="forgetForm.newPassword" style="width: 50%; margin-left: 2%"/>
            </el-form-item>
            <el-form-item label="Mật khẩu xác nhận" prop="confirmPassword">
              <el-input type="password" v-model="forgetForm.confirmPassword" style="width: 50%"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">Xác nhận</el-button>
              <el-button @click="resetForm">Làm mới</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import { mapActions } from 'vuex'

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    /**
     * Validate Confirm Password
     */
    const validateConfirmPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Vui lòng xác nhận lại mật khẩu mới !!!'))
      } else if (value !== this.forgetForm.newPassword) {
        callback(new Error('Mật khẩu xác nhận không trùng khớp, vui lòng nhập lại !!!'))
      } else {
        callback()
      }
    }
    return {
      forgetForm: {
        newPassword: '',
        confirmPassword: ''
      },
      rules: {
        newPassword: [
          {required: true, message: 'Vui lòng nhập mật khẩu mới !', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, validator: validateConfirmPass, trigger: 'blur'}
        ]
      }
    }
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
    ...mapActions('changePassword', ['_changePassword']),
    /**
     * Validate and Submit Form
     */
    submitForm () {
      this.$refs['forgetForm'].validate((valid) => {
        if (valid) {
          this.$confirm('Bạn có chắc chắn muốn thay đổi mật khẩu ?', 'Đổi mật khẩu ?', {
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Hủy bỏ',
            type: 'warning'
          }).then(() => {
            const loader = this.getLoader()
            this._changePassword(this.forgetForm.newPassword).then(res => {
              this.closeLoader(loader)
              this.showMessage('Mật khẩu đã được thay đổi !!!', 'success')
              this.resetForm()
            })
              .catch(e => {
                this.closeLoader(loader)
                console.error(e)
              })
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
      this.$refs['forgetForm'].resetFields()
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
  #forget-form{
    margin-left: 2% ;
  }
</style>
