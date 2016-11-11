package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 *  公司关联地址表， 主要是用在收货公司地址，其他公司类型暂时用不到
 * @author li
 */
@Entity
@Table(name = "address")
public class Address extends BaseBean{		
	
	@Column(name="COMPANY_ID",nullable=true)
	private Integer companyId;//公司id	
	
	@Column(name="ADDRESS",nullable=true)
	private String address;//英文名称
	
	@Column(name="LINKMAN",nullable=true)
	private String linkMan;//联系人
	
	@Column(name="TEL",nullable=true)
	private String tel;//联系电话
	
	@Transient
	private Integer flag=1;// 1 新增或修改  3 删除
	

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
		
}
