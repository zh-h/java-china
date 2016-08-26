package com.javachina.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.blade.kit.PatternKit;
import com.javachina.model.Userinfo;
import com.javachina.service.UserinfoService;

@Service
public class UserinfoServiceImpl implements UserinfoService {
	
	@Override
	public Userinfo getUserinfo(Integer uid) {
		return Userinfo.db.findByPK(uid, Userinfo.class);
	}
	
	@Override
	public Pager<Userinfo> getPageList(Integer status, Integer is_essence, String orderBy, int page, int count) {
		return Userinfo.db.eq("status", status).eq("is_essence", is_essence).orderBy(orderBy).page(page, count, Userinfo.class);
	}
	
	@Override
	public boolean save(Integer uid) {
		if(null == uid){
			return false;
		}
		try {
			Userinfo userinfo = new Userinfo();
			userinfo.uid = uid;
			Userinfo.db.insert(userinfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean update(Integer uid, String nickName, String jobs, String webSite, 
			String github, String weibo, String location, String signature, String instructions) {
		if(null != uid){
			StringBuffer updateSql = new StringBuffer("update t_userinfo set ");
			List<Object> params = new ArrayList<Object>();
			if(null != nickName){
				updateSql.append("nick_name = ?, ");
				params.add(nickName);
			}
			if(null != jobs){
				updateSql.append("jobs = ?, ");
				params.add(jobs);
			}
			if(null != webSite){
				updateSql.append("web_site = ?, ");
				params.add(webSite);
			}
			if(null != github){
				if(github.equals("") || PatternKit.isStudentNum(github)){
					updateSql.append("github = ?, ");
					params.add(github);
				}
			}
			if(null != weibo){
				if(weibo.equals("") || PatternKit.isStudentNum(weibo)){
					updateSql.append("weibo = ?, ");
					params.add(weibo);
				}
			}
			if(null != location){
				updateSql.append("location = ?, ");
				params.add(location);
			}
			if(null != signature){
				updateSql.append("signature = ?, ");
				params.add(signature);
			}
			if(null != instructions){
				updateSql.append("instructions = ?, ");
				params.add(instructions);
			}
			if(params.size() > 0){
				updateSql = new StringBuffer(updateSql.substring(0, updateSql.length() - 2));
				updateSql.append(" where uid = ? ");
				params.add(uid);
				
				Userinfo.db.sql(updateSql.toString(), params.toArray()).execute();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean delete(Integer uid) {
		if(null != uid){
			Userinfo userinfo = new Userinfo(); 
			userinfo.uid = uid;
			Userinfo.db.delete(userinfo);
			return true;
		}
		return false;
	}

}
