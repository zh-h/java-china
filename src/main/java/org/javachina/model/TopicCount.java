package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

@Table(name = "t_topiccount", cached = false)
public class TopicCount extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer tid;
	public Integer views;
	public Integer loves;
	public Integer favorites;
	public Integer sinks;
	public Integer comments;
	public Integer create_time;
	
}