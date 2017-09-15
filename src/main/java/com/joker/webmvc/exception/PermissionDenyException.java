package com.joker.webmvc.exception;

/**
 * 方法不允许请求异常
 * @author joker
 *{@link https://github.com/Jokerblazes/webmvc.git}
 */
public class PermissionDenyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PermissionDenyException(String msg) {
		super("此方法不允许" + msg +"请求");
	}
	
}
