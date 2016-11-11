package com.liu.qinziyou.entity.baseinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "area_dict")
public class AreaDict extends BaseBean{
	/**
	 * 地区字典表
	 */
	
	@Column(name="NAME",nullable=false)	
	private String name;//	
	
	@Column(name="LEVEL",nullable=false)	
	private Integer level;//级 1 国家， 2 省级  3 市 4 县 5 	镇 6 村
	
	@Column(name="PARENTID",nullable=false)
	private Integer parentId; //父亲ID
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效
    
	//@Formula("(SELECT A.NAME FROM AREA_DICT A WHERE A.ID=PARENTID)") 
	//private String parentName;//	
	
	

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
	
}
