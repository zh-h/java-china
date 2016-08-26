package com.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.javachina.model.Openid;
import com.javachina.service.OpenIdService;

@Service
public class OpenIdServiceImpl implements OpenIdService {

	@Override
	public Openid getOpenid(String type, Integer open_id) {
		if(null == open_id || StringKit.isBlank(type)){
			return null;
		}
		return Openid.db.eq("open_id", open_id).eq("type", type).first(Openid.class);
	}

	@Override
	public boolean save(String type, Integer open_id, Integer uid) {
		if(StringKit.isNotBlank(type) && null != open_id && null != uid){
			try {
				
				Long count = Openid.db.eq("open_id", open_id).eq("type", type).eq("uid", uid).count(Openid.class);
				if(count == 0){
					Openid openid = new Openid();
					openid.type = type;
					openid.open_id = open_id;
					openid.uid = uid;
					openid.create_time = DateKit.getCurrentUnixTime();
					
					Openid.db.insert(openid);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(String type, Integer open_id) {
		if(null != open_id && StringKit.isNotBlank(type)){
			Openid openid = new Openid();
			openid.type = type;
			openid.open_id = open_id;
			Openid.db.delete(openid);
			return true;
		}
		return false;
	}

}
