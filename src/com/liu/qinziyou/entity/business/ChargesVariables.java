package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 费用变量配置 配置一些在计算的时候有变化的变量值
 * @author li
 */
@Entity
@Table(name = "charges_variables")
public class ChargesVariables extends BaseBean{	
	
	@Column(name="BUSINESSTYPE",nullable=true)
	private Integer businessType;//业务类型	
	
	@Column(name="FEE_TYPE",nullable=true)
	private Integer feeType; // 计费种类
	
	@Column(name="OP_TYPE",nullable=true)
	private Integer opType;//操作类型 1 鼎丰单抬头 2 鼎丰双抬头  3 客户单抬头 4 客户双抬头 5 无
	
	@Column(name="VARIABLE_NAME",nullable=true)
	private String variableName;//费用参数变量名
	
	@Column(name="VARIABLE_VALUE",nullable=true)
	private Double variableValue; //费用变量
	
	@Column(name="SCODE",nullable=true)
	private String scode; //代码
	
	@Column(name="DESCRIPTION",nullable=true)
	private String description;//公式使用说明	
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;// 0 有效  1 无效
	
	@Column(name="SEQUENCE",nullable=true)
	private Integer sequence;//排序用	
	
	@Transient
	private String feeTypeName; // 计费种类 名称
	
	@Transient
	private String mode;// add 表示添加 del 表示删除 update 表示更新

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	public Double getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(Double variableValue) {
		this.variableValue = variableValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getOpType() {
		return opType;
	}

	public void setOpType(Integer opType) {
		this.opType = opType;
	}


	public Integer getFeeType() {
		return feeType;
	}

	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
