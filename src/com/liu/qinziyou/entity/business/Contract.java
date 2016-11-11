package com.liu.qinziyou.entity.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
/**
 *  合同协议 
 * @author li
 */
@Entity
@Table(name = "contract")
public class Contract extends BaseBean {
	/**
	 * 合同协议
	 */
	private static final long serialVersionUID = 8816618731704602328L;

	@Column(name = "CTCODE", nullable = true)
	private String ctCode;// 档案编号

	@Column(name="BUYER_ID",nullable=false)
	Integer buyerId;//关联companyID  需方 一般是协议的甲方	

	@Column(name = "BUSINESSTYPE", nullable = true)
	private Integer businessType;// 协议类型
		
	@Column(name = "BUYER_SIGNDTE", nullable = true)
	private Date buyerSigndte;// 甲签约日期
	
	@Column(name = "BUYER_SIGNNAME", nullable = true)
	private String buyerSignname;// 甲方签约代表
	
	@Column(name="SELLER_ID",nullable=false)
	private Integer sellerId;//关联companyID  供方 一般是协议的乙方
	
	@Column(name = "SELLER_SIGNDTE", nullable = true)
	private Date sellerSignDte;// 乙方签约日期
	
	@Column(name = "SELLER_SIGNNAME", nullable = true)
	private String sellerSignName;// 乙方签约代表	
	
	@Column(name = "VALIDDATE", nullable = true)
	private Date validDate;// 合同的有效日期	
	
	@Column(name = "CONTENT", nullable = true)
	private String content;// 合同协议内容	
	
	@Column(name = "STATUS", nullable = true)
	private Integer status;// 未审核，已审核
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效
	
	@Column(name = "REMARK", nullable = true)
	private String remark;// 备注
	
	@Transient
	private String buyerName; //甲方
	
	@Transient
	private String sellerName; //乙方		

	public String getCtCode() {
		return ctCode;
	}

	public void setCtCode(String ctCode) {
		this.ctCode = ctCode;
	}

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

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getBuyerSignname() {
		return buyerSignname;
	}

	public void setBuyerSignname(String buyerSignname) {
		this.buyerSignname = buyerSignname;
	}
	

	public String getSellerSignName() {
		return sellerSignName;
	}

	public void setSellerSignName(String sellerSignName) {
		this.sellerSignName = sellerSignName;
	}

	

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public Date getBuyerSigndte() {
		return buyerSigndte;
	}

	public void setBuyerSigndte(Date buyerSigndte) {
		this.buyerSigndte = buyerSigndte;
	}

	public Date getSellerSignDte() {
		return sellerSignDte;
	}

	public void setSellerSignDte(Date sellerSignDte) {
		this.sellerSignDte = sellerSignDte;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	

	
}
