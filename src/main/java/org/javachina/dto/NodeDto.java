package org.javachina.dto;

import java.util.List;

import org.javachina.model.Node;

public class NodeDto extends Node {
	
	private static final long serialVersionUID = 1L;
	public List<Node> items;
	
	public NodeDto(Node node) {
		nid = node.nid;
		slug = node.slug;
		pid = node.pid;
		topics = node.topics;
		title = node.title;
	}
	
}
