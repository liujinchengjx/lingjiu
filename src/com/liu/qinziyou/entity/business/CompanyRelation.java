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
@Table(name = "company_relation")
public class CompanyRelation extends BaseBean{
	
	@Column(name="BUYER_ID",nullable=false)
	Integer buyerId;//关联companyID  需方
	
	@Column(name="SELLER_ID",nullable=false)
	Integer sellerId;//关联companyID  供方
	
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	
	
	
}
