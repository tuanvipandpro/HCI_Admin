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
              <el-button style="float: right; padding: 3px 0" type="text" @click="editCard(1)">
                <i class="el-icon-edit"></i>
              </el-button>
            </div>
            <div class="item">
              <i class="el-icon-user"></i>
              {{cardFirst.name}}
            </div>
            <div class="item">
              <i class="el-icon-message"></i>
              {{cardFirst.email}}
            </div>
            <div class="item">
              <i class="el-icon-coordinate"></i>
              {{cardFirst.address}}
            </div>
            <div class="item">
              <i class="el-icon-mobile-phone"></i>
              {{cardFirst.phone}}
            </div>
          </el-card>
          <el-card class="box-card" style="margin-top: 1%; width: 95%;">
            <div slot="header" class="clearfix">
              <span style="font-size: 20px; font-weight: bold;">Sân quản lý</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="editCard(2)">
                <i class="el-icon-edit"></i>
              </el-button>
            </div>
            <div class="item">
              <i class="el-icon-baseball"></i>
              {{cardSecond.name}}
            </div>
            <div class="item">
              <i class="el-icon-message"></i>
              {{cardSecond.email}}
            </div>
            <div class="item">
              <i class="el-icon-location-outline"></i>
              {{cardSecond.address}}
            </div>
            <div class="item">
              <i class="el-icon-mobile-phone"></i>
              {{cardSecond.phone}}
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-dialog title="Chỉnh sửa" :visible.sync="visibleCard" style="text-align: left">
        <el-form :model="formData">
            <el-form-item ><el-input placeholder="Tên" v-model="formData.name"/></el-form-item>
            <el-form-item ><el-input placeholder="Email" v-model="formData.email"/></el-form-item>
            <el-form-item ><el-input placeholder="Địa chỉ" v-model="formData.address"/></el-form-item>
            <el-form-item ><el-input placeholder="Số điện thoại" v-model="formData.phone"/></el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="visibleCard = false">Hủy Bỏ</el-button>
          <el-button type="primary" @click="visibleCard = false">Xác Nhận</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import Menu from '../Common/Menu'

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      visibleCard: false,
      name: '',
      cardFirst: {
        name: 'Lê Minh Tuấn',
        email: 'tuanlmse130138@fpt.edu.vn',
        address: ' Lô E2a-7, Đường D1 Khu Công nghệ cao, P. Long Thạnh Mỹ, Q. 9, TP. Hồ Chí Minh',
        phone: '0326 987 216'
      },
      cardSecond: {
        name: 'Sân Phúc Lộc',
        email: 'phuclocfootball@gmail.com',
        address: ' Lô E2a-7, Đường D1 Khu Công nghệ cao, P. Long Thạnh Mỹ, Q. 9, TP. Hồ Chí Minh',
        phone: '0326 987 216'
      },
      formData: {}
    }
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
    editCard (value) {
      switch (value) {
        case 1 : {
          this.formData = this.cardFirst
          break
        }
        case 2 : {
          this.formData = this.cardSecond
          break
        }
      }
      this.visibleCard = !this.visibleCard
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
