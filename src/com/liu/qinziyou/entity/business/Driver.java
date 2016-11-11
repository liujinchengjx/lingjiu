package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
/**
 * 司机
 * @author li
 */
@Entity
@Table(name = "driver")
public class Driver extends BaseBean{
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="DRIVER_NO",nullable=true)
	private String driverNo;//司机编码
	
	@Column(name="TRANID",nullable=true)
	private Integer tranId;//对应运输公司ID
	
	@Column(name="NAME",nullable=true)
	private String name;//司机名字
	
	@Column(name="SEX",nullable=true)
	private Integer sex;//性别
	
	@Column(name="PHONE",nullable=true)
	private String phone;//手机
	
	@Column(name="TEL",nullable=true)
	private String tel;//电话	
	
	@Column(name="LIVEADDRESS",nullable=true)
	private String liveAddress;//居住地址
	
	@Column(name="IDCARD",nullable=true)
	private String idCard;//证件名 如身份证
	
	@Column(name="DRIVER_LICENSE",nullable=true)
	private String driverLicense;//驾驶执照
	
	@Column(name="DRIVER_LICENSE_DTE",nullable=true)
	private String driverLicenseDte;//驾证取证时间
	
	@Column(name="DRIVER_LICENSE_CHKDTE",nullable=true)
	private String driverLicenseChkDte;//驾照年审时间
	
	@Column(name="ALLOW_DRIVER_NUM",nullable=true)
	private String allowDriverNum;//准驾号码
	
	@Column(name="DONUM",nullable=true)
	private String doNum;//从业证号
	
	@Column(name="DEGREES",nullable=true)
	private String degress;//学位
	
	@Column(name="CENSUS",nullable=true)
	private String census;//户籍
	
	@Column(name="STATUS",nullable=true)
	private Integer status;//审核状态    10 待审核  20已审核 

	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	//@Formula("(SELECT A.CNAME FROM TRANSPORT_COMPANY A WHERE  A.ID=TRANID)")
	@Transient
	private String tranName; //所属运输公司
	
	public Integer getTranId() {
		return tranId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String getDriverLicenseDte() {
		return driverLicenseDte;
	}

	public void setDriverLicenseDte(String driverLicenseDte) {
		this.driverLicenseDte = driverLicenseDte;
	}

	public String getDriverLicenseChkDte() {
		return driverLicenseChkDte;
	}

	public void setDriverLicenseChkDte(String driverLicenseChkDte) {
		this.driverLicenseChkDte = driverLicenseChkDte;
	}

	public String getAllowDriverNum() {
		return allowDriverNum;
	}

	public void setAllowDriverNum(String allowDriverNum) {
		this.allowDriverNum = allowDriverNum;
	}

	public String getDoNum() {
		return doNum;
	}

	public void setDoNum(String doNum) {
		this.doNum = doNum;
	}

	public String getDegress() {
		return degress;
	}

	public void setDegress(String degress) {
		this.degress = degress;
	}

	public String getCensus() {
		return census;
	}

	public void setCensus(String census) {
		this.census = census;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public String getTranName() {
		return tranName;
	}

	public void setTranName(String tranName) {
		this.tranName = tranName;
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
	
}
