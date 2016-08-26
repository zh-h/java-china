package com.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Settings对象
 */
@Table(name = "t_settings", cached = false)
public class Settings extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	public String skey;
	public String svalue;
	
}