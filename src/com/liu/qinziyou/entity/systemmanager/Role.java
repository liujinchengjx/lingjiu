package com.liu.qinziyou.entity.systemmanager;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "um_role")
public class Role extends BaseBean{
	/**
	 * 角色
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="ROLE_NAME",nullable=false)	
	private String roleName;//角色名称
	
	@Column(name="DESCRIPTION",nullable=true)
	private String description;//描述
	
//	@Column(name="IS_FROZE",nullable=true)
//	private Integer isFroze=1;//是否冻结 
//	
//	@Column(name="IS_APPROVED",nullable=true)
//	private Integer isApproved;//是否复核。
//	
//	@Column(name="IS_DELETE",nullable=true)
//	private Integer isDelete;//是否删除。
//	
//	@Column(name="AUDIT_DATE",nullable=true)
//	private Date auditDate;//复核时间
	
	@Column(name="ENTERPRISE_ID",nullable=true)
	private Integer enterpriseId=1;//单位ID,是属于哪个经营公司的，默认是1 鼎丰

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
		
}
