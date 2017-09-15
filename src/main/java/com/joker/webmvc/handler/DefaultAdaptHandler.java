package com.joker.webmvc.handler;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.joker.webmvc.utils.EntitySet;
import com.joker.webmvc.utils.Invocation;
import com.joker.webmvc.utils.ParamNode;
import com.joker.webmvc.utils.Utils;

/**
 * 默认参数处理handler
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class DefaultAdaptHandler implements AdaptHandler {
	
	public Object[] getParams(HttpServletRequest request,HttpServletResponse response,Invocation invocation) {
		//1:获取传入参数和名字
		Map<String,Object> paramsMap = getParams(invocation);
		
		if (paramsMap.size() == 0) 
			return null;
		
		//获取前台参数组装到 EntitySet
		EntitySet set = new EntitySet();
		set.buildEntitySet(request.getParameterMap());
		
		Map<String,ParamNode> nodeMap = set.getNodesMap();
		Map<String,String> javaMap = set.getJavaMap();
		Object[] results = new Object[paramsMap.size()];
		int i = 0;
		
		//前台参数组装成函数入参
		for (String key : paramsMap.keySet()) {
			Parameter parameter =  (Parameter) (paramsMap.get(key));
			Object result = null;
			Class clazz =  parameter.getType();
			//如果是基本类型直接赋值
			if (Utils.isJavaType(clazz)) {
				result = javaMap.get(key);
			} else if (clazz == HttpServletRequest.class) {
				result = request;
			} else if (clazz == HttpServletResponse.class) {
				result = response;
			} else {
				try {
					result = clazz.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
				setValue(result, nodeMap.get(key));
			}
			results[i] = result;
			i++;
		}
		return results;
	}
	
	//bena递归对象初始化
	private void setValue(Object parentObject,ParamNode node) {
		int size = node.size();
		if (size == 0) {
			return;
		}
		try {
			if(parentObject == null)
				parentObject = parentObject.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		for (ParamNode tempNode : node.getNodes()) {
			Object sonObject = handleField(parentObject, tempNode);
			setValue(sonObject, tempNode);
		}
		
	}
	
	//bean设值
	private Object handleField(Object parentObject,ParamNode node) {
		try {
			Field field = parentObject.getClass().getDeclaredField(node.getName());
			if (!Utils.isJavaType(field.getType())) {
				Object finalObject = field.getType().newInstance();
				field.setAccessible(true);
				field.set(parentObject, finalObject);
				return finalObject;
			} else {
				field.setAccessible(true);
				Object finalObject = node.getValue();
				field.set(parentObject, Utils.handlerStringToObject(finalObject, field.getType()));
				return parentObject;
			}

		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//获取函数入参
	private Map<String,Object> getParams(Invocation invocation) {
		Map<String,Object> map = new LinkedHashMap<String, Object>();
		Method method = invocation.getMethod();
		Class clazz = invocation.getController().getClass();
		String[] pns = Utils.getParameterNamesByAsm5(clazz, method);  
		Object[] objects = invocation.getMethod().getParameters();
		for (int i = 0 ; i < pns.length ; i++) {
			map.put(pns[i], objects[i]);
		}
		return map;
	}
	
//	private boolean isJavaType(Class clazz) {
//		if (clazz.equals(Boolean.class) || clazz.equals(Number.class)  || clazz.equals(String.class) || clazz == int.class || clazz == float.class)
//			return true;
//		return false;
//	}
	
//	private Object handlerStringToString(Object object,Class clazz) {
//		Object result = null;
//		if (clazz.equals(String.class)) {
//			result = object;
//		} else if (clazz == int.class) {
//			result = Integer.parseInt((String) object);
//		} else if (clazz.equals(Float.class)) {
//			result = Float.parseFloat((String) object);
//		} else if (clazz.equals(Double.class)) {
//			result = Double.parseDouble((String) object);
//		} else if (clazz.equals(Boolean.class)) {
//			result = Boolean.parseBoolean((String) object);
//		}
//		return result;
//	}
	
	
	
	
}
