package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "framework_systems")
public class FrameworkSystems extends BaseBean{
	/**
	 * 整个系统
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="SN",nullable=false,length=100)	
	private String sn;//系统序列号
	
	@Column(name="NAME",nullable=true)
	private String name;//系统名称
	
	@Column(name="ICON",nullable=false ,length=100)
	private String icon;//图标
	
	@Column(name="DESCRIPTION",nullable=true,length=200)
	private String description;//模块描述	

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
