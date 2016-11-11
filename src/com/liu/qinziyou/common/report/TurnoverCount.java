package com.liu.qinziyou.common.report;

import java.sql.Timestamp;
/**
 * 营业额统计
 * @author Administrator
 *
 */
public class TurnoverCount {
	
	private Integer orderNo;//序号
	
	private Integer customerId; //客户ID
	
	private String customerName; //客户名称
	
	private Timestamp customDate;//报关日期
	
	private Double amt;//营业额
	
	private String curreny;//币种
	
	private String serviceMan;//客服员
	
	private String businessMan;//业务员
	
	private String remark;//备注
	
	public TurnoverCount(){
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getCustomDate() {
		return customDate;
	}

	public void setCustomDate(Timestamp customDate) {
		this.customDate = customDate;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public String getServiceMan() {
		return serviceMan;
	}

	public void setServiceMan(String serviceMan) {
		this.serviceMan = serviceMan;
	}

	public String getBusinessMan() {
		return businessMan;
	}

	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCurreny() {
		return curreny;
	}

	public void setCurreny(String curreny) {
		this.curreny = curreny;
	}
	
	
}
