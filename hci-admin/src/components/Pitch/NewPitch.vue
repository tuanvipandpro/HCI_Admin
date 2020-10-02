!<template>
  <div id="new-pitch">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-1"' />
      </el-col>
      <el-col :span="19">
        <div id="new-pitch-form">
          <el-form
            ref="pitchForm"
            status-icon
            :model="pitchForm"
            :rules="rules"
            label-width="130px"
            label-position="left"
          >
            <h2>New Pitch</h2>
            <el-form-item label="Name" prop="name">
              <el-input type="text" v-model="pitchForm.name" style="width: 50%"/>
            </el-form-item>
            <el-form-item label="Category" prop="category">
              <el-select v-model="pitchForm.category" placeholder="Select category of pitch" style="width: 50%">
                  <el-option label="Mini pitch (5)" value="1"></el-option>
                  <el-option label="Medium pitch (7)" value="2"></el-option>
                  <el-option label="Large pitch (11)" value="3"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Price (VND)" prop="price">
              <!-- <el-input type="number" v-model="pitchForm.price" class="txt-price" min="0"/> -->
              <el-input-number v-model="pitchForm.price" :min="0" />
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
      pitchForm: {
        name: '',
        category: '',
        price: ''
      },
      tempForm: {},
      rules: {
        name: [
          {required: true, message: 'Please input name of pitch !!!', trigger: 'blur'}
        ],
        category: [
          {required: true, message: 'Please input category of pitch !!!', trigger: 'blur'}
        ],
        price: [
          {required: true, message: 'Please input price of pitch !!!', trigger: 'blur'}
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
      this.tempForm = {...this.pitchForm}
      this.$refs['pitchForm'].validate((valid) => {
        if (valid) {
          this.$confirm('Create this notification?. Continue?', 'Warning', {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }).then(() => {
            const loader = this.getLoader()
            setTimeout(() => {
              this.resetForm()
              this.showMessage('Create Pitch Successful !!!', 'success')
              this.closeLoader(loader)
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
      this.$refs['pitchForm'].resetFields()
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
  #new-pitch-form {
    margin-left: 2%;
  }
  .txt-price {
    width: 50%
  }
</style>
