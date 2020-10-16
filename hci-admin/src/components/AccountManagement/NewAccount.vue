!<template>
  <div id="new-account">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-2"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <div id="new-notification-form">
          <el-form
            ref="notifyForm"
            status-icon
            :model="notifyForm"
            :rules="rules"
            label-width="130px"
            label-position="left"
          >
            <h2>Thêm mới tài khoản nhân viên</h2>
            <el-form-item label="Tên tài khoản" prop="title">
              <el-input type="text" v-model="notifyForm.title" style="width: 50%"/>
            </el-form-item>
            <el-form-item label="Mật khẩu" prop="shortContent">
              <el-input type="text" v-model="notifyForm.shortContent" style="width: 50%"/>
            </el-form-item>
            <el-form-item label="Mã nhân viên" prop="content">
              <el-input type="text" v-model="notifyForm.content" class="txt-content"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">Lưu</el-button>
              <el-button @click="resetForm">Làm mới</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import { mapState, mapMutations, mapActions } from 'vuex'
export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      loader: {},
      employeeID: '',
      notifyForm: {
        title: '',
        shortContent: '',
        content: ''
      },
      tempForm: {},
      rules: {
        title: [
          {required: true, message: 'Tên tài khoản đang trống !!!', trigger: 'blur'}
        ],
        shortContent: [
          {required: true, message: 'Mật khẩu đang trống !!!', trigger: 'blur'}
        ],
        content: [
          {required: true, message: 'Mã nhân viên đang trống !!!', trigger: 'blur'}
        ]
      }
    }
  },
  computed: {
    ...mapState('createNotification', [

    ])
  },
  mounted () {
    this.initPage()
  },
  methods: {
    ...mapMutations('createNotification', [

    ]),
    ...mapActions('createNotification', [

    ]),
    /**
     * Run when page initialization
     */
    initPage () {
      this.checkAuthen()
      this.employeeID = sessionStorage.getItem('employeeId')
    },
    /**
     * Validate and Submit Form
     */
    submitForm () {
      this.tempForm = {...this.notifyForm}
      this.$refs['notifyForm'].validate((valid) => {
        if (valid) {
          this.$confirm('Bạn có muốn tạo mới thông báo này ?', 'Warning', {
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Hủy bỏ',
            type: 'warning'
          }).then(() => {
            this.loader = this.getLoader()
            this._setEmployeeID(this.employeeID)
            this._setNotificationFormData(this.notifyForm)
            return this._createNotification()
          }).then(() => {
            this.closeLoader(this.loader)
            this.showMessage('Tạo mới thông báo thành công !!!', 'success')
          })
        } else {
          return false
        }
      })
    },
    /**
     * Reset Form to empty
     */
    resetForm () {
      this.$refs['notifyForm'].resetFields()
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
     * Check Authen in Session Storage
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
  #new-notification-form {
    margin-left: 2%;
  }
  .txt-content {
    width: 50%
  }

  .txt-content .el-textarea__inner {
    min-height: 15vh !important;
  }
</style>
