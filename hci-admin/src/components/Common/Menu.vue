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
        <el-menu-item index="2-1" @click="doNewPitch">New Pitch</el-menu-item>
        <el-menu-item index="2-2">Update Pitch</el-menu-item>
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
     * Go to home page 1
     */
    doHome () {
      this.transitTo('UserManagement', undefined)
    },
    /**
     * Go to new pitch page 2-1
     */
    doNewPitch () {
      this.transitTo('NewPitch', undefined)
    },
    /**
     * Go to new notification page 3
     */
    doNotification () {
      this.transitTo('Notification', undefined)
    },
    /**
     * Go to new change password page 4-1
     */
    doChangePassword () {
      this.transitTo('ChangePassword', undefined)
    },
    /**
     * Logout 4-2
     */
    doLogout () {
      this.$confirm('Do you want to logout?', 'Warning', {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        const loader = this.getLoader()
        setTimeout(() => {
          sessionStorage.clear()
          this.transitTo('Login', undefined)
          this.closeLoader(loader)
        }, 1000)
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
        name: name,
        params: data
      }
      this.$router.push(param)
    }
  }
}
</script>
