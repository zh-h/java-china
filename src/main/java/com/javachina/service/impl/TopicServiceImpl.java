package com.javachina.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.javachina.dto.TopicDto;
import com.javachina.model.Topic;
import com.javachina.service.CommentService;
import com.javachina.service.NodeService;
import com.javachina.service.NoticeService;
import com.javachina.service.SettingsService;
import com.javachina.service.TopicCountService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

@Service
public class TopicServiceImpl implements TopicService {

	@Inject
	private UserService userService;

	@Inject
	private NodeService nodeService;

	@Inject
	private CommentService commentService;

	@Inject
	private NoticeService noticeService;

	@Inject
	private SettingsService settingsService;

	@Inject
	private TopicCountService topicCountService;

	@Override
	public Topic getTopic(Integer tid) {
		return Topic.db.findByPK(tid, Topic.class);
	}

	@Override
	public Pager<TopicDto> getTopics(int page, int limit) {
		
		String sql = "select a.tid, a.title, a.create_time, a.update_time, b.title as node_name, b.slug as node_slug, c.avatar "
				+ "from t_topic a "
				+ "left join t_node b on a.nid = b.nid "
				+ "left join t_user c on a.user_name = c.login_name "
				+ "where a.status = ?";
		
		return Topic.db.sql(sql, 1).orderBy("a.weight desc").page(page, limit, TopicDto.class);
	}
	

}
