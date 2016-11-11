package com.liu.qinziyou.web.action.systemmanager;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.tree.MenuTree;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.systemmanager.FrameworkButtons;
import com.liu.qinziyou.entity.systemmanager.FrameworkModules;
import com.liu.qinziyou.entity.systemmanager.FrameworkSubsystems;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IFrameworkButtonsService;
import com.liu.qinziyou.services.systemmanager.IFrameworkModulesService;
import com.liu.qinziyou.services.systemmanager.IMenuService;
import com.liu.qinziyou.web.action.BaseAction;

public class MenuAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(MenuAction.class);
	private FrameworkSubsystems fSubs;
	private FrameworkModules fm;
	private FrameworkButtons fb;
	private Integer userId;
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{
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
		}catch(Exception e){
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean list(){
		MsgBean msg = new MsgBean();
		try{
//			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
//			PageHelper ph=new PageHelper();
//			ph=userService.selectUser(start,limit);	
//			msg.setRows(ph.getRows());
//			msg.setTotal(ph.getTotal());
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//获取子系统
//	public MsgBean getSubsystemsList(){
//		MsgBean msg = new MsgBean();
//		try{
//			IFrameworkSubsystemsService frameworkSubsystemsService = (IFrameworkSubsystemsService)BeanLoader.getWebBean("frameworkSubsystemsService");			
//			List<FrameworkSubsystems> ls=frameworkSubsystemsService.selectSubsystems(fSubs.getSystemId());
//			msg.setOutRows(true);//只用输出rows
//			msg.setRows(ls);//
//			msg.setMsg("成功获取子系统 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
//		}catch(Exception e){
//			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);		
//		}
//		return msg;
//	}
	//获取模块列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getModulesList(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkModulesService frameworkModulesService = (IFrameworkModulesService)BeanLoader.getWebBean("frameworkModulesService");
			PageHelper ph=new PageHelper();			
			ph=frameworkModulesService.selectModules(fm.getSystemId(),fm.getName(),start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	
	//获取按钮列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getButtonsList(){
		MsgBean msg = new MsgBean();
		try{
			IFrameworkButtonsService frameworkButtonsService = (IFrameworkButtonsService)BeanLoader.getWebBean("frameworkButtonsService");
			PageHelper ph=new PageHelper();			
			ph=frameworkButtonsService.selectButtons(fb.getSystemId(),fb.getModules(),start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	
	//加载菜单
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean loadMenuList(){
		MsgBean msg = new MsgBean();
		try{
			IMenuService menuService = (IMenuService)BeanLoader.getWebBean("menuService");
			Integer userId = super.getLoginUserId();
			if(userId!=null){
				List<MenuTree> menuList=(List<MenuTree>)menuService.loadMenuList(userId);
				msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS).setRows(menuList);		
			}else{
				msg.setMsg("加载菜单失败").setMsgCode(MsgBean.MsgCode.FAILURE);	
			}
		}catch(ServiceException e){
			log.error("加载菜单失败==="+e.getMessage());
			msg.setMsg("加载菜单失败==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	
	public FrameworkSubsystems getFSubs() {
		return fSubs;
	}
	public void setFSubs(FrameworkSubsystems subs) {
		fSubs = subs;
	}
	public FrameworkModules getFm() {
		return fm;
	}
	public void setFm(FrameworkModules fm) {
		this.fm = fm;
	}
	public FrameworkButtons getFb() {
		return fb;
	}
	public void setFb(FrameworkButtons fb) {
		this.fb = fb;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	

}
