package com.javachina.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.blade.kit.DateKit;
import com.javachina.Constant;
import com.javachina.kit.Utils;
import com.javachina.model.Comment;
import com.javachina.model.Topic;
import com.javachina.model.User;
import com.javachina.service.CommentService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private UserService userService;

	@Inject
	private TopicService topicService;

	@Override
	public Comment getComment(Integer cid) {
		return Comment.db.findByPK(cid, Comment.class);
	}

	@Override
	public Pager<Map<String, Object>> getPageListMap(Integer tid, Integer uid, String orderby, int page, int count) {

		Pager<Comment> pager = Comment.db.eq("tid", tid).eq("uid", uid).orderBy(orderby).page(page, count,
				Comment.class);
		if (null != pager) {
			return this.getCommentPageMap(pager);
		}
		return null;
	}

	private Pager<Map<String, Object>> getCommentPageMap(Pager<Comment> commentPage) {

		long totalCount = commentPage.getTotal();
		int page = commentPage.getPageNum();
		int limit = commentPage.getLimit();

		Pager<Map<String, Object>> result = new Pager<Map<String, Object>>(totalCount, page, limit);

		List<Comment> comments = commentPage.getList();
		List<Map<String, Object>> nodeMaps = new ArrayList<Map<String, Object>>();
		if (null != comments && comments.size() > 0) {
			for (Comment comment : comments) {
				Map<String, Object> map = this.getCommentDetail(comment, null);
				if (null != map && !map.isEmpty()) {
					nodeMaps.add(map);
				}
			}
		}
		result.setList(nodeMaps);
		return result;
	}

	private Map<String, Object> getCommentDetail(Comment comment, Integer cid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null == comment) {
			comment = this.getComment(cid);
		}
		if (null != comment) {

			Integer comment_uid = comment.uid;
			User comment_user = userService.getUser(comment_uid);
			Topic topic = topicService.getTopic(comment.tid);
			if (null == comment_user || null == topic) {
				return map;
			}

			map.put("cid", comment.cid);
			map.put("tid", comment.tid);
			map.put("role_id", comment_user.role_id);
			map.put("reply_name", comment_user.login_name);
			map.put("reply_time", comment.create_time);
			map.put("device", comment.device);
			map.put("reply_avatar", Utils.getAvatar(comment_user.avatar, Constant.ImageTypes.small));
			map.put("title", topic.title);
		}
		return map;
	}

	@Override
	public Integer save(Integer uid, Integer toUid, Integer tid, String content, String ua) {
		Comment comment = new Comment();
		comment.uid = uid;
		comment.to_uid = toUid;
		comment.tid = tid;
		comment.content = content;
		comment.device = ua;
		comment.create_time = DateKit.getCurrentUnixTime();

		try {
			return Comment.db.insert(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Integer cid) {
		if (null != cid) {
			Comment comment = new Comment();
			comment.cid = cid;
			Comment.db.delete(comment);
			return true;
		}
		return false;
	}

	@Override
	public Comment getTopicLastComment(Integer tid) {
		return Comment.db.eq("tid", tid).orderBy("cid desc").first(Comment.class);
	}

	@Override
	public Long getComments(Integer uid) {
		return Comment.db.eq("uid", uid).count(Long.class);
	}

}
