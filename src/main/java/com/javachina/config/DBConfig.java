package com.javachina.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.blade.config.ApplicationConfig;
import com.blade.config.BaseConfig;
import com.blade.ioc.annotation.Component;
import com.blade.jdbc.Base;
import com.javachina.Application;

@Component
public class DBConfig implements BaseConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DBConfig.class);
	
	@Override
	public void config(ApplicationConfig applicationConfig) {
		// 配置数据库
		try {
			InputStream in = Application.class.getClassLoader().getResourceAsStream("druid.properties");
			Properties props = new Properties();
			props.load(in);
			Base.open(DruidDataSourceFactory.createDataSource(props));
			//Cache cache = new DefaultCache();
			//db.enableCache(cache);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
