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
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-s-custom"></i>
          <span>Quản lý nhân viên</span>
        </template>
        <el-menu-item index="1-1" @click="doHome">Tất cả nhân viên</el-menu-item>
        <el-menu-item index="1-2" @click="insertEmployee">Thêm mới nhân viên</el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-s-cooperation"></i>
          <span>Quản lý tài khoản</span>
        </template>
        <el-menu-item index="2-1" @click="getAllAccounts">Tất cả tài khoản</el-menu-item>
        <el-menu-item index="2-2" @click="doNewAccount">Tạo mới tài khoản</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-edit-outline"></i>
          <span>Quản lý tin tức</span>
        </template>
        <el-menu-item index="3-1" @click="getNotifications">Tất cả tin tức</el-menu-item>
        <el-menu-item index="3-2" @click="doNotification">Thêm mới tin tức</el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-date"></i>
          <span>Quản lý ca</span>
        </template>
        <el-menu-item index="4-1" @click="doWork">Tất cả ca làm việc</el-menu-item>
        <el-menu-item index="4-2" @click="doWorkRequest">Ca làm đề xuất</el-menu-item>
        <el-menu-item index="4-3" @click="doWorkAssign">Phân công</el-menu-item>
      </el-submenu>
      <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>Cài đặt</span>
        </template>
        <el-menu-item index="5-1" @click="doChangePassword">Đổi mặt khẩu</el-menu-item>
        <el-menu-item index="5-2" @click="doLogout">Đăng xuất</el-menu-item>
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
     * Go to insert employee page 1-2
     */
    insertEmployee () {
      this.transitTo('AddingEmployee', undefined)
    },
    /**
     * Go to account management page 2-1
     */
    getAllAccounts () {
      this.transitTo('AccountManagement', undefined)
    },
    /**
     * Go to new account page 2-1
     */
    doNewAccount () {
      this.transitTo('NewAccount', undefined)
    },
    /**
     * Go to manage pitch pitch page 2-2
     */
    managePitch () {
      this.transitTo('PitchManagement', undefined)
    },
    /**
     * Go to new notification page 3
     */
    getNotifications () {
      this.transitTo('NotificationManagement', undefined)
    },
    /**
     * Go to new notification page 3
     */
    doNotification () {
      this.transitTo('Notification', undefined)
    },
    /**
     * Go to work page 4-1
     */
    doWork () {
      this.transitTo('Work', undefined)
    },
    /**
     * Go to work request page 4-2
     */
    doWorkRequest () {
      this.transitTo('WorkRequest', undefined)
    },
    /**
     * Go to work page 4-2
     */
    doWorkAssign () {
      this.transitTo('WorkAssign', undefined)
    },
    /**
     * Go to new change password page 5-1
     */
    doChangePassword () {
      this.transitTo('ChangePassword', undefined)
    },
    /**
     * Logout 5-2
     */
    doLogout () {
      this.$confirm('Bạn có muốn đăng xuất?', 'Đăng xuất ?', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy Bỏ',
        type: 'warning'
      }).then(() => {
        const loader = this.getLoader()
        sessionStorage.clear()
        this.transitTo('Login', undefined)
        this.closeLoader(loader)
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
<style>
  #hci-menu{
      position: fixed;
      left: 0;
      z-index: 1;
      width: 20.83333%;
  }
</style>
