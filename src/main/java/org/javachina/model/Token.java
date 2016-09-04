package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Token对象
 */
@Table(name = "t_token", cached = true)
public class Token extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue
	public Integer id;

	public Integer uid;
	
	public String acess_token;

	public String scope;
	
	public Integer create_time;
	
	public Integer expired_time;
	
}