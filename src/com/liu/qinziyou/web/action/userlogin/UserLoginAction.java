package com.liu.qinziyou.web.action.userlogin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.Configuration;
import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.MsgBean.MsgCode;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.common.util.DateUtil;
import com.liu.qinziyou.common.util.KeyFactory;
import com.liu.qinziyou.common.util.LoginCache;
import com.liu.qinziyou.common.util.LoginInfoUtil;
import com.liu.qinziyou.entity.log.LogLogin;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.log.ILogService;
import com.liu.qinziyou.services.systemmanager.IUserService;
import com.liu.qinziyou.web.action.BaseAction;

public class UserLoginAction extends BaseAction {
	protected static final Log log = LogFactory.getLog(UserLoginAction.class);

	private String loginName;
	private String password;
	private Integer loginType;//1 后台用户登陆  2 会员登陆。 没有传值就默认为0.
	private String autologin;
	private String validateCode;
	
	private String orgPasswod;
	private String newPassword;
	
	private User usr;
	
	public MsgBean login() {
		try {

			boolean isNeedValidatecode = true;// 默认需要校验验证码
			if (Configuration.getConfigValue("IS_NEED_VALIDATECODE") != null
					&& !Configuration.getConfigValue("IS_NEED_VALIDATECODE").equals("1")) {
				isNeedValidatecode = false;
			}
			if(loginType == null){
				loginType = 1;
			}
			if(loginType.equals(2)){//会员登陆不校验验证码
				isNeedValidatecode = false;
			}
			// 1, 检查验证码是否有效
			String validateCode1 = LoginInfoUtil.getInstance().getValidateCode(super.getRequest());
			if (isNeedValidatecode) {
				if (validateCode1 == null
						|| !validateCode1.equals(KeyFactory.createValidationcode(validateCode))) {
					return new MsgBean().setMsg("验证码无效").setMsgCode(MsgBean.MsgCode.FAILURE);
				}
			}
			// 2, 验证码通过，清除！
			LoginInfoUtil.getInstance().clearValidateCode(super.getRequest(),super.getResponse());
			IUserService userService = (IUserService) BeanLoader.getWebBean("userService");
			MsgBean msgBean = new MsgBean();
			try {
				User user = userService.userLogin(loginName, password,loginType);
				if (user != null) {// 登录成功
					LoginInfoUtil loginInfoUtil = LoginInfoUtil.getInstance();
					loginInfoUtil.setLoginInfo(super.getRequest(), super.getResponse(), loginName, user.getEnterpriseId(),user.getId(),user.getUserType());
					String sessionId = super.getRequest().getSession().getId();
					LoginCache.getInstance().putUserSessionId(user.getId(),sessionId);// 缓存SEESION ID
					LoginCache.getInstance().putLoginUser(user.getId(), user);//缓存登录用户信息
					msgBean.setMsg("登录成功").setMsgCode(MsgCode.SUCCESS);
					writeLoginLog(user);
				}
			} catch (ServiceException e) {
				msgBean.setMsg(e.getMessage()).setMsgCode(MsgCode.FAILURE);
			} catch (Exception e) {
				msgBean.setMsg(e.getMessage()).setMsgCode(MsgCode.FAILURE);
			}
			return msgBean;
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgBean().setMsg("登录失败，请重试或联系管理员").setMsgCode(
					MsgBean.MsgCode.FAILURE);
		}
	}
	
	protected void writeLoginLog(User user){
		try{
			ILogService logService  =  (ILogService) BeanLoader.getWebBean("logService");
			LogLogin logLogin = new LogLogin();
			logLogin.setUserId(user.getId());
			logLogin.setLoginBrowser(super.getRequest().getHeader("user-agent"));
			logLogin.setLoginIp(super.getReqIP(super.getRequest()));
			logLogin.setLoginTime(DateUtil.getCurrentTime());
			logLogin.setCompanyName(user.getCompany().getCname());
			logLogin.setUserName(user.getUserName());
			logLogin.setMobile(user.getTel());
			user.setLastLoginDate(user.getLoginDate());
			user.setLoginDate(DateUtil.getCurrentTime());
			logLogin.setCreateBy(user.getUserName());
			logLogin.setCreateTime(DateUtil.getCurrentTime());
			logLogin.setLastModifyBy(user.getUserName());
			logLogin.setLastModifyTime(DateUtil.getCurrentTime());
			logService.update(user);
			logService.add(logLogin);
		}catch(Exception e){
			log.equals("写登录日志失败");
		}
		
	}
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean changePassword(){
		MsgBean msg = new MsgBean();
		try{
			Integer loginUserId = super.getLoginUserId();
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			userService.changePassword(loginUserId,orgPasswod,newPassword);
			msg.setMsg("修改密码成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("修改密码失败---"+e.getMessage());
			msg.setMsg("修改密码失败---"+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}

	public MsgBean exitLogin() {
		try {
			Integer loginUserId = super.getLoginUserId();
			if (loginUserId != null) {// 清楚登录缓存
				LoginCache.getInstance().clearLoginCache(super.getLoginUserId());
			}
			LoginInfoUtil.getInstance().clearLoginInfo(super.getRequest(),super.getResponse());// 清除登录cookie
			super.getRequest().getSession().invalidate();// 清除登录session
			return new MsgBean().setMsgCode(MsgBean.MsgCode.SUCCESS).setMsg("退出成功！");
		} catch (Exception e) {
			return new MsgBean().setMsgCode(MsgBean.MsgCode.FAILURE).setMsg("退出出错，请重试！");
		}
	}

	@InterceptorNames( { @InterceptorName("loginInterceptor") })
	public MsgBean getUserInfo() {
		MsgBean msgBean = new MsgBean();
		try {
			Integer loginUserId = LoginInfoUtil.getInstance().getUserId(super.getRequest());
			IUserService userService = (IUserService) BeanLoader.getWebBean("userService");
			User user = userService.getUserInfo(loginUserId);
			if (user != null) {
				Map<String, Object> mapContent = new HashMap<String, Object>();
				mapContent.put("userId", user.getId());
				mapContent.put("realName", user.getRealName());
				mapContent.put("userName", user.getUserName());
				mapContent.put("companyName", user.getCompany().getCname());
				mapContent.put("lastLoginDate", user.getLastLoginDate());
				msgBean.setMsgCode(MsgBean.MsgCode.SUCCESS).setMsg("成功").setEntity(mapContent);
			}else{
				msgBean.setMsgCode(MsgBean.MsgCode.FAILURE).setMsg("获取用户信息失败");
			}
			return msgBean;
		} catch (Exception e) {
			e.printStackTrace();
			return new MsgBean().setMsgCode(MsgBean.MsgCode.FAILURE).setMsg("发生异常请联系管理员");
		}
	}
	public MsgBean registeUser(){
		MsgBean msg = new MsgBean();
		try{						
			IUserService userService = (IUserService)BeanLoader.getWebBean("userService");
			//usr.setLowerdName(usr.getUserName().toLowerCase());
			userService.saveUser(usr);
			msg.setEntity(usr.getId()).setMsg("注册成功").setMsgCode(MsgBean.MsgCode.SUCCESS);				
		}catch(ServiceException e){
			log.error("注册失败==="+e.getMessage());
			msg.setMsg("注册用户失败==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getAutologin() {
		return autologin;
	}

	public void setAutologin(String autologin) {
		this.autologin = autologin;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
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

	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}
	
	

}
