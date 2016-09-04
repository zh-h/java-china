package org.javachina.controller.api;

import org.javachina.dto.RestResponse;
import org.javachina.dto.SiteCount;
import org.javachina.kit.Utils;
import org.javachina.service.EnvService;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.Response;

@Controller(value = "env", suffix = ".json")
public class EnvController {

	@Inject
	private EnvService envService;
	
	@Route("site_count")
	public void getEnvCount(Response response) {
		SiteCount siteCount = envService.getSiteCount();
		RestResponse<SiteCount> restResponse = RestResponse.build(siteCount);
		response.json(Utils.toJSONString(restResponse));
	}

}
