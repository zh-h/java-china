package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Favorite对象
 */
@Table(name = "t_favorite", cached = false)
public class Favorite extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	
	//topic:帖子 node:节点
	public String type;
	
	public Integer uid;
	
	public Integer event_id;
	
	public Integer create_time;
	
}