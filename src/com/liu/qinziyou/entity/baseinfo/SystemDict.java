package com.liu.qinziyou.entity.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;
@Entity
@Table(name = "system_dict")
public class SystemDict extends BaseBean{
	/**
	 * 字典
	 */
	@Column(name="TYPE",nullable=false)	
	private String type;//类型
	
	@Column(name="TYPE_NAME",nullable=false)	
	private String typeName;//类型名字	
	
	@Column(name="DICT_CODE",nullable=false)
	private String dictCode; //参数代码
	
	@Column(name="DICT_NAME",nullable=false)
	private String dictName; //参数名称
	
	@Column(name="DICT_ENAME",nullable=true)
	private String dictEName;//参数名称(英文)
	
	@Column(name="REF_KEY_VALUE",nullable=true)
	private String refKeyValue;//引用外键值
	
	@Column(name="SEQUENCE",nullable=true)
	private Integer sequence;//排序用	
	
	@Column(name="FLAG",nullable=false)
	private Integer flag;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getDictEName() {
		return dictEName;
	}

	public void setDictEName(String dictEName) {
		this.dictEName = dictEName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getRefKeyValue() {
		return refKeyValue;
	}

	public void setRefKeyValue(String refKeyValue) {
		this.refKeyValue = refKeyValue;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
