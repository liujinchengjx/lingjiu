package com.liu.qinziyou.web.action.systemmanager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.systemmanager.UserInRole;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IUserInRoleService;
import com.liu.qinziyou.web.action.BaseAction;

public class UserInRoleAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(UserInRoleAction.class);
	private UserInRole uRole;
	private Integer userId;
	private String userInRoles;
	private String searchValue;	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");
			userInRoleService.add(uRole);
			msg.setEntity(uRole.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");				
			userInRoleService.saveUserInRole(userInRoles);			
			msg.setMsg("权限保存成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");
			userInRoleService.del(uRole);			
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
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");
			uRole=(UserInRole)userInRoleService.getBeanById(UserInRole.class,uRole.getId());
			msg.setEntity(uRole);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//读到角色权限列表 有分页
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean list(){
		MsgBean msg = new MsgBean();
		try{
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");
			PageHelper ph=new PageHelper();
			ph=userInRoleService.selectUserInRole(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	//读取用户权限列表 根据userId
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getRoles(){
		MsgBean msg = new MsgBean();
		try{
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");
			List<UserInRole> plst=new ArrayList<UserInRole>();			
			plst=userInRoleService.selectRolesByUserId(userId);	
			msg.setOutRows(outRows);
			msg.setRows(plst);		
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchUserInRole(){
		MsgBean msg = new MsgBean();
		try{
			IUserInRoleService userInRoleService = (IUserInRoleService)BeanLoader.getWebBean("userInRoleService");
			PageHelper ph=new PageHelper();
			String searchValue=this.getRequest().getParameter("val");
			ph=userInRoleService.searchUserInRole(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public UserInRole getURole() {
		return uRole;
	}
	public void setURole(UserInRole role) {
		uRole = role;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getUserInRoles() {
		return userInRoles;
	}
	public void setUserInRoles(String userInRoles) {
		this.userInRoles = userInRoles;
	}
	
}
