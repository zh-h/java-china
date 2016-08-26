package com.javachina.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.blade.kit.DateKit;
import com.blade.kit.EncrypKit;
import com.blade.kit.FileKit;
import com.blade.kit.StringKit;
import com.javachina.ImageTypes;
import com.javachina.Types;
import com.javachina.kit.Utils;
import com.javachina.model.LoginUser;
import com.javachina.model.User;
import com.javachina.model.Userinfo;
import com.javachina.service.ActivecodeService;
import com.javachina.service.CommentService;
import com.javachina.service.FavoriteService;
import com.javachina.service.NoticeService;
import com.javachina.service.TopicService;
import com.javachina.service.UserService;
import com.javachina.service.UserinfoService;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private ActivecodeService activecodeService;

	@Inject
	private TopicService topicService;

	@Inject
	private UserinfoService userinfoService;

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
	public User signup(String loginName, String passWord, String email) {
		if (StringKit.isBlank(loginName) || StringKit.isBlank(passWord) || StringKit.isBlank(email)) {
			return null;
		}

		User user = this.getUserByLoginName(loginName);
		if (null != user) {
			return user;
		}

		user = this.getUserByEmail(email);
		if (null != user) {
			return user;
		}

		try {

			int time = DateKit.getCurrentUnixTime();
			String pwd = EncrypKit.md5(loginName + passWord);
			String avatar = "avatar/default/" + StringKit.getRandomNumber(1, 5) + ".png";

			User temp = new User();
			temp.login_name = loginName;
			temp.pass_word = pwd;
			temp.email = email;
			temp.avatar = avatar;
			temp.status = 0;
			temp.create_time = time;
			temp.update_time = time;

			User.db.insert(temp);

			// 发送邮件通知
			activecodeService.save(user, Types.signup.toString());
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(Integer uid) {
		if (null != uid) {
			User temp = new User();
			temp.uid = uid;
			User.db.delete(temp);
			return true;
		}
		return false;
	}

	@Override
	public boolean resetPwd(String email) {
		return false;
	}

	@Override
	public User signin(String loginName, String passWord) {
		if (StringKit.isBlank(loginName) || StringKit.isBlank(passWord)) {
			return null;
		}
		String pwd = EncrypKit.md5(loginName + passWord);

		User user = User.db.in("status", new Object[] { 0, 1 }).eq("login_name", loginName).eq("pass_word", pwd)
				.first(User.class);

		if (null == user) {
			user = User.db.in("status", new Object[] { 0, 1 }).eq("email", loginName).eq("pass_word", pwd)
					.first(User.class);
		}
		return user;
	}

	@Override
	public Map<String, Object> getUserDetail(Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != uid) {
			User user = this.getUser(uid);
			if (null == user) {
				return map;
			}
			map.put("username", user.login_name);
			map.put("uid", uid);
			map.put("email", user.email);
			String avatar = Utils.getAvatar(user.avatar, ImageTypes.normal);
			map.put("avatar", avatar);
			map.put("create_time", user.create_time);

			Userinfo userinfo = userinfoService.getUserinfo(uid);
			if (null != userinfo) {
				map.put("jobs", userinfo.jobs);
				map.put("github", userinfo.github);
				map.put("weibo", userinfo.weibo);
				map.put("nick_name", userinfo.nick_name);
				map.put("location", userinfo.location);
				map.put("signature", userinfo.signature);
				map.put("web_site", userinfo.web_site);
				map.put("instructions", userinfo.instructions);
			}
		}
		return map;
	}

	@Override
	public boolean updateStatus(Integer uid, Integer status) {
		if (null != uid && null != status) {
			try {
				User temp = new User();
				temp.uid = uid;
				temp.status = status;
				User.db.update(temp);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updateAvatar(Integer uid, String avatar_path) {
		try {
			if (null == uid || StringKit.isBlank(avatar_path)) {
				return false;
			}

			File file = new File(avatar_path);
			if (file.exists()) {

				User user = this.getUser(uid);
				if (null == user) {
					return false;
				}

				String ext = FileKit.getExtension(file.getName());
				if (StringKit.isBlank(ext)) {
					ext = "png";
				}

				String key = "avatar/" + user.login_name + "/" + StringKit.getRandomChar(4) + "/"
						+ StringKit.getRandomNumber(4) + "." + ext;

				/*boolean flag = QiniuKit.upload(file, key);
				if (flag) {
					User temp = new User();
					temp.uid = uid;
					temp.avatar = key;
					User.db.update(temp);
					return true;
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePwd(Integer uid, String newpwd) {
		try {
			if (null == uid || StringKit.isBlank(newpwd)) {
				return false;
			}
			User temp = new User();
			temp.uid = uid;
			temp.pass_word = newpwd;
			User.db.update(temp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public LoginUser getLoginUser(User user, Integer uid) {
		if (null == user) {
			user = this.getUser(uid);
		}
		if (null != user) {
			LoginUser loginUser = new LoginUser();
			loginUser.setUid(user.uid);
			loginUser.setUser_name(user.login_name);
			loginUser.setPass_word(user.pass_word);
			loginUser.setStatus(user.status);
			loginUser.setRole_id(user.role_id);
			String avatar = Utils.getAvatar(user.avatar, ImageTypes.normal);
			loginUser.setAvatar(avatar);

			Long comments = commentService.getComments(user.uid);
			loginUser.setComments(comments);

//			Long topics = topicService.getTopics(user.uid);
//			loginUser.setTopics(topics);

			Long notices = noticeService.getNotices(user.uid);
			loginUser.setNotices(notices);

			Userinfo userinfo = userinfoService.getUserinfo(user.uid);
			if (null != userinfo) {
				loginUser.setJobs(userinfo.jobs);
				loginUser.setNick_name(userinfo.nick_name);
			}

			Long my_topics = favoriteService.favorites(Types.topic.toString(), user.uid);
			Long my_nodes = favoriteService.favorites(Types.node.toString(), user.uid);

			loginUser.setMy_topics(my_topics);
			loginUser.setMy_nodes(my_nodes);

			Long following = favoriteService.favorites(Types.following.toString(), user.uid);
			loginUser.setFollowing(following);

			return loginUser;
		}
		return null;
	}

	@Override
	public boolean hasUser(String login_name) {
		if (StringKit.isNotBlank(login_name)) {
			Long count = User.db.eq("login_name", login_name).in("status", new Object[] { 0, 1 }).count(Long.class);
			if (count == 0) {
				count = User.db.eq("email", login_name).in("status", new Object[] { 0, 1 }).count(Long.class);
			}
			return count > 0;
		}
		return false;
	}

	@Override
	public User getUserByLoginName(String user_name) {
		if (StringKit.isNotBlank(user_name)) {
			return User.db.eq("status", 1).eq("login_name", user_name).first(User.class);
		}
		return null;
	}

	public User getUserByEmail(String email) {
		if (StringKit.isNotBlank(email)) {
			return User.db.eq("status", 1).eq("email", email).first(User.class);
		}
		return null;
	}

	@Override
	public boolean updateRole(Integer uid, Integer role_id) {
		try {
			if (null == uid || null == role_id || role_id == 1) {
				return false;
			}
			User temp = new User();
			temp.uid = uid;
			temp.role_id = role_id;
			User.db.update(temp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
