package org.javachina.config;

import com.blade.config.ApplicationConfig;
import com.blade.config.BaseConfig;
import com.blade.ioc.annotation.Component;
import com.blade.mvc.view.ViewSettings;
import com.blade.mvc.view.template.JetbrickTemplateEngine;

@Component
public class MvcConfig implements BaseConfig {
	
	@Override
	public void config(ApplicationConfig applicationConfig) {
		JetbrickTemplateEngine templateEngine = new JetbrickTemplateEngine();
		ViewSettings.$().templateEngine(templateEngine);
		
	}
	
}
