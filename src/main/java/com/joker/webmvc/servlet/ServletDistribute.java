package com.joker.webmvc.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.joker.webmvc.annotation.ResponseBody;
import com.joker.webmvc.exception.PermissionDenyException;
import com.joker.webmvc.utils.Invocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 转发类--负责请求的转发
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class ServletDistribute extends ServletFramework {
	
	private final static Logger logger = LoggerFactory.getLogger(ServletDistribute.class);
	private static final long serialVersionUID = 1L;

	//请求分发逻辑处理
	@Override
	protected void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
		//1:获取对应invocation
		Invocation invocation = maphandler.getMethodByRequest(controllerSet,req);
		logger.info("执行方法 {}",invocation);
		if (invocation == null) {
			return;
		}
		
		//2:判断该函数是否允许对应的method
		String methodName = req.getMethod();
		if (!invocation.exitMethod(methodName)) {
			logger.info("不存在{},此方法！");
			throw new PermissionDenyException(methodName);
		}
		
		//3:传入参数处理
		Object[] results = adaptHandler.getParams(req,resp,invocation);
		logger.info("参数列表 {}", Arrays.asList(results));
		Method method = invocation.getMethod();
		Object result = null;
		//4:反射调用
		try {
			result = method.invoke(invocation.getController(), results);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//5:是否需要restful
		ResponseBody rb = method.getAnnotation(ResponseBody.class);
		//6:返回前台
		if (rb == null) {
			logger.info("转发给前台 {}",result);
			forward(req,(String)result,resp);
		} else {
			logger.info("打印给前台 {}",result);
			print(resp, result);
		}
		
	}
	
	//转发
	private void forward(HttpServletRequest req,String path,HttpServletResponse resp) {
		try {
			req.getRequestDispatcher("/" + (String) path + ".jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//输出
	private void print(HttpServletResponse resp,Object result) {
		try {
			Object json = JSONObject.toJSON(result);
			PrintWriter out = resp.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
