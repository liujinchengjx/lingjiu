package com.liu.qinziyou.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	public HttpServletRequest getRequest() ;
	public HttpServletResponse getResponse();
}
