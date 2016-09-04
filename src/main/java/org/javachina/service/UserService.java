package org.javachina.service;

import com.blade.jdbc.Pager;
import org.javachina.model.LoginUser;
import org.javachina.model.User;

public interface UserService {
	
	User getUser(Integer uid);
	
	Pager<User> getPageList(Integer status, Integer uid, String orderby, int page, int count);
	
	User getUser(String username, String password);

	LoginUser getLoginUser(User user);
}
