package com.javachina.service;

import java.util.Map;

import com.blade.jdbc.Pager;
import com.javachina.model.LoginUser;
import com.javachina.model.User;

public interface UserService {
	
	User getUser(Integer uid);
	
	User getUserByLoginName(String user_name);
	
	Map<String, Object> getUserDetail(Integer uid);
	
	Pager<User> getPageList(Integer status, Integer uid, String orderby, int page, int count);
	
	User signup(String loginName, String passWord, String email);
	
	User signin(String loginName, String passWord);
	
	LoginUser getLoginUser(User user, Integer uid);
	
	boolean hasUser(String login_name);
	
	boolean delete(Integer uid);
	
	boolean updateStatus(Integer uid, Integer status);
	
	boolean resetPwd(String email);

	boolean updateAvatar(Integer uid, String avatar_path);

	boolean updatePwd(Integer uid, String new_pwd);

	boolean updateRole(Integer uid, Integer role_id);
	
}
