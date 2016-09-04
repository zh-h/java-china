package org.javachina.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.view.ModelAndView;
import com.blade.patchca.DefaultPatchca;
import org.javachina.dto.RestResponse;
import org.javachina.kit.SessionKit;
import org.javachina.model.LoginUser;
import org.javachina.model.User;
import org.javachina.service.UserService;

@Controller
public class IndexController {

    private DefaultPatchca patchca = new DefaultPatchca();

    @Inject
    private UserService userService;

    @Route(value = "signin", method = HttpMethod.GET)
    public ModelAndView signinPage(ModelAndView mav){
        mav.setView("signin");
        return mav;
    }

    @Route(value = "signin", method = HttpMethod.POST)
    @JSON
    public RestResponse<String> signin(Request request,
                                       @RequestParam String user_name,
                                       @RequestParam String pass_word){

        RestResponse<String> restResponse = new RestResponse<String>();

        if(StringKit.isBlank(user_name) || StringKit.isBlank(pass_word)){
            restResponse.setMsg("用户名和密码不能为空");
            return restResponse;
        }

        User user = userService.getUser(user_name, pass_word);
        if(null == user){
            restResponse.setMsg("用户名或密码错误");
            return restResponse;
        }

        if(user.status == 0){
            restResponse.setMsg("该用户尚未激活，请登录邮箱激活帐号后登录");
            return restResponse;
        }
        LoginUser loginUser = userService.getLoginUser(user);
        SessionKit.setLoginUser(request.session(), loginUser);
        restResponse.setSuccess(true);
        return restResponse;
    }

    @Route(value = "signup", method = HttpMethod.GET)
    public ModelAndView signupPage(ModelAndView mav){
        mav.setView("signup");
        return mav;
    }

    @Route(value = "signup", method = HttpMethod.POST)
    public RestResponse<String> signup(ModelAndView mav){
        RestResponse<String> restResponse = new RestResponse<String>();
        return restResponse;
    }

    @Route(value = "forgot", method = HttpMethod.GET)
    public ModelAndView forgotPage(ModelAndView mav){
        mav.setView("forgot");
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
