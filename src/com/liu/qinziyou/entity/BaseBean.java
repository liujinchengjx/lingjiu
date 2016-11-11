package com.liu.qinziyou.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseBean implements IBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="ID",insertable=false)
	private Integer id; //唯一标示
	
	@Column(name="CREATE_BY",nullable=true,length=30)
	private String createBy;//创建人
	
	@Column(name="CREATE_TIME",nullable=true)
	private java.sql.Timestamp  createTime;//创建时间
	
	@Column(name="LAST_MODIFY_BY",nullable=true,length=30)
	private String lastModifyBy;//最后修改人taxid
	
	@Column(name="LAST_MODIFY_TIME",nullable=true) 
	private java.sql.Timestamp lastModifyTime;//最后修改时间	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setLastModifyTime(java.sql.Timestamp lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getLastModifyBy() {
		return lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	public java.sql.Timestamp getLastModifyTime() {
		return lastModifyTime;
	}
	
}
