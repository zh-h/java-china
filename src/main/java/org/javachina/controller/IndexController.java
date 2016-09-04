package org.javachina.controller;

import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.view.ModelAndView;
import com.blade.patchca.DefaultPatchca;

@Controller
public class IndexController {

    private DefaultPatchca patchca = new DefaultPatchca();

    @Route(value = "signin", method = HttpMethod.GET)
    public ModelAndView signin(ModelAndView mav){
        mav.setView("signin");
        return mav;
    }

    @Route(value = "signup", method = HttpMethod.GET)
    public ModelAndView signup(ModelAndView mav){
        mav.setView("signup");
        return mav;
    }

    @Route(value = "captcha", method = HttpMethod.GET)
    public void captcha(Request request, Response response){
        patchca.size(200, 40).render(request, response);
    }

    @Route(value = "markdown", method = HttpMethod.GET)
    public ModelAndView markdown(ModelAndView mav){
        mav.setView("markdown");
        return mav;
    }

    @Route(value = "faq", method = HttpMethod.GET)
    public ModelAndView faq(ModelAndView mav){
        mav.setView("faq");
        return mav;
    }

    @Route(value = "about", method = HttpMethod.GET)
    public ModelAndView about(ModelAndView mav){
        mav.setView("about");
        return mav;
    }

    @Route(value = "donate", method = HttpMethod.GET)
    public ModelAndView donate(ModelAndView mav) {
        mav.setView("donate");
        return mav;
    }
}
