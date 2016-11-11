package com.liu.qinziyou.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.LoginInfoUtil;

@InterceptorNames({@InterceptorName("actionPropertiesInterceptor")})
public abstract class BaseAction implements Action {
	protected String id;
	protected Integer start = 0;
	protected Integer limit = 10;
	protected boolean outRows=false; //这个参数是 设定msg对象是否只输出rows集合到html端，默认是将整个msg对象输出到html
	private HttpServletRequest request;
	private HttpServletResponse response;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected String getReqIP(HttpServletRequest req) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}


	public boolean isOutRows() {
		return outRows;
	}

	public void setOutRows(boolean outRows) {
		this.outRows = outRows;
	}
	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public Integer getLoginUserId() {
		return LoginInfoUtil.getInstance().getUserId(this.getRequest());
	}
	public String getUserName() {
		return LoginInfoUtil.getInstance().getLoginName(this.getRequest());
	}
	
	public Integer getLoginUserType() {
		return LoginInfoUtil.getInstance().getUserType(this.getRequest());
	}
	public Integer getLoginUserCompanyId() {
		return LoginInfoUtil.getInstance().getUserCompanyId(this.getRequest());
	}
}
