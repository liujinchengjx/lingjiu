package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

/***
 * 
 * 供应商银行数据信息-用于独立维护供应商基础数据信息
 * 
 * author min.shen
 * 
 * 2015-03-31
 * */
@Entity
@Table(name = "sup_bank_info")
public class SuplerBankInfo extends BaseBean{
	private static final long serialVersionUID = 8816618731704602328L;
	
	//供应商ID
	@Column(name="SUPCOMPANYID",nullable=true)
	private int SupCompanyId;
	//SWIFI CODE
	@Column(name="SWIFTCODE",nullable=true)
	private String SwifCode;
	//银行账户
	@Column(name="BANKACCOUNT",nullable=true)
	private String BankAccount;
	//开户行
	@Column(name="BANKNAME",nullable=true)
	private String BankName;
	//开户人
	@Column(name="BANKUSERNAME",nullable=true)
	private String BankUserName;
	//银行地址
	@Column(name="BANKADRESS",nullable=true)
	private String BankAddress;
	//公司地址
	@Column(name="COMPANYADRESS",nullable=true)
	private String CompanyAddress;
	
	
	public String getBankAccount() {
		return BankAccount;
	}
	public void setBankAccount(String bankAccount) {
		BankAccount = bankAccount;
	}
	public int getSupCompanyId() {
		return SupCompanyId;
	}
	public void setSupCompanyId(int supCompanyId) {
		SupCompanyId = supCompanyId;
	}
	public String getSwifCode() {
		return SwifCode;
	}
	public void setSwifCode(String swifCode) {
		SwifCode = swifCode;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public String getBankUserName() {
		return BankUserName;
	}
	public void setBankUserName(String bankUserName) {
		BankUserName = bankUserName;
	}
	public String getBankAddress() {
		return BankAddress;
	}
	public void setBankAddress(String bankAddress) {
		BankAddress = bankAddress;
	}
	public String getCompanyAddress() {
		return CompanyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		CompanyAddress = companyAddress;
	}
}
