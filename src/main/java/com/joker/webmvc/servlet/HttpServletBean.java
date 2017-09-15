package com.joker.webmvc.servlet;

import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化Servlet
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public abstract class HttpServletBean extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	//允许访问的url
	protected Set<String> requiredUrls;
	
	//未实现设定允许url功能
	@Override
	public void init() throws ServletException {
		initFrameWork();
	}
	
	
	protected abstract void initFrameWork();
}
