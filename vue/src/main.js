import Vue from 'vue'
import App from './App'
import Keen from 'keen-ui'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import RouterMap from './routers'

// 注册插件
Vue.use(Keen);
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

