package com.liu.qinziyou.web.action.systemmanager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.systemmanager.Role;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IRoleService;
import com.liu.qinziyou.web.action.BaseAction;

public class RoleAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(RoleAction.class);
	private Role role;
	private String searchValue;
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IRoleService roleService = (IRoleService)BeanLoader.getWebBean("roleService");
			roleService.add(role);
			msg.setEntity(role.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IRoleService roleService = (IRoleService)BeanLoader.getWebBean("roleService");
			roleService.update(role);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){//删除
		MsgBean msg = new MsgBean();
		try{						
			IRoleService roleService = (IRoleService)BeanLoader.getWebBean("roleService");
			roleService.delete(role.getId(),1);			
			msg.setMsg("删除成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IRoleService roleService = (IRoleService)BeanLoader.getWebBean("roleService");
			role=(Role)roleService.getBeanById(Role.class,role.getId());
			msg.setEntity(role);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchRole(){
		MsgBean msg = new MsgBean();
		try{
			IRoleService roleService = (IRoleService)BeanLoader.getWebBean("roleService");
			PageHelper ph=new PageHelper();
			ph=roleService.searchRole(searchValue,start,limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
