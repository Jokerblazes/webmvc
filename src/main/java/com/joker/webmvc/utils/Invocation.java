package com.joker.webmvc.utils;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射容器类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class Invocation {
	
	private Method method;//反射方法类
	private Object controller;//控制实例
	private Object[] args;//参数
	private RequestMethod[] requestMethods;//允许执行的方法数组
	
	
	public Invocation(Method method, Object controller, Object[] args,RequestMethod[] requestMethods) {
		super();
		this.method = method;
		this.controller = controller;
		this.args = args;
		this.requestMethods = requestMethods;
	}

	public RequestMethod[] getRequestMethods() {
		return requestMethods;
	}

	public void setRequestMethods(RequestMethod[] requestMethods) {
		this.requestMethods = requestMethods;
	}

	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Object getController() {
		return controller;
	}
	public void setController(Object controller) {
		this.controller = controller;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	public boolean exitMethod(String method) {
		if (requestMethods.length == 0) 
			return true;
		for (RequestMethod m : requestMethods)
			if (m.name().equals(method)) 
				return true;
		return false;
	}

	@Override
	public String toString() {
		return "Invocation{" +
				"method=" + method +
				", controller=" + controller +
				", args=" + Arrays.toString(args) +
				", requestMethods=" + Arrays.toString(requestMethods) +
				'}';
	}
}
