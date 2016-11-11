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
import com.liu.qinziyou.entity.systemmanager.PopedomInUser;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IPopedomInUserService;
import com.liu.qinziyou.web.action.BaseAction;

public class PopedomInUserAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(PopedomInUserAction.class);
	private PopedomInUser pUser;
	private Integer userId;
	private String searchValue;
	private String popedomInUsers;
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");							
			popedomInUserService.add(pUser);
			msg.setEntity(pUser.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);				
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
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");				
			popedomInUserService.savePopedomInUser(popedomInUsers);			
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
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");
			popedomInUserService.del(pUser);			
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
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");
			pUser=(PopedomInUser)popedomInUserService.getBeanById(PopedomInUser.class,pUser.getId());
			msg.setEntity(pUser);			
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
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");
			PageHelper ph=new PageHelper();
			ph=popedomInUserService.selectPopedomInUser(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	//读取用户权限列表 根据userId
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getPopedomsByUserId(){
		MsgBean msg = new MsgBean();
		try{
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");
			List<PopedomInUser> plst=new ArrayList<PopedomInUser>();			
			plst=popedomInUserService.selectPopedomsByUserId(userId);	
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
			IPopedomInUserService popedomInUserService = (IPopedomInUserService)BeanLoader.getWebBean("popedomInUserService");
			PageHelper ph=new PageHelper();			
			ph=popedomInUserService.searchPopedomInUser(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	public PopedomInUser getPUser() {
		return pUser;
	}
	public void setPUser(PopedomInUser user) {
		pUser = user;
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
	public String getPopedomInUsers() {
		return popedomInUsers;
	}
	public void setPopedomInUsers(String popedomInUsers) {
		this.popedomInUsers = popedomInUsers;
	}

	
}
