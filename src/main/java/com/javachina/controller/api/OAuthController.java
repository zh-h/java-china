package com.javachina.controller.api;

import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.web.http.HttpMethod;
import com.blade.web.http.Response;
import com.javachina.dto.RestResponse;
import com.javachina.kit.Utils;
import com.javachina.model.Token;
import com.javachina.model.User;
import com.javachina.service.TokenService;
import com.javachina.service.UserService;

@Controller(value = "oauth", suffix = ".json")
public class OAuthController {

    @Inject
    private UserService userService;

    @Inject
    private TokenService tokenService;

    /**
     * 密码认证获取token
     */
    @Route(value = "authorization", method = HttpMethod.POST)
    public void authorization(Response response,
            @RequestParam("username") String username,
            @RequestParam("password") String password){

        RestResponse<Token> restResponse = new RestResponse<Token>();
        User user = userService.getUser(username, password);
        if(null == user) {
            restResponse.error("用户名或密码错误");
        } else{
            if(user.status == 0){
                restResponse.error("用户未激活");
            } else{
                // 颁发token
                Token token = tokenService.create(user.uid, "login");
                restResponse.setPayload(token);
            }
        }
        response.json(Utils.toJSONString(restResponse));
    }

}
