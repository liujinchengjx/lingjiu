package com.liu.qinziyou.web.action.systemmanager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IUserService;
import com.liu.qinziyou.web.action.BaseAction;

public class UserAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(UserAction.class);
	private User usr;
	private Integer userId;
	private Integer flag;
	private String searchValue;
	private String orgPasswod;
	private String newPassword;
	private Integer userType;
	private String positionCode;//职位管理
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean saveUser(){
		MsgBean msg = new MsgBean();
		try{						
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			usr.setLowerdName(usr.getUserName().toLowerCase());
			userService.saveUser(usr);
			msg.setEntity(usr.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);				
		}catch(ServiceException e){
			log.error("保存用户失败==="+e.getMessage());
			msg.setMsg("保存用户失败==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{						
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			userService.delete(usr.getId(),1);			
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
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			usr=(User)userService.getBeanById(User.class,userId);
			msg.setEntity(usr);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
 	public MsgBean resetPwd(){//重置密码
		MsgBean msg = new MsgBean();
		try{						
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			userService.resetPwd(userId);			
			msg.setMsg("重置密码成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("重置密码失败---"+e.getMessage());
			msg.setMsg("重置密码失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
 	public MsgBean active(){//激活用户
		MsgBean msg = new MsgBean();
		try{						
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			userService.activeUser(userId,flag);			
			msg.setMsg("激活成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("激活失败---"+e.getMessage());
			msg.setMsg("激活失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean changePassword(){
		MsgBean msg = new MsgBean();
		try{
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			userService.changePassword(userId,orgPasswod,newPassword);
			msg.setMsg("修改密码成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("修改密码失败---"+e.getMessage());
			msg.setMsg("修改密码失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean updatePosition(){//更新职位信息
		MsgBean msg = new MsgBean();
		try{
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			PageHelper pageHelper =  userService.searchUser(positionCode, 0, 10);
			if (pageHelper.getRows() != null && pageHelper.getRows().size() > 0 ) {
				msg.setMsg("该职位已分配用户 ").setMsgCode(MsgBean.MsgCode.FAILURE);
				return msg;
			}
			User user = userService.getUserInfo(userId);
		
			userService.updatePosition(userId,positionCode);
			msg.setMsg("分配职位成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("分配职位失败---"+e.getMessage());
			msg.setMsg("分配职位失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean delPosition(){//更新职位信息
		MsgBean msg = new MsgBean();
		try{
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");			
			userService.updatePosition(userId,null);
			msg.setMsg("分配职位成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("分配职位失败---"+e.getMessage());
			msg.setMsg("分配职位失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })//搜索
	public MsgBean searchUser(){
		MsgBean msg = new MsgBean();
		try{
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			PageHelper ph=userService.searchUser(searchValue,userType,flag,start,limit);	
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("搜索成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("搜索用户失败---"+e.getMessage());
			msg.setMsg("搜索用户失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	public User getUsr() {
		return usr;
	}
	public void setUsr(User usr) {
		this.usr = usr;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOrgPasswod() {
		return orgPasswod;
	}
	public void setOrgPasswod(String orgPasswod) {
		this.orgPasswod = orgPasswod;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

}
