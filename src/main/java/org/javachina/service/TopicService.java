package org.javachina.service;

import java.util.List;

import com.blade.jdbc.Pager;
import org.javachina.dto.TopicDto;
import org.javachina.model.Topic;

public interface TopicService {
	
	Topic getTopic(Integer tid);
	
	TopicDto getTopicDetail(Integer tid);
	
	Pager<TopicDto> getTopics(Integer nid, int page, int limit);

	List<TopicDto> getTodayTopics(int limit);
	
}
