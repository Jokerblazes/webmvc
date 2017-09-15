package com.joker.webmvc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.joker.webmvc.utils.EntitySet;
import com.joker.webmvc.utils.RequestMethod;

/**
 * Unit test for simple App.
 */
public class AppTest {
//	@Test
//	public void testControlles() throws IllegalArgumentException, InvocationTargetException {
//		try {
//			Object object = TestController.class.newInstance();
//			Method[] methods = TestController.class.getDeclaredMethods();  
//			Method m = methods[0];
//			Parameter[] parameters = m.getParameters();  
//			System.out.println(parameters.length);
//	        for (final Parameter parameter : parameters) {  
//	        	System.out.print(parameter.getName() + ' ');  
//	        }  
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testEmue() {
//		RequestMethod m = RequestMethod.GET;
//		System.out.println(m.name().equals("GET"));
//	}
//	
//	@Test 
//	public void testS() {
//		try {
//			Field f = EntitySet.class.getDeclaredField("a");
//			f.setAccessible(true);
//			System.out.println(f);
//			try {
//				System.out.println(f.getType());
//				f.getType().newInstance();
//			} catch (InstantiationException | IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (NoSuchFieldException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testIsEqual() {
//	}
	
//	@Test
//	public void testEntity() {
//		Map<String,String> map = new HashMap<>();
//		map.put("user.page.start", "1");
//		map.put("user.page.end", "1");
//		map.put("user.page.number", "1");
//		EntitySet set = new EntitySet();
//		set.buildEntitySet(map);
//	}
}
