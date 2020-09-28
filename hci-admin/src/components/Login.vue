!<template>
    <div id="login-container">
        <el-card id="login-form">
            <h1>Login</h1>
            <el-form>
                <el-form-item>
                    <el-input v-model="formData.username" placeholder="Username"/>
                </el-form-item>
                <el-form-item>
                    <el-input type="password" v-model="formData.password" placeholder="Password"/>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="showForm">Login</el-button>
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
      },
      fullscreenLoading: false,
      btnLabel: 'Logins'
    }
  },
  methods: {
    showForm () {
      const loading = this.$loading({
        lock: true,
        spinner: 'el-icon-loading',
        text: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })

      setTimeout(() => {
        // Todo
        this.checkLogin()
        loading.close()
      }, 2000)
    },
    /**
     * Check Login
     */
    checkLogin () {
      if (this.formData.username === 'leminhtuan' && this.formData.password === '1') {
        this.transitTo('/home', {username: this.formData.username})
      } else {
        this.showMessage('Sorry, username or password is incorrect !', 'warning')
      }
    },
    /**
     * Transit to another component
     * @param path
     * @param data
     */
    transitTo (path, data) {
      const param = {
        path: path,
        query: data
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
      /* center */
      display: flex;
      justify-content: center;
      /* align-items: center; */
    }

    #login-form {
        margin-top: 25vh;
        width: 22vw;
        height: 35vh;
    }
</style>
