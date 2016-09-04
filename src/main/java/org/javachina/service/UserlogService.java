package org.javachina.service;

public interface UserlogService {
	
	void save(Integer uid, String action, String content);
	
}
