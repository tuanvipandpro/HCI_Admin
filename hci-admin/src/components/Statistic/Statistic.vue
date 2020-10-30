!<template>
  <div id="profile">
    <el-row style="text-align: left">
      <!-- Menu -->
      <el-col :span='5'>
        <hci-menu :activeIndex='"1-1"' />
      </el-col>
      <!-- Content -->
      <el-col :offset="5" :span="19">
          <div>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 95%;">
                <div slot="header" class="clearfix">
                  <span style="font-size: 20px; font-weight: bold;">Bộ lọc</span>
                </div>
                <el-date-picker v-model="month" type="month" placeholder="Chọn tháng"/>
              </el-card>
          </div>
          <div class="chart-row">
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 46.5%;">
                  <bar-chart :data="datas[0]" :options="options[0]" style="width: 400px; height: 230px"/>
              </el-card>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 46.5%;">
                  <pie-chart :data="datas[1]" :options="options[1]" style="width: 400px; height: 230px"/>
              </el-card>
          </div>
          <div>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%;">
                  <line-chart :data="datas[2]" :options="options[2]" style="height: 300px"/>
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
      datas: [
        {
          labels: ['Sân 1', 'Sân 2', 'Sân 3', 'Sân 4'],
          datasets: [
            {
              label: 'đơn vị (lượt đặt)',
              backgroundColor: ['#3e95cd', '#8e5ea2', '#3cba9f', '#e8c3b9'],
              data: [123, 124, 132, 121]
            }
          ]
        },
        {
          labels: ['Sân 1', 'Sân 2', 'Sân 3', 'Sân 4'],
          datasets: [
            {
              label: 'đơn vị (giờ)',
              backgroundColor: ['#c45850', '#3e95cd', '#8e5ea2', '#e8c3b9'],
              data: [5, 6, 7, 8]
            }
          ]
        },
        {
          labels: ['8h', '10h', '12h', '14h', '16h', '18h', '20h', '22h'],
          datasets: [
            {
              label: 'Sân 1 (5)',
              borderColor: '#c45850',
              data: [10, 5, 2, 4, 20, 38, 34, 10]
            },
            {
              label: 'Sân 2 (5)',
              borderColor: '#3cba9f',
              data: [8, 6, 3, 5, 22, 30, 38, 12]
            },
            {
              label: 'Sân 3 (5)',
              borderColor: '#3e95cd',
              data: [4, 8, 3, 7, 19, 36, 40, 15]
            },
            {
              label: 'Sân 4 (7)',
              borderColor: '#f39c12',
              data: [7, 9, 4, 5, 15, 30, 31, 20]
            }
          ]
        }
      ],
      options: [
        {
          title: {
            display: true,
            text: 'Biểu đồ đặt sân tháng 10/2020'
          },
          responsive: true,
          maintainAspectRatio: false
        },
        {
          title: {
            display: true,
            text: 'Tỉ lệ đặt sân tháng 10/2020'
          },
          responsive: true,
          maintainAspectRatio: false
        },
        {
          title: {
            display: true,
            text: 'Biểu đồ khung giờ được yêu thích 10/2020'
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
