package org.javachina.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.blade.kit.EncrypKit;
import com.blade.kit.StringKit;
import org.javachina.Constant;
import org.javachina.kit.Utils;
import org.javachina.model.LoginUser;
import org.javachina.model.User;
import org.javachina.model.Userinfo;
import org.javachina.service.*;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private TopicService topicService;

//	@Inject
//	private UserinfoService userinfoService;

	@Inject
	private FavoriteService favoriteService;

	@Inject
	private CommentService commentService;

	@Inject
	private NoticeService noticeService;

	@Override
	public User getUser(Integer uid) {
		return User.db.findByPK(uid, User.class);
	}

	@Override
	public Pager<User> getPageList(Integer status, Integer uid, String orderby, int page, int count) {
		return User.db.eq("status", status).eq("uid", uid).orderBy(orderby).page(page, count, User.class);
	}

	@Override
	public User getUser(String loginname, String password) {
		if (StringKit.isBlank(password) || StringKit.isBlank(password)) {
			return null;
		}

		String pwd = EncrypKit.md5(loginname + password);
		User user = User.db.eq("login_name", loginname).eq("pass_word", pwd).first(User.class);
		if (null == user) {
			user = User.db.eq("email", loginname).eq("pass_word", pwd).first(User.class);
		}
		return user;
	}

	@Override
	public LoginUser getLoginUser(User user) {
		LoginUser loginUser = new LoginUser();
		loginUser.setUid(user.uid);
		loginUser.setUser_name(user.login_name);
		loginUser.setStatus(user.status);
		loginUser.setRole_id(user.role_id);
		loginUser.setAvatar(user.avatar);

		Long comments = commentService.getComments(user.uid);
		loginUser.setComments(comments);

		long topics = topicService.getTopics(user.login_name);
		loginUser.setTopics(topics);

		Long notices = noticeService.getNotices(user.uid);
		loginUser.setNotices(notices);

//		Userinfo userinfo = userinfoService.getUserinfo(user.getUid());
//		if(null != userinfo){
//			loginUser.setJobs(userinfo.getJobs());
//			loginUser.setNick_name(userinfo.getNick_name());
//		}

//		Long my_topics = favoriteService.favorites(Types.topic.toString(), user.getUid());
//		Long my_nodes = favoriteService.favorites(Types.node.toString(), user.getUid());
//
//		loginUser.setMy_topics(my_topics);
//		loginUser.setMy_nodes(my_nodes);
//
//		Long following = favoriteService.favorites(Types.following.toString(), user.getUid());
//		loginUser.setFollowing(following);

		return loginUser;
	}

}
