package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Userlog对象
 */
@Table(name = "t_userlog", cached = false)
public class Userlog extends Model {

	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	
	public Integer uid;
	
	public String action;
	
	public String content;
	
	public String ip_addr;
	
	public Integer create_time;
	
}