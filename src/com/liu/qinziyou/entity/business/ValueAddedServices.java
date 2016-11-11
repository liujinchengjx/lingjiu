package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
/**
 *@author jincheng
 *增值服务定义表。新增时同时生成对应收费费用
 */
@Entity
@Table(name = "value_added_services")
public class ValueAddedServices  extends BaseBean{
	
	@Column(name="SERVICE_NAME", nullable = false)
	private String serviceName;//增值服务名称
	
	@Column(name="FEE_UNIT", nullable = false)
	private String feeUnit;//增值服务收费单位
	/**
	 增值服务工作单类型 
	 1 仓储租赁及库存管理
	 2 提货、送货 
	 3 杂费（隧道费，停车费，转单费，入场费 等）
	 4 增值服务
	 */
	@Column(name="TYPE", nullable = true)
	private Integer type;
	
	@Column(name="CHARGE_VARIABLES_ID", nullable = false)
	private Integer chargesVariablesId;//对应的收费代码 关联charges_variables的id。	
	
	@Column(name="IF_SHOW", nullable = true)
	private Integer ifShow;// 是否显示 这个主要是用在页面上显示用 0 显示 1 不显示

	@Column(name="SHOW_FEE", nullable = true)
	private String showFee;// 判断是否显示应收费用
	
	@Transient
	private ChargesVariables chargesVariables;
	
	@Transient
	private String coinValues;
	
	

	public String getShowFee() {
		return showFee;
	}

	public void setShowFee(String showFee) {
		this.showFee = showFee;
	}

	public String getCoinValues() {
		return coinValues;
	}

	public void setCoinValues(String coinValues) {
		this.coinValues = coinValues;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getFeeUnit() {
		return feeUnit;
	}

	public void setFeeUnit(String feeUnit) {
		this.feeUnit = feeUnit;
	}

	public Integer getChargesVariablesId() {
		return chargesVariablesId;
	}

	public void setChargesVariablesId(Integer chargesVariablesId) {
		this.chargesVariablesId = chargesVariablesId;
	}

	public ChargesVariables getChargesVariables() {
		return chargesVariables;
	}

	public void setChargesVariables(ChargesVariables chargesVariables) {
		this.chargesVariables = chargesVariables;
	}

	public Integer getIfShow() {
		return ifShow;
	}

	public void setIfShow(Integer ifShow) {
		this.ifShow = ifShow;
	}
	
	
}
