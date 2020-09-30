!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"1"' />
      </el-col>
      <el-col :span="19">
          <h1>User Management</h1>
          <el-table
            stripe
            :data="accountData.filter(data => !search || data.fullname.toLowerCase().includes(search.toLowerCase()))"
            style="width: 95%; margin-left: 2.5%"
            empty-text="No Data">
          <el-table-column
            label="Username"
            prop="username">
          </el-table-column>
          <el-table-column
            label="Fullname"
            prop="fullname">
          </el-table-column>
          <el-table-column
            label="Address"
            prop="address">
          </el-table-column>
          <el-table-column
            align="right">
            <template slot="header" slot-scope="scope">
              <el-input
                v-model="search"
                size="mini"
                placeholder="Type to search"/>
            </template>
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Menu from '../Common/Menu'

export default {
  data () {
    return {
      accountData: [{
        username: 'Tom@123',
        fullname: 'Tom',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        username: 'John@abc',
        fullname: 'John',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        username: 'Morgan@xyz',
        fullname: 'Morgan',
        address: 'No. 189, Grove St, Los Angeles'
      }, {
        username: 'Jessy@hihi',
        fullname: 'Jessy',
        address: 'No. 189, Grove St, Los Angeles'
      }],
      search: ''
    }
  },
  components: {
    'hci-menu': Menu
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
    handleEdit (index, row) {
      console.log(index, row)
    },
    handleDelete (index, row) {
      console.log(index, row)
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
</style>
