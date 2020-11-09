!<template>
  <div id="profile">
    <el-row style="text-align: left">
      <!-- Menu -->
      <el-col :span='5'>
        <hci-menu :activeIndex='"1-1"' />
      </el-col>
      <!-- Content -->
      <el-col :offset="5" :span="19">
          <div class="chart-row title-row">
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 55%;" shadow="hover">
                <div slot="header" class="clearfix">
                  <i class="el-icon-s-operation"></i>
                  <span style="font-size: 20px; font-weight: bold; margin-left: 5px">Bộ lọc</span>
                </div>
                <el-date-picker v-model="month" type="month" placeholder="Chọn tháng" @change="changeDate"/>
              </el-card>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 38%;" shadow="hover">
                <div slot="header" class="clearfix">
                  <i class="el-icon-s-data"></i>
                  <span style="font-size: 20px; font-weight: bold; margin-left: 5px">Đánh giá</span>
                </div>
                <div>
                  <span style="font-size: 20px; font-weight: bold;">
                    Điểm yêu thích: 4.8 <i class="el-icon-star-off"></i>
                  </span>
                </div>
              </el-card>
          </div>
          <div class="chart-row table-row">
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 35%;" shadow="hover">
                  <line-chart :data="datas[2]" :options="options[2]" style="height: 330px; width: 100%"/>
              </el-card>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 58%;" shadow="hover">
                <!-- <div slot="header" class="clearfix">
                  <i class="el-icon-s-operation"></i>
                  <span style="font-size: 18px; font-weight: bold; margin-left: 5px">Bộ lọc</span>
                </div> -->
                <template>
                  <el-table :data="tableData" stripe style="width: 100%;" height="300">
                    <el-table-column fixed prop="name" label="Tên"/>
                    <el-table-column prop="total" label="Tổng lượt đặt"/>
                    <el-table-column prop="time" label="Tổng giờ đặt"/>
                    <el-table-column prop="like" label="Số lần hủy"/>
                  </el-table>
                </template>
              </el-card>
          </div>
          <div class="chart-row">
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 46.5%;" shadow="hover">
                  <bar-chart :data="datas[0]" :options="options[0]" style="width: 100%; height: 330px; "/>
              </el-card>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 46.5%;" shadow="hover">
                  <pie-chart :data="datas[1]" :options="options[1]" style="width: 100%; height: 330px; "/>
              </el-card>
          </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import LineChart from '../Common/Chart/LineChart'
import BarChart from '../Common/Chart/BarChart'
import PieChart from '../Common/Chart/PieChart'

export default {
  components: {
    'hci-menu': Menu,
    'pie-chart': PieChart,
    'bar-chart': BarChart,
    'line-chart': LineChart
  },
  data () {
    return {
      month: new Date(),
      tableData: [
        {
          name: 'Minh Tuấn',
          total: '18',
          time: '18:30:00',
          like: '1'
        },
        {
          name: 'Anh Dũng',
          total: '16',
          time: '16:30:00',
          like: '1'
        },
        {
          name: 'Phương Đạt',
          total: '15',
          time: '15:00:00',
          like: '0'
        },
        {
          name: 'Thanh Tú',
          total: '12',
          time: '12:30:00',
          like: '2'
        },
        {
          name: 'Công Phượng',
          total: '8',
          time: '8:30:00',
          like: '0'
        },
        {
          name: 'Tiến Dũng',
          total: '7',
          time: '7:30:00',
          like: '0'
        },
        {
          name: 'Quang Hải',
          total: '6',
          time: '72:30:00',
          like: '0'
        }
      ],
      datas: [
        {
          labels: ['Sân 5', 'Sân 7', 'Sân 11'],
          datasets: [
            {
              label: 'đơn vị (giờ)',
              backgroundColor: ['#3e95cd', '#8e5ea2', '#3cba9f'],
              data: [185, 92, 32]
            }
          ]
        },
        {
          labels: ['Sân 5', 'Sân 7', 'Sân 11'],
          datasets: [
            {
              label: 'đơn vị (%)',
              backgroundColor: ['#c45850', '#3e95cd', '#8e5ea2'],
              data: [58, 30, 12]
            }
          ]
        },
        {
          labels: ['8h', '10h', '12h', '14h', '16h', '18h', '20h', '22h'],
          datasets: [
            {
              label: 'Sân 5',
              borderColor: '#c45850',
              data: [10, 5, 2, 4, 20, 38, 34, 10]
            },
            {
              label: 'Sân 7',
              borderColor: '#3cba9f',
              data: [8, 6, 3, 5, 22, 30, 38, 12]
            },
            {
              label: 'Sân 11',
              borderColor: '#3e95cd',
              data: [4, 8, 3, 7, 19, 36, 40, 15]
            }
          ]
        }
      ],
      options: [
        {
          title: {
            display: true,
            text: 'Thống kê thời gian đặt sân tháng 11/2020'
          },
          responsive: true,
          maintainAspectRatio: false
        },
        {
          title: {
            display: true,
            text: 'Tỉ lệ đặt sân tháng 11/2020'
          },
          responsive: true,
          maintainAspectRatio: false
        },
        {
          title: {
            display: true,
            text: 'Biểu đồ khung giờ được yêu thích 11/2020'
          },
          responsive: true,
          maintainAspectRatio: false
        }
      ]
    }
  },
  mounted () {
    this.checkAuthen()
  },
  methods: {
    /**
     * Change Date
     */
    changeDate () {
      const loader = this.getLoader()
      setTimeout(() => {
        this.closeLoader(loader)
      }, 300)
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
  .chart-row{
    display: flex;
  }
</style>
