package com.javachina.controller.api;

import com.alibaba.fastjson.JSON;
import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.web.http.Response;
import com.javachina.model.Node;
import com.javachina.service.NodeService;

@Controller("/api")
public class NodeController {

	@Inject
	private NodeService nodeService;
	
	@Route("/nodes")
	public void getNodes(
			Response response,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
		
		Pager<Node> pager = nodeService.getNodes("topics desc", page, limit);
		response.json(JSON.toJSONString(pager));
	}
	
}
