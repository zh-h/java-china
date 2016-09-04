package org.javachina.model;

import com.blade.jdbc.Model;
import com.blade.jdbc.annotation.Column;
import com.blade.jdbc.annotation.GeneratedValue;
import com.blade.jdbc.annotation.Id;
import com.blade.jdbc.annotation.Table;

/**
 * Topic对象
 */
@Table(name = "t_topic", cached = false)
public class Topic extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true)
	@GeneratedValue 
	public Integer tid;
	
	//发布人
	public String user_name;
	
	//所属节点
	public Integer nid;
	
	//最后回复人
	public String reply_user;
	
	//帖子标题
	public String title;
	
	//帖子内容
	public String content;
	
	//是否置顶
	public Integer is_top;
	
	//是否是精华贴
	public Integer is_essence;
	
	// 帖子权重
	public Double weight;
	
	//帖子创建时间
	public Integer create_time;
	
	//最后更新时间
	public Integer update_time;
	
	//1:正常 2:删除
	public Integer status;
		
}