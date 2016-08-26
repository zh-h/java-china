package com.javachina.service;

import com.javachina.model.TopicCount;

public interface TopicCountService {
	
	TopicCount getCount(Integer tid);
	
	boolean update(String type, Integer tid, int count);
	
	boolean save(Integer tid, Integer create_time);
	
}
