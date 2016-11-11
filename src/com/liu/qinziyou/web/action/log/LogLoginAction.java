package com.liu.qinziyou.web.action.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.log.ILogService;
import com.liu.qinziyou.web.action.BaseAction;

public class LogLoginAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(LogLoginAction.class);
	private String startTime;
	private String endTime;
	private String searchValue;
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchLogLogin(){
		MsgBean msg = new MsgBean();
		try{
			ILogService logService = (ILogService)BeanLoader.getWebBean("logService");
			PageHelper ph=new PageHelper();
			ph=logService.searchLogLogin(startTime, endTime, searchValue, start,limit);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("读登录日志失败==="+e.getStackTrace());
		}		
		return msg;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	
}
