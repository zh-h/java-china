package com.javachina.controller;

import com.blade.annotation.Controller;
import com.blade.annotation.Route;
import com.blade.kit.io.StringBuilderWriter;
import com.blade.view.ModelAndView;
import com.blade.web.http.HttpMethod;

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
    public ModelAndView index(){
        return getView("index");
    }

    /**
     * 注册页
     * @return
     */
    @Route(value = "/signup", method = HttpMethod.GET)
    public ModelAndView signup(){
        return getView("signup");
    }

    /**
     * 登陆页
     * @return
     */
    @Route(value = "/signin", method = HttpMethod.GET)
    public ModelAndView signin(){
        return getView("signin");
    }

}
