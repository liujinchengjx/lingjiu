package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "um_user_in_role")
public class UserInRole extends BaseBean{
	/**
	 * 用户 角色 中间表
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="USER_ID",nullable=false)	
	private Integer userId;//用户id
	
	@Column(name="ROLE_ID",nullable=true)
	private Integer roleId;//角色Id
	
	@Transient
	private Integer flag=1;// 铺助字段 1 表示 已经存在  2 表示新增 3 要删除数据此记录
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}	
	
}
