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
            <el-form-item label="Tên sân" prop="name">
              <el-input type="text" v-model="pitchForm.name" style="width: 50%" placeholder="Nhập tên sân"/>
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
        <!-- <line-chart :data="chartData" :options="options"/> -->
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
// import LineChart from '../Common/Chart/LineChart'

export default {
  components: {
    // 'line-chart': LineChart,
    'hci-menu': Menu
  },
  data () {
    return {
      // Start Chart
      // chartData: {
      //   labels: [1975, 1980, 1985, 1990, 1995, 2000, 2005, 2010, 2015, 2020],
      //   datasets: [{
      //     data: [70, 95, 100, 120, 257, 271, 300, 321, 383, 450],
      //     label: 'Cây lương thực',
      //     borderColor: '#3e95cd'
      //   }, {
      //     data: [70, 80, 111, 129, 135, 209, 247, 372, 400, 426],
      //     label: 'Cây công nghiệp',
      //     borderColor: '#8e5ea2'
      //   }, {
      //     data: [70, 78, 128, 150, 203, 276, 300, 317, 375, 434],
      //     label: 'Rau đậu',
      //     borderColor: '#3cba9f'
      //   }, {
      //     data: [70, 107, 170, 200, 254, 293, 314, 337, 268, 384],
      //     label: 'Cây ăn quả',
      //     borderColor: '#e8c3b9'
      //   }, {
      //     data: [70, 100, 135, 157, 187, 201, 222, 272, 312, 433],
      //     label: 'Cây khác',
      //     borderColor: '#c45850'
      //   }
      //   ]
      // },
      // options: {
      //   title: {
      //     display: true,
      //     text: 'Đồ thị tăng trưởng giá trị sản xuất các nhóm cây trồng'
      //   },
      //   responsive: true,
      //   maintainAspectRatio: false
      // },
      // End Chart
      pitchForm: {
        name: '',
        category: '',
        price: ''
      },
      tempForm: {},
      rules: {
        name: [
          {required: true, message: 'Vui lòng nhập tên của sân !!!', trigger: 'blur'}
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
