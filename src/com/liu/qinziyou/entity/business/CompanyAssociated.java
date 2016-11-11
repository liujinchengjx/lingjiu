package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 公司之间的关系 供方跟需方式多对一的关系
 * @author liujc
 *
 */
@Entity
@Table(name = "company_associated")
public class CompanyAssociated extends BaseBean{
	
	@Column(name="MAIN_COMPANY_ID",nullable=false)
	Integer mainCompanyId;//主公司
	
	@Column(name="VICE_COMPANY_ID",nullable=false)
	Integer viceCompanyId;//副公司

	public Integer getMainCompanyId() {
		return mainCompanyId;
	}

	public void setMainCompanyId(Integer mainCompanyId) {
		this.mainCompanyId = mainCompanyId;
	}

	public Integer getViceCompanyId() {
		return viceCompanyId;
	}

	public void setViceCompanyId(Integer viceCompanyId) {
		this.viceCompanyId = viceCompanyId;
	}	
}
