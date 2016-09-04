package org.javachina.controller.api;

import java.util.List;

import org.javachina.annotation.Permissions;
import org.javachina.dto.RestResponse;
import org.javachina.dto.TopicDto;
import org.javachina.kit.Utils;
import org.javachina.service.TopicService;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.RestController;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.http.Response;

@RestController(value = "topics", suffix = ".json")
public class TopicController {

	@Inject
	private TopicService topicService;

	/**
	 * 获取帖子列表
	 * 
	 * @param response
	 * @param nid
	 * @param is_essence
	 * @param page
	 * @param limit
	 */
	@Route(value = "/", method = HttpMethod.GET)
	public void getTopic(Response response, @RequestParam(value = "nid", required = false) Integer nid,
			@RequestParam(value = "is_essence", required = false) Integer is_essence,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		Pager<TopicDto> pager = topicService.getTopics(null, page, limit);

		RestResponse<Pager<TopicDto>> restResponse = RestResponse.build(pager);

		response.json(Utils.toJSONString(restResponse));
	}

	@Route(value="today", method = HttpMethod.GET)
	@Permissions
	public RestResponse<List<TopicDto>> getTodayHot(Response response,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		List<TopicDto> list = topicService.getTodayTopics(limit);
		RestResponse<List<TopicDto>> restResponse = RestResponse.build(list);
		return restResponse;
	}

}
