package com.liu.qinziyou.common.aop;



public abstract class AbstractInterceptor {

	private AbstractInterceptor nextInterceptor;
	
	public AbstractInterceptor getNextInterceptor() {
		return nextInterceptor;
	}

	public void setNextInterceptor(AbstractInterceptor nextInterceptor) {
		this.nextInterceptor = nextInterceptor;
	}

	protected abstract Object after(ActionInvoke invInfo) throws Exception;

	protected abstract Object before(ActionInvoke invInfo) throws Exception;
	
	public Object intercept(ActionInvoke invInfo) throws Exception {
		Object obj = null;
		obj =  before(invInfo);//调用前置拦截方法
		if(obj!=null) return obj;
		if(this.nextInterceptor!=null){
			obj =  this.nextInterceptor.intercept(invInfo);
		}else{
			obj =   invInfo.invoke();
		}
		Object afterObject = after( invInfo);//调用后置拦截方法
		if(afterObject!=null){
			obj = afterObject;
		}
		return obj;
	}

	

}
