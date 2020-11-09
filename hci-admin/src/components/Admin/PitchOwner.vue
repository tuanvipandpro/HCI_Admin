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
          <div style="text-align: center"><h1>Quản lý chủ sân</h1></div>
          <div>
            <el-button icon="el-icon-circle-plus" style="margin-left: 1vw;" round @click="transitTo('CreatePitchOwner')">
              Thêm mới chủ sân
            </el-button>
          </div>
          <div>
            <el-table :data="tableDate">
                <el-table-column prop="username" label="Tài khoản" align="center" fixed/>
                <el-table-column prop="ownerName" label="Họ tên" align="center">
                  <template slot-scope="scope">
                    <el-popover trigger="hover" placement="left">
                      <p>Họ Tên: {{ scope.row.ownerName }}</p>
                      <p>Email: {{ scope.row.email }}</p>
                      <p>Số điện thoại: {{ scope.row.phone }}</p>
                      <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.ownerName }}</el-tag>
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <el-table-column prop="pitchName" label="Tên Sân" align="center">
                  <template slot-scope="scope">
                    <el-popover trigger="hover" placement="left">
                      <p>Tên Sân: {{ scope.row.pitchName }}</p>
                      <!-- <p>Loại Sân: {{ scope.row.type }}</p> -->
                      <p>Địa chỉ: {{ scope.row.address }}</p>
                      <p>Thời gian hoạt động: 08:00 ~ 22:00</p>
                      <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.pitchName }}</el-tag>
                      </div>
                    </el-popover>
                  </template>
                </el-table-column>
                <!-- <el-table-column prop="address" label="Địa chỉ" align="center"/> -->
                <!-- <el-table-column prop="statusString" label="Trạng thái" align="center"/> -->
                <el-table-column label="Chức năng" align="center" fixed="right" >
                    <template slot-scope="scope" >
                        <el-button v-if="!scope.row.status" type="text" size="small" @click="updateStatus(scope)">Mở tài khoản</el-button>
                        <el-button v-else type="text" size="small" @click="updateStatus(scope)">Đóng tài khoản</el-button>
                    </template>
                </el-table-column>
            </el-table>
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
      tableDate: [
        {
          username: 'leminhtuan',
          ownerName: 'Lê Minh Tuấn',
          pitchName: 'Sân Phúc Lộc',
          address: '442 Lê Văn Việt, Tăng Nhơn Phú A, Quận 9, TP.HCM',
          type: '5-7-11',
          status: true,
          email: 'tuanlm@gmail.com',
          phone: '0123456789',
          statusString: 'Đang hoạt động'
        },
        {
          username: 'datvtp',
          ownerName: 'Văn Thành Phương Đạt',
          pitchName: 'Sân Tiến Phát',
          address: '387 Man Thiện, Tăng Nhơn Phú A, Quận 9, TP.HCM',
          type: '5-7-11',
          status: true,
          email: 'datvtp@gmail.com',
          phone: '0123456789',
          statusString: 'Đang hoạt động'
        },
        {
          username: 'tult',
          ownerName: 'Lê Thanh Tú',
          pitchName: 'Sân Hiệp Phú',
          address: '451/2 Lê Văn Việt, Tăng Nhơn Phú A, Quận 9, TP.HCM',
          type: '5-7-11',
          status: true,
          email: 'tult@gmail.com',
          phone: '0123456789',
          statusString: 'Đang hoạt động'
        },
        {
          username: 'sondt',
          ownerName: 'Đặng Hoàng Sơn',
          pitchName: 'Sân Kaly',
          address: '315 Hoàng Diệu 2, Linh Trung, Quận Thủ Đức, TP.HCM',
          type: '5-7-11',
          status: true,
          email: 'sondt1@gmail.com',
          phone: '0123456789',
          statusString: 'Đang hoạt động'
        },
        {
          username: 'dunggla',
          ownerName: 'Giang Lưu Anh Dũng',
          pitchName: 'Sân Nam Lý',
          address: '55/83B Lê Văn Việt, Quận 9, TP.HCM',
          type: '5-7-11',
          status: true,
          email: 'dunggla@gmail.com',
          phone: '0123456789',
          statusString: 'Đang hoạt động'
        }
      ]
    }
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
    /**
     * Update Status
     */
    updateStatus (scope) {
      let title = (scope.row.status)
        ? 'Hủy tài khoản'
        : 'Mở tài khoản'

      let message = (scope.row.status)
        ? 'Bạn có chắc chắn muốn dừng hoạt động của chủ sân này ?'
        : 'Bạn có chắc chắn muốn mở lại của chủ sân này ?'

      this.$confirm(message, title, {
        confirmButtonText: 'Đồng ý',
        cancelButtonText: 'Hủy Bỏ',
        type: 'warning'
      }).then(() => {
        const loader = this.getLoader()
        setTimeout(() => {
          this.closeLoader(loader)
          scope.row.status = !scope.row.status
          // scope.row.statusString = (scope.row.status) ? 'Đang hoạt động' : 'Đã hủy'
          this.showMessage('Thao tác thành công !', 'success')
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
  #pitch-owner-footer {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    color: white;
    text-align: center;
  }
</style>
