package com.liu.qinziyou.entity.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 *  公司 涵盖系统内所有的公司定义
 *  用companytype区分 1：运营公司 2：客户 3：客户的客户（客户的收货公司） 4：供应商(客户的供应商或运营公司的供应商) 5、货运公司
 * @author li
 */
@Entity
@Table(name = "company")
public class Company extends BaseBean{
	
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="CCODE",nullable=true)
	private String ccode;//档案编号	
	
	@Column(name="CNAME",nullable=true)
	private String cname;//中文名称
	
	@Column(name="PINYIN",nullable=true)
	private String pinyin;//中文名称拼音
	
	@Column(name="ENAME",nullable=true)
	private String ename;//英文名称	
	
	@Column(name="CSNAME",nullable=true)
	private String csname;//中文简称
	
	@Column(name="ESNAME",nullable=true)
	private String esname;//英文简称
	
	@Column(name="HSCODE",nullable=true)
	private String hsCode;//海关代码
	
	@Column(name="LICENSE",nullable=true)
	private String license;//营业执照
	
	@Column(name="ORGCODE",nullable=true)
	private String orgCode;//组织机构代码
	
	@Column(name="TAXNO",nullable=true)
	private String taxNo;//税务登记税号	
	
	@Column(name="LEGALNAME",nullable=true)
	private String legalName;//法人代表
	
	@Column(name="LINKMAN",nullable=true)
	private String linkMan;//联系人
	
	@Column(name="TEL",nullable=true)
	private String tel;//联系电话
	
	@Column(name="PHONE",nullable=true)
	private String phone;//手机号码
	
	@Column(name="FAX",nullable=true)
	private String fax;//传真号码
	
	@Column(name="EMAIL",nullable=true)
	private String email;//电子邮箱
	
	@Column(name="ADDRESS",nullable=true)
	private String address;//公司地址
	
	@Column(name="PCODE",nullable=true)
	private String pcode;//邮政编码
	
	@Column(name="BANK",nullable=true)
	private String bank;//开户银行
	
	@Column(name="BANKNAME",nullable=true)
	private String bankName;//开户行名称
	
	@Column(name="BANKACCOUNT",nullable=true)
	private String bankAccount;//银行账户号码
	
	@Column(name="BANK_ACCOUNT_NAME",nullable=true)
	private String bankAcountName;//开户人
	
	@Column(name="BANKADDRESS",nullable=true)
	private String bankAddress;//银行地址
	
	@Column(name="SWIFTCODE",nullable=true)
	private String swiftCode;//快速查询码，主要是用于快速查询某知名银行的代码	
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;//0 有效  1 无效
	
	@Column(name="COMPANY_TYPE",nullable=true)
	private Integer companyType;//1：运营公司 2：客户 3：客户的客户（客户的收货公司） 4：供应商(客户的供应商或运营公司的供应商) 5：货运公司
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	@Column(name="REMARK1",nullable=true)
	private String remark1;//备注
	
	@Column(name="DOCUMENTS_REQUIRED",nullable=true)
	private String documentsRequired;// 单据要求
	
	@Column(name="STATUS",nullable=true)
	private Integer status;//审核状态    10 待审核  20已审核  30 冻结  40 暂停业务（用户客户欠费是不走货）
	
	
	@Transient
	private Company company;//对应的供方，需方
	
	@Transient
	private Integer useContractNo=1;//0是  1 否   是否用客户号码报关
	@Transient
	private Integer opType;//合作模式   目前只有 companyType 目前只有 companyType 为 2 的公司才有这个属性 为了方便，直接放到这里
	@Transient
	private String businessType;//业务类型 目前只有 companyType 为 2 的公司才有这个属性 为了方便，直接放到这里
	@Transient
	private String grade;//1 预申报 2 后申报  companyType 2 的时候关联 附带过来的属性
	
	@Transient
	private List<Address> addList;//地址列表
	@Transient
	private String addresss;//地址字符串
	
	
	public String getCcode() {
		return ccode;
	}
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public String getEsname() {
		return esname;
	}
	public void setEsname(String esname) {
		this.esname = esname;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
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
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getHsCode() {
		return hsCode;
	}
	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public Integer getCompanyType() {
		return companyType;
	}
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getBankAcountName() {
		return bankAcountName;
	}
	public void setBankAcountName(String bankAcountName) {
		this.bankAcountName = bankAcountName;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public List<Address> getAddList() {
		return addList;
	}
	public void setAddList(List<Address> addList) {
		this.addList = addList;
	}
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public String getDocumentsRequired() {
		return documentsRequired;
	}
	public void setDocumentsRequired(String documentsRequired) {
		this.documentsRequired = documentsRequired;
	}	
}
