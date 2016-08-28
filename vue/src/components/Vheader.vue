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
              <a class="navbar-brand" href="/">Java中国</a>
          </div>
          
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                  <li><a href="/markdown">MarkDown</a></li>
                  <li><a href="/essence">精华贴</a></li>
              </ul>
              <ul class="nav navbar-nav navbar-right">
                <li><a href="">首页</a></li>
                <li><a href="/signup">注册</a></li> 
                <li><a href="/signin">登录</a></li>
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

<style>

</style>