package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Love对象
 */
@Table(name = "t_love", cached = false)
public class Love extends Model {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	public Integer tid;
	public Integer uid;
		
}