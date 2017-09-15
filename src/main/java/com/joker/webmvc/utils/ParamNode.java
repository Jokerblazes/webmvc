package com.joker.webmvc.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class ParamNode {
	
	private String name;
	private List<ParamNode> nodes;
	private String value;
	
	public ParamNode(String name) {
		this.name = name;
		nodes = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void addNode(ParamNode node) {
		this.nodes.add(node);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public ParamNode getParamNode(String key) {
		for (ParamNode node : nodes) 
			if (node.getName().equals(key))
				return node;
		return null;
	}
	
	public int size() {
		return nodes.size();
	}

	public List<ParamNode> getNodes() {
		return nodes;
	}

	
	

	
	
}
