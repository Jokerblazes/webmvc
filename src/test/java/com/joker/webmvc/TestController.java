package com.joker.webmvc;

import com.joker.webmvc.annotation.Controller;
import com.joker.webmvc.annotation.RequestMapping;
import com.joker.webmvc.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/test1")
	@ResponseBody
	public String test1(String a) {
		System.out.println("111");
		return "a";
	}
	
	
	
}
