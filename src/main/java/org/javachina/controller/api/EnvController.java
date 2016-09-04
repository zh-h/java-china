package org.javachina.controller.api;

import org.javachina.dto.RestResponse;
import org.javachina.dto.SiteCount;
import org.javachina.service.EnvService;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Response;

@RestController(value = "env", suffix = ".json")
public class EnvController {

	@Inject
	private EnvService envService;

	@Route(value = "site_count", method = HttpMethod.GET)
	public RestResponse<SiteCount> getEnvCount(Response response) {
		SiteCount siteCount = envService.getSiteCount();
		RestResponse<SiteCount> restResponse = RestResponse.build(siteCount);
		return restResponse;
	}

}
