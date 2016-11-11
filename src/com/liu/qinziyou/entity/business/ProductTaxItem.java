package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
/**
 *  申报要素
 * @author li
 */
@Entity
@Table(name = "producttaxitem")
public class ProductTaxItem extends BaseBean{	
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="SERIALNO",nullable=true)
	private String serialNo;//流水序号
	
	@Column(name="CNAME",nullable=true)
	private String cname;//中文名称
	
	@Column(name="ENAME",nullable=true)
	private String ename;//英文名称
	
	@Column(name="PN",nullable=true)
	private String pn;//型号	
	
	@Column(name="BRAND",nullable=true)
	private String brand;//品牌
	
	@Column(name="CODE_TS",nullable=true)
	private String codeTs;//海关编码	
	
	@Column(name="ORG",nullable=true)
	private String org;//原产地	
	
	@Column(name="DECLAREELEMENTS",nullable=true)
	private String declareElements;//海关申报 要素	
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注	
	
	@Column(name="USAGES",nullable=true)
	private String usages;//用途	
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效
	//申报要素提示信息
	@Transient //@Formula("(SELECT A.CRITERIONDECLARE FROM producttaxcode A WHERE A.CODE_TS=CODE_TS)")
	private String customsCriterionTips;
	
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPn() {
		return pn;
	}

	public void setPn(String pn) {
		this.pn = pn;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCodeTs() {
		return codeTs;
	}

	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
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

	
	public String getCustomsCriterionTips() {
		return customsCriterionTips;
	}

	public void setCustomsCriterionTips(String customsCriterionTips) {
		this.customsCriterionTips = customsCriterionTips;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getUsages() {
		return usages;
	}

	public void setUsages(String usages) {
		this.usages = usages;
	}
	
}
