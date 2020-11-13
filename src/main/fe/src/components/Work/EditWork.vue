!<template>
  <div id="userManagement">
    <el-row>
      <el-col :span='5' style="text-align: left">
        <hci-menu :activeIndex='"4-1"' />
      </el-col>
      <el-col :offset="5" :span="19">
          <h1>Chỉnh sửa ca</h1>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import Menu from '../Common/Menu'

export default {
  data () {
    return {
      workObj: {}
    }
  },
  components: {
    'hci-menu': Menu
  },
  computed: {
    ...mapState('workEdit', ['_workObj'])
  },
  mounted () {
    if (!sessionStorage.getItem('username')) {
      this.transitTo('Login', undefined)
    } else {
      const loader = this.getLoader()
      this._getWorkById(this.$route.params.id).then(res => {
        this.closeLoader(loader)
        console.log(this._workObj)
      })
        .catch(e => {
          this.closeLoader(loader)
          console.error(e)
        })
    }
  },
  methods: {
    ...mapActions('workEdit', ['_getWorkById', '_updateWork']),
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
    }
  }
}
</script>
<style>
  .el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
    color: white;
  }
  .el-button {
    color: black;
  }
</style>
