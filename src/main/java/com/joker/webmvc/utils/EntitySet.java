package com.joker.webmvc.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求容器类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class EntitySet {
	private Map<String,String> javaMap;//java基础类型对象
	private Map<String,ParamNode> nodesMap;//实体类对象
	public EntitySet() {
		javaMap = new HashMap<>();
		nodesMap = new HashMap<>();
	}
	
//	public void  buildEntitySet(Map<String,String[]> map) {
//		for (String key : map.keySet()) {
//			if (key.indexOf(".") == -1) {
//				javaMap.put(key, map.get(key)[0]);
//			} else {
//				String[] names = key.split(".");
//				ParamNode parentNode = new ParamNode(names[0]);
//				nodesMap.put(names[0], parentNode);
//				buidNode(names,1,map.get(key)[0],parentNode);
//				
//			}
//		}
//	}
	
	//构建容器
	public void  buildEntitySet(Map<String,String[]> map) {
		for (String key : map.keySet()) {
			if (key.indexOf(".") == -1) {
				javaMap.put(key, map.get(key)[0]);
			} else {
				String[] names = key.split("\\.");
				ParamNode parentNode = nodesMap.get(names[0]);
				if (parentNode == null) {
					parentNode = new ParamNode(names[0]);
				}
				nodesMap.put(names[0], parentNode);
				buidNode(names,1,map.get(key)[0],parentNode);
			}
		}
	}
	
	//构建子节点
	private void buidNode(String[] key,int i,String value,ParamNode parentNode) {
		ParamNode node = parentNode.getParamNode(key[i]);
		if (node == null) {
			node = new ParamNode(key[i]);
			parentNode.addNode(node);
		}
		if (key.length == (i+1)) {
			node.setValue(value);
			return;
		}
		i++;
		buidNode(key, i, value, node);
	}
	
	public Map<String, String> getJavaMap() {
		return javaMap;
	}

	public Map<String, ParamNode> getNodesMap() {
		return nodesMap;
	}
	
	

	
}
