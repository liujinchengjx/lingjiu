package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

/**
 *@author minge 增值服务币种收费表。新增时同时生成对应收费费用
 */
@Entity
@Table(name = "value_added_services_param")
public class ValueAddedServicesParam extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6554657628879241769L;

	@Column(name = "SERVICE_ID", nullable = false)
	private Integer serviceId;// 增值服务ID

	@Column(name = "CURRENCY", nullable = false)
	private String currency;// 币种

	@Column(name = "AMT", nullable = true)
	private Double amt;

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
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



}
