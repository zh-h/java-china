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


}
