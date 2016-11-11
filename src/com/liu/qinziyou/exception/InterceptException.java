package com.liu.qinziyou.exception;

public class InterceptException extends RuntimeException{
	public InterceptException(){
		super();
	} 
	public InterceptException(String arg1){
		super(arg1);
	}
	public InterceptException (Throwable arg1) {
		super(arg1);
	}
}
