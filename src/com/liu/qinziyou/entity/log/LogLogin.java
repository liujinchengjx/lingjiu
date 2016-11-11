package com.liu.qinziyou.entity.log;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 网站登陆日志
 */
@Entity
@Table(name = "log_login")
public class LogLogin extends BaseBean {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8106967214881944957L;



	/**
	 * 用户ID
	 */
	@Column(name = "USER_ID", nullable = false)
	private Integer userId;

	/**
	 * 用户名称
	 */
	@Column(name = "USER_NAME", length = 32)
	private String userName;

	/**
	 * 登录浏览器
	 */
	@Column(name = "LOGIN_BROWSER", length = 300)
	private String loginBrowser;

	/**
	 * 登录IP
	 */
	@Column(name = "LOGIN_IP", length = 30)
	private String loginIp;

	/**
	 * 登录时间
	 */
	@Column(name = "LOGIN_TIME", length = 19)
	private Timestamp loginTime;

	/**
	 * 手机号码
	 */
	@Column(name = "MOBILE", length = 64)
	private String mobile;

	/**
	 * 企业名称
	 */
	@Column(name = "COMPANY_NAME", length = 64)
	private String companyName;


	// Constructors
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginBrowser() {
		return this.loginBrowser;
	}

	public void setLoginBrowser(String loginBrowser) {
		this.loginBrowser = loginBrowser;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Timestamp getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	

}