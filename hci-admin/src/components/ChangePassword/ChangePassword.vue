!<template>
  <div id="change-password">
    <el-row style="text-align: left">
      <!-- Menu -->
      <el-col :span='5'>
        <hci-menu :activeIndex='"4-1"' />
      </el-col>
      <!-- Content -->
      <el-col :span="19">
        <div id="forget-form">
          <el-form
            ref="forgetForm"
            status-icon
            :model="forgetForm"
            :rules="rules"
          >
            <h2>Change Password</h2>
            <el-form-item label="New Password" prop="newPassword">
              <el-input type="password" v-model="forgetForm.newPassword" style="width: 50%; margin-left: 2%"/>
            </el-form-item>
            <el-form-item label="Confirm Password" prop="confirmPassword">
              <el-input type="password" v-model="forgetForm.confirmPassword" style="width: 50%"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">Submit</el-button>
              <el-button @click="resetForm">Reset</el-button>
            </el-form-item>
          </el-form>
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
    const validateNewPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input new password !!!'))
      // } else if (value !== this.forgetForm.confirmPassword) {
      //   callback(new Error('New password and confirm password isn\'t match !!!'))
      } else {
        callback()
      }
    }
    const validateConfirmPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input confirm password !!!'))
      } else if (value !== this.forgetForm.newPassword) {
        callback(new Error('New password and confirm password isn\'t match !!!'))
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
        newPassword: [{validator: validateNewPass, trigger: 'blur'}],
        confirmPassword: [{validator: validateConfirmPass, trigger: 'blur'}]
      }
    }
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
    submitForm () {
      this.$refs['forgetForm'].validate((valid) => {
        if (valid) {
          this.$confirm('Your password will be change. Continue?', 'Warning', {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            const loader = this.getLoader()
            setTimeout(() => {
              this.showMessage('Your password is changed !!!', 'success')
              this.closeLoader(loader)
            }, 1000)
          })
        } else {
          return false
        }
      })
    },
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
      if (sessionStorage.getItem('username')) {
        // this.showMessage('Welcome back, ' + this.$route.params.username, 'success')
      } else {
        this.transitTo('Login', undefined)
      }
    }
  }
}
</script>

<style>
  #forget-form{
    margin-left: 1% ;
  }
</style>
