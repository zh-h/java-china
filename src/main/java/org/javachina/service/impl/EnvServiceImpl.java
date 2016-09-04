package org.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import org.javachina.dto.SiteCount;
import org.javachina.model.Settings;
import org.javachina.service.EnvService;

@Service
public class EnvServiceImpl implements EnvService {
	
	@Override
	public SiteCount getSiteCount() {
		SiteCount siteCount = new SiteCount();
		String users = Settings.db.eq("skey", "user_count").first(Settings.class).svalue;
		String topics = Settings.db.eq("skey", "topic_count").first(Settings.class).svalue;
		String comments = Settings.db.eq("skey", "comment_count").first(Settings.class).svalue;
		siteCount.users = Integer.valueOf(users);
		siteCount.topics = Integer.valueOf(topics);
		siteCount.comments = Integer.valueOf(comments);
		return siteCount;
	}
	
}
