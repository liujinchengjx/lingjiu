package com.liu.qinziyou.web.action.systemmanager;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.entity.systemmanager.FrameworkButtons;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IFrameworkButtonsService;
import com.liu.qinziyou.web.action.BaseAction;

public class FrameworkButtonsAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(FrameworkButtonsAction.class);	
	private FrameworkButtons fun;
	private String modules;
	private Integer userId;
	private Integer toolBarSeq;
	private String searchValue;
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			fun.setToolTip(fun.getText());
			fun.setSystemId(1);//默认1
			frameworkButtonsService.add(fun);
			msg.setEntity(fun.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{						
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			frameworkButtonsService.update(fun);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{						
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			frameworkButtonsService.del(fun);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{						
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			fun=(FrameworkButtons)frameworkButtonsService.getBeanById(FrameworkButtons.class,fun.getId());
			msg.setEntity(fun);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//获取按钮列表 根据SystemId
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getButtonsListBySystemId(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			PageHelper ph=new PageHelper();			
			ph=frameworkButtonsService.selectButtons(fun.getSystemId(),fun.getModules(),start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);	
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	
	//加载模块功能按钮
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean loadButtonsList(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			Integer userId = super.getLoginUserId();
			if(userId!=null){
				List bars=frameworkButtonsService.loadButtonsList(userId,modules, toolBarSeq);
				msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS).setRows(bars);	
			}else{
			    msg.setMsg("加载功能按钮失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
			}
		}catch(ServiceException e){
			log.error("加载功能按钮失败==="+e.getMessage());
			msg.setMsg("加载功能按钮失败==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchButtons(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			PageHelper ph=new PageHelper();
			ph=frameworkButtonsService.searchButtons(searchValue,start,limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);	
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}	
	public FrameworkButtons getFun() {
		return fun;
	}
	public void setFun(FrameworkButtons fun) {
		this.fun = fun;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
	public Integer getToolBarSeq() {
		return toolBarSeq;
	}
	public void setToolBarSeq(Integer toolBarSeq) {
		this.toolBarSeq = toolBarSeq;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
