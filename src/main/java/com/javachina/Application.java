package com.javachina;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.blade.Blade;
import com.blade.Bootstrap;
import com.blade.context.BladeWebContext;
import com.blade.embedd.EmbedJettyServer;
import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Base;
import com.blade.jdbc.DataBase;
import com.blade.jdbc.cache.Cache;
import com.blade.jdbc.cache.DefaultCache;
import com.bladejava.view.template.JetbrickTemplateEngine;
import com.javachina.ext.Funcs;
import com.javachina.service.SettingsService;

import jetbrick.template.resolver.GlobalResolver;

public class Application extends Bootstrap {
	
	@Inject
	private SettingsService settingsService;
	
	@Override
	public void init(Blade blade) {
		blade.loadAppConf("app.properties");
		blade.basePackage("com.javachina");
		// 模板引擎
		JetbrickTemplateEngine jetbrickTemplateEngine = new JetbrickTemplateEngine(BladeWebContext.servletContext());
		GlobalResolver resolver = jetbrickTemplateEngine.getJetEngine().getGlobalResolver();
		resolver.registerFunctions(Funcs.class);
		Constant.VIEW_CONTEXT = jetbrickTemplateEngine.getJetEngine().getGlobalContext();
		blade.viewEngin(jetbrickTemplateEngine);
		
		// 配置数据库
		try {
			InputStream in = Application.class.getClassLoader().getResourceAsStream("druid.properties");
			Properties props = new Properties();
			props.load(in);
			DataBase db = Base.open(DruidDataSourceFactory.createDataSource(props));
			//Cache cache = new DefaultCache();
			//db.enableCache(cache);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 初始化配置
//		AES.setKey(blade.config().get("app.aes_salt"));
	}
	
	@Override
	public void contextInitialized(Blade blade) {
		settingsService.refreshCount();
		Constant.SYS_INFO = settingsService.getSystemInfo();
		Constant.VIEW_CONTEXT.set("base", BladeWebContext.servletContext().getContextPath());
		Constant.VIEW_CONTEXT.set("version", Constant.APP_VERSION);
		Constant.VIEW_CONTEXT.set("cdn", Constant.CDN_URL);
		Constant.VIEW_CONTEXT.set(Map.class, "sys_info", Constant.SYS_INFO);
		Constant.VIEW_CONTEXT.set(String.class, "site_url", Constant.SITE_URL);
	}
	
	public static void main(String[] args) throws Exception {
		Blade.me().enableServer(false).app(Application.class).start(EmbedJettyServer.class);
	}
}
