package com.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.blade.kit.EncrypKit;
import com.blade.kit.StringKit;
import com.javachina.model.User;
import com.javachina.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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

}
