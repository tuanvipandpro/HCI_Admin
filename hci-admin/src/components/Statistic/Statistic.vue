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
                <el-date-picker v-model="month" type="month" placeholder="Pick a month"/>
              </el-card>
          </div>
          <div class="bar-chart">
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 95%;">
                  <bar-chart :data="data" :options="options[0]"/>
              </el-card>
              <el-card class="box-card" style="margin-left: 2%; margin-top: 1%; width: 95%;">
                  <pie-chart :data="data" :options="options[1]"/>
              </el-card>
          </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'
import BarChart from '../Common/Chart/BarChart'
import PieChart from '../Common/Chart/PieChart'

export default {
  components: {
    'hci-menu': Menu,
    'pie-chart': PieChart,
    'bar-chart': BarChart
  },
  data () {
    return {
      month: new Date(),
      data: {
        labels: ['Sân 1', 'Sân 2', 'Sân 3', 'Sân 4'],
        datasets: [
          {
            label: 'đơn vị (giờ)',
            backgroundColor: ['#3e95cd', '#8e5ea2', '#3cba9f', '#e8c3b9'],
            data: [5, 6, 7, 8, 0]
          }
        ]
      },
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
