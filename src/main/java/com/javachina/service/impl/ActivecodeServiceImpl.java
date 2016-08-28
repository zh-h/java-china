package com.javachina.service.impl;

import com.blade.ioc.annotation.Inject;
import com.blade.ioc.annotation.Service;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.javachina.model.Activecode;
import com.javachina.model.User;
import com.javachina.service.ActivecodeService;
import com.javachina.service.SendMailService;
import com.javachina.service.UserService;

@Service
public class ActivecodeServiceImpl implements ActivecodeService {
	
	@Inject
	private SendMailService sendMailService;
	
	@Inject
	private UserService userService;
	
	@Override
	public Activecode getActivecode(String code) {
		if(StringKit.isBlank(code)){
			return null;
		}
		return Activecode.db.eq("code",	code).first(Activecode.class);
	}
	
	public Activecode getActivecodeById(Integer id) {
		return Activecode.db.findByPK(id, Activecode.class);
	}
		
	@Override
	public String save(User user, String type) {
		
		if(null == user || StringKit.isBlank(type)){
			return null;
		}
		
		int time = DateKit.getCurrentUnixTime();
		int expires_time = time + 3600;
		String code = StringKit.getRandomChar(32);
		try {
			
			Activecode temp = new Activecode();
			temp.uid = user.uid;
			temp.code = code;
			temp.type = type;
			temp.expires_time = expires_time;
			temp.create_time = time;
			
			Activecode.db.insert(temp);
			
//			userinfoService.save(user.uid);
			
			if(type.equals("signup")){
				sendMailService.signup(user.login_name, user.email, code);
			}
			
			if(type.equals("forgot")){
				sendMailService.forgot(user.login_name, user.email, code);
			}
			
			return code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean useCode(String code) {
		if(StringKit.isBlank(code)){
			return false;
		}
		try {
			Activecode.db.sql("update t_activecode set is_use = ? where code = ?", 1, code).execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean resend(Integer uid) {
		if(null != uid){
			User user = userService.getUser(uid);
			if(null == user){
				return false;
			}
			
			int time = DateKit.getCurrentUnixTime();
			int expires_time = time + 3600;
			String code = StringKit.getRandomChar(32);
			try {
				
				Activecode temp = new Activecode();
				temp.uid = user.uid;
				temp.code = code;
				temp.type = "signup";
				temp.expires_time = expires_time;
				temp.create_time = time;
				
				Activecode.db.insert(temp);
				
				sendMailService.signup(user.login_name, user.email, code);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
