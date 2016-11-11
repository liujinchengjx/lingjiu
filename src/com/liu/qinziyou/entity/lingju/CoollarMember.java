package com.liu.qinziyou.entity.lingju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "coollar_member")
public class CoollarMember extends BaseBean{

	
	@Column(name="MEMBER_CODE",nullable=false)	
	private String memberCode;//会员号
	
	@Column(name="MEMBER_NAME",nullable=false)	
	private String memberName;//会员姓名
	
	@Column(name="PHONE_NUMBER",nullable=false)	
	private String phoneNumber;//手机号
	
	@Column(name="EMAIL",nullable=true)	
	private String email;//邮件
	
	@Column(name="PWD",nullable=true)
	private String pwd;
	
	@Column(name="ADDRESS",nullable=false)	
	private String address;//地址
	
	@Column(name="state",nullable=false)	
	private Integer state;//会员状态 0 无效 1 有效

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
	
	
}
