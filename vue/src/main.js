import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import VueStrap from 'vue-strap'
import RouterMap from './routers'

// 注册两个插件
Vue.use(VueResource)
Vue.use(VueRouter)

Vue.http.options.emulateJSON = true

const router = new VueRouter({
  history: true,
  hashbang: false
})

router.redirect({
  '*': '/home'
})

//独立出来的路由
RouterMap(router)

router.start(App, '#app')

