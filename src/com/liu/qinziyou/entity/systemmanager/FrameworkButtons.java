package com.liu.qinziyou.entity.systemmanager;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "framework_buttons")
public class FrameworkButtons extends BaseBean{
	/**
	 *  功能动作按钮
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="NAME",nullable=true)	
	private String name;//java端的请求地址
	
	@Column(name="POPEDOM",nullable=true)
	private Integer popedom;//权限编号
	
	@Column(name="TEXT",nullable=true)	
	private String text;//按钮名字
	
	@Column(name="ICON",nullable=true)
	private String icon;//图标
	
	@Column(name="TOOLTIP",nullable=true)
	private String toolTip;//按钮提示信息
	
	@Column(name="MODULES",nullable=true)
	private String modules;//模块名	
	
	@Column(name="SEQUENCE",nullable=true)
	private Integer sequence;//模块次序
	
	@Column(name="HANDLER",nullable=true)
	private String hander;//动作，方法名
	
	@Column(name="IN_TOOLBAR",nullable=false)
	private Integer inToolbar;//工具栏 这个表示，一个页面可能有几个功能按钮栏，1 表示第一个工具栏，2 以此类推
	
	
	@Column(name="SYSTEM_ID",nullable=true)
	private Integer systemId;//系统id
	
	@Transient
	private String menu;//
    //@Formula("(SELECT CONCAT(B.DESCRIPTION,'---',A.DESCRIPTION) FROM FRAMEWORK_MODULES A,FRAMEWORK_SUBSYSTEMS B WHERE A.SUBSYSTEM=B.NAME AND A.NAME=MODULES)")
	
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
	
	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getHander() {
		return hander;
	}

	public void setHander(String hander) {
		this.hander = hander;
	}

	public Integer getSystemId() {
		return systemId;
	}

	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Integer getInMenu() {
		return inMenu;
	}

	public void setInMenu(Integer inMenu) {
		this.inMenu = inMenu;
	}

	public Integer getInToolbar() {
		return inToolbar;
	}

	public void setInToolbar(Integer inToolbar) {
		this.inToolbar = inToolbar;
	}
	
}
