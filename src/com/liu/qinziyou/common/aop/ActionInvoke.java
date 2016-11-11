package com.liu.qinziyou.common.aop;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.web.action.BaseAction;

public class ActionInvoke {
	private static final Logger logger = Logger.getLogger(ActionInvoke.class);
	private Set<String> inteceptorNameSet = new HashSet<String>();
	private List<AbstractInterceptor> interceptorList = new LinkedList<AbstractInterceptor>();
	
	public void addInterceptor(AbstractInterceptor interceptor){
			int listSize = interceptorList.size();
			if(listSize>0){
				interceptorList.get(listSize-1).setNextInterceptor(interceptor);
			}
			interceptorList.add(interceptor);
	}
	
	BaseAction baseAction;
	/**
	 * 被调用的方法
	 */
	Method method;
	/**
	 *被调用方法的参数列表
	 */
	Object[] args;
	/**
	 *调用之后的结果
	 */

	Object result;
	/**
	 *抛出的异常
	 */

	Throwable exception;

	public ActionInvoke(BaseAction baseAction,Method method, Object[] args) {
		this.baseAction =baseAction;
		this.args = args;
		this.method = method;
	}
	
	public Object excute() throws Exception{
		Object obj = null;
		//将类名上的拦截器加入到拦截器链中
		if(baseAction.getClass().isAnnotationPresent(InterceptorNames.class)){
			InterceptorNames interceptorNames = baseAction.getClass().getAnnotation(InterceptorNames.class);  
			joinInteceptor(interceptorNames);
		}
		if(method.isAnnotationPresent(InterceptorNames.class)){
			InterceptorNames interceptorNames = method.getAnnotation(InterceptorNames.class);  
			joinInteceptor(interceptorNames);
		}
		if(interceptorList.size()>0){
			obj = interceptorList.get(0).intercept(this);
		}else{
			obj = this.invoke();
		}
		return obj;
		
	}
	
	public Object invoke() throws Exception{
		return this.method.invoke(this.baseAction, this.args);
	}
	

	
	
	private void joinInteceptor(InterceptorNames interceptorNames) throws Exception {
		InterceptorName[] interceptorNameArray = interceptorNames.value();
		for(InterceptorName interceptorName:interceptorNameArray){
			String className = InterceptorMapper.getInstance().getInterceptorMap().get(interceptorName.value());
			if(className==null){
				logger.error("没有对应的拦截器："+interceptorName.value());
				throw new Exception("没有对应的拦截器："+interceptorName.value());
			}
			if(!inteceptorNameSet.contains(className)){
				this.addInterceptor((AbstractInterceptor)Class.forName(className).newInstance());
			}
			
		}
	}

	public BaseAction getBaseAction() {
		return baseAction;
	}

	public void setBaseAction(BaseAction baseAction) {
		this.baseAction = baseAction;
	}
	
	
	
}
