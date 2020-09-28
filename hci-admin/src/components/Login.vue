!<template>
    <div id="login-form">
        <el-card>
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
      btnLabel: 'Logins'
    }
  },
  methods: {
    showForm () {
      console.log(this.formData)

      if (this.formData.username === 'leminhtuan' && this.formData.password === '1') {
        this.$router.push('/home')
      } else {
        this.$message('Username or Password is incorrect !!!!')
      }
    }
  }
}
</script>
<style>
    #login-form {
        margin-top: 20vh;
        width: 30%;
    }
    body {
        background-color: #2d3436;
    }
</style>
