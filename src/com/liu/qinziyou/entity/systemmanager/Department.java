package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "um_department")
public class Department extends BaseBean{
	/**
	 * 部门
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="DEPARTMENT_NAME",nullable=false)	
	private String departmentName;//部门名称
	
	@Column(name="ENTERPRISE_ID",nullable=true)
	private Integer enterpriseId;//单位id
	
	@Column(name="LEADER",nullable=false)
	private String leader;//负责人
	
	@Column(name="TEL",nullable=true)
	private String tel;//电话	

	@Column(name="EMAIL",nullable=true)
	private String email;//邮箱	
	
	@Column(name="PINYIN",nullable=true)
	private String pinyin;//邮箱	
	
	@Column(name="SEQUENCER",nullable=true)
	private Integer sequencer;//次序
	
	@Column(name="IS_DELETE",nullable=true)
	private Integer isDelete;//是否删除
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	@Column(name="USER_NUMBER",nullable=true)
	private Integer userNumber;//部门人数	
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public Integer getEnterpriseId() {
		return enterpriseId;
	}


	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	public String getLeader() {
		return leader;
	}


	public void setLeader(String leader) {
		this.leader = leader;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPinyin() {
		return pinyin;
	}


	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}


	public Integer getSequencer() {
		return sequencer;
	}


	public void setSequencer(Integer sequencer) {
		this.sequencer = sequencer;
	}


	public Integer getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}


	public Integer getUserNumber() {
		return userNumber;
	}

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}


	public Integer getFlag() {
		return flag;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}	
	
}
