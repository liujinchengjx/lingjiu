package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "group_user")
public class GroupUser extends BaseBean{
	
	@Column(name="GROUP_ID",nullable=false)	
	private Integer groupId;//群ID
	
	@Column(name="USER_ID",nullable=false)	
	private Integer userId;//用户ID
	
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
