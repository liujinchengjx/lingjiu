package com.liu.qinziyou.entity.business;

import com.liu.qinziyou.entity.BaseBean;
/**
 *  商品编码和ITEM表的VIEW，用来传值 
 * @author liujc
 */
public class ProductTaxCodeItem extends BaseBean{	
	private static final long serialVersionUID = 8816618731704602328L;
		
	private String productName;//商品描述	
	
	private String codeTs;//海关编码 10位
	
	private String codet;//海关编码，8位
	
	private String codes;//附加编码  2位
	
	private String criterionDeclare;//海关申报 要素 提示信息	
	
	private String unit1;//第一单位
	
	private String unit2;//第二单位
	
	private String notes;//注明信息	
	
	private String controlMa;//监管条件 海关监管条件
	
	private String quarantineMa;//检疫类的  监管条件 国检监管条件
	
	private String tarifMark;//	
	
	private String highRate;//最高税率
	
	private String lowRate;//最低税率  关税率 这个是最常取的  关税率
	
	private String commRate;//普通税率  关税率
	
	private String expReTaxRate;//出口退税率
	
	private String addRate;//增值税率
	
	private String consumeRate;//消费税
	
	private String countryTax;//国别税
	
	private String flag;////0 有效  1 无效	
	
	private String remark;//备注
	
	private String provisionalTax;//暂定税率
	
	private String declareElements;//海关申报 要素
	
	private String unit1Name;//单位1
	
	private String unit2Name;//单位2

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getCodet() {
		return codet;
	}

	public void setCodet(String codet) {
		this.codet = codet;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}
	
	public String getCriterionDeclare() {
		return criterionDeclare;
	}

	public void setCriterionDeclare(String criterionDeclare) {
		this.criterionDeclare = criterionDeclare;
	}

	public String getUnit1() {
		return unit1;
	}

	public void setUnit1(String unit1) {
		this.unit1 = unit1;
	}

	public String getUnit2() {
		return unit2;
	}

	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getControlMa() {
		return controlMa;
	}

	public void setControlMa(String controlMa) {
		this.controlMa = controlMa;
	}

	public String getTarifMark() {
		return tarifMark;
	}

	public void setTarifMark(String tarifMark) {
		this.tarifMark = tarifMark;
	}


	
	public String getHighRate() {
		return highRate;
	}

	public void setHighRate(String highRate) {
		this.highRate = highRate;
	}

	public String getLowRate() {
		return lowRate;
	}

	public void setLowRate(String lowRate) {
		this.lowRate = lowRate;
	}

	public String getCommRate() {
		return commRate;
	}

	public void setCommRate(String commRate) {
		this.commRate = commRate;
	}

	public String getExpReTaxRate() {
		return expReTaxRate;
	}

	public void setExpReTaxRate(String expReTaxRate) {
		this.expReTaxRate = expReTaxRate;
	}

	public String getAddRate() {
		return addRate;
	}

	public void setAddRate(String addRate) {
		this.addRate = addRate;
	}

	public String getConsumeRate() {
		return consumeRate;
	}

	public void setConsumeRate(String consumeRate) {
		this.consumeRate = consumeRate;
	}

	public String getCountryTax() {
		return countryTax;
	}

	public void setCountryTax(String countryTax) {
		this.countryTax = countryTax;
	}

	public String getDeclareElements() {
		return declareElements;
	}

	public void setDeclareElements(String declareElements) {
		this.declareElements = declareElements;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getQuarantineMa() {
		return quarantineMa;
	}

	public void setQuarantineMa(String quarantineMa) {
		this.quarantineMa = quarantineMa;
	}

	public String getUnit1Name() {
		return unit1Name;
	}

	public void setUnit1Name(String unit1Name) {
		this.unit1Name = unit1Name;
	}

	public String getUnit2Name() {
		return unit2Name;
	}

	public void setUnit2Name(String unit2Name) {
		this.unit2Name = unit2Name;
	}

	public String getProvisionalTax() {
		return provisionalTax;
	}

	public void setProvisionalTax(String provisionalTax) {
		this.provisionalTax = provisionalTax;
	}
	
	
}
