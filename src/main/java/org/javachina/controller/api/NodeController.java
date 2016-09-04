package org.javachina.controller.api;

import java.util.List;

import org.javachina.dto.RestResponse;
import org.javachina.kit.Utils;
import org.javachina.model.Node;
import org.javachina.service.NodeService;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.Response;

@Controller(value = "nodes", suffix = ".json")
public class NodeController {

	@Inject
	private NodeService nodeService;
	
	@Route
	public void getNodes(
			Response response,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
		
		Pager<Node> pager = nodeService.getNodes("topics desc", page, limit);
		RestResponse<Pager<Node>> restResponse = RestResponse.build(pager);

		response.json(Utils.toJSONString(restResponse));
	}
	
	@Route("hot")
	public void getHotNodes(Response response,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		
		List<Node> list = nodeService.getHotNodes(limit);
		RestResponse<List<Node>> restResponse = RestResponse.build(list);

		response.json(Utils.toJSONString(restResponse));
	}
	
}
