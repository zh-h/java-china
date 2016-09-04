package org.javachina.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import org.javachina.dto.TopicDto;
import org.javachina.kit.Utils;
import org.javachina.model.Topic;
import org.javachina.service.CommentService;
import org.javachina.service.NodeService;
import org.javachina.service.NoticeService;
import org.javachina.service.SettingsService;
import org.javachina.service.TopicCountService;
import org.javachina.service.TopicService;
import org.javachina.service.UserService;

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
	public TopicDto getTopicDetail(Integer tid) {
		if(null != tid){
			String sql = "select a.tid, a.user_name, a.reply_user, a.title, a.content, a.create_time, a.update_time, " +
					"b.title as node_name, b.slug as node_slug, c.avatar, d.comments "
					+ "from t_topic a "
					+ "left join t_node b on a.nid = b.nid "
					+ "left join t_user c on a.user_name = c.login_name "
					+ "left join t_topiccount d on a.tid = d.tid "
					+ "where a.status = ? ";
			
			TopicDto topicDto = Topic.db.sql(sql, 1).first(TopicDto.class);
			String content = Utils.markdown2html(topicDto.content);
			topicDto.content = content;
			return topicDto;
		}
		return null;
	}
	
	@Override
	public Pager<TopicDto> getTopics(Integer nid, int page, int limit) {
		String sql = "select a.tid, a.user_name, a.reply_user, a.title, a.create_time, a.update_time, " +
				"b.title as node_name, b.slug as node_slug, c.avatar, d.comments "
				+ "from t_topic a "
				+ "left join t_node b on a.nid = b.nid "
				+ "left join t_user c on a.user_name = c.login_name "
				+ "left join t_topiccount d on a.tid = d.tid "
				+ "where a.status = ? ";
		if(null != nid){
			sql += "and a.nid = " + nid;
		}
		
		return Topic.db.sql(sql, 1).orderBy("a.weight desc").page(page, limit, TopicDto.class);
	}

	@Override
	public List<TopicDto> getTodayTopics(int limit) {
		
		if(limit < 1 || limit > 20){
			limit = 20;
		}
		
		String sql = "select a.tid, a.user_name, a.title, b.avatar "
				+ "from t_topic a "
				+ "left join t_user b on a.user_name = b.login_name "
				+ "where a.status = 1 order by a.weight desc limit ?";
		return Topic.db.sql(sql, limit).list(TopicDto.class);
	}
	
}
