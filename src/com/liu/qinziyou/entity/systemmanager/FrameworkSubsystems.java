package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "framework_subsystems")
public class FrameworkSubsystems extends BaseBean{
	/**
	 *  子系统表格
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="NAME",nullable=false,length=100)	
	private String name;//子系统名称
	
	@Column(name="POPEDOM",nullable=true)
	private Integer popedom;//权限编号
	
	@Column(name="HTMLURL",nullable=false,length=100)	
	private String htmlUrl;//html的url地址
	
	@Column(name="ICON",nullable=false ,length=100)
	private String icon;//图标
	
	@Column(name="EXPANDED",nullable=true)
	private Integer expanded;//是否展开
	
	@Column(name="SEQUENCE",nullable=true)
	private Integer sequence;//模块次序
	
	@Column(name="DESCRIPTION",nullable=true,length=200)
	private String description;//模块描述
	
	@Column(name="SYSTEM_ID",nullable=true)
	private Integer systemId;//系统id

	

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

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getExpanded() {
		return expanded;
	}

	public void setExpanded(Integer expanded) {
		this.expanded = expanded;
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
	
}
