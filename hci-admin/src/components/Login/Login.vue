<template>
    <div id="login-container">
        <el-card id="login-form">
            <h1>Đăng nhập</h1>
            <el-form>
                <el-form-item>
                    <el-input v-model="formData.username" placeholder="Tài khoản"/>
                </el-form-item>
                <el-form-item>
                    <el-input type="password" v-model="formData.password" placeholder="Mật khẩu"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleForm">Đăng nhập</el-button>
                </el-form-item>
                <el-form-item style="margin-top: -3vh">
                    <el-link :underline="false" type="primary" href="#/forget">Quên Mật Khẩu</el-link>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
export default {
  data () {
    return {
      formData: {
        username: '',
        password: ''
      }
    }
  },
  methods: {
    /**
     * Handle Login Form
     */
    handleForm () {
      const loader = this.getLoader()

      setTimeout(() => {
        // Todo
        this.checkLogin()
        this.closeLoader(loader)
      }, 1000)
    },
    /**
     * Check Login
     */
    checkLogin () {
      const accountList = [
        {username: 'leminhtuan', password: '1'},
        {username: 'dunggla', password: '1'},
        {username: 'admin', password: '1'}
      ]
      if (accountList.some(account => account.username === this.formData.username && account.password === this.formData.password)) {
        sessionStorage.setItem('username', this.formData.username)
        let scrNm = (this.formData.username === 'admin') ? 'AdminHome' : 'Statistic'
        this.transitTo(scrNm, {username: this.formData.username})
      } else {
        this.showMessage('Tài khoản hoặc mật khẩu không chính xác !', 'warning')
      }
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
    #login-container {
      width: 100%;
      height: 100vh;
      background-color: azure;
      position: relative;
      display: flex;
      justify-content: center;
    }

    #login-form {
        margin-top: 20vh;
        width: 22vw;
        height: 50vh;
    }
</style>
