package com.liu.qinziyou.entity.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 原产国
 * @author liujc
 *
 */
@Entity
@Table(name = "origin_country")
public class OriginCountry extends BaseBean{
	@Column(name="CCODE",nullable=false)	
	private String ccode;//海关编码
	
	@Column(name="CSNAME",nullable=false)	
	private String csname;//中文名称
	
	@Column(name="ESNAME",nullable=false)
	private String esname;//英文名称
	
	@Column(name="ALIAS",nullable=true)
	private String alias;//别名
	
	
	@Column(name="DISCOUNT_FLAG",nullable=true)
	private Integer discountFlag;//0 是  1 否   是否最惠国
	
	@Column(name="EPIDEMIC_FLAG",nullable=true)
	private Integer epidemicFlag;//0否   1 是  是否疫区
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;// 0  有效  1 无效
	
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public String getEsname() {
		return esname;
	}
	public void setEsname(String esname) {
		this.esname = esname;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getDiscountFlag() {
		return discountFlag;
	}
	public void setDiscountFlag(Integer discountFlag) {
		this.discountFlag = discountFlag;
	}
	public Integer getEpidemicFlag() {
		return epidemicFlag;
	}
	public void setEpidemicFlag(Integer epidemicFlag) {
		this.epidemicFlag = epidemicFlag;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}	
	
}
