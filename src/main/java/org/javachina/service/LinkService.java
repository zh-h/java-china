package org.javachina.service;

import org.javachina.model.Link;

public interface LinkService {
	
	Link getLink(Integer id);
	
	boolean save( String title, String url);
	
	boolean delete(Integer id);
		
}
