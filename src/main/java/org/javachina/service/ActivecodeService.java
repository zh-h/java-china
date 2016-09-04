package org.javachina.service;

import org.javachina.model.Activecode;
import org.javachina.model.User;

public interface ActivecodeService {
	
	Activecode getActivecode(String code);
	
	String save(User user, String type);
	
	boolean useCode(String code);

	boolean resend(Integer uid);
	
}
