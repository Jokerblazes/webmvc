package com.joker.webmvc.handler;


import javax.servlet.http.HttpServletRequest;

import com.joker.webmvc.utils.ControllerSet;
import com.joker.webmvc.utils.Invocation;

/**
 * url处理类接口
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public interface MapHandler {
	
	/**
	 * 获取容器中存取的Invocation
	 * @param controllerSet
	 * @param req
	 * @return
	 * @author joker
	 *{@link https://github.com/Jokerblazes/webmvc.git}
	 */
	public Invocation getMethodByRequest(ControllerSet controllerSet,HttpServletRequest req);
}
