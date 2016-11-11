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
import com.liu.qinziyou.entity.systemmanager.FrameworkSubsystems;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IFrameworkSubsystemsService;
import com.liu.qinziyou.web.action.BaseAction;

public class FrameworkSubsystemsAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(FrameworkSubsystemsAction.class);
	private FrameworkSubsystems fSubs;
	private String searchValue;
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");
			frameworkSubsystemsService.add(fSubs);
			msg.setEntity(fSubs.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");
			frameworkSubsystemsService.update(fSubs);			
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
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");
			frameworkSubsystemsService.del(fSubs);			
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
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");
			fSubs=(FrameworkSubsystems)frameworkSubsystemsService.getBeanById(FrameworkSubsystems.class,fSubs.getId());
			msg.setEntity(fSubs);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//读到模块列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean list(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");
			PageHelper ph=new PageHelper();
			ph=frameworkSubsystemsService.selectSubsystemsList(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	//获取子系统
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getSubsystemsList(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");			
			List<FrameworkSubsystems> ls=frameworkSubsystemsService.selectSubsystems(fSubs.getSystemId());
			msg.setOutRows(outRows);//只用输出rows
			msg.setRows(ls);//
			msg.setMsg("成功获取子系统 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchSubsystems(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");
			PageHelper ph=new PageHelper();			
			ph=frameworkSubsystemsService.searchSubsystems(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	public FrameworkSubsystems getFSubs() {
		return fSubs;
	}
	public void setFSubs(FrameworkSubsystems subs) {
		fSubs = subs;
	}
	

}
