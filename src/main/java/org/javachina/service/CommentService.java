package org.javachina.service;

import java.util.Map;

import com.blade.jdbc.Pager;
import org.javachina.model.Comment;

public interface CommentService {
	
	Comment getComment(Integer cid);
		
	Comment getTopicLastComment(Integer tid);
	
	Pager<Map<String, Object>> getPageListMap(Integer tid, Integer uid, String orderby, int page, int count);
	
	Integer save( Integer uid, Integer toUid, Integer tid, String content, String ua);
	
	boolean delete(Integer cid);
	
	Long getComments(Integer uid);
		
}
