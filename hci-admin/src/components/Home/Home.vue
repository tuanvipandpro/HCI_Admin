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
          <el-menu-item index="1">
            <i class="el-icon-s-custom"></i>
            <span>User Management</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-football"></i>
              <span>Pitch Management</span>
            </template>
            <el-menu-item index="2-1">New Pitch</el-menu-item>
            <el-menu-item index="2-2">Update User</el-menu-item>
          </el-submenu>
          <el-menu-item index="3">
            <i class="el-icon-s-order"></i>
            <span>New Notification</span>
          </el-menu-item>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span>Setting</span>
            </template>
            <el-menu-item index="4-1">Change Password</el-menu-item>
            <el-menu-item index="4-2" @click="doLogout">Logout</el-menu-item>
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
      const loader = this.getLoader()

      setTimeout(() => {
        // Todo
        sessionStorage.clear()
        this.transitTo('Login', undefined)
        this.closeLoader(loader)
      }, 2000)
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
