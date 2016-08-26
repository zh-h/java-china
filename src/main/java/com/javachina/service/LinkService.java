package com.javachina.service;

import com.javachina.model.Link;

public interface LinkService {
	
	Link getLink(Integer id);
	
	boolean save( String title, String url);
	
	boolean delete(Integer id);
		
}
