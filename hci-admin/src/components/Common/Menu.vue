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
          <i class="el-icon-data-analysis"></i>
          <span>Trang Chủ</span>
        </template>
        <el-menu-item index="1-1" @click="doHome">Thống kê</el-menu-item>
        <el-menu-item index="1-2" @click="historyPitch">Lịch sử</el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-football"></i>
          <span>Sân</span>
        </template>
        <el-menu-item index="2-1" @click="doNewPitch">Thêm mới sân</el-menu-item>
        <el-menu-item index="2-2" @click="managePitch">Quản lý sân</el-menu-item>
        <el-menu-item index="2-3" @click="orderPitchManagement">Quản lý đặt sân</el-menu-item>
      </el-submenu>
      <!-- <el-menu-item index="3" @click="doNotification">
        <i class="el-icon-s-order"></i>
        <span>Thêm mới thông báo</span>
      </el-menu-item> -->
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>Thông tin chung</span>
        </template>
        <el-menu-item index="4-0" @click="doProfile">Hồ sơ</el-menu-item>
        <el-menu-item index="4-1" @click="doChangePassword">Đổi mặt khẩu</el-menu-item>
        <el-menu-item index="4-2" @click="doLogout">Đăng xuất</el-menu-item>
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
      this.transitTo('Statistic', undefined)
    },
    /**
     * Go to new pitch page 2-1
     */
    doNewPitch () {
      this.transitTo('NewPitch', undefined)
    },
    /**
     * Go to manage pitch pitch page 2-2
     */
    managePitch () {
      this.transitTo('ManagePitch', undefined)
    },
    /**
     * Go to manage pitch pitch page 2-3
     */
    historyPitch () {
      this.transitTo('HistoryPitch', undefined)
    },
    /**
     * Go to manage pitch pitch page 2-3
     */
    orderPitchManagement () {
      this.transitTo('OrderPitchManagement', undefined)
    },
    /**
     * Go to new notification page 3
     */
    doNotification () {
      this.transitTo('Notification', undefined)
    },
    /**
     * Go to new change password page 4-0
     */
    doProfile () {
      this.transitTo('Profile', undefined)
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
      this.$confirm('Bạn có muốn đăng xuất?', 'Đăng xuất', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy Bỏ',
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
<style>
  #hci-menu{
    position: fixed;
    left: 0;
    z-index: 1;
    width: 20.8333%;
  }
</style>
