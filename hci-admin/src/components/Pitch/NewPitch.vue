!<template>
  <div id="new-pitch">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <div id="new-pitch-form">
          <el-form
            ref="pitchForm"
            status-icon
            :model="pitchForm"
            :rules="rules"
            label-width="130px"
            label-position="left"
          >
            <h2>Thêm mới sân</h2>
            <el-form-item label="Tên sân" prop="name">
              <el-input type="text" v-model="pitchForm.name" style="width: 50%" placeholder="Nhập tên sân"/>
            </el-form-item>
            <el-form-item label="Địa chỉ" prop="address">
              <el-input type="text" v-model="pitchForm.name" style="width: 50%" placeholder="Nhập địa chỉ sân"/>
            </el-form-item>
            <el-form-item label="Loại sân " prop="category">
              <el-select v-model="pitchForm.category" placeholder="Chọn loại sân" style="width: 50%">
                  <el-option label="Sân nhỏ (5)" value="1"></el-option>
                  <el-option label="Sân vừa (7)" value="2"></el-option>
                  <el-option label="Sân lớn (11)" value="3"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="Giá (VND)" prop="price">
              <el-input-number v-model="pitchForm.price" :min="100000" step="1000"/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm">Xác nhận</el-button>
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

export default {
  components: {
    'hci-menu': Menu
  },
  data () {
    return {
      pitchForm: {
        name: '',
        address: '',
        category: '',
        price: ''
      },
      tempForm: {},
      rules: {
        name: [
          {required: true, message: 'Vui lòng nhập tên của sân !!!', trigger: 'blur'}
        ],
        address: [
          {required: true, message: 'Vui lòng nhập địa chỉ của sân !!!', trigger: 'blur'}
        ],
        category: [
          {required: true, message: 'Vui lòng chọn loại sân !!!', trigger: 'blur'}
        ],
        price: [
          {required: true, message: 'Vui lòng chọn giá sân !!!', trigger: 'blur'}
        ]
      }
    }
  },
  /**
  * Mounted
  */
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
          this.$confirm('Bạn có muốn tạo mới thông báo này?', 'Warning', {
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Hủy Bỏ',
            type: 'warning'
          }).then(() => {
            const loader = this.getLoader()
            setTimeout(() => {
              this.resetForm()
              this.showMessage('Tạo sân thành công !!!', 'success')
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
