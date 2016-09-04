package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Activecode对象
 */
@Table(name = "t_activecode", cached = false)
public class Activecode extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	
	public Integer uid;
	
	public String code;
	
	public String type;
	
	public Integer is_use;
	
	//过期时间
	public Integer expires_time;
	
	//创建时间
	public Integer create_time;
	
}