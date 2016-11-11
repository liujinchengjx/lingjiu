package com.liu.qinziyou.common.aop;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.LoginUserUtil;
import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.util.DateUtil;
import com.liu.qinziyou.common.util.KeyFactory;
import com.liu.qinziyou.common.util.LoginCache;
import com.liu.qinziyou.common.util.LoginInfoUtil;

/**
 * 判断是否登录的拦截器
 * @author liujc
 *2013-3-1
 */
public class LoginInterceptor extends AbstractInterceptor{
	
	private final static Log logger = LogFactory.getLog(LoginInterceptor.class);
	protected long timeOut = 120L;
	@Override
	protected Object after(ActionInvoke invInfo) throws Exception{
		//logger.info("LoginInterceptor after");
		return null;
		
	}

	@Override
	protected Object before(ActionInvoke invInfo) throws Exception{
		//logger.info("LoginInterceptor before");
		HttpServletRequest request = invInfo.getBaseAction().getRequest();
		HttpServletResponse response = invInfo.getBaseAction().getResponse();
		LoginInfoUtil loginInfoUtil = LoginInfoUtil.getInstance();
		String userCookie = loginInfoUtil.getUserCookie(request);
		if(userCookie == null) {
			MsgBean msgBean = new MsgBean();
			msgBean.setMsg("没有登录").setMsgCode(MsgBean.MsgCode.LOGIN_EXCEPTION).setFailureCode("1");
			logger.info("没有登录");
			return msgBean;
		}
		
		//取登录信息
		String sLoginTime = loginInfoUtil.getLoginTime(request);
		String sLastAccessTime = loginInfoUtil.getLastAccessTime(request);
		Integer userId = loginInfoUtil.getUserId(request);
		Integer companyId = loginInfoUtil.getUserCompanyId(request);
		String sCurrentUserKey = loginInfoUtil.getCurrentUserKey(request);
		
		String newCurrentUserKey = KeyFactory.createKeyForUser(userId,companyId, new Long(sLoginTime));
		
		if(!sCurrentUserKey.equals(newCurrentUserKey)){
			loginInfoUtil.clearLoginInfo(request, response);//清除登录信息
			MsgBean msgBean = new MsgBean();
			msgBean.setMsg("登录信息异常").setMsgCode(MsgBean.MsgCode.LOGIN_EXCEPTION).setFailureCode("2");//登录信息异常
			logger.info("登录信息异常");
			return msgBean;
		}
		
		Timestamp lastAccessTime = new Timestamp(new Long(sLastAccessTime));
		Timestamp currentTime = new Timestamp(new Long(System.currentTimeMillis()));
		float dateDiff = DateUtil.dateDiff(currentTime, lastAccessTime, 2);

		//登录时间大于TIMEOUT_TIME分钟
		if(dateDiff > this.timeOut){
			loginInfoUtil.clearLoginInfo(request, response);//清除登录COOKIE信息
			MsgBean msgBean = new MsgBean();
			msgBean.setMsg("登录超时").setMsgCode(MsgBean.MsgCode.LOGIN_EXCEPTION).setFailureCode("3");//登录超时
			logger.info("登录超时");
			return msgBean;
		}
		
	
		String reqSessionId = request.getSession().getId();
		String onlineSessionId = LoginCache.getInstance().getUserSessionId(userId);
		if(null == onlineSessionId || !reqSessionId.equals(onlineSessionId)){
			loginInfoUtil.clearLoginInfo(request, response);//清除登录信息
			MsgBean msgBean = new MsgBean();
			msgBean.setMsg("你的账号已在其他地方登录，你被迫下线，如非你本人操作，请立即重新登录并修改密码").setMsgCode(MsgBean.MsgCode.LOGIN_EXCEPTION).setFailureCode("5");//SESSIONID异常，被后面登录的挤出去了
			logger.info("sessionID异常，请求sessionid:"+reqSessionId+ " 用户ID"+userId+" sessionid:"+onlineSessionId);
			return msgBean;
		}
		
		//更新线程变量里的登录用户信息
		LoginUserUtil.setLoginUser(LoginCache.getInstance().getLoginUser(userId));
		//登录验证通过后，更新最后访问时间
		loginInfoUtil.updateLastAccessTime(request, response);
		return null;
	}
}
