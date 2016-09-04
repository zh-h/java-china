package org.javachina.interceptor;

import com.blade.Blade;
import com.blade.ioc.annotation.Component;
import com.blade.mvc.annotation.Intercept;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.blade.mvc.interceptor.Interceptor;

@Component
@Intercept
public class BaseInterceptor implements Interceptor {

//	private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
	
	
	@Override
	public boolean before(Request request, Response response) {
		
//		String reqUri = request.uri();
//		Route route = request.route();
		request.attribute("cdn", Blade.$().environment().getString("app.cdn"));
		request.attribute("version", Blade.$().environment().getString("app.version"));

		return true;
	}

	@Override
	public boolean after(Request request, Response response) {
		return true;
	}

}
