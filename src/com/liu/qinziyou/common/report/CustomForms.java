package com.liu.qinziyou.common.report;

import java.sql.Timestamp;

/**
 * 这个导出报关单的一些参数数据用
 * 
 * @author Administrator
 * 
 */
public class CustomForms {
	private String cfOrderNo;//
	private String coOrderNo;// 原合同号
	private String customerName; // 客户名称
	private Timestamp customDate;// 报关日期
	private String customOrderNo;// 报关单号
	private Double amt;// 报关金额
	private String currency;// 报关币种
	private Double tariffAmt;// 关税
	private Double vatAmt;// 增值税

	private Double fareAmt;// 运费
	private Double premiumsAmt;// 保费
	private Double mixAmt;// 杂费
	// 操作方式
	private String opType;
	// 成交方式
	private String dealType;
	// 入库单号
	private String stockInNum;
	// 入库单备注
	private String stockInRemark;
	//客户报关单号
	private String concactNumber;
	//特殊标示
	private String specialFlag;
	//结算时间
	private Timestamp settlementDate;
	//refNo
	private String refNo;
	//汇率
	private Double rate;
	//货值
	private Double goodsRmbAmt;
	//服务费
	private Double serviceAmt;
	//消费税
	private Double consumptionTax;
	
	public String getConcactNumber() {
		return concactNumber;
	}

	public void setConcactNumber(String concactNumber) {
		this.concactNumber = concactNumber;
	}

	public String getStockInNum() {
		return stockInNum;
	}

	public void setStockInNum(String stockInNum) {
		this.stockInNum = stockInNum;
	}

	public String getStockInRemark() {
		return stockInRemark;
	}

	public void setStockInRemark(String stockInRemark) {
		this.stockInRemark = stockInRemark;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public CustomForms() {
	}

	public String getCfOrderNo() {
		return cfOrderNo;
	}

	public void setCfOrderNo(String cfOrderNo) {
		this.cfOrderNo = cfOrderNo;
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

	public Double getTariffAmt() {
		return tariffAmt;
	}

	public void setTariffAmt(Double tariffAmt) {
		this.tariffAmt = tariffAmt;
	}

	public Double getVatAmt() {
		return vatAmt;
	}

	public void setVatAmt(Double vatAmt) {
		this.vatAmt = vatAmt;
	}

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCoOrderNo() {
		return coOrderNo;
	}

	public void setCoOrderNo(String coOrderNo) {
		this.coOrderNo = coOrderNo;
	}

	public Double getFareAmt() {
		return fareAmt;
	}

	public void setFareAmt(Double fareAmt) {
		this.fareAmt = fareAmt;
	}

	public Double getPremiumsAmt() {
		return premiumsAmt;
	}

	public void setPremiumsAmt(Double premiumsAmt) {
		this.premiumsAmt = premiumsAmt;
	}

	public Double getMixAmt() {
		return mixAmt;
	}

	public void setMixAmt(Double mixAmt) {
		this.mixAmt = mixAmt;
	}

	public String getSpecialFlag() {
		return specialFlag;
	}

	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}

	public Timestamp getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Timestamp settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	public Double getGoodsRmbAmt() {
		return goodsRmbAmt;
	}

	public void setGoodsRmbAmt(Double goodsRmbAmt) {
		this.goodsRmbAmt = goodsRmbAmt;
	}

	public Double getServiceAmt() {
		return serviceAmt;
	}

	public void setServiceAmt(Double serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public Double getConsumptionTax() {
		return consumptionTax;
	}

	public void setConsumptionTax(Double consumptionTax) {
		this.consumptionTax = consumptionTax;
	}
	
}
