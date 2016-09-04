package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Notice对象
 */
@Table(name = "t_notice", cached = false)
public class Notice extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer id;
	
	public Integer to_uid;
	
	public Integer event_id;
	
	public String type;
	
	public Boolean is_read;
	
	public Integer create_time;
	

}