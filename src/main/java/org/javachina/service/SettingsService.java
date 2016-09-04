package org.javachina.service;

import java.util.Map;

import org.javachina.model.Settings;

public interface SettingsService {
	
	Settings getSettings(String skey);
	
	Map<String, Object> getSystemInfo();
	
	boolean save( String svalue );
	
	boolean delete(String skey);

	boolean updateCount(String skey, int count);
	
	boolean refreshCount();

	boolean update(String site_title, String site_keywords, String site_description, String allow_signup);
	
}
