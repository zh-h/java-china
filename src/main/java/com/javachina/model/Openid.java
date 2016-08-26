package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Activecode对象
 */
@Table(name = "t_openid", cached = false)
public class Openid extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	
	public String type;
	
	public Integer open_id;
	
	public Integer uid;
	
	public Integer create_time;
	
}