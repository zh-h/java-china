package com.javachina.controller.api;

import com.alibaba.fastjson.JSON;
import com.blade.annotation.Controller;
import com.blade.annotation.RequestParam;
import com.blade.annotation.Route;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.web.http.Response;
import com.javachina.dto.TopicDto;
import com.javachina.service.TopicService;

@Controller("/api")
public class TopicController {

	@Inject
	private TopicService topicService;
	
	@Route("/topics")
	public void getTopic(
			Response response,
			@RequestParam(value = "nid", required = false) Integer nid,
			@RequestParam(value = "is_essence", required = false) Integer is_essence,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
		
		Pager<TopicDto> pager = topicService.getTopics(page, limit);
		response.json(JSON.toJSONString(pager));
	}
	
}
