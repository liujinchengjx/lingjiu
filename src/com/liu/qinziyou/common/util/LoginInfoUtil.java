package com.liu.qinziyou.common.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;



public  class LoginInfoUtil {
	public final static long TIMEOUT_TIME = 120;// 多少超时时间
	public static final String currentUserCookie="gscm_user";

	public static final String currentLoginName="gscm_ln";

	public static LoginInfoUtil LoginInfoUtil;
	public final static LoginInfoUtil getInstance() {
		if(LoginInfoUtil==null){
			LoginInfoUtil = new LoginInfoUtil();
		}
		return LoginInfoUtil;
	}
	/**
	 * 设置登录信息到cookie里
	 * @param request
	 * @param response
	 * @param loginName 登录名
	 * @param userId 用户ID
	 * @param areaId 用户区县ID
	 * @return
	 */
	public String setLoginInfo(HttpServletRequest request, HttpServletResponse response, String loginName,Integer companyId,Integer userId,Integer userType) throws Exception{
		
		if(companyId==null) throw new Exception("公司ID为空");
		if(userId==null) throw new Exception("用户ID为空");
		
		Long loginTime = System.currentTimeMillis();// 登录时间
		Long lastAccessTime = loginTime + 1;// 加1跟登录时间区别一下
		String currentUserKey = KeyFactory.createKeyForUser(userId,companyId, loginTime);// 加密后的串

		/** 更新Cache里用户的最后访问时间* */
		LoginCache.getInstance().setUserLastAccessTime(userId, lastAccessTime);

		String loginInfoStr = currentUserKey + "_" + companyId  + "_" +userId + "_" + loginTime+ "_" + lastAccessTime+"_"+userType;
		CookieUtil.setCookie(request, response, currentLoginName, loginName,false);
		CookieUtil.setCookie(request, response, currentUserCookie,loginInfoStr, false);
		return currentUserKey;

	}
	
	/**
	 * 更新最后访问时间
	 * @param request
	 * @param response
	 */
	public void updateLastAccessTime(HttpServletRequest request, HttpServletResponse response){
		Long currtentTime = System.currentTimeMillis();
		String sLastAccessTime = this.getLastAccessTime(request);//取最后访问时间
		String loginInfoStr = this.getUserCookie(request);
		loginInfoStr = loginInfoStr.replace("_"+sLastAccessTime, "_"+currtentTime.toString());//用当前时间替换最后访问时间
		
		/**更新Cache里用户的最后访问时间**/
		LoginCache.getInstance().setUserLastAccessTime(this.getUserId(request), currtentTime);
		/**更新cookie里用户的最后访问时间**/
		CookieUtil.setCookie(request, response, currentUserCookie, loginInfoStr, false);
	}
	
	
	/**
	 * 清除登录信息
	 * @param request
	 * @param response
	 */
	public void clearLoginInfo(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.deleteCookie(request, response, currentLoginName);
		CookieUtil.deleteCookie(request, response, currentUserCookie);
	}
	/**
	 * 获取登录用户信息
	 * @param request
	 * @return
	 */
	public String getUserCookie(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, currentUserCookie);
	}
	
	/**
	 * 获得登录信息数组
	 * @param request
	 * @return
	 */
	public String[] getUserCookies(HttpServletRequest request) {
		String userCookie = this.getUserCookie(request);
		if (userCookie != null && userCookie.length() > 0) {
			String[] aryUserCookies = userCookie.split("\\_");
			if (aryUserCookies.length >= 4) {
				return aryUserCookies;
			}
		}
		return null;
	}

	public final static String VALIDATE_CODE_COOKIE_NAME = "_vc";
	public final static String VALIDATE_CODE_COOKIE_TRUENAME = "_truevc";

	public final static void setValidateCode(HttpServletRequest request, HttpServletResponse response, String validateCode) {
		// 设置验证码加密COOKIE
		CookieUtil.setCookie(request, response, VALIDATE_CODE_COOKIE_NAME, KeyFactory.createValidationcode(validateCode),
				false);
	}

	/**
	 * 取加密后的验证码cookie
	 * @param request
	 * @return
	 */
	public String getValidateCode(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, VALIDATE_CODE_COOKIE_NAME);
	}
	
	/**
	 * 取原生验证码COOKIE
	 * @param request
	 * @return
	 */
	public  String getTrueCode(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, VALIDATE_CODE_COOKIE_TRUENAME);
	}
	
	
	public  void clearValidateCode(HttpServletRequest request, HttpServletResponse response) {
		//清除加密后的验证码
		CookieUtil.deleteCookie(request, response, VALIDATE_CODE_COOKIE_NAME);
		//清除原生验证码
		CookieUtil.deleteCookie(request, response, VALIDATE_CODE_COOKIE_TRUENAME);
	}

	
	
	/**
	 * 获取登录用户信息加密串
	 * @param request
	 * @return
	 */
	public String getCurrentUserKey(HttpServletRequest request){
		String currentUserKey = null;
		String[] aryUserCookies = this.getUserCookies(request);
		if(aryUserCookies!=null){
			currentUserKey = aryUserCookies[0];
		}
		return currentUserKey;
	}
	
	
	
	public Integer getUserId(HttpServletRequest request) {
		Integer userId = null;
		String[] aryUserCookies = this.getUserCookies(request);

		if (aryUserCookies != null) {
			String suserId = aryUserCookies[2];
			String slastAccessTime = aryUserCookies[4];
			if (StringUtils.isNotEmpty(slastAccessTime)) {
				Long newTime = System.currentTimeMillis();
				Timestamp lastAccessTimestamp = new Timestamp(new Long(slastAccessTime));
				Timestamp nowTimestamp = new Timestamp(newTime);
				float dateDiff = DataUtil.dateDiff(nowTimestamp, lastAccessTimestamp, 2);
				if (dateDiff < TIMEOUT_TIME) {
					userId = new Integer(suserId);
				}
			}
		}
		return userId;
	}
	
	public String getLoginTime(HttpServletRequest request) {
		String loginTime= null;
		String[] aryUserCookies = this.getUserCookies(request);
		if(aryUserCookies!=null){
			loginTime = aryUserCookies[3];
		}
		return loginTime;
	}
	
	public String getLastAccessTime(HttpServletRequest request) {
		String lastAccessTime= null;
		String[] aryUserCookies = this.getUserCookies(request);
		if(aryUserCookies!=null){
			lastAccessTime = aryUserCookies[4];
		}
		return lastAccessTime;
	}
	
	public Integer getUserCompanyId(HttpServletRequest request) {
		Integer companyId= null;
		String[] aryUserCookies = this.getUserCookies(request);
		if(aryUserCookies!=null){
			companyId = new Integer(aryUserCookies[1]);
		}
		return companyId;
	}
	
	public Integer getUserType(HttpServletRequest request) {
		Integer userType= null;
		String[] aryUserCookies = this.getUserCookies(request);
		if(aryUserCookies!=null){
			userType =  new Integer(aryUserCookies[5]);
		}
		return userType;
	}
	
	public String getLoginName(HttpServletRequest request) {
		return CookieUtil.getCookieValue(request, currentLoginName);
	}

}
