package com.javachina.service;

import java.util.Map;

import com.blade.jdbc.Pager;

public interface NoticeService {
	
	boolean save(String type, Integer to_uid, Integer event_id);
	
	boolean read(Integer to_uid);
	
	Pager<Map<String, Object>> getNoticePage(Integer to_uid, Integer page, Integer count);

	Long getNotices(Integer to_uid);
	
}
