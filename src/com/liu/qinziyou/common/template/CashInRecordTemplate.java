package com.liu.qinziyou.common.template;
/**
 * 来款记录模版，导入的excel文件先到达 CashInRecordTemplate
 * @author Administrator
 *
 */
public class CashInRecordTemplate {
	private String customerName;//客户名称
	private String accountName;//来款账户名称，可能为公司名称，也可能为个人账户名
	private String accountBank;//来款银行
	private String time;//来款时间，记录银行来款信息上的来款时间
	private String currency;//币种
	private String amt;//来款金额 单位为分	
	private String remark; //备注  
	
	
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	
	
	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public CashInRecordTemplate(){		 	  
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
			
}
