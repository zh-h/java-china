package com.javachina.service;

import java.util.List;
import java.util.Map;

import com.blade.jdbc.Pager;

public interface FavoriteService {
	
	boolean isFavorite(String type, Integer uid, Integer event_id);
	
	// 查询我收藏的帖子
	Pager<Map<String, Object>> getMyTopics(Integer uid, Integer page, Integer count);
	
	// 查询我关注的用户
	Pager<Map<String, Object>> getFollowing(Integer uid, Integer page, Integer count);
	
	// 查询我收藏的节点
	List<Map<String, Object>> getMyNodes(Integer uid);
	
	Integer update(String type, Integer uid, Integer eventId);
	
	Long favorites(String type, Integer uid);
}
