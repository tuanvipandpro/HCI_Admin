<template>
    <div id="forget-container">
        <el-card id="forget-form">
            <h3>Lấy lại mật khẩu</h3>
            <el-form ref="forgetPasswordForm" status-icon :rules="rules" :model="formData">
                <el-form-item prop="username">
                    <el-input v-model="formData.username" placeholder="Tài khoản"/>
                </el-form-item>
                <el-divider/>
                <el-form-item>
                    <el-button type="primary" @click="handleForm">Xác Nhận</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-dialog title="Xác thực tài khoản" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
            <el-form v-if="optionForm === 0">
                <h4>Tips: Nhập mã xác thực từ email bạn đã đăng ký</h4>
                <el-form-item>
                    <el-input v-model="verifyCode" placeholder="Mã xác thực"/>
                </el-form-item>
                <el-divider/>
                <el-form-item>
                    <el-button type="primary" @click="optionForm = 1">Xác Nhận</el-button>
                </el-form-item>
            </el-form>
            <el-form ref="confirmPasswordForm" status-icon :rules="confirmRules" :model="formPassword" v-else>
                <el-form-item prop="password">
                    <el-input type="password" v-model="formPassword.password" placeholder="Mật khẩu"/>
                </el-form-item>
                <el-form-item prop="confirm">
                    <el-input type="password" v-model="formPassword.confirm" placeholder="Xác nhận mật khẩu"/>
                </el-form-item>
                <el-divider/>
                <el-form-item>
                    <el-button type="primary" @click="afterHandlePopup">Xác Nhận</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
export default {
  data () {
    return {
      dialogFormVisible: false,
      formData: {
        username: '',
        password: ''
      },
      formPassword: {
        password: '',
        confirm: ''
      },
      verifyCode: '',
      optionForm: 0,
      // Rules
      rules: {
        username: [{required: true, message: 'Vui lòng nhập tài khoản !', trigger: 'blur'}]
      },
      confirmRules: {
        password: [{required: true, message: 'Vui lòng nhập mật khẩu mới !', trigger: 'blur'}],
        confirm: [{required: true, message: 'Vui lòng xác nhận mật khẩu mới !', trigger: 'blur'}]
      }
    }
  },
  methods: {
    /**
     * Handle Login Form
     */
    handleForm () {
      this.$refs['forgetPasswordForm'].validate((valid) => {
        if (valid) {
          const loader = this.getLoader()
          setTimeout(() => {
            this.dialogFormVisible = true
            this.closeLoader(loader)
          }, 1000)
        } else return false
      })
    },
    /**
     * Verify account by verify code
     */
    afterHandlePopup () {
      const loader = this.getLoader()
      setTimeout(() => {
        this.transitTo('Login', undefined)
        this.closeLoader(loader)
      }, 1000)
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
        // path: path,
        // query: data
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
<style>
    #forget-container {
      width: 100%;
      height: 100vh;
      background-color: azure;
      position: relative;
      display: flex;
      justify-content: center;
    }

    #forget-form {
        margin-top: 20vh;
        width: 25vw;
        height: 35vh;
    }
</style>
