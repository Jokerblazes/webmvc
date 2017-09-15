package com.joker.webmvc.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.webmvc.annotation.RequestMapping;
import com.joker.webmvc.handler.AdaptHandler;
import com.joker.webmvc.handler.DefaultAdaptHandler;
import com.joker.webmvc.handler.DefaultMapHandler;
import com.joker.webmvc.handler.MapHandler;
import com.joker.webmvc.utils.ClasspathPackageScanner;
import com.joker.webmvc.utils.ControllerSet;
import com.joker.webmvc.utils.Invocation;
import com.joker.webmvc.utils.RequestMethod;

/**
 * Servlet的抽象容器类、负责管理管理controller
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public abstract class ServletFramework extends HttpServletBean {
	
	private Logger logger = LoggerFactory.getLogger(ServletFramework.class);
	private static final long serialVersionUID = 1L;
	//负责对url的过滤逻辑的handler
	protected MapHandler maphandler;
	
	//对参数处理的handler
	protected AdaptHandler adaptHandler;
	
	//存储controller
	protected ControllerSet controllerSet;
	
	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doDelete!");
		doDispatch(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doGet!");
		doDispatch(req,resp);
	}
	
	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doGet!");
		doDispatch(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doPost!");
		doDispatch(req,resp);
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doOptions!");
		doDispatch(req,resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doPut!");
		doDispatch(req,resp);
	}
	
	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("执行 {}","doTrace!");
		doDispatch(req,resp);
	}
	
	protected abstract void doDispatch(HttpServletRequest req, HttpServletResponse resp);
	
	//初始化Servlet容器
	@Override
	protected void initFrameWork() {
		logger.info("初始化！");
		//第定义handler暂未实现，当前版本只提供默认的handler
		maphandler = new DefaultMapHandler();
		adaptHandler = new DefaultAdaptHandler();
		controllerSet = new ControllerSet();
		//扫描controller包，读取类上的Rest注解和方法上的注解并注册
		
		String packageName = this.getServletConfig().getInitParameter("controllerPackage");
		logger.info("开始扫描 {}",packageName);
		ClasspathPackageScanner scan = new ClasspathPackageScanner(packageName);
		List<String> controllerNames = null;
		try {
			controllerNames = scan.getFullyQualifiedClassNameList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info(packageName + "包中有如下类 {}",controllerNames);
		//注册Invocation
		for (String beanName : controllerNames) {
			initInvocation(beanName);
		}
	}
	
	//初始化invocation
	private void initInvocation(String beanName) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(beanName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Object controller = null;
		try {
			controller = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		RequestMapping reqMap = clazz.getAnnotation(RequestMapping.class);
		if (reqMap == null) 
			throw new RuntimeException("未配置Rest！");
		String value = reqMap.value();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			RequestMapping methodReq = method.getAnnotation(RequestMapping.class);
			if (methodReq == null) 
				throw new RuntimeException("未配置Rest！");
			String methodValue = methodReq.value();
			RequestMethod[] methodTypes = methodReq.method();
			Invocation invocation = new Invocation(method, controller, method.getParameters(), methodTypes);
			controllerSet.setInvocation(value + methodValue, invocation);
		}
	}
	
}
