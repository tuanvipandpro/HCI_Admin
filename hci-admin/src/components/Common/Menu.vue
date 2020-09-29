!<template>
  <div id="hci-menu">
    <el-menu
      :default-active="activeIndex"
      active-text-color="#ffd04b"
      background-color="#545c64"
      text-color="#ffffff"
      class="el-menu-vertical-demo"
      style="min-height: 100vh"
    >
      <el-menu-item index="1" @click="doHome">
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
      <el-menu-item index="3" @click="doNotification">
        <i class="el-icon-s-order"></i>
        <span>New Notification</span>
      </el-menu-item>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>Setting</span>
        </template>
        <el-menu-item index="4-1" @click="doChangePassword"
          >Change Password</el-menu-item
        >
        <el-menu-item index="4-2" @click="doLogout">Logout</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  props: {
    activeIndex: String
  },
  methods: {
    /**
     * Go to home page
     */
    doHome () {
      this.transitTo('Home', undefined)
    },
    /**
     * Go to new notification page
     */
    doNotification () {
      this.transitTo('Notification', undefined)
    },
    /**
     * Go to new change password page
     */
    doChangePassword () {
      this.transitTo('ChangePassword', undefined)
    },
    /**
     * Logout
     */
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
    }
  }
}
</script>
