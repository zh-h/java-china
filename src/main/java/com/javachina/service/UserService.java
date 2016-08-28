package com.javachina.service;

import com.blade.jdbc.Pager;
import com.javachina.model.User;

public interface UserService {
	
	User getUser(Integer uid);
	
	Pager<User> getPageList(Integer status, Integer uid, String orderby, int page, int count);
	
	User getUser(String username, String password);
	
}
