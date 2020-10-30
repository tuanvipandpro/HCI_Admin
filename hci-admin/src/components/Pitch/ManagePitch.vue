!<template>
  <div id="new-pitch">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-2"' />
      </el-col>
      <el-col :offset="5" :span="19">
        <h1 style="text-align: center">Quản lý sân</h1>
        <div>
            <el-table :data="tableDate">
                <el-table-column prop="id" label="Mã Sân" align="center" fixed/>
                <el-table-column prop="name" label="Tên" align="center"/>
                <el-table-column prop="type" label="Loại Sân" align="center"/>
                <el-table-column prop="price" label="Giá (VND)" align="center"/>
                <el-table-column label="Chức năng" align="center" fixed="right" >
                    <template slot-scope="scope" >
                        <el-button v-if="scope.row.status" type="text" size="small" @click="updateStatus(scope)">Mở sân</el-button>
                        <el-button v-else type="text" size="small" @click="updateStatus(scope)">Hủy sân</el-button>
                    </template>
                </el-table-column>
            </el-table>
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
      tableDate: [
        {
          id: 1,
          name: 'Sân 01',
          type: 'Sân nhỏ (5)',
          status: true,
          price: 100000
        },
        {
          id: 2,
          name: 'Sân 02',
          type: 'Sân nhỏ (5)',
          status: true,
          price: 100000
        },
        {
          id: 3,
          name: 'Sân 03',
          type: 'Sân nhỏ (5)',
          status: true,
          price: 100000
        },
        {
          id: 4,
          name: 'Sân 04',
          type: 'Sân vừa (7)',
          status: false,
          price: 100000
        }
      ]
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
     * Update Status
     */
    updateStatus (scope) {
      let message = (scope.row.status)
        ? 'Bạn có chắc chắn muốn dừng hoạt động của sân này ?'
        : 'Bạn có chắc chắn muốn mở lại của sân này ?'

      this.$confirm(message, 'Warning', {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy Bỏ',
        type: 'warning'
      }).then(() => {
        const loader = this.getLoader()
        setTimeout(() => {
          this.closeLoader(loader)
          this.showMessage('Thao tác thành công !', 'success')
          scope.row.status = !scope.row.status
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

</style>
