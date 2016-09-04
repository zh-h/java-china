package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Node对象
 */
@Table(name = "t_node", cached = true)
public class Node extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer nid;
	
	//父节点id
	public Integer pid;
	
	//节点名称
	public String title;
	
	//节点描述
	public String description;
	
	//节点英文简写
	public String slug;
	
	// 节点图片
	public String pic;
	
	//帖子数
	public Integer topics;
	
	//创建时间
	public Integer create_time;
	
	//最后更新时间
	public Integer update_time;
	
	//是否删除
	public Integer is_del;
	
}