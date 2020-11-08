!<template>
  <div id="hci-admin-menu">
    <el-menu
      :default-active="activeIndex"
      active-text-color="#ffd04b"
      background-color="#545c64"
      text-color="#ffffff"
      class="el-menu-vertical-demo"
      style="min-height: 100vh"
    >
      <el-menu-item index="1" @click="doHome">
          <i class="el-icon-data-analysis"></i>
          <span>Trang Chủ</span>
      </el-menu-item>
      <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-user"></i>
            <span>Tài Khoản</span>
          </template>
          <el-menu-item index="2-1" @click="doPitchOwner">Quản lý chủ sân</el-menu-item>
          <el-menu-item index="2-2" @click="doUserManagement">Quản lý khách hàng</el-menu-item>
          <!-- <el-menu-item index="2-3" @click="doBlackList">Danh sách đen</el-menu-item> -->
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>Thông tin chung</span>
        </template>
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
      this.transitTo('AdminHome', undefined)
    },
    /**
     * Go to pitch owner page 2-1
     */
    doPitchOwner () {
      this.transitTo('PitchOwner', undefined)
    },
    /**
     * Go to pitch owner page 2-2
     */
    doUserManagement () {
      this.transitTo('UserManagement', undefined)
    },
    /**
     * Go to pitch owner page 2-3
     */
    doBlackList () {
      this.transitTo('BlackList', undefined)
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
  #hci-admin-menu{
    position: fixed;
    left: 0;
    z-index: 1;
    width: 20.8333%;
  }
</style>
