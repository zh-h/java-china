package com.javachina;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.blade.Blade;
import com.blade.embedd.EmbedJettyServer;
import com.blade.jdbc.Base;

public class Application {

	public static void main(String[] args) throws Exception {
		Blade blade = Blade.me();
		blade.loadAppConf("app.properties");

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
		Blade.me().enableServer(false).start(EmbedJettyServer.class);
	}
}
