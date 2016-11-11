package com.liu.qinziyou.common.aop;

import java.util.HashMap;
import java.util.Map;
/**
 * 拦截器名和类名对应
 * 以后可以写在配置文件中
 * @author liujc
 *2013-3
 */
public class InterceptorMapper {
	private static InterceptorMapper instance = new InterceptorMapper();
	public static InterceptorMapper getInstance(){
		return instance;
	}
	public InterceptorMapper(){
		interceptorMap = new HashMap<String,String>();
		interceptorMap.put("loginInterceptor", "com.liu.qinziyou.common.aop.LoginInterceptor");
		interceptorMap.put("rightInterceptor", "com.liu.qinziyou.common.aop.RightInterceptor");
		interceptorMap.put("actionPropertiesInterceptor", "com.liu.qinziyou.common.aop.ActionPropertiesInterceptor");
		interceptorMap.put("checkPriceCacheLoadedInterceptor", "com.liu.qinziyou.common.aop.CheckPriceCacheLoadedInterceptor");
		
		
	}
	private  Map<String,String> interceptorMap;
	public Map<String, String> getInterceptorMap() {
		return interceptorMap;
	}

	

}
