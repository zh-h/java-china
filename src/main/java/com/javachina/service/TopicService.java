package com.javachina.service;

import com.blade.jdbc.Pager;
import com.javachina.dto.TopicDto;
import com.javachina.model.Topic;

public interface TopicService {
	
	Topic getTopic(Integer tid);
	
	Pager<TopicDto> getTopics(int page, int limit);
	
}
