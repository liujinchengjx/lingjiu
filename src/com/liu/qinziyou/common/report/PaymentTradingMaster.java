package com.liu.qinziyou.common.report;

import java.sql.Timestamp;
import java.util.List;
/**
 * 付款通知书表头 一般贸易 独用
 * @author Administrator
 *
 */
public class PaymentTradingMaster {
	  private String orderNo = ""; // 订单号码
	  private String cusRefNo = ""; // 客户的参考号码
	  private String dealType;//成交方式
	  private Timestamp settlementDate; //结算日期
	  private Double rate;//结算汇率
	  private Double fare;// 运费
	  private Double premiums;// 保费
	  private Double mixFee;// 杂费
	  private Double fpmFare;// 运保杂总额
	  private String currency;//运保杂 币种
	  
	  private Double totalQty;//总数量
	  private Double totalGoodsAmt;//进口总货值（非本币金额）
	  private Double totalGoodsRmbAmt;//进口总RMB货值（本币金额）
	  
	  private Double totalServiceAmt; //总服务费用RMB
	  private Double totalTariffAmt; //总关税RMB
	  private Double totalVatAmt;//总增值税RMB
	  
	  private Double totalDeliveryAmt;//总配送费用RMB
	  private Double totalInspectionAmt;//总商检费用RMB
	  private Double totalDisinfectionAmt;//总消毒费用RMB
	  
	  private Double totalDDIAmt;//商检/消毒、配送总费用
	  
	  private Double totalWorkAmt;//工作单产生的费用 放这里 叫杂费
	  
	  private Double totalAmt;//总应来款
	  
	  private String opCompanyName;//经营公司
	  private String opCompanyEnName;//经营公司
	  private String opCompanyAddress = ""; //经营公司 地址
	  private String opCompanyTel = ""; //经营公司 电话
	  private String opCompanyFax = ""; //经营公司传真
	  
	  private String customerName;//客户公司
	  private String customerAddress = ""; //客户公司地址
	  private String customerTel = ""; //客户公司电话
	  private String customerFax = ""; //客户公司传真  
	  private Integer optype;// 抬头模式
	  
	  private String payeeName;//收款方
	  private String payeeBankName = ""; //收款银行
	  private String payeeBankAccount = ""; //收款银行账户
	  
	  private String oper;//制单人
	  
	  private String auditMan;//审核人
	  
	  private String remark;//备注
	  
	  
	  
	  private List<ProductItem> productItemList;
	  public PaymentTradingMaster(){
	  }
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCusRefNo() {
		return cusRefNo;
	}
	public void setCusRefNo(String cusRefNo) {
		this.cusRefNo = cusRefNo;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	
	public Timestamp getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(Timestamp settlementDate) {
		this.settlementDate = settlementDate;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Double getPremiums() {
		return premiums;
	}
	public void setPremiums(Double premiums) {
		this.premiums = premiums;
	}
	public Double getMixFee() {
		return mixFee;
	}
	public void setMixFee(Double mixFee) {
		this.mixFee = mixFee;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getTotalGoodsAmt() {
		return totalGoodsAmt;
	}
	public void setTotalGoodsAmt(Double totalGoodsAmt) {
		this.totalGoodsAmt = totalGoodsAmt;
	}
	public Double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(Double totalQty) {
		this.totalQty = totalQty;
	}
	
	public Double getTotalGoodsRmbAmt() {
		return totalGoodsRmbAmt;
	}
	public void setTotalGoodsRmbAmt(Double totalGoodsRmbAmt) {
		this.totalGoodsRmbAmt = totalGoodsRmbAmt;
	}
	public Double getTotalServiceAmt() {
		return totalServiceAmt;
	}
	public void setTotalServiceAmt(Double totalServiceAmt) {
		this.totalServiceAmt = totalServiceAmt;
	}
	public Double getTotalTariffAmt() {
		return totalTariffAmt;
	}
	public void setTotalTariffAmt(Double totalTariffAmt) {
		this.totalTariffAmt = totalTariffAmt;
	}
	public Double getTotalVatAmt() {
		return totalVatAmt;
	}
	public void setTotalVatAmt(Double totalVatAmt) {
		this.totalVatAmt = totalVatAmt;
	}

	public Double getTotalDeliveryAmt() {
		return totalDeliveryAmt;
	}
	public void setTotalDeliveryAmt(Double totalDeliveryAmt) {
		this.totalDeliveryAmt = totalDeliveryAmt;
	}
	public Double getTotalInspectionAmt() {
		return totalInspectionAmt;
	}
	public void setTotalInspectionAmt(Double totalInspectionAmt) {
		this.totalInspectionAmt = totalInspectionAmt;
	}
	public Double getTotalDisinfectionAmt() {
		return totalDisinfectionAmt;
	}
	public void setTotalDisinfectionAmt(Double totalDisinfectionAmt) {
		this.totalDisinfectionAmt = totalDisinfectionAmt;
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
	public Integer getOptype() {
		return optype;
	}
	public void setOptype(Integer optype) {
		this.optype = optype;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ProductItem> getProductItemList() {
		return productItemList;
	}
	public void setProductItemList(List<ProductItem> productItemList) {
		this.productItemList = productItemList;
	}
	public String getOpCompanyEnName() {
		return opCompanyEnName;
	}
	public void setOpCompanyEnName(String opCompanyEnName) {
		this.opCompanyEnName = opCompanyEnName;
	}
	public Double getFpmFare() {
		return fpmFare;
	}
	public void setFpmFare(Double fpmFare) {
		this.fpmFare = fpmFare;
	}
	public Double getTotalDDIAmt() {
		return totalDDIAmt;
	}
	public void setTotalDDIAmt(Double totalDDIAmt) {
		this.totalDDIAmt = totalDDIAmt;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
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
	public Double getTotalWorkAmt() {
		return totalWorkAmt;
	}
	public void setTotalWorkAmt(Double totalWorkAmt) {
		this.totalWorkAmt = totalWorkAmt;
	}
	public String getAuditMan() {
		return auditMan;
	}
	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}
	
}
