package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

/**
 *  收款人
 * @author li
 */
@Entity
@Table(name = "payee")
public class Payee extends BaseBean{		
	
	@Column(name="CNAME",nullable=true)
	private String cname;//中文名称	
	
	@Column(name="ENAME",nullable=true)
	private String ename;//英文名称
	
	@Column(name="TEL",nullable=true)
	private String tel;//联系电话
	
	@Column(name="PHONE",nullable=true)
	private String phone;//手机号码	
	
	@Column(name="BANKNAME",nullable=true)
	private String bankName;//开户行名称
	
	@Column(name="OPENNAME",nullable=true)
	private String openName;//开户人
	
	@Column(name="BANKACCOUNT",nullable=true)
	private String bankAccount;//银行账户号码
	
	@Column(name="OPEN_ADDRESS",nullable=true)
	private String openAddress;//开户地址
	
	@Column(name="REMARKS",nullable=true)
	private String remarks;//备注

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getOpenAddress() {
		return openAddress;
	}

	public void setOpenAddress(String openAddress) {
		this.openAddress = openAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOpenName() {
		return openName;
	}

	public void setOpenName(String openName) {
		this.openName = openName;
	}

	
	
		
}
