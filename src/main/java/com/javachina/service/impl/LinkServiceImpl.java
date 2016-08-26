package com.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.javachina.model.Link;
import com.javachina.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService {
	
	@Override
	public Link getLink(Integer id) {
		return Link.db.findByPK(id, Link.class);
	}
	
	@Override
	public boolean save(String title, String url) {
		return false;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(null != id){
			Link link = new Link();
			link.id = id;
			Link.db.delete(link);
			return true;
		}
		return false;
	}
		
}
