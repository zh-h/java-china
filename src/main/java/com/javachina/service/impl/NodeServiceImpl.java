package com.javachina.service.impl;

import java.util.List;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;
import com.javachina.model.Node;
import com.javachina.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

	@Override
	public Node getNode(Integer nid) {
		return Node.db.eq("is_del", 0).eq("nid", nid).first(Node.class);
	}

	@Override
	public Node getNode(String slug) {
		return Node.db.eq("is_del", 0).eq("slug", slug).first(Node.class);
	}

	@Override
	public Pager<Node> getNodes(String orderBy, int page, int limit) {
		return Node.db.orderBy(orderBy).neq("pid", 0).page(page, limit, Node.class);
	}

	@Override
	public List<Node> getHotNodes(int limit) {
		return Node.db.eq("is_del", 0).orderBy("topics desc").page(1, limit, Node.class).getList();
	}

}
