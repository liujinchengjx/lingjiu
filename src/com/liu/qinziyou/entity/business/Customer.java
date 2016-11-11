package com.liu.qinziyou.entity.business;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
@Entity
@Table(name = "customer")
public class Customer extends BaseBean{
	
	private static final long serialVersionUID = 8816618731704602328L;
	
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="COMPANY_ID")
	private Company company;

	@Column(name="REGCAPITAL",nullable=true)
	private String regCapital;//注册资金
	
	@Column(name="REGDATE",nullable=true)
	private Date regDate;//注册日期
	
	@Column(name="OFFICESPACE",nullable=true)
	private String officeSpace;//办公场地
	
	@Column(name="OFFICEAREA",nullable=true)
	private String officeArea;//办公面积

	@Column(name="ISTAXPAYER",nullable=true)
	private Integer isTaxPayer;//是否一般纳税是否一般纳税人 0 是，1 不是
	
	@Column(name="GRADE",nullable=true)
	private String grade;//1 预申报 2 后申报
	
	@Column(name="CREDITGRADE",nullable=true)
	private String creditGrade;//客户信用度等级 1 A级 2 B级 3 C级
	
	@Column(name="CUSTOMERSOURCE",nullable=true)
	private String customerSource;//客户来源
	
	@Column(name="CURRENCY",nullable=true)
	private String currency;//交易币种
	
	@Column(name="CUSTYPE",nullable=true)
	private String cusType;//客户类型
	
	@Column(name="SERVICEFEE",nullable=true)
	private String serviceFee;//服务费及税款支付方式
	
	@Column(name="PAYMENT",nullable=true)
	private String payMent;//贷款支付方式
	
	@Column(name="BUSINESSMAN",nullable=true)
	private String businessMan;//业务负责人
		
	@Column(name="BUSINESSPARTMAN",nullable=true)
	private String businessPartMan;//部门负责人
	
	@Column(name="SERVICEMAN",nullable=true)
	private String serviceMan;//客服负责人

	@Column(name="SERVICEPARTMAN",nullable=true)
	private String servicePartMan;//客服部门负责人
	
	@Column(name="INVOICE_ADDRESS",nullable=true)
	private String invoiceAddress;//发票地址
	
	@Column(name="ATTACHFILEID",nullable=true)
	private String attachfileid;//上传附件ID	
	
	@Column(name="USE_CONTRACTNO",nullable=true)
	private Integer useContractNo=1;//0是  1 否   是否用客户号码报关
	
	@Column(name="BUSINESSTYPE",nullable=true)
	private String businessType;//业务类型 目前只有 companyType 为 2 的公司才有这个属性 为了方便，直接放到这里
	
	@Column(name="OP_TYPE",nullable=true)
	private Integer opType;//合作模式   目前只有 companyType 目前只有 companyType 为 2 的公司才有这个属性 为了方便，直接放到这里
	
	@Column(name="BELONG_TO",nullable=true)
	private Integer belongTo;//客户归属 1 公司客户 2  个人客户
	
	//以下是仓储客客户计算的相关折扣率
	@Column(name="RENT_DISCOUNT_RATE",nullable=true)
	private Double rentDiscountRate=1.0D;//费用折扣率  仓库租赁及库存管理费用折扣率
	
	@Column(name="DN_DISCOUNT_RATE",nullable=true)
	private Double dnDiscountRate=1.0D;//费用折扣率  提货/送货费用折扣率
	
	@Column(name="MIX_DISCOUNT_RATE",nullable=true)
	private Double mixDiscountRate=1.0D;//费用折扣率  杂费（隧道、停车、转单、入场）费用折扣率
	
	@Column(name="ADD_DISCOUNT_RATE",nullable=true)
	private Double addDiscountRate=1.0D;// 增值服务费用折扣率
	
	@Column(name="DISCOUNTRATE_STATUS",nullable=true)
	private Integer discountRateStatus;// 折扣率的审核状态 10 未审核 20 已审核
	
	@Column(name="INSTOCK_FEE",nullable=true)
	private Double instockFee=1.0D;// 入仓费
	
	
	@Column(name="INVOICE_COMPANY",nullable=true)
	private String invoiceCompany;//开票公司
	
	@Column(name="CONTACTER",nullable=true)
	private String contacter;//开票联系人
	
	@Column(name="CONTACT_PHONE",nullable=true)
	private String contactPhone;//开票联系人
	/**
	 * 负责此客户的职位编码，可多选。多个职位编码之间用英文逗号分隔
	 * 在新增和修改客户时选择职位编码。只能选择 叶子节点。
	 * 根据登录用户的职位编码匹配（like ）此字段来达到客户隔离的目的。
	 */
	@Column(name = "POSITION_CODE", nullable = true)
	private String positionCode;//
	
	@Transient
	private String cusInChargeList;// 费用参数配置
	
	@Transient
	private String linkMans;// 费用参数配置
	
	@Transient
	private String discounts;// 折扣配置
	
	@Transient
	private List<LinkMan> linkManList;// 费用参数配置
	
	@Transient
	private List<ValueAddedServicesDiscount> discountList;// 折扣列表
	
	@Transient
	private String fileUrl;//文件下载地址
	
	
	public String getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getOfficeSpace() {
		return officeSpace;
	}
	public void setOfficeSpace(String officeSpace) {
		this.officeSpace = officeSpace;
	}
	public String getOfficeArea() {
		return officeArea;
	}
	public void setOfficeArea(String officeArea) {
		this.officeArea = officeArea;
	}
	public Integer getIsTaxPayer() {
		return isTaxPayer;
	}
	public void setIsTaxPayer(Integer isTaxPayer) {
		this.isTaxPayer = isTaxPayer;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCreditGrade() {
		return creditGrade;
	}
	public void setCreditGrade(String creditGrade) {
		this.creditGrade = creditGrade;
	}

	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}	
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getPayMent() {
		return payMent;
	}
	public void setPayMent(String payMent) {
		this.payMent = payMent;
	}
	public String getBusinessMan() {
		return businessMan;
	}
	public void setBusinessMan(String businessMan) {
		this.businessMan = businessMan;
	}
	public String getServiceMan() {
		return serviceMan;
	}
	public void setServiceMan(String serviceMan) {
		this.serviceMan = serviceMan;
	}
	
	public String getAttachfileid() {
		return attachfileid;
	}
	public void setAttachfileid(String attachfileid) {
		this.attachfileid = attachfileid;
	}
	
	public String getCusInChargeList() {
		return cusInChargeList;
	}
	public void setCusInChargeList(String cusInChargeList) {
		this.cusInChargeList = cusInChargeList;
	}
	
	public String getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(String linkMans) {
		this.linkMans = linkMans;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Integer getUseContractNo() {
		return useContractNo;
	}
	public void setUseContractNo(Integer useContractNo) {
		this.useContractNo = useContractNo;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getOpType() {
		return opType;
	}
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	public List<LinkMan> getLinkManList() {
		return linkManList;
	}
	public void setLinkManList(List<LinkMan> linkManList) {
		this.linkManList = linkManList;
	}
	public String getInvoiceAddress() {
		return invoiceAddress;
	}
	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}
	public String getBusinessPartMan() {
		return businessPartMan;
	}
	public void setBusinessPartMan(String businessPartMan) {
		this.businessPartMan = businessPartMan;
	}
	public String getServicePartMan() {
		return servicePartMan;
	}
	public void setServicePartMan(String servicePartMan) {
		this.servicePartMan = servicePartMan;
	}
	public Integer getBelongTo() {
		return belongTo;
	}
	public void setBelongTo(Integer belongTo) {
		this.belongTo = belongTo;
	}
	public Double getRentDiscountRate() {
		return rentDiscountRate;
	}
	public void setRentDiscountRate(Double rentDiscountRate) {
		this.rentDiscountRate = rentDiscountRate;
	}
	public Double getDnDiscountRate() {
		return dnDiscountRate;
	}
	public void setDnDiscountRate(Double dnDiscountRate) {
		this.dnDiscountRate = dnDiscountRate;
	}
	public Double getMixDiscountRate() {
		return mixDiscountRate;
	}
	public void setMixDiscountRate(Double mixDiscountRate) {
		this.mixDiscountRate = mixDiscountRate;
	}
	public Double getAddDiscountRate() {
		return addDiscountRate;
	}
	public void setAddDiscountRate(Double addDiscountRate) {
		this.addDiscountRate = addDiscountRate;
	}
	public Integer getDiscountRateStatus() {
		return discountRateStatus;
	}
	public void setDiscountRateStatus(Integer discountRateStatus) {
		this.discountRateStatus = discountRateStatus;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public Double getInstockFee() {
		return instockFee;
	}
	public void setInstockFee(Double instockFee) {
		this.instockFee = instockFee;
	}
	public String getInvoiceCompany() {
		return invoiceCompany;
	}
	public void setInvoiceCompany(String invoiceCompany) {
		this.invoiceCompany = invoiceCompany;
	}
	public String getContacter() {
		return contacter;
	}
	public void setContacter(String contacter) {
		this.contacter = contacter;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getDiscounts() {
		return discounts;
	}
	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}
	public List<ValueAddedServicesDiscount> getDiscountList() {
		return discountList;
	}
	public void setDiscountList(List<ValueAddedServicesDiscount> discountList) {
		this.discountList = discountList;
	}	

}
