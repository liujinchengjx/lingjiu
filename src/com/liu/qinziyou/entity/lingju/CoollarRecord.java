package com.liu.qinziyou.entity.lingju;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "coollar_record")
public class CoollarRecord  extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7453780372998729130L;
	
	@Column(name="GOODS_ID",nullable=true)
	private Integer goodsId;
	
	@Column(name="GOODS_NAME",nullable=false)	
	private String goodsName;//商品名称
	
	@Transient
	private CoollarGoods coollarGoods;
	
	@Column(name="MEMBER_CODE",nullable=true)
	private String memberCode;
	
	@Column(name="ORDER_CODE",nullable=true)
	private String orderCode;
	
	@Column(name="ACTIVITY_CODE",nullable=true)
	private String activityCode;//活动编号，以这个来区分
	
	@Column(name="COOLLAR_TIME",nullable=true)
	private java.sql.Timestamp  coollarTime;//
	

	@Column(name="COOLLAR_QTY",nullable=true)
	private Integer  coollarQty;//
	
	@Column(name="COOLAR_PRICE",nullable=false)	
	private Integer coollarPrice;//领用单价
	
	
	@Column(name="LOGISTICS_FEE",nullable=false)	
	private Integer logisticsFee;//物流单价
	
	
	@Column(name="TOTAL_FEE",nullable=false)//总费用
	private Integer totalFee;//总价 = coollarPrice*coollarQty+logisticsFee*coollarQty
	
	@Column(name="RECEIVER_NAME",nullable=false)
	private String receiverName;//收货人姓名
	
	@Column(name="RECEIVER_MOBILE",nullable=false)
	private String receiverMobile;//收货人电话
	
	
	@Column(name="PROVINCE_NAME",nullable=true)
	private String  provinceName;//
	
	
	
	
	@Column(name="CITY_NAME",nullable=true)
	private String  cityName;//

	

	@Column(name="AREA_NAME",nullable=true)
	private String  areaName;
	
	
	@Column(name="ADDRESS",nullable=true)
	private String  address;//详细地址
	
	@Column(name="FULL_ADDRESS",nullable=true)
	private String  fullAddress;//详细地址
	
	@Column(name="STATE",nullable=true)
	private Integer state;//订单状态 1 取消 10 待确定  20 以确定 
	
	@Column(name="FLEIGHT_STATE",nullable=true)
	private Integer fleightState;//订单状态  10 未发货  20 已发货
	
	@Column(name="FLEIGHT_COMPANY",nullable=true)
	private String fleightCompany;//发运公司 
	
	@Column(name="FLEIGHT_NUMBER",nullable=true)
	private String fleightNumber;//货运单号
	
	@Column(name="FLEIGHT_TIME",nullable=true)
	private java.sql.Timestamp  fleightTime;//发运时间
	
	@Column(name="PAYWAY",nullable=true)
	private Integer payway;//付款方式
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	@Column(name="PRIZE",nullable=true)
	private Integer prize;//此订单会员获得的奖励 单位是分
	
	@Transient
	private String stateName;
	@Transient
	private String fleightStateName;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}


	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public java.sql.Timestamp getCoollarTime() {
		return coollarTime;
	}

	public void setCoollarTime(java.sql.Timestamp coollarTime) {
		this.coollarTime = coollarTime;
	}

	public Integer getCoollarQty() {
		return coollarQty;
	}

	public void setCoollarQty(Integer coollarQty) {
		this.coollarQty = coollarQty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public Integer getFleightState() {
		return fleightState;
	}

	public void setFleightState(Integer fleightState) {
		this.fleightState = fleightState;
	}

	public String getFleightCompany() {
		return fleightCompany;
	}

	public void setFleightCompany(String fleightCompany) {
		this.fleightCompany = fleightCompany;
	}

	public String getFleightNumber() {
		return fleightNumber;
	}

	public void setFleightNumber(String fleightNumber) {
		this.fleightNumber = fleightNumber;
	}

	public java.sql.Timestamp getFleightTime() {
		return fleightTime;
	}

	public void setFleightTime(java.sql.Timestamp fleightTime) {
		this.fleightTime = fleightTime;
	}

	public CoollarGoods getCoollarGoods() {
		return coollarGoods;
	}

	public void setCoollarGoods(CoollarGoods coollarGoods) {
		this.coollarGoods = coollarGoods;
	}

	public Integer getCoollarPrice() {
		return coollarPrice;
	}

	public void setCoollarPrice(Integer coollarPrice) {
		this.coollarPrice = coollarPrice;
	}

	public Integer getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(Integer logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPayway() {
		return payway;
	}

	public void setPayway(Integer payway) {
		this.payway = payway;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPrize() {
		return prize;
	}

	public void setPrize(Integer prize) {
		this.prize = prize;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStateName() {
		if(this.state.intValue()==10){
			stateName = "等待客服确认";
		}else if(this.state.intValue()==20){
			stateName = "客服已确认";
		}else if(this.state.intValue()==1){
			stateName = "客服已取消";
		}
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getFleightStateName() {
		
		if(this.fleightState.intValue()==10){
			fleightStateName = "等待发货";
		}else if(this.fleightState.intValue()==20){
			fleightStateName = "已经发货";
		}
		return fleightStateName;
	}

	public void setFleightStateName(String fleightStateName) {
		this.fleightStateName = fleightStateName;
	}
	
	
	
}
