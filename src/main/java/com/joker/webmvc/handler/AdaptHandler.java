package com.joker.webmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.webmvc.utils.Invocation;

/**
 * 参数处理handler接口
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public interface AdaptHandler {
	/**
	 * 获取参数
	 * @param request
	 * @param response
	 * @param invocation
	 * @return
	 * @author joker
	 *{@link https://github.com/Jokerblazes/webmvc.git}
	 */
	public Object[] getParams(HttpServletRequest request,HttpServletResponse response,Invocation invocation);
}
