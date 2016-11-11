package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;
/**
 * 车辆
 * @author li
 */
@Entity
@Table(name = "truck")
public class Truck extends BaseBean{
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="TRANID",nullable=true)
	private Integer tranId;//对应运输公司ID
	
	@Column(name="DRIVERID",nullable=true)
	private Integer driverId;//配送司机
	
	@Column(name="TONNAGE",nullable=true)
	private String tonnage;//吨位
	
	@Column(name="SELFWEIGHT",nullable=true)
	private String selfWeight;//自重
	
	@Column(name="ACTUALLOADWEIGHT",nullable=true)
	private Double actualLoadWeight;//实际载重	
	
	@Column(name="OVERALLSIZE",nullable=true)
	private String overAllSize;//外形尺寸
	
	@Column(name="CARNO_HK",nullable=true)
	private String carnoHk;//车牌号-香港
	
	@Column(name="CARNO_CH",nullable=true)
	private String carnoCh;//车牌号-大陆
	
	@Column(name="VOLUME",nullable=true)
	private String volume;//容积
	
	@Column(name="HSCODE",nullable=true)
	private String hsCode;//海关编码，不是商品编码 是车辆过关编码
	
	@Column(name="TYPE",nullable=true)
	private Integer type;//类型 中港车、内地车、港牌车
	
	@Column(name="STATUS",nullable=true)
	private Integer status;//汽车状态
	
	@Column(name="BLNO", nullable = true)
	private String blNo;//提运单号,这里的六联单号是关务部的人，每天将当天要出车的车辆绑定当天的六联单号，供装车的时候进行绑定
	
	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	@Transient
	private String tranName; //所属运输公司
	
	@Transient
	private String driverName; //配送司机
	
	@Transient
	private String tonnageName; //吨位
	
	@Transient
	private String typeName; //车辆类型名称

	public Integer getTranId() {
		return tranId;
	}

	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}

	public String getTonnage() {
		return tonnage;
	}

	public void setTonnage(String tonnage) {
		this.tonnage = tonnage;
	}

	public String getSelfWeight() {
		return selfWeight;
	}

	public void setSelfWeight(String selfWeight) {
		this.selfWeight = selfWeight;
	}


	public Double getActualLoadWeight() {
		return actualLoadWeight;
	}

	public void setActualLoadWeight(Double actualLoadWeight) {
		this.actualLoadWeight = actualLoadWeight;
	}

	public String getOverAllSize() {
		return overAllSize;
	}

	public void setOverAllSize(String overAllSize) {
		this.overAllSize = overAllSize;
	}

	public String getCarnoHk() {
		return carnoHk;
	}

	public void setCarnoHk(String carnoHk) {
		this.carnoHk = carnoHk;
	}

	public String getCarnoCh() {
		return carnoCh;
	}

	public void setCarnoCh(String carnoCh) {
		this.carnoCh = carnoCh;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getTranName() {
		return tranName;
	}

	public void setTranName(String tranName) {
		this.tranName = tranName;
	}

	public String getTonnageName() {
		return tonnageName;
	}

	public void setTonnageName(String tonnageName) {
		this.tonnageName = tonnageName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	
}
