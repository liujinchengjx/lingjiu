package com.liu.qinziyou.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CookieUtil {

	protected static final Logger logger = Logger.getLogger(CookieUtil.class);

    /**
     * 获取Cookie的值，默认无需转码
     * @param request
     * @param cookieName
     * @return
     */ 
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		return getCookieValue(request, cookieName, false);
	}

    /**
     * 获取Cookie的值
     * @param request
     * @param cookieName
     * @param isDecoder 是否需要转码
     * @return
     */
	public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
		Cookie cookieList[] = request.getCookies();
		if (cookieList == null || cookieName == null)
			return null;
		String retValue = null;
		try {
			for (int i = 0; i < cookieList.length; i++) {
				if (cookieList[i].getName().equals(cookieName)) {
					if (isDecoder) {
						retValue = URLDecoder.decode(cookieList[i].getValue(), "utf-8");
					} else {
						retValue = cookieList[i].getValue();
					}
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
		}
		return retValue;
	}

    /**
     * 设置Cookie 的值
     * @param response
     * @param cookieName
     * @param cookieValue
     */
	public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
		if (cookieValue == null) {
			cookieValue = "";
		}
		setCookie(null, response, cookieName, cookieValue);
	}

   /**
    * 设置Cookie 的值
    * @param request
    * @param response
    * @param cookieName
    * @param cookieValue
    */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue) {
		setCookie(request, response, cookieName, cookieValue, -1);
	}

    /**
     * 设置Cookie 的值
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxage) {
		setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
	}

    /**
     * 设置Cookie 的值
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param isEncode
     */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, boolean isEncode) {
		setCookie(request, response, cookieName, cookieValue, -1, isEncode);
	}

    /**
     * 设置Cookie 的值
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param isEncode
     */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxage, boolean isEncode) {
		doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
	}

	/**
	 * 删除 Cookie
	 * @param request
	 * @param response
	 * @param cookieName
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		doSetCookie(request, response, cookieName, "", -1, false);
	}

    /**
     * 删除 Cookie
     * @param response
     * @param cookieName
     */
	public static void deleteCookie(HttpServletResponse response, String cookieName) {
		doSetCookie(null, response, cookieName, "", -1, false);
	}

    /**
     * 设置Cookie 的值
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param isEncode
     */
	private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,
			String cookieValue, int cookieMaxage, boolean isEncode) {
		try {
			if (cookieValue != null && cookieValue.equals("")) {
				cookieValue = null;
			} else if (isEncode) {
				cookieValue = URLEncoder.encode(cookieValue, "utf-8");
			}
			Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxage > 0)
				cookie.setMaxAge(cookieMaxage);
			else {
				if (cookieValue == null)
					cookie.setMaxAge(0);
			}
			if (null != request)
				cookie.setDomain(getDomainName(request));
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

    /**
     * 获取域名
     * @param request
     * @return
     */
	private static final String getDomainName(HttpServletRequest request) {
		String domainName = null;
		if (domainName == null || domainName.equals("")) {
			//String serverName = request.getRequestURL().toString();
			//TESETETEST
			String serverName = request.getHeader("Referer");
			if (serverName == null || serverName.equals("")) {
				domainName = "";
			} else {
				serverName = serverName.toLowerCase();
				serverName = serverName.substring(7);
				final int end = serverName.indexOf("/");
				serverName = serverName.substring(0, end);
				if(serverName.indexOf(":") > 0){
					String[] ary = serverName.split("\\:");
					serverName = ary[0];
				}
				domainName = serverName;
				/*
				final String[] domains = serverName.split("\\.");
				int len = domains.length;
				if (len > 1) {
					domainName = "." + domains[len - 2] + "." + domains[len - 1];
				} else {
					domainName = serverName;
				}*/
			}
		}
		/*if (domainName != null && domainName.indexOf(":") > 0) {
			String[] ary = domainName.split("\\:");
			domainName = ary[0];
		}*/
		logger.debug("CookieUtils取域名domainName=" + domainName);
		return domainName;
	}

}