package com.liu.qinziyou.web.action.mobile.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.MsgBean.MsgCode;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.common.util.LoginCache;
import com.liu.qinziyou.common.util.LoginInfoUtil;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IUserService;
import com.liu.qinziyou.web.action.userlogin.UserLoginAction;

public class LoginAction  extends UserLoginAction{
	protected static final Log log = LogFactory.getLog(LoginAction.class);
	private String loginName;
	private String password;
	public MsgBean login() {
		try {
			IUserService userService = (IUserService) BeanLoader.getWebBean("userService");
			MsgBean msgBean = new MsgBean();
			try {
				User user = userService.userLogin(loginName, password,2);
				if (user != null) {// 登录成功
					LoginInfoUtil loginInfoUtil = LoginInfoUtil.getInstance();
					loginInfoUtil.setLoginInfo(super.getRequest(), super.getResponse(), loginName, user.getEnterpriseId(),user.getId(),user.getUserType());
					String sessionId = super.getRequest().getSession().getId();
					LoginCache.getInstance().putUserSessionId(user.getId(),sessionId);// 缓存SEESION ID
					LoginCache.getInstance().putLoginUser(user.getId(), user);//缓存登录用户信息
					msgBean.setMsg("登录成功").setMsgCode(MsgCode.SUCCESS);
					super.writeLoginLog(user);
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
	
	
}
