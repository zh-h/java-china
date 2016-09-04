package org.javachina.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.javachina.dto.NodeDto;
import org.javachina.model.Node;
import org.javachina.service.NodeService;

import com.blade.ioc.annotation.Service;
import com.blade.jdbc.Pager;

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
		if(page < 1){
			page = 1;
		}
		if(limit < 1 || limit > 10){
			limit = 10;
		}
		return Node.db.orderBy(orderBy).neq("pid", 0).page(page, limit, Node.class);
	}

	@Override
	public List<NodeDto> getNodes() {
		List<Node> parents = Node.db.eq("is_del", 0).eq("pid", 0).orderBy("topics desc").list(Node.class);
		List<NodeDto> list = new ArrayList<NodeDto>();
		for(int i=0,len=parents.size(); i<len; i++){
			Node node = parents.get(i);
			NodeDto nodeDto = new NodeDto(node);
			List<Node> items = Node.db.eq("is_del", 0).eq("pid", node.nid).orderBy("topics desc").list(Node.class);
			if(null != items){
				nodeDto.items = items;
			}
			list.add(nodeDto);
		}
		return list;
	}

}
