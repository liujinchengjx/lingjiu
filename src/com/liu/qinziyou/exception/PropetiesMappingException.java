package com.liu.qinziyou.exception;

public class PropetiesMappingException  extends RuntimeException{
	public PropetiesMappingException(){
		super();
	} 
	
	public PropetiesMappingException(String msg){
		super(msg);
	}
	
	public PropetiesMappingException(Throwable arg1){
		super(arg1);
	}
}
