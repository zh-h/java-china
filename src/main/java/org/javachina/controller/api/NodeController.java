package org.javachina.controller.api;

import java.util.List;

import org.javachina.dto.RestResponse;
import org.javachina.kit.Utils;
import org.javachina.model.Node;
import org.javachina.service.NodeService;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Response;

@RestController(value = "nodes", suffix = ".json")
public class NodeController {

	@Inject
	private NodeService nodeService;

	@Route(value = "/", method = HttpMethod.GET)
	public void getNodes(Response response,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		
		Pager<Node> pager = nodeService.getNodes("topics desc", page, limit);
		RestResponse<Pager<Node>> restResponse = RestResponse.build(pager);

		response.json(Utils.toJSONString(restResponse));
	}

	@Route(value = "hot", method = HttpMethod.GET)
	public RestResponse<List<Node>> getHotNodes(Response response) {
		List<Node> hot_nodes = nodeService.getNodes("topics desc", 1, 10).getList();
		RestResponse<List<Node>> restResponse = RestResponse.build(hot_nodes);
		return restResponse;
	}

}
