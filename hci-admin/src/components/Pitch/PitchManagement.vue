!<template>
  <div id="new-pitch">
    <el-row style="text-align: left">
      <el-col :span='5'>
        <hci-menu :activeIndex='"2-3"' />
      </el-col>
      <el-col :span="19">
        <h1 style="text-align: center">Pitch Management</h1>
        <div style="float: right; margin-right: 10px">
          <el-date-picker
            v-model="dateSearch"
            type="date"
            :picker-options="pickerOptions"
            placeholder="Pick a day">
          </el-date-picker>
          <el-time-select
            placeholder="Start time"
            v-model="startTime"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30'
            }">
          </el-time-select>
          <el-time-select
            placeholder="End time"
            v-model="endTime"
            :picker-options="{
              start: '08:30',
              step: '00:15',
              end: '18:30',
              minTime: startTime
            }">
          </el-time-select>
        </div>
        <div>
          <el-table
            :data="subDataTable"
            stripe
            style="width: 100%">
            <el-table-column type="expand">
              <template slot-scope="props">
                <p>Customer: {{ props.row.name }}</p>
                <p>Address: {{ props.row.address }}</p>
                <p>Phone number: {{ props.row.phone }}</p>
              </template>
            </el-table-column>
            <el-table-column
              label="Date"
              prop="date">
            </el-table-column>
            <el-table-column
              label="Pitch"
              prop="pitch">
            </el-table-column>
            <el-table-column
              label="Start time"
              prop="startTime">
            </el-table-column>
            <el-table-column
              label="End time"
              prop="endTime">
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
      subDataTable: [],
      tableData: [{
        date: '2016-05-03',
        startTime: '6:00',
        endTime: '7:30',
        name: 'Tom',
        pitch: 'Pitch 1',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }, {
        date: '2016-05-02',
        startTime: '14:30',
        endTime: '15:30',
        name: 'Tom',
        pitch: 'Pitch 2',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }, {
        date: '2016-05-04',
        startTime: '15:00',
        endTime: '17:00',
        name: 'Tom',
        pitch: 'Pitch 3',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }, {
        date: '2016-05-01',
        startTime: '17:15',
        endTime: '18:45',
        name: 'Tom',
        pitch: 'Pitch 1',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }, {
        date: '2016-05-08',
        startTime: '18:00',
        endTime: '20:00',
        name: 'Tom',
        pitch: 'Pitch 2',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }, {
        date: '2016-10-08',
        startTime: '19:00',
        endTime: '20:00',
        name: 'Tom',
        pitch: 'Pitch 3',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }, {
        date: '2020-10-07',
        startTime: '19:00',
        endTime: '20:30',
        name: 'Tom',
        pitch: 'Pitch 2',
        address: 'No. 189, Grove St, Los Angeles',
        phone: '0123456789'
      }],
      startTime: '',
      endTime: '',
      pickerOptions: {
        disabledDate (time) {
          let yesterday = new Date()
          yesterday.setDate(yesterday.getDate() - 1)
          return time.getTime() <= yesterday
        }
      },
      dateSearch: '',
      value2: ''
    }
  },
  watch: {
    dateSearch () {
      debugger
      let date = new Date()
      date.setDate(this.dateSearch)
      this.subDataTable = this.tableData.filter(data => !this.dateSearch || data.date === date)
    }
  },
  mounted () {
    /**
     * Check Authentication in session storage
     * If false => redirect to login page
     */
    this.checkAuthen()
    this.subDataTable = this.tableData
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

</style>
