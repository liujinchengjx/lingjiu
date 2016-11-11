package com.liu.qinziyou.common.report;

import java.util.List;
/**
 * 预览增值税和关税的表头
 * @author Administrator
 *
 */
public class TariffAndVatMaster {
	  private String customsName = "皇岗海关"; // 海关关口
	  private String signDate = ""; // 填发日期
	  private String orderNo;// 合同编号
	  private String customsNo;// 报关单统一编号
	  private String conveyance;// 运输工具
	  private String blno;// 提运单号
	  private String taxBilltimeLimit;// 出税单时限
	  private String amtUppercase;// 金额大写
	  private String amtLowercase;// 金额小写
	  private String reportingUnitCode;// 申报单位代码 （双抬头 是客户，单抬头是 经营公司自己代码）
	  
	  private String receivablesUnit = "中央金库"; // 收款单位 
	  private String receivablesSubject="1";//收款科目
	  private String receivablesExchequer="中央金库";//收款国库
	  private String receivablesLevel="中央";//收款次级
	  
	  private String paymentUnit;//缴款单位 (一般是经营公司)
	  private String paymentUnitCode;//缴款单位的代码
	  private String paymentUnitAccount;//缴款单位的账户
	  private String paymentUnitBank;//缴款单位的开户行
	  private String paymentUnitBankCode;//缴款单位的开户行的代码
	  private String paymentUnitTaxNo;//缴款单位的税务号
	  
	  private String receivingUnit;//收货公司 一般是指经营公司的客户
	  private String receivingUnitCode;//收货公司代码
	  
	  private String remark;//备注

	  private String year;//年
	  private String month;//月
	  private String day;//日
	  private List<ProductTax> productTaxList;
	  public TariffAndVatMaster(){
	  }
		public String getCustomsName() {
			return customsName;
		}
		public void setCustomsName(String customsName) {
			this.customsName = customsName;
		}
		public String getSignDate() {
			return signDate;
		}
		public void setSignDate(String signDate) {
			this.signDate = signDate;
		}
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public String getCustomsNo() {
			return customsNo;
		}
		public void setCustomsNo(String customsNo) {
			this.customsNo = customsNo;
		}
		public String getConveyance() {
			return conveyance;
		}
		public void setConveyance(String conveyance) {
			this.conveyance = conveyance;
		}
		public String getBlno() {
			return blno;
		}
		public void setBlno(String blno) {
			this.blno = blno;
		}
		public String getTaxBilltimeLimit() {
			return taxBilltimeLimit;
		}
		public void setTaxBilltimeLimit(String taxBilltimeLimit) {
			this.taxBilltimeLimit = taxBilltimeLimit;
		}
		public String getAmtUppercase() {
			return amtUppercase;
		}
		public void setAmtUppercase(String amtUppercase) {
			this.amtUppercase = amtUppercase;
		}
		public String getAmtLowercase() {
			return amtLowercase;
		}
		public void setAmtLowercase(String amtLowercase) {
			this.amtLowercase = amtLowercase;
		}
		public String getReportingUnitCode() {
			return reportingUnitCode;
		}
		public void setReportingUnitCode(String reportingUnitCode) {
			this.reportingUnitCode = reportingUnitCode;
		}
		public String getReceivablesUnit() {
			return receivablesUnit;
		}
		public void setReceivablesUnit(String receivablesUnit) {
			this.receivablesUnit = receivablesUnit;
		}
		public String getReceivablesSubject() {
			return receivablesSubject;
		}
		public void setReceivablesSubject(String receivablesSubject) {
			this.receivablesSubject = receivablesSubject;
		}
		public String getReceivablesExchequer() {
			return receivablesExchequer;
		}
		public void setReceivablesExchequer(String receivablesExchequer) {
			this.receivablesExchequer = receivablesExchequer;
		}
		public String getReceivablesLevel() {
			return receivablesLevel;
		}
		public void setReceivablesLevel(String receivablesLevel) {
			this.receivablesLevel = receivablesLevel;
		}
		public String getPaymentUnit() {
			return paymentUnit;
		}
		public void setPaymentUnit(String paymentUnit) {
			this.paymentUnit = paymentUnit;
		}
		public String getPaymentUnitCode() {
			return paymentUnitCode;
		}
		public void setPaymentUnitCode(String paymentUnitCode) {
			this.paymentUnitCode = paymentUnitCode;
		}
		public String getPaymentUnitAccount() {
			return paymentUnitAccount;
		}
		public void setPaymentUnitAccount(String paymentUnitAccount) {
			this.paymentUnitAccount = paymentUnitAccount;
		}
		public String getPaymentUnitBank() {
			return paymentUnitBank;
		}
		public void setPaymentUnitBank(String paymentUnitBank) {
			this.paymentUnitBank = paymentUnitBank;
		}
		public String getPaymentUnitBankCode() {
			return paymentUnitBankCode;
		}
		public void setPaymentUnitBankCode(String paymentUnitBankCode) {
			this.paymentUnitBankCode = paymentUnitBankCode;
		}
		public String getPaymentUnitTaxNo() {
			return paymentUnitTaxNo;
		}
		public void setPaymentUnitTaxNo(String paymentUnitTaxNo) {
			this.paymentUnitTaxNo = paymentUnitTaxNo;
		}
		public String getReceivingUnit() {
			return receivingUnit;
		}
		public void setReceivingUnit(String receivingUnit) {
			this.receivingUnit = receivingUnit;
		}
		public String getReceivingUnitCode() {
			return receivingUnitCode;
		}
		public void setReceivingUnitCode(String receivingUnitCode) {
			this.receivingUnitCode = receivingUnitCode;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public List<ProductTax> getProductTaxList() {
			return productTaxList;
		}
		public void setProductTaxList(List<ProductTax> productTaxList) {
			this.productTaxList = productTaxList;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getDay() {
			return day;
		}
		public void setDay(String day) {
			this.day = day;
		}

		
}
