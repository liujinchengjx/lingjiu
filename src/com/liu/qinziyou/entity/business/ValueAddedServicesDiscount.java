package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 *@author minge 增值服务客户折扣表
 */
@Entity
@Table(name = "value_added_services_discount")
public class ValueAddedServicesDiscount extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6554657628879241769L;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private Integer customerId;// 客户ID
	
	@Column(name = "SERVICE_CODE", nullable = false)
	private String serviceCode;// 增值服务ID

	@Column(name = "CURRENCY", nullable = false)
	private String currency;// 币种

	@Column(name = "DISCOUNT", nullable = true)
	private Double discount;
	@Transient
	private String mode;// add 表示添加 del 表示删除 update 表示更新
	
	//增值服务名称
	@Transient
	private String serviceName;
	
	
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}


}
