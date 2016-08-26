package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Link对象
 */
@Table(name = "t_link", cached = false)
public class Link extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	
	public String title;
	
	public String url;
	
	public Integer create_time;
	
}