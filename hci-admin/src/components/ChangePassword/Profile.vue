!<template>
  <div id="profile">
    <el-row style="text-align: left">
      <!-- Menu -->
      <el-col :span='5'>
        <hci-menu :activeIndex='"4-0"' />
      </el-col>
      <!-- Content -->
      <el-col :offset="5" :span="19">
        <div id="profile-container">
          <el-card class="box-card" style="margin-top: 1%; width: 95%;">
            <div slot="header" class="clearfix">
              <span style="font-size: 20px; font-weight: bold;">Tài khoản</span>
              <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-edit"></i></el-button>
            </div>
            <div class="item">
              <i class="el-icon-user"></i>
              Lê Minh Tuấn
            </div>
            <div class="item">
              <i class="el-icon-message"></i>
              tuanlmse130138@fpt.edu.vn
            </div>
            <div class="item">
              <i class="el-icon-coordinate"></i>
              Lô E2a-7, Đường D1 Khu Công nghệ cao, P. Long Thạnh Mỹ, Q. 9, TP. Hồ Chí Minh
            </div>
            <div class="item">
              <i class="el-icon-mobile-phone"></i>
              0326 987 216
            </div>
          </el-card>
          <el-card class="box-card" style="margin-top: 1%; width: 95%;">
            <div slot="header" class="clearfix">
              <span style="font-size: 20px; font-weight: bold;">Sân quản lý</span>
              <el-button style="float: right; padding: 3px 0" type="text"><i class="el-icon-edit"></i></el-button>
            </div>
            <div class="item">
              <i class="el-icon-baseball"></i>
              Sân Phúc Lộc
            </div>
            <div class="item">
              <i class="el-icon-message"></i>
              phuclocfootball@gmail.com
            </div>
            <div class="item">
              <i class="el-icon-location-outline"></i>
              Lô E2a-7, Đường D1 Khu Công nghệ cao, P. Long Thạnh Mỹ, Q. 9, TP. Hồ Chí Minh
            </div>
            <div class="item">
              <i class="el-icon-mobile-phone"></i>
              0326 987 216
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {}
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
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
      if (!sessionStorage.getItem('username')) {
        this.transitTo('Login', undefined)
      }
    }
  }
}
</script>

<style>
  #profile-container{
    margin-left: 2% ;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .item {
    margin-bottom: 18px;
  }

  i {
    margin-right: 8px;  
  }
</style>
