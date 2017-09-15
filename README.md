# webmvc

## 主要功能

`实现对请求的统一管理，控制层容器对控制层集中化的管理。`

## 特色功能

* 通过注解实现restful

## maven

```xml
 <repositories>
	<repository>
		<id>webmvc</id>
		<url>https://raw.github.com/Jokerblazes/maven-project/master/com</url>
	</repository>
</repositories>

<dependency>
      <artifactId>webmvc</artifactId>
      <groupId>com.joker</groupId>
      <version>0.0.1-SNAPSHOT</version>
</dependency>
```



## demo

```java
@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping("/index")
	public String index(String a,HttpServletRequest request) {
		return "index";
	}
	
	@RequestMapping("/testUser")
	@ResponseBody
	public User testUser(User user,HttpServletRequest request) {
		return user;
	}
}
```

