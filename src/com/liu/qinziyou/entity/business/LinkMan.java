package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
@Entity
@Table(name = "linkman")
public class LinkMan  extends BaseBean{
	
	@Column(name="COMPANY_ID",nullable=true)
	private Integer companyId;//公司id	
	
	@Column(name="TYPE",nullable=false)
	private Integer type;//联系人类型1 窗口联系人 2 财务联系人 3其他联系人
	
	@Column(name="NAME",nullable=false)
	private String name;//姓名
	
	@Column(name="TEL",nullable=true)
	private String tel;	//电话
	
	@Column(name="PHONE",nullable=true)
	private String phone;//手机
	
	@Column(name="EMAIL",nullable=true)
	private String email;//邮件
	
	@Column(name="FAX",nullable=true)
	private String fax;//传真
	
	@Transient
	private String mode;// add 表示添加 del 表示删除 update 表示更新
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	
}
