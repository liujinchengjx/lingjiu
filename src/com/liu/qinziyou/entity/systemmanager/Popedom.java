package com.liu.qinziyou.entity.systemmanager;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "um_popedom")
public class Popedom extends BaseBean{
	/**
	 * 权限表
	 * <!-- popedom定义规则：系统（FrameworkSystems 默认是01）权限两位+子系统（FrameworkSubSystems）权限两位+
	 *  模块权限(FrameworkModules)两位 +按钮功能权限两位（数字不足两位，前面补零） 总共8位数
	 *  
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="POPEDOM",nullable=false)	
	private Integer popedom;//权限值
	
	@Column(name="POPEDOM_NAME",nullable=true)
	private String popedomName;//权限名
	
	@Column(name="DESCRIPTION",nullable=true)
	private String description;//描述
	
	@Column(name="POPEDOM_GROUP",nullable=true)
	private Integer popedomGroup;//权限组
	
	@Column(name="MODULE_GROUP",nullable=true)
	private Integer moduleGroup;//模块组
	
	
	@Column(name="SYSTEM_ID",nullable=true)
	private Integer systemId;//id 默认1
	
//	@Formula("(SELECT DISTINCT A.DESCRIPTION FROM FRAMEWORK_SUBSYSTEMS A WHERE A.POPEDOM=POPEDOM_GROUP)")
	@Transient
	private String menuOne;//一级菜单
	
//	@Formula("(SELECT DISTINCT A.DESCRIPTION FROM FRAMEWORK_MODULES A WHERE A.POPEDOM=MODULE_GROUP)")
	@Transient
	private String menuTwo;//二级菜单


	public Integer getPopedom() {
		return popedom;
	}


	public void setPopedom(Integer popedom) {
		this.popedom = popedom;
	}


	public String getPopedomName() {
		return popedomName;
	}


	public void setPopedomName(String popedomName) {
		this.popedomName = popedomName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getPopedomGroup() {
		return popedomGroup;
	}


	public void setPopedomGroup(Integer popedomGroup) {
		this.popedomGroup = popedomGroup;
	}


	public Integer getSystemId() {
		return systemId;
	}


	public void setSystemId(Integer systemId) {
		this.systemId = systemId;
	}


	public Integer getModuleGroup() {
		return moduleGroup;
	}


	public void setModuleGroup(Integer moduleGroup) {
		this.moduleGroup = moduleGroup;
	}


	public String getMenuOne() {
		return menuOne;
	}


	public void setMenuOne(String menuOne) {
		this.menuOne = menuOne;
	}


	public String getMenuTwo() {
		return menuTwo;
	}


	public void setMenuTwo(String menuTwo) {
		this.menuTwo = menuTwo;
	}		
}
