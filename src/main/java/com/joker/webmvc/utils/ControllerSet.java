package com.joker.webmvc.utils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * controller容器
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class ControllerSet {
	private ConcurrentHashMap<String, Invocation> controllerMap = new ConcurrentHashMap<String, Invocation>();
	
	public synchronized Invocation getInvocation(String uri) {
		return controllerMap.get(uri);
	}
	
	public synchronized void setInvocation(String uri,Invocation invocation) {
		controllerMap.put(uri, invocation);
	}
}
