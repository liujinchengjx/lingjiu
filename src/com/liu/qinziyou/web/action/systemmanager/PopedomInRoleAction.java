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
import com.liu.qinziyou.entity.systemmanager.PopedomInRole;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IPopedomInRoleService;
import com.liu.qinziyou.web.action.BaseAction;

public class PopedomInRoleAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(PopedomInRoleAction.class);
	private PopedomInRole pRole;
	private Integer roleId;
	private String popedomInRoles;
	private String searchValue;	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");
			popedomInRoleService.add(pRole);
			msg.setEntity(pRole.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");				
			popedomInRoleService.savePopedomInRoles(popedomInRoles);			
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
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");
			popedomInRoleService.del(pRole);			
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
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");
			pRole=(PopedomInRole)popedomInRoleService.getBeanById(PopedomInRole.class,pRole.getId());
			msg.setEntity(pRole);			
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
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");
			PageHelper ph=new PageHelper();
			ph=popedomInRoleService.selectPopedomInRole(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	//读取角色权限列表 根据roleId
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getPopedomsByRoleId(){
		MsgBean msg = new MsgBean();
		try{
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");
			List<PopedomInRole> plst=new ArrayList<PopedomInRole>();			
			plst=popedomInRoleService.selectPopedomsByRoleId(roleId);	
			msg.setEntity(plst).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);	
		}
		return msg;
	}
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchPopedomInRole(){
		MsgBean msg = new MsgBean();
		try{
			IPopedomInRoleService popedomInRoleService = (IPopedomInRoleService)BeanLoader.getWebBean("popedomInRoleService");
			PageHelper ph=new PageHelper();
			ph=popedomInRoleService.searchPopedomInRole(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);	
		}
		return msg;
	}
	public PopedomInRole getPRole() {
		return pRole;
	}
	public void setPRole(PopedomInRole role) {
		pRole = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getPopedomInRoles() {
		return popedomInRoles;
	}
	public void setPopedomInRoles(String popedomInRoles) {
		this.popedomInRoles = popedomInRoles;
	}
	
}
