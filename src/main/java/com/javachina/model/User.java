package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * User对象
 */
@Table(name = "t_user", cached = true)
public class User extends Model {

	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer uid;
	
	public String login_name;
	
	public String pass_word;
	
	//头像
	public String avatar;
	
	//电子邮箱
	public String email;
	
	//创建时间
	public Integer create_time;
	
	//最后一次操作时间
	public Integer update_time;
	
	//5:普通用户 2:管理员 1:系统管理员
	public Integer role_id;
	
	//0:待激活 1:正常 2：删除
	public Integer status;
	

}