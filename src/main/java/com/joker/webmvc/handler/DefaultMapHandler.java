package com.joker.webmvc.handler;


import javax.servlet.http.HttpServletRequest;

import com.joker.webmvc.utils.ControllerSet;
import com.joker.webmvc.utils.Invocation;

/**
 * 默认url到Invocation处理类
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class DefaultMapHandler implements MapHandler {
	public Invocation getMethodByRequest(ControllerSet controllerSet,HttpServletRequest req) {
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf("/") + 1);
		uri = uri.substring(uri.indexOf("/"));
		Invocation invocation = controllerSet.getInvocation(uri);
		return invocation;
	}
}
