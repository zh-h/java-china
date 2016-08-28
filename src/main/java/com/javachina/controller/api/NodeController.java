package com.javachina.controller.api;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.web.http.Response;
import com.javachina.dto.RestResponse;
import com.javachina.kit.Utils;
import com.javachina.model.Node;
import com.javachina.service.NodeService;

@Controller(value = "/nodes", suffix = ".json")
public class NodeController {

	@Inject
	private NodeService nodeService;
	
	@Route("/")
	public void getNodes(
			Response response,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
		
		Pager<Node> pager = nodeService.getNodes("topics desc", page, limit);
		RestResponse restResponse = RestResponse.build(pager);

		response.json(Utils.toJSONString(restResponse));
	}
	
	@Route("/hot")
	public void getHotNodes(Response response,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		
		List<Node> list = nodeService.getHotNodes(limit);
		RestResponse restResponse = RestResponse.build(list);

		response.json(Utils.toJSONString(restResponse));
	}
	
}
