package com.javachina.service.impl;

import com.blade.context.ApplicationWebContext;
import com.blade.ioc.annotation.Service;
import com.blade.kit.DateKit;
import com.javachina.kit.Utils;
import com.javachina.model.Userlog;
import com.javachina.service.UserlogService;


@Service
public class UserlogServiceImpl implements UserlogService {
	
	@Override
	public void save(final Integer uid, final String action, final String content) {
		final String ip = Utils.getIpAddr(ApplicationWebContext.request());
		Runnable t = new Runnable() {
			@Override
			public void run() {
				try {
					Userlog userlog = new Userlog();
					userlog.uid = uid;
					userlog.action = action;
					userlog.content = content;
					userlog.ip_addr = ip;
					userlog.create_time = DateKit.getCurrentUnixTime();
					Userlog.db.insert(userlog);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		Utils.run(t);
	}
	
}