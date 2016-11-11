package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "group")
public class Group  extends BaseBean{
	
	@Column(name="GROUP_NAME",nullable=false)	
	private String groupName;//群名称
	
	@Column(name="CUSTOMER_COUNT",nullable=false)	
	private Integer customerCount;//客户数，这个群里有多个个客户
	
	@Column(name="USER_COUNT",nullable=false)	//用户数，这个群里有多少个用户
	private Integer userCount;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
	
}
