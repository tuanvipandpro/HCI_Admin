!<template>
  <div id="new-notification">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"3"' />
      </el-col>
      <el-col :span="19">
        <div id="new-notification-form">
          <el-form
            ref="notifyForm"
            status-icon
            :model="notifyForm"
            :rules="rules"
            label-width="130px"
            label-position="left"
          >
            <h2>New Notification</h2>
            <el-form-item label="Title" prop="title">
              <el-input type="text" v-model="notifyForm.title" style="width: 50%"/>
            </el-form-item>
            <el-form-item label="Short Content" prop="short">
              <el-input type="text" v-model="notifyForm.short" style="width: 50%"/>
            </el-form-item>
            <el-form-item label="Content" prop="content">
              <el-input type="textarea" v-model="notifyForm.content" class="txt-content"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">Submit</el-button>
              <el-button @click="resetForm">Reset</el-button>
            </el-form-item>
          </el-form>
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
    return {
      notifyForm: {
        title: '',
        short: '',
        content: ''
      },
      tempForm: {},
      rules: {
        title: [
          {required: true, message: 'Please input title of notification !!!', trigger: 'blur'}
        ],
        short: [
          {required: true, message: 'Please input short description of notification !!!', trigger: 'blur'}
        ],
        content: [
          {required: true, message: 'Please input content of notification !!!', trigger: 'blur'}
        ]
      }
    }
  },
  mounted () {
    /**
     * Check Authentication in session storage
     * If false => redirect to login page
     */
    this.checkAuthen()
  },
  methods: {
    /**
     * Validate and Submit Form
     */
    submitForm () {
      this.tempForm = {...this.notifyForm}
      this.$refs['notifyForm'].validate((valid) => {
        if (valid) {
          this.$confirm('Create this notification?. Continue?', 'Warning', {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            const loader = this.getLoader()
            setTimeout(() => {
              this.showMessage('Create Notification Successful !!!', 'success')
              this.closeLoader(loader)
              this.resetForm()
            }, 1000)
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
