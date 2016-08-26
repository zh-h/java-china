package com.javachina.controller.api;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.web.http.Response;
import com.javachina.model.Node;
import com.javachina.service.NodeService;

@Controller(value = "/api", suffix = ".json")
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
	
	@Route("/nodes/hot")
	public void getHotNodes(Response response,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		
		List<Node> list = nodeService.getHotNodes(limit);
		response.json(JSON.toJSONString(list));
	}
	
}
