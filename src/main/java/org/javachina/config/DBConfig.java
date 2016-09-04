package org.javachina.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.javachina.Application;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.blade.config.ApplicationConfig;
import com.blade.config.BaseConfig;
import com.blade.ioc.annotation.Component;
import com.blade.jdbc.Base;

@Component
public class DBConfig implements BaseConfig {
	
	@Override
	public void config(ApplicationConfig applicationConfig) {
		// 配置数据库
		try {
			InputStream in = Application.class.getClassLoader().getResourceAsStream("druid.properties");
			Properties props = new Properties();
			props.load(in);
			Base.open(DruidDataSourceFactory.createDataSource(props));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
