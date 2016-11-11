package com.liu.qinziyou.common.report;

import java.sql.Timestamp;
import java.util.List;

/**
 * 付款通知书表头 委托单/发货单/仓租费 通用
 * 
 * @author Administrator
 * 
 */
public class PaymentCommonMaster {
	private String referOrderNo;// 参考号码

	private String customerName;// 客户公司
	private String customerAddress = ""; // 客户公司地址
	private String customerTel = ""; // 客户公司电话
	private String customerFax = ""; // 客户公司传真

	private String opCompanyName;// 经营公司
	private String opCompanyEnName;// 经营公司
	private String opCompanyAddress = ""; // 经营公司 地址
	private String opCompanyTel = ""; // 经营公司 电话
	private String opCompanyFax = ""; // 经营公司传真

	private String payeeName;// 收款方
	private String payeeBankName = ""; // 收款银行
	private String payeeBankAccount = ""; // 收款银行账户

	private Timestamp settlementDate;// 结算日期
	private String sDate;
	private String currency;// 币种 默认是 港币(应收币种)
	// 应付币种
	private String payCurrency;
	private Double rate;// 汇率
	private Double totalArAmt;// 总金额
	private Double totalLocAmt;// 总金额

	private String opUserName;// 操作人

	private List<PaymentCommonItem> paymentCommonItemList;

	// 制单人
	private String createName;
	// 结算人员
	private String audiName;

	// 结算开始日期
	private String bDate;
	// 结算结束日期
	private String eDate;

	// 订单信息列表
	private List<MonthPaymentSettlemt> mpstList;
	
	// 出入仓费
	private String totalStockAmt;//本币
	private String totalStockLAmt;//结算
	// 代垫费
	private String totalMatFeeAmt;
	private String totalMatLFeeAmt;
	// 增值服务费
	private String totalAddSerAmt;
	private String totalAddLSerAmt;
	// 仓储费
	private String totalWhAmt;
	private String totalLWhAmt;
	// 清关费
	private String totalCuClearAmt;
	private String totalCuLClearAmt;
	
	//结算主体代码-001表示私户、002表示鼎丰、003表示广通
	private String payCode;
	

	public String getTotalStockLAmt() {
		return totalStockLAmt;
	}

	public void setTotalStockLAmt(String totalStockLAmt) {
		this.totalStockLAmt = totalStockLAmt;
	}

	public String getTotalMatLFeeAmt() {
		return totalMatLFeeAmt;
	}

	public void setTotalMatLFeeAmt(String totalMatLFeeAmt) {
		this.totalMatLFeeAmt = totalMatLFeeAmt;
	}

	public String getTotalAddLSerAmt() {
		return totalAddLSerAmt;
	}

	public void setTotalAddLSerAmt(String totalAddLSerAmt) {
		this.totalAddLSerAmt = totalAddLSerAmt;
	}

	public String getTotalLWhAmt() {
		return totalLWhAmt;
	}

	public void setTotalLWhAmt(String totalLWhAmt) {
		this.totalLWhAmt = totalLWhAmt;
	}

	public String getTotalCuLClearAmt() {
		return totalCuLClearAmt;
	}

	public void setTotalCuLClearAmt(String totalCuLClearAmt) {
		this.totalCuLClearAmt = totalCuLClearAmt;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getTotalStockAmt() {
		return totalStockAmt;
	}

	public void setTotalStockAmt(String totalStockAmt) {
		this.totalStockAmt = totalStockAmt;
	}

	public String getTotalMatFeeAmt() {
		return totalMatFeeAmt;
	}

	public void setTotalMatFeeAmt(String totalMatFeeAmt) {
		this.totalMatFeeAmt = totalMatFeeAmt;
	}

	public String getTotalAddSerAmt() {
		return totalAddSerAmt;
	}

	public void setTotalAddSerAmt(String totalAddSerAmt) {
		this.totalAddSerAmt = totalAddSerAmt;
	}

	public String getTotalWhAmt() {
		return totalWhAmt;
	}

	public void setTotalWhAmt(String totalWhAmt) {
		this.totalWhAmt = totalWhAmt;
	}

	public String getTotalCuClearAmt() {
		return totalCuClearAmt;
	}

	public void setTotalCuClearAmt(String totalCuClearAmt) {
		this.totalCuClearAmt = totalCuClearAmt;
	}

	public String getbDate() {
		return bDate;
	}

	public void setbDate(String bDate) {
		this.bDate = bDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public List<MonthPaymentSettlemt> getMpstList() {
		return mpstList;
	}

	public void setMpstList(List<MonthPaymentSettlemt> mpstList) {
		this.mpstList = mpstList;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getAudiName() {
		return audiName;
	}

	public void setAudiName(String audiName) {
		this.audiName = audiName;
	}

	public PaymentCommonMaster() {
	}

	public String getPayCurrency() {
		return payCurrency;
	}

	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
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

	public Double getTotalArAmt() {
		return totalArAmt;
	}

	public void setTotalArAmt(Double totalArAmt) {
		this.totalArAmt = totalArAmt;
	}

	public Double getTotalLocAmt() {
		return totalLocAmt;
	}

	public void setTotalLocAmt(Double totalLocAmt) {
		this.totalLocAmt = totalLocAmt;
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

	public List<PaymentCommonItem> getPaymentCommonItemList() {
		return paymentCommonItemList;
	}

	public void setPaymentCommonItemList(
			List<PaymentCommonItem> paymentCommonItemList) {
		this.paymentCommonItemList = paymentCommonItemList;
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

	public String getOpCompanyEnName() {
		return opCompanyEnName;
	}

	public void setOpCompanyEnName(String opCompanyEnName) {
		this.opCompanyEnName = opCompanyEnName;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

}
