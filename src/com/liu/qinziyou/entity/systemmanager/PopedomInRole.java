package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "um_popedom_in_role")
public class PopedomInRole extends BaseBean{
	/**
	 * 权限在角色里 中间表
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="ROLE_ID",nullable=false)	
	private Integer roleId;//角色id
	
	@Column(name="POPEDOM",nullable=true)
	private Integer popedom;//权限值	

	@Column(name="USER_TYPE",nullable=true)
	private Integer userType;//用户类型。0-单位；1-用户；2-角色	
	
	@Column(name="AUDIT_FLAG",nullable=true)
	private Integer auditFlag;//复核标识。0-待复核；1-未通过；2-已通过
	
	@Transient
	private Integer flag=1;// 铺助字段 1 表示 已经存在  2 表示新增 3 要删除数据此记录 
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPopedom() {
		return popedom;
	}

	public void setPopedom(Integer popedom) {
		this.popedom = popedom;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag = auditFlag;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


}
