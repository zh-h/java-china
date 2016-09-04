package org.javachina;

import java.util.HashMap;
import java.util.Map;

import com.blade.Blade;

public class Constant {

	public static boolean IS_DEV = true;

	public static final String UPLOAD_FOLDER = "assets/temp";

	public static final String USER_IN_COOKIE = "SH_SIGNIN_USER";
	public static final String JC_REFERRER_COOKIE = "JC_REFERRER_COOKIE";

	// 名人名言key
	public static String FAMOUS_KEY = "";
	
	// 今日名言
	public static Map<String, Object> today_famous = new HashMap<String, Object>();
	
	public static String SITE_URL = Blade.$().environment().getString("app.site_url");
	public static String CDN_URL = "";
	public static String APP_VERSION = "";
	
	public static String GITHUB_CLIENT_ID = "";
	public static String GITHUB_CLIENT_SECRET = "";
	public static String GITHUB_REDIRECT_URL = "";
	
	// sendcloud邮件配置
	public static String MAIL_API_USER;
	public static String MAIL_API_KEY;

	public static enum ImageTypes {
        small,
        normal
    }
}
