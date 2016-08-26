package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Comment对象
 */
@Table(name = "t_comment", cached = false)
public class Comment extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer cid;
	
	//评论人uid
	public Integer uid;
	
	//被评论人uid
	public Integer to_uid;
	
	//帖子id
	public Integer tid;
	
	//评论内容
	public String content;
	
	//设备
	public String device;
	
	//评论时间
	public Integer create_time;
	
	
}