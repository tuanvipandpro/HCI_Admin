!<template>
  <div id="home">
    <el-row style="text-align: left;">
      <el-col :span='5'>
        <el-menu
           active-text-color="#ffd04b"
           background-color="#545c64"
           text-color="#ffffff"
           class="el-menu-vertical-demo"
           style="min-height: 100vh;"
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-s-custom"></i>
              <span>User Managerment</span>
            </template>
            <el-menu-item index="1-1">Active User</el-menu-item>
            <el-menu-item index="1-2">Ban User</el-menu-item>
          </el-submenu>
          <el-menu-item index="2">
            <i class="el-icon-s-order"></i>
            <span>New Notification</span>
          </el-menu-item>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>Setting</span>
            </template>
            <el-menu-item index="3-1">Change Password</el-menu-item>
            <el-menu-item index="3-2" @click="doLogout">Logout</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  mounted () {
    this.checkAuthen()
  },
  methods: {
    doLogout () {
      const loading = this.$loading({
        lock: true,
        spinner: 'el-icon-loading',
        text: 'Loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })

      setTimeout(() => {
        // Todo
        this.transitTo('Login', undefined)
        loading.close()
      }, 2000)
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
        this.showMessage('Welcome back, ' + this.$route.params.username, 'success')
      } else {
        this.transitTo('Login', undefined)
      }
    }
  }
}
</script>

<style>
</style>
