package com.liu.qinziyou.common.report;

import java.sql.Timestamp;
import java.util.List;
/**
 * 账单统计 统计仓储涉及到的费用
 * @author Administrator
 *
 */
public class PaymentCountMaster {
	  private String referOrderNo;// 参考号码
	  private String customerName;//客户公司
	  private String customerAddress = ""; //客户公司地址
	  private String customerTel = ""; //客户公司电话
	  private String customerFax = ""; //客户公司传真
	  
	  private String opCompanyName;//经营公司
	  private String opCompanyEnName;//经营公司
	  private String opCompanyAddress = ""; //经营公司 地址
	  private String opCompanyTel = ""; //经营公司 电话
	  private String opCompanyFax = ""; //经营公司传真
	  
	  private String payeeName;//收款方
	  private String payeeBankName = ""; //收款银行
	  private String payeeBankAccount = ""; //收款银行账户
	  
	  private String startTime;//开始时间
	  private String endTime;//截止时间
	  
	  private Timestamp settlementDate;//结算日期
	  private String currency;//币种 默认是 港币
	  private Double rate;//汇率
	  private Double totalAmt;//总金额
	  
	  private List<PaymentCountItem> paymentCountItemList;
	  public PaymentCountMaster(){
	  }
	public String getOpCompanyName() {
		return opCompanyName;
	}
	public void setOpCompanyName(String opCompanyName) {
		this.opCompanyName = opCompanyName;
	}
	
	public String getOpCompanyAddress() {
		return opCompanyAddress;
	}
	public void setOpCompanyAddress(String opCompanyAddress) {
		this.opCompanyAddress = opCompanyAddress;
	}
	public String getOpCompanyTel() {
		return opCompanyTel;
	}
	public void setOpCompanyTel(String opCompanyTel) {
		this.opCompanyTel = opCompanyTel;
	}
	public String getOpCompanyFax() {
		return opCompanyFax;
	}
	public void setOpCompanyFax(String opCompanyFax) {
		this.opCompanyFax = opCompanyFax;
	}
	public Timestamp getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Timestamp settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCustomerFax() {
		return customerFax;
	}
	public void setCustomerFax(String customerFax) {
		this.customerFax = customerFax;
	}
	public String getReferOrderNo() {
		return referOrderNo;
	}
	public void setReferOrderNo(String referOrderNo) {
		this.referOrderNo = referOrderNo;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeBankName() {
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName) {
		this.payeeBankName = payeeBankName;
	}
	public String getPayeeBankAccount() {
		return payeeBankAccount;
	}
	public void setPayeeBankAccount(String payeeBankAccount) {
		this.payeeBankAccount = payeeBankAccount;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public List<PaymentCountItem> getPaymentCountItemList() {
		return paymentCountItemList;
	}
	public void setPaymentCountItemList(List<PaymentCountItem> paymentCountItemList) {
		this.paymentCountItemList = paymentCountItemList;
	}
	public String getOpCompanyEnName() {
		return opCompanyEnName;
	}
	public void setOpCompanyEnName(String opCompanyEnName) {
		this.opCompanyEnName = opCompanyEnName;
	}
	  
	  
}
