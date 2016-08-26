package com.javachina.service;

import com.blade.jdbc.Pager;
import com.javachina.model.Userinfo;

public interface UserinfoService {
	
	Userinfo getUserinfo(Integer uid);
	
	Pager<Userinfo> getPageList(Integer status, Integer is_essence, String orderBy, int page, int count);
	
	boolean save(Integer uid);
	
	boolean update(Integer uid, String nickName, String jobs, String webSite, String github, String weibo, String location, String signature, String instructions );
	
	boolean delete(Integer uid);
		
}
