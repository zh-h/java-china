package com.javachina.controller.api;

import com.alibaba.fastjson.JSON;
import com.blade.annotation.Controller;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.web.http.Response;
import com.javachina.dto.SiteCount;
import com.javachina.service.EnvService;

@Controller(value = "/api", suffix = ".json")
public class EnvController {

	@Inject
	private EnvService envService;
	
	@Route("/counts")
	public void getEnvCount(Response response) {
		SiteCount siteCount = envService.getSiteCount();
		response.json(JSON.toJSONString(siteCount));
	}
	
}
