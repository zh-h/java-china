package org.javachina.interceptor;

import org.javachina.annotation.Permissions;
import org.javachina.dto.RestResponse;
import org.javachina.kit.Utils;
import org.javachina.model.Token;
import org.javachina.service.TokenService;

import com.blade.ioc.annotation.Component;
import com.blade.ioc.annotation.Inject;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.Intercept;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.interceptor.Interceptor;
import com.blade.mvc.route.Route;

@Component
@Intercept
public class BaseInterceptor implements Interceptor {

//	private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);

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
