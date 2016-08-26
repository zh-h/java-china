package com.javachina.controller.api;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.web.http.Response;
import com.javachina.dto.TopicDto;
import com.javachina.service.TopicService;

@Controller(value = "/api", suffix = ".json")
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
	@Route("/topics")
	public void getTopic(Response response, @RequestParam(value = "nid", required = false) Integer nid,
			@RequestParam(value = "is_essence", required = false) Integer is_essence,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		Pager<TopicDto> pager = topicService.getTopics(page, limit);
		response.json(JSON.toJSONString(pager));
	}

	@Route("/topics/today")
	public void getTodayHot(Response response,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		
		List<TopicDto> list = topicService.getTodayTopics(limit);
		response.json(JSON.toJSONString(list));
	}

}
