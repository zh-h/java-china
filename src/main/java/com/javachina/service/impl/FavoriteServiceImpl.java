package com.javachina.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.javachina.Types;
import com.javachina.model.Favorite;
import com.javachina.model.Topic;
import com.javachina.service.FavoriteService;
import com.javachina.service.NodeService;
import com.javachina.service.TopicCountService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Inject
	private TopicService topicService;
	
	@Inject
	private UserService userService;
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private TopicCountService topicCountService;
	
	public Favorite getFavorite(String type,Integer uid, Integer event_id) {
		return Favorite.db.eq("type", type).eq("uid", uid).eq("event_id", event_id).first(Favorite.class);
	}
	
	@Override
	public Integer update(String type, Integer uid, Integer event_id) {
		
		try {
			int count = 0;
			boolean isFavorite = this.isFavorite(type, uid, event_id);
			if(!isFavorite){
				Favorite favorite = new Favorite();
				favorite.type = type;
				favorite.uid = uid;
				favorite.event_id = event_id;
				favorite.create_time = DateKit.getCurrentUnixTime();
				Favorite.db.insert(favorite);
				count = 1;
			} else {
				Favorite favorite = new Favorite();
				favorite.type = type;
				favorite.uid = uid;
				favorite.event_id = event_id;
				Favorite.db.delete(favorite);
				count = -1;
			}
			
			// 收藏帖子
			if(type.equals(Types.topic.toString())){
				topicCountService.update(Types.favorites.toString(), event_id, count);
				//topicService.updateWeight(event_id);
			}
			
			// 帖子点赞
			if(type.equals(Types.love.toString())){
				topicCountService.update(Types.loves.toString(), event_id, count);
				//topicService.updateWeight(event_id);
			}
			
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isFavorite(String type, Integer uid, Integer event_id) {
		if (StringKit.isBlank(type) || null == uid || null == event_id) {
			return false;
		}
		return null != this.getFavorite(type, uid, event_id);
	}

	@Override
	public Long favorites(String type, Integer uid) {
		if(null != uid && StringKit.isNotBlank(type)){
			return Favorite.db.eq("type", type).eq("uid", uid).count(Long.class);
		}
		return 0L;
	}

	@Override
	public Pager<Map<String, Object>> getMyTopics(Integer uid, Integer page, Integer count) {
		if(null != uid){
			if(null == page || page < 1){
				page = 1;
			}
			
			if(null == count || count < 1){
				count = 10;
			}
			
			Pager<Favorite> faPage = Favorite.db.eq("type", Types.topic.toString()).eq("uid", uid).orderBy("id desc").page(page, count, Favorite.class);
			if(null != faPage && faPage.getTotal() > 0){
				long totalCount = faPage.getTotal();
				int page_ = faPage.getPageNum();
				int pageSize = faPage.getLimit();
				
				Pager<Map<String, Object>> result = new Pager<Map<String,Object>>(totalCount, page_, pageSize);
				
				List<Favorite> favorites = faPage.getList();
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				if(null != favorites && favorites.size() > 0){
					for(Favorite favorite : favorites){
						Integer tid = favorite.event_id;
						Topic topic = topicService.getTopic(tid);
						/*Map<String, Object> topicMap = topicService.getTopicMap(topic, false);
						if(null != topicMap && !topicMap.isEmpty()){
							list.add(topicMap);
						}*/
					}
				}
				result.setList(list);
				
				return result;
			}
		}
		return null;
	}

	@Override
	public Pager<Map<String, Object>> getFollowing(Integer uid, Integer page, Integer count) {
		if(null != uid){
			if(null == page || page < 1){
				page = 1;
			}
			if(null == count || count < 1){
				count = 10;
			}
			
			Pager<Favorite> faPage = Favorite.db.eq("type", Types.following.toString()).eq("uid", uid).orderBy("id desc").page(page, count, Favorite.class);
			if(null != faPage && faPage.getTotal() > 0){
				long totalCount = faPage.getTotal();
				int page_ = faPage.getPageNum();
				int pageSize = faPage.getLimit();
				Pager<Map<String, Object>> result = new Pager<Map<String,Object>>(totalCount, page_, pageSize);
				
				List<Favorite> favorites = faPage.getList();
				
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				if(null != favorites && favorites.size() > 0){
					for(Favorite favorite : favorites){
						Integer user_id = favorite.event_id;
						Map<String, Object> userMap = userService.getUserDetail(user_id);
						if(null != userMap && !userMap.isEmpty()){
							list.add(userMap);
						}
					}
				}
				result.setList(list);
				return result;
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getMyNodes(Integer uid) {
		if(null != uid){
			List<Favorite> favorites = Favorite.db.eq("type", Types.node.toString()).eq("uid", uid).orderBy("id desc").list(Favorite.class);
			if(null != favorites && favorites.size() > 0){
				List<Map<String, Object>> result = new ArrayList<Map<String,Object>>(favorites.size());
//				for(Favorite favorite : favorites){
//					Integer nid = favorite.event_id;
//					Map<String, Object> node = nodeService.getNodeDetail(null, nid);
//					result.add(node);
//				}
				return result;
			}
		}
		return null;
	}
	
}
