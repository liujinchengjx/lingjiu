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
import com.liu.qinziyou.entity.systemmanager.FrameworkModules;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IFrameworkModulesService;
import com.liu.qinziyou.web.action.BaseAction;

public class FrameworkModulesAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(FrameworkModulesAction.class);
	private FrameworkModules fm;
	private String searchValue;
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			frameworkModulesService.add(fm);
			msg.setEntity(fm.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			frameworkModulesService.update(fm);			
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
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			frameworkModulesService.del(fm);			
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
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			fm=(FrameworkModules)frameworkModulesService.getBeanById(FrameworkModules.class,fm.getId());
			msg.setEntity(fm);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//读到子模块列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean list(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			PageHelper ph=new PageHelper();
			ph=frameworkModulesService.selectModules(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);	
		}
		return msg;
	}
	//获取子系统 有分页
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getModules(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			PageHelper ph=new PageHelper();			
			ph=frameworkModulesService.selectModules(fm.getSystemId(),fm.getName(),start,limit);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功获取子系统 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);	
		}
		return msg;
	}
	//获取子系统 返回list
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getModulesList(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");					
			List lst=frameworkModulesService.selectModulesList(fm.getSystemId(),fm.getName());			
			msg.setOutRows(outRows);//只用输出rows
			msg.setRows(lst);
			msg.setMsg("成功获取子系统 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);	
		}
		return msg;
	}
	
	
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchModules(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			PageHelper ph=new PageHelper();
			ph=frameworkModulesService.searchModules(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	public FrameworkModules getFm() {
		return fm;
	}
	public void setFm(FrameworkModules fm) {
		this.fm = fm;
	}
}
