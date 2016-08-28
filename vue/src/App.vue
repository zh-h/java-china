<template>
  <div>
    <vheader></vheader>
    <div class="container">
      <div class="row">
        <router-view></router-view>
      </div>
    </div>
    <vfooter></vfooter>
  </div>
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

</style>