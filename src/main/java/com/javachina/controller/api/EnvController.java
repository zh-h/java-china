package com.javachina.controller.api;

import com.blade.annotation.Controller;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.web.http.Response;
import com.javachina.dto.RestResponse;
import com.javachina.dto.SiteCount;
import com.javachina.kit.Utils;
import com.javachina.service.EnvService;

@Controller(value = "/env/", suffix = ".json")
public class EnvController {

	@Inject
	private EnvService envService;
	
	@Route("/site_count")
	public void getEnvCount(Response response) {
		SiteCount siteCount = envService.getSiteCount();
		RestResponse restResponse = RestResponse.build(siteCount);

		response.json(Utils.toJSONString(restResponse));
	}
	
}
