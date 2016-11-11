package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 职位表
 */
@Entity
@Table(name = "position")
public class Position extends BaseBean {
	
	private static final long serialVersionUID = 901709805599016216L;
	/**
	 * 职位编码  10开头,代表总经理。1001副总 100101财务总监 100101财务经理。每两位代表一个层级
	 * 100102客服经理 
	 */
	@Column(name = "POSITION_CODE", unique = true, nullable = false, insertable = true, updatable = true)
	private String positionCode;

	/**
	 * 职位名称 
	 */
	@Column(name = "POSISTION_NAME", nullable = false, insertable = true, updatable = true)
	private String positionName;

	/**
	 * 职位级别 1,2,3,4
	 */
	@Column(name = "POSISTION_LEVEL", nullable = false, insertable = true, updatable = true)
	private Integer positionLevel;
	
	/**
	 * 职位排序，显示排序用
	 */
	@Column(name = "POSITION_INDEX", nullable = true, insertable = true, updatable = true)
	private Integer positionIndex;

	/**
	 * 职位是否启用（0：启用， 1：不启用）
	 */
	@Column(name = "POSITION_STATUS", nullable = false, insertable = true, updatable = true)
	private Integer positionStatus;
	
	@Transient
	private String parentPositionCode;

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Integer getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(Integer positionLevel) {
		this.positionLevel = positionLevel;
	}

	public Integer getPositionIndex() {
		return positionIndex;
	}

	public void setPositionIndex(Integer positionIndex) {
		this.positionIndex = positionIndex;
	}


	public Integer getPositionStatus() {
		return positionStatus;
	}

	public void setPositionStatus(Integer positionStatus) {
		this.positionStatus = positionStatus;
	}

	public String getParentPositionCode() {
		return parentPositionCode;
	}

	public void setParentPositionCode(String parentPositionCode) {
		this.parentPositionCode = parentPositionCode;
	}
	
	
}