package com.liu.qinziyou.entity.systemmanager;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "framework_modules")
public class FrameworkModules extends BaseBean{
	/**
	 *  模块表格
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="NAME",nullable=false)	
	private String name;//模块名称
	
	@Column(name="POPEDOM",nullable=true)
	private Integer popedom;//权限编号
	
	@Column(name="URL",nullable=false)	
	private String url;//html的url地址
	
	@Column(name="ICON",nullable=false)
	private String icon;//图标
	
	@Column(name="SUBSYSTEM",nullable=true)
	private String subSystem;//所属子系统
	
	@Column(name="SEQUENCE",nullable=true)
	private Integer sequence;//模块次序
	
	@Column(name="DESCRIPTION",nullable=true)
	private String description;//模块描述
	
	@Column(name="SYSTEM_ID",nullable=true)
	private Integer systemId;//系统id
	
	@Column(name="IN_MENU",nullable=true)
	private Integer inMenu;//是否显示：1、显示；0、不显示	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPopedom() {
		return popedom;
	}

	public void setPopedom(Integer popedom) {
		this.popedom = popedom;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public Integer getInMenu() {
		return inMenu;
	}

	public void setInMenu(Integer inMenu) {
		this.inMenu = inMenu;
	}
}
