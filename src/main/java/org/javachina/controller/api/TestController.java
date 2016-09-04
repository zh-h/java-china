package org.javachina.controller.api;

import org.javachina.config.AopInterceptor;
import org.javachina.dto.RestResponse;
import org.javachina.dto.SiteCount;
import org.javachina.kit.Utils;
import org.javachina.service.EnvService;

import com.blade.aop.annotation.Aop;
import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.PathVariable;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;

@Controller(value = "test", suffix = ".json")
@Aop(value = AopInterceptor.class, methodPrefix = "get")
public class TestController {

	@Inject
	private EnvService envService;

	@Route("a")
	public void getEnvCount(Request request, Response response, @RequestParam int name) {

		System.out.println("name is " + name);

		SiteCount siteCount = envService.getSiteCount();
		RestResponse<SiteCount> restResponse = RestResponse.build(siteCount);
		response.json(Utils.toJSONString(restResponse));
	}

	@Route("b/:id")
	public void b(@PathVariable int id) {
		System.out.println("id is " + id);
	}

}
