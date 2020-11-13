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
                <el-form-item>
                    <el-button type="danger" @click="googleLogin">
                        <i class="fab fa-google" style="margin-right: 5px"/>Google
                    </el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex'
import firebase from 'firebase/app'
import 'firebase/auth'

export default {
  data () {
    return {
      formData: {
        username: '',
        password: ''
      },
      loader: {},
      GGProvider: {}
    }
  },
  computed: {
    ...mapState('login', [
      '_formData'
    ])
  },
  mounted () {
    const firebaseConfig = {
      apiKey: 'AIzaSyAnr_BazeYYEvj5PLtwqXRZWBhNzBL4wuk',
      authDomain: 'hr-app-d0ca5.firebaseapp.com',
      databaseURL: 'https://hr-app-d0ca5.firebaseio.com',
      projectId: 'hr-app-d0ca5',
      storageBucket: 'hr-app-d0ca5.appspot.com',
      messagingSenderId: '543795805536',
      appId: '1:543795805536:web:334cd7c57e9bfb77d53205',
      measurementId: 'G-8L6D7PEBYB'
    }

    if (!firebase.apps.length) firebase.initializeApp(firebaseConfig)
    this.GGProvider = new firebase.auth.GoogleAuthProvider()
  },
  methods: {
    ...mapMutations('login', [
      '_setFormData'
    ]),
    ...mapActions('login', [
      '_checkLogin', '_googleLogin'
    ]),
    /**
     * Check Login By Gmail
     */
    async googleLogin () {
      try {
        const result = await firebase.auth().signInWithPopup(this.GGProvider)
        const idToken = await firebase.auth().currentUser.getIdToken(true)

        const res = await this._googleLogin({idToken: idToken, email: result.email})

        sessionStorage.setItem('username', res.data.username)
        sessionStorage.setItem('employeeId', res.data.employeeId)
        sessionStorage.setItem('token', 'Bearer ' + res.data.token)

        this.transitTo('UserManagement', {username: this.formData.username})
      } catch (e) {
        if (e.toString().includes('Request failed with status code 401')) {
          this.showMessage('Tài khoản của bạn không đủ quyền để đăng nhập vào trang này !', 'warning')
        } else {
          this.showMessage('Tài khoản hoặc mật khẩu không chính xác !', 'warning')
        }
      }
    },
    /**
     * Handle Login Form
     */
    handleForm () {
      this.loader = this.getLoader()
      this.checkLogin()
    },
    /**
     * Check Login
     */
    checkLogin () {
      this._setFormData(this.formData)
      this._checkLogin().then((res) => {
        this.closeLoader(this.loader)

        sessionStorage.setItem('username', this.formData.username)
        sessionStorage.setItem('employeeId', res.data.employeeId)
        sessionStorage.setItem('token', 'Bearer ' + res.data.token)

        this.transitTo('UserManagement', {username: this.formData.username})

        this.showMessage('Welcome back ' + this.formData.username + ' !', 'success')
      }).catch(e => {
        this.closeLoader(this.loader)

        // Check Error Status
        if (e.toString().includes('Request failed with status code 401')) {
          this.showMessage('Tài khoản của bạn không đủ quyền để đăng nhập vào trang này !', 'warning')
        } else {
          this.showMessage('Tài khoản hoặc mật khẩu không chính xác !', 'warning')
        }
      })
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
        margin-top: 12%;
        width: 25%;
        height: 52%;
    }
</style>
