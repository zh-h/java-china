package com.javachina.service;

import java.util.List;

import com.blade.jdbc.Pager;
import com.javachina.dto.TopicDto;
import com.javachina.model.Topic;

public interface TopicService {
	
	Topic getTopic(Integer tid);
	
	Pager<TopicDto> getTopics(int page, int limit);

	List<TopicDto> getTodayTopics(int limit);
	
}
