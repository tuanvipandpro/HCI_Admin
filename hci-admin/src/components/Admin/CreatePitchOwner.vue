!<template>
  <div id="owner-main-container">
    <el-row style="text-align: left">
      <!-- Menu -->
      <el-col :span='5'>
        <hci-admin-menu :activeIndex='"2-1"'/>
      </el-col>
      <!-- Content -->
      <el-col :offset="5" :span="19">
        <div>
            <div id="create-new-owner-form">
                <el-button style="margin-top: 1%" icon="el-icon-caret-left" @click="transitTo('PitchOwner')">Quay lại</el-button>
                <el-form ref="ownerForm" :model="newPitchOwner">
                    <h3>Thêm mới chủ sân</h3>
                    <el-form-item>
                        <el-input v-model="newPitchOwner.username" placeholder="Tài Khoản" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="password" v-model="newPitchOwner.password" placeholder="Mật khẩu" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input type="password" v-model="newPitchOwner.confirm" placeholder="Xác Nhận Mật khẩu" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="newPitchOwner.username" placeholder="Tên chủ sân" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="newPitchOwner.email" placeholder="Email" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="newPitchOwner.phone" placeholder="Số điện thoại" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="newPitchOwner.phone" placeholder="Tên Sân" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-input v-model="newPitchOwner.phone" placeholder="Địa chỉ" style="width: 95%;"/>
                    </el-form-item>
                    <el-form-item>
                        <el-button icon="el-icon-success" type="primary" @click="submitForm" round>Xác nhận</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import MenuAdmin from '../Common/MenuAdmin'

export default {
  components: {
    'hci-admin-menu': MenuAdmin
  },
  data () {
    return {
      newPitchOwner: {
        username: '',
        password: '',
        confirm: '',
        email: '',
        ownerName: '',
        pitchName: '',
        pitchAddress: '',
        phone: ''
      }
    }
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
    submitForm () {
      this.$confirm('Bạn có chắc chắn muốn tạo mới chủ sân?', 'Tạo mới chủ sân', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy bỏ',
        type: 'warning'
      }).then(() => {
        const loader = this.getLoader()
        setTimeout(() => {
          this.closeLoader(loader)
          this.transitTo('PitchOwner')
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
  #create-new-owner-form{
    margin-left: 2% ;
  }
</style>
