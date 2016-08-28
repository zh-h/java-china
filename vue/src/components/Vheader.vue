<template>
  <nav class="navbar navbar-default box" role="navigation">
      <div class="container">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="${base_url('')}">Java中国</a>
          </div>
          
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                  <li><a href="/markdown">MarkDown</a></li>
                  <li><a href="/essence">精华贴</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="">首页</a></li>
                <li><a href="${base_url('/signup')}">注册</a></li> 
                <li><a href="${base_url('/signin')}">登录</a></li>
              </ul>
              <form class="navbar-form navbar-right" onsubmit="return dispatch();" role="search" method="get" target="_blank">
                <div class="form-group input-sm">
                      <input type="text" class="form-control input-sm" placeholder="搜索..." name="q" id="q"/>
                </div>
              </form>
          </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav>
</template>

<script type="text/babel">
  
  export default {
    data () {
      return {
        show: true,
        nav: 'nav',
        isTop: true,
        isVisible: true,
        headlineFinal: ''
      }
    },
    watch: {
      'headline': function (val, oldVal) {
        this.show = false
        setTimeout(() => {
          this.show = true
          this.headlineFinal = val
        }, 400)
      }
    },
    ready () {
      this.scroll()
    },
    methods: {
      scroll () {
        let beforeScrollTop = document.body.scrollTop
        window.onscroll = () => {
          const afterScrollTop = document.body.scrollTop
          const delta = afterScrollTop - beforeScrollTop
          this.isTop = afterScrollTop === 0
          if (delta === 0) return false
          beforeScrollTop = afterScrollTop
          this.isVisible = delta <= 0
          if (afterScrollTop < 48) {
            this.isVisible = true
          }
        }
      }
    }
  }
</script>

<style scoped>
  
</style>