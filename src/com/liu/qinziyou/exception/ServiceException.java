package com.liu.qinziyou.exception;

import org.springframework.dao.DataAccessException;

public class ServiceException extends DataAccessException{

	public ServiceException(String msg){
		super(msg);
	} 
}
