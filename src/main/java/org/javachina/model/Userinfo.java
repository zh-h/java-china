package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Userinfo对象
 */
@Table(name = "t_userinfo", cached = true)
public class Userinfo extends Model {

	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	public Integer uid;
	
	public String nick_name;
	
	public String jobs;
	
	public String web_site;
	
	public String location;
	
	public String github;
	
	public String weibo;
	
	public String signature;
	
	public String instructions;
	
}