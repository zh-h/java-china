'use strict'

export default function(router){
	// 路由map
	router.map({
	  '/home': {
	    component: function (resolve) {
	      require(['./components/Home'], resolve)
	    }
	  },
	  '/about': {
	    component: function (resolve) {
	      require(['./components/About'], resolve)
	    }
	  },
	  '/faq': {
	    component: function (resolve) {
	      require(['./components/Faq'], resolve)
	    }
	  },
	  '/markdown': {
	    component: function (resolve) {
	      require(['./components/Markdown'], resolve)
	    }
	  },
	  '/about': {
	    component: function (resolve) {
	      require(['./components/About'], resolve)
	    }
	  },
	  '/signin': {
	    component: function (resolve) {
	      require(['./components/Signin'], resolve)
	    }
	  },
	  '/signup': {
	    component: function (resolve) {
	      require(['./components/Signup'], resolve)
	    }
	  },
	  '/member/:username': {
	    component: function (resolve) {
	      require(['./components/Member'], resolve)
	    }
	  },
	  '/topic/:id': {
	    name: 'article',
	    component: function (resolve) {
	      require(['./components/Topic'], resolve)
	    }
	  }
	})
}