package com.javachina.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.ioc.annotation.Service;
import com.blade.kit.CollectionKit;
import com.blade.kit.StringKit;
import com.javachina.model.Comment;
import com.javachina.model.Settings;
import com.javachina.model.Topic;
import com.javachina.model.User;
import com.javachina.service.SettingsService;

@Service
public class SettingsServiceImpl implements SettingsService {
	
	@Override
	public Settings getSettings(String skey) {
		return Settings.db.findByPK(skey, Settings.class);
	}
	
	@Override
	public boolean save( String svalue ) {
		return false;
	}
	
	@Override
	public boolean delete(String skey) {
		if(null != skey){
			Settings temp = new Settings();
			temp.skey = skey;
			Settings.db.delete(temp);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getSystemInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Settings> settings = Settings.db.cached(false).list(Settings.class);
		if(CollectionKit.isNotEmpty(settings)){
			for(Settings setting : settings){
				map.put(setting.skey, setting.svalue);
			}
		}
		return map;
	}

	@Override
	public boolean updateCount(String skey, int count) {
		try {
			if (StringKit.isNotBlank(skey) && count != 0) {
				Settings settings = this.getSettings(skey);
				if (null != settings) {
					if (StringKit.isNumber(settings.svalue.trim())) {
						Long cur_count = Long.valueOf(settings.svalue.trim());
						String val = (cur_count + count) + "";
						Settings temp = new Settings();
						temp.skey = skey;
						temp.svalue = val;
						Settings.db.update(temp);
						return true;
					}
				}
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean refreshCount() {
		
		Long comments = Comment.db.count(Comment.class);
		Long users = User.db.eq("status", 1).count(User.class);
		Long topics = Topic.db.eq("status", 1).count(Topic.class);
		
//		Settings.db.sql("update t_settings set svalue = ? where skey = ?", users, Types.user_count.toString()).execute();
//		Settings.db.sql("update t_settings set svalue = ? where skey = ?", comments, Types.comment_count.toString()).execute();
//		Settings.db.sql("update t_settings set svalue = ? where skey = ?", topics, Types.topic_count.toString()).execute();
		
		return true;
	}
	
	@Override
	public boolean update(String site_title, String site_keywords, String site_description, String allow_signup) {
		try {
			if (StringKit.isNotBlank(site_title)) {
				Settings temp = new Settings();
				temp.skey = "site_title";
				temp.svalue = site_title;
				Settings.db.update(temp);
			}
			if (StringKit.isNotBlank(site_keywords)) {
				Settings temp = new Settings();
				temp.skey = "site_keywords";
				temp.svalue = site_keywords;
				Settings.db.update(temp);
			}
			if (StringKit.isNotBlank(site_description)) {
				Settings temp = new Settings();
				temp.skey = "site_description";
				temp.svalue = site_description;
				Settings.db.update(temp);
			}
			if (StringKit.isNotBlank(allow_signup)) {
				Settings temp = new Settings();
				temp.skey = "allow_signup";
				temp.svalue = allow_signup;
				Settings.db.update(temp);
			} 
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
}
