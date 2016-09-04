package org.javachina.controller.api;

import org.javachina.dto.RestResponse;
import org.javachina.kit.Utils;
import org.javachina.model.Token;
import org.javachina.model.User;
import org.javachina.service.TokenService;
import org.javachina.service.UserService;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Response;

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
