package com.javachina.controller.api;

import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.web.http.Request;
import com.blade.web.http.Response;
import com.javachina.dto.RestResponse;
import com.javachina.dto.SiteCount;
import com.javachina.kit.Utils;
import com.javachina.service.EnvService;

@Controller(value = "test", suffix = ".json")
public class TestController {

	@Inject
	private EnvService envService;

	@Route("a")
	public void getEnvCount(Request request, Response response, @RequestParam("name") int name) {
		
		System.out.println("name is " + name);
		
		SiteCount siteCount = envService.getSiteCount();
		RestResponse<SiteCount> restResponse = RestResponse.build(siteCount);
		response.json(Utils.toJSONString(restResponse));
	}

}
