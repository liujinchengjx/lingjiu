package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "group_customer")
public class GroupCustomer extends BaseBean{
	
	@Column(name="GROUP_ID",nullable=false)	
	private Integer groupId;//群ID
	
	@Column(name="CUSTOMER_COMPANY_ID",nullable=false)
	private Integer customerCompanyId;// 客户公司ID
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getCustomerCompanyId() {
		return customerCompanyId;
	}
	public void setCustomerCompanyId(Integer customerCompanyId) {
		this.customerCompanyId = customerCompanyId;
	}
	
	
}
