package com.javachina.interceptor;

import com.blade.annotation.Intercept;
import com.blade.interceptor.Interceptor;
import com.blade.ioc.annotation.Inject;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.blade.route.Route;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.annotation.Permissions;
import com.javachina.dto.RestResponse;
import com.javachina.kit.Utils;
import com.javachina.model.Token;
import com.javachina.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercept
public class BaseInterceptor implements Interceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);

	@Inject
	private TokenService tokenService;

	@Override
	public boolean before(Request request, Response response) {

		String reqUri = request.uri();

		Route route = request.route();
		Permissions p = route.getAction().getAnnotation(Permissions.class);

		if(!reqUri.equals("/oauth/authorization.json") && null != p){

			RestResponse<String> restResponse = new RestResponse<String>();

			String xtoken = request.header("X-Ca-token");
			String apiVer = request.header("X-Ca-Api-Version");
			String timestamp = request.header("X-Ca-timestamp");

			if(StringKit.isBlank(xtoken)){
				restResponse.error("Request Token is null");
				response.json(Utils.toJSONString(restResponse));
				return false;
			}

			Token token = tokenService.getToken(xtoken);

			// 无效
			if(null == token){
				restResponse.error("Invalid Token");
				response.json(Utils.toJSONString(restResponse));
				return false;
			}

			// 过期
			if(token.expired_time < DateKit.getCurrentUnixTime()){
				restResponse.error("Expired Token");
				response.json(Utils.toJSONString(restResponse));
				return false;
			}

		}
		return true;
	}

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}

}
