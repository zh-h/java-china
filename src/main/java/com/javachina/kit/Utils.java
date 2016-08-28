package com.javachina.kit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.blade.kit.DateKit;
import com.blade.kit.HashidKit;
import com.blade.kit.StringKit;
import com.blade.web.http.Request;
import com.javachina.ImageTypes;


/**
 * 工具类
 */
public class Utils {

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(Request request) {
		if (null == request) {
			return "0.0.0.0";
		}
		String ip = request.header("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.header("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.header("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.address();
		}
		return ip;
	}
	
	/**
	 * 获取@的用户列表
	 * @param str
	 * @return
	 */
	public static Set<String> getAtUsers(String str){
		Set<String> users = new HashSet<String>();
		if(StringKit.isNotBlank(str)){
			Pattern pattern= Pattern.compile("\\@([a-zA-Z_0-9-]+)\\s");
			Matcher matcher = pattern.matcher(str);
			while(matcher.find()){
				users.add(matcher.group(1));
			}
		}
		
		return users;
	}
	
	public static String getAvatar(String avatar, ImageTypes imageTypes){
		//return QiniuKit.getUrl(avatar + '-' + imageTypes.toString());
		return "";
	}
	
	public static boolean isEmail(String str){
		if(StringKit.isBlank(str)){
			return false;
		}
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 判断用户是否可以注册
	 * @param user_name
	 * @return
	 */
	public static boolean isSignup(String user_name){
		if(StringKit.isNotBlank(user_name)){
			user_name = user_name.toLowerCase();
			if(user_name.indexOf("admin") != -1 ||
					user_name.indexOf("test") != -1 ||
					user_name.indexOf("support") != -1){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean isLegalName(String str){
		if(StringKit.isNotBlank(str)){
			Pattern pattern = Pattern.compile("^[a-zA-Z_0-9]{4,16}$");
			if(!pattern.matcher(str).find()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("resource")
	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {    
        FileChannel inputChannel = null;    
        FileChannel outputChannel = null;    
	    try {
	        inputChannel = new FileInputStream(source).getChannel();
	        outputChannel = new FileOutputStream(dest).getChannel();
	        outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
	    } finally {
	        inputChannel.close();
	        outputChannel.close();
	    }
	}
	
	public static Integer getTodayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}
	
	public static Integer getYesterdayTime() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, -24);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(today.getTimeInMillis()).substring(0, 10));
	}

	public static Integer getTomorrowTime() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.set(Calendar.HOUR_OF_DAY, 24);
		tomorrow.set(Calendar.MINUTE, 0);
		tomorrow.set(Calendar.SECOND, 0);
		return Integer.valueOf(String.valueOf(tomorrow.getTimeInMillis()).substring(0, 10));
	}
	
	public static void run(Runnable t){
		Executors.newSingleThreadExecutor().execute(t);
	}
	
	/**
	 * 计算帖子权重
	 * 
	 * 根据点赞数、收藏数、评论数、下沉数、创建时间计算
	 * 
	 * @param loves			点赞数：权重占比1
	 * @param favorites 	点赞数：权重占比2
	 * @param comment		点赞数：权重占比2
	 * @param sinks			点赞数：权重占比-1
	 * @param create_time	创建时间，越早权重越低
	 * @return
	 */
	public static double getWeight(Long loves, Long favorites, Long comment, Long sinks, Long create_time) {
		
		long score = Math.max(loves - 1, 1) + favorites * 2 + comment * 2 - sinks;
		
		// 投票方向
		int sign = (score == 0) ? 0 : (score > 0 ? 1 : -1);
		
		// 帖子争议度
		double order = Math.log10(Math.max(Math.abs(score), 1));
		
		// 1459440000是项目创建时间
		double seconds = create_time - 1459440000;
		return Double.parseDouble(String.format("%.2f", order + sign * seconds / 45000));
	}

	public static String toJSONString(Object object){
		return JSON.toJSONString(object, true);
	}

	public static String createToken(Integer uid) {
		return new HashidKit(uid+"", 32).encode(DateKit.getCurrentUnixTime() + uid);
	}
}
