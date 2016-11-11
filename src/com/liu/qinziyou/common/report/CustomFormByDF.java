package com.liu.qinziyou.common.report;

import javax.persistence.Column;

/**
 * 这个导出报关单的一些参数数据用
 * @author Administrator
 *
 */
public class CustomFormByDF {
	private String cfOrderNo;    //子单号
	private String coOrderNo;    //原合同号
	private String orgCartno;    //客户箱号
	private String customOrderNo;//报关单号 
	private String pn;           //产品型号
	private Double qty;          //数量
	private String currency;     //报关币种
	private Double amt;          //报关金额
	private Double amtRmb;       //人民币货值
	private Double customRate;   //海关汇率
	private Double tariffAmt;    //实际关税
	private Double vatAmt;       //增值税
	private Double price;        //外币单价	
	private String brand;		 //品牌	
	private String productName;  //产品名称	
	private String origin;		 //原产地
	private String codeTs;       //海关税号
	
	public CustomFormByDF(){
	}

	public String getCfOrderNo() {
		return cfOrderNo;
	}

	public void setCfOrderNo(String cfOrderNo) {
		this.cfOrderNo = cfOrderNo;
	}

	public String getCoOrderNo() {
		return coOrderNo;
	}

	public void setCoOrderNo(String coOrderNo) {
		this.coOrderNo = coOrderNo;
	}

	public String getOrgCartno() {
		return orgCartno;
	}

	public void setOrgCartno(String orgCartno) {
		this.orgCartno = orgCartno;
	}

	public String getCustomOrderNo() {
		return customOrderNo;
	}

	public void setCustomOrderNo(String customOrderNo) {
		this.customOrderNo = customOrderNo;
	}
	
	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}	

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmt() {
		return amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	public Double getAmtRmb() {
		return amtRmb;
	}

	public void setAmtRmb(Double amtRmb) {
		this.amtRmb = amtRmb;
	}

	public Double getCustomRate() {
		return customRate;
	}

	public void setCustomRate(Double customRate) {
		this.customRate = customRate;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}
}
