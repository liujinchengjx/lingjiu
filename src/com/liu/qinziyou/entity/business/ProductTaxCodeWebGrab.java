package com.liu.qinziyou.entity.business;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
/**
 *  商品编码表 网页抓取
 * @author jincheng
 */
@Entity
@Table(name = "producttaxcode_web_grab")
public class ProductTaxCodeWebGrab extends BaseBean{	
	private static final long serialVersionUID = 8816618731704602328L;
		
	@Column(name="PRODUCT_NAME",nullable=true)
	private String productName;//商品描述	
	
	@Column(name="CODE_TS",nullable=true)
	private String codeTs;//海关编码 10位
	
	@Column(name="CODE_T",nullable=true)
	private String codet;//海关编码，8位
	
	@Column(name="CODE_S",nullable=true)
	private String codes;//附加编码  2位
	
	@Column(name="CRITERIONDECLARE",nullable=true)
	private String criterionDeclare;//海关申报 要素 提示信息	
	
	@Column(name="UNIT1",nullable=true)
	private String unit1;//第一单位
	
	@Column(name="UNIT2",nullable=true)
	private String unit2;//第二单位
	
	@Column(name="NOTES",nullable=true)
	private String notes;//注明信息	
	
	@Column(name="CONTROL_MA",nullable=true)
	private String controlMa;//监管条件
	
	@Column(name="QUARANTINE_MA",nullable=true)
	private String quarantineMa;//检疫类的  监管条件
	
	@Column(name="TARIFMARK",nullable=true)
	private String tarifMark;//	
	
	@Column(name="HIGH_RATE",nullable=true)
	private Double highRate;//最高税率
	
	@Column(name="LOW_RATE",nullable=true)
	private Double lowRate;//最低税率  关税率 这个是最常取的  关税率
	
	@Column(name="COMM_RATE",nullable=true)
	private Double commRate;//普通税率  关税率
	
	@Column(name="EXPRETAXRATE",nullable=true)
	private Double expReTaxRate;//出口退税率
	
	@Column(name="ADD_RATE",nullable=true)
	private Double addRate;//增值税率
	
	@Column(name="CONSUME_RATE",nullable=true)
	private Double consumeRate;//消费税
	
	@Column(name="COUNTRYTAX",nullable=true)
	private Double countryTax;//国别税
	
	
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	@Column(name="GRAB_TIME",nullable=true)
	private Timestamp grabTime;//抓取时间
	
	@Column(name="GRAB_FLAG",nullable=true)
	private Integer grabFlag; //0失败 1成功
	
	@Transient
	private String unit1Name;//单位1
	
	@Transient
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


	public Double getHighRate() {
		return highRate;
	}

	public void setHighRate(Double highRate) {
		this.highRate = highRate;
	}

	public Double getLowRate() {
		return lowRate;
	}

	public void setLowRate(Double lowRate) {
		this.lowRate = lowRate;
	}

	public Double getCommRate() {
		return commRate;
	}

	public void setCommRate(Double commRate) {
		this.commRate = commRate;
	}

	public Double getExpReTaxRate() {
		return expReTaxRate;
	}

	public void setExpReTaxRate(Double expReTaxRate) {
		this.expReTaxRate = expReTaxRate;
	}

	public Double getAddRate() {
		return addRate;
	}

	public void setAddRate(Double addRate) {
		this.addRate = addRate;
	}

	public Double getConsumeRate() {
		return consumeRate;
	}

	public void setConsumeRate(Double consumeRate) {
		this.consumeRate = consumeRate;
	}

	public Double getCountryTax() {
		return countryTax;
	}

	public void setCountryTax(Double countryTax) {
		this.countryTax = countryTax;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Timestamp getGrabTime() {
		return grabTime;
	}

	public void setGrabTime(Timestamp grabTime) {
		this.grabTime = grabTime;
	}

	public Integer getGrabFlag() {
		return grabFlag;
	}

	public void setGrabFlag(Integer grabFlag) {
		this.grabFlag = grabFlag;
	}
	
	
	
	
}
