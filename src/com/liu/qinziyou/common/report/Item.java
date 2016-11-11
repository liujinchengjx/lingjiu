package com.liu.qinziyou.common.report;

/**
 * 发票 合同的产品信息
 * @author Administrator
 *
 */
public class Item {
	  private String cartonNo;//箱号
	  private String orgCartonNo;//原箱号
	  private Integer productId; //产品id
	  private String productName = ""; //产品名称
	  private String pn ; // 型号
	  private String brand ; //品牌
	  private String org = ""; //原产地
	  private String price; //单价
	  private String currency; //币种
	  private String qty; //数量
	  private String unit; //数量单位
	  private String nw; //净重
	  private String gw; //毛重	  
	  private String weightUnit="Kgs"; //重量单位 默认是 kg
	  private String amt; //金额	  
	  private String dealType; //成交方式	  
	  private Integer length;
	  private Integer width;
	  private Integer height;	  
	  private String remark; //备注
	  
	  private Double tempQty; //Double型的数量
	  private Double tempNw; //Double型的净重
	  private Double tempGw; //Double型的毛重	
	  
	  //下面两个字段是给价格检查用
	  private Double customsPrice; //报关价 已经报过的单价 
	  private Integer checkflag; //价格检查 标识 0 更上次一致 1 低于 10% 2 高于 10% 3 第一次报关
	  private Integer orderidFrom;//订单 来源 1 来自旧的数据库 2 来自新的数据库
	  private String orderNo;//订单号
	  private Integer orderId;//订单号ID
	  public Item(){
		  
	  }
	public String getCartonNo() {
		return cartonNo;
	}
	public void setCartonNo(String cartonNo) {
		this.cartonNo = cartonNo;
	}
	public String getOrgCartonNo() {
		return orgCartonNo;
	}
	public void setOrgCartonNo(String orgCartonNo) {
		this.orgCartonNo = orgCartonNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getCustomsPrice() {
		return customsPrice;
	}
	public void setCustomsPrice(Double customsPrice) {
		this.customsPrice = customsPrice;
	}
	public Integer getCheckflag() {
		return checkflag;
	}
	public void setCheckflag(Integer checkflag) {
		this.checkflag = checkflag;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
	public String getNw() {
		return nw;
	}
	public void setNw(String nw) {
		this.nw = nw;
	}
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	
	public Integer getOrderidFrom() {
		return orderidFrom;
	}
	public void setOrderidFrom(Integer orderidFrom) {
		this.orderidFrom = orderidFrom;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getTempQty() {
		return tempQty;
	}
	public void setTempQty(Double tempQty) {
		this.tempQty = tempQty;
	}
	public Double getTempNw() {
		return tempNw;
	}
	public void setTempNw(Double tempNw) {
		this.tempNw = tempNw;
	}
	public Double getTempGw() {
		return tempGw;
	}
	public void setTempGw(Double tempGw) {
		this.tempGw = tempGw;
	}
	
}
