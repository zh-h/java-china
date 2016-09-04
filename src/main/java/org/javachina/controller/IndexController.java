package org.javachina.controller;

import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.ModelAndView;

/**
 * Created by oushaku on 16/8/26.
 */
@Controller("/")
public class IndexController extends BaseController{

    /**
     * 首页
     * @return
     */
    @Route(value = "/", method = HttpMethod.GET)
    public ModelAndView index(@RequestParam("name") String name){
    	System.out.println("name = " + name);
        return getView("index");
    }


}
