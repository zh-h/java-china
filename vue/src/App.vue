<template>
  <vheader></vheader>
  <div class="container">
    <div class="row">
      <router-view></router-view>
    </div>
  </div>
  <vfooter></vfooter>
</template>

<script type="text/babel">
  import Vheader from './components/Vheader'
  import Vfooter from './components/Vfooter'

  export default {
    components: {
      Vheader,
      Vfooter
    }
  }
</script>

<style>
@import './assets/css/bootstrap.min.css'
</style>