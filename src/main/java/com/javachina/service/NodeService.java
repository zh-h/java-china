package com.javachina.service;

import com.blade.jdbc.Pager;
import com.javachina.model.Node;

public interface NodeService {
	
	Node getNode(Integer nid);
	
	Node getNode(String slug);

	Pager<Node> getNodes(String orderBy, int page, int limit);
	
}