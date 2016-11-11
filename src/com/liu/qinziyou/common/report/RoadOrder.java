package com.liu.qinziyou.common.report;

import java.sql.Timestamp;
import java.util.List;

/**
 * 公路舱单信息
 * @author Administrator
 *
 */
public class RoadOrder {
	private String carnoHk;//车牌
	private String blNo;//提运单号
	private String destPortPort = ""; //进口口岸
	private Timestamp loadTime;//装货时间
	private String itemCount="";//件数
	private String totalGw;//总毛重
	private String packingType="";//包装种类
	private String packingTypeCode="2";//包装种类代码
	private String tankNo;//柜号
	private String bracketNo;//托架号
	private Double bracketWeight;//架重
	private Double totalAmt;//总金额
	private String currency;// 币种
	private String buyerCompany = ""; //买方单位名称
	private String sellerCompany = ""; //卖方单位名称
	private List<RoadOrderItem> roadOrderItems;
	public String getCarnoHk() {
		return carnoHk;
	}
	public void setCarnoHk(String carnoHk) {
		this.carnoHk = carnoHk;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public String getDestPortPort() {
		return destPortPort;
	}
	public void setDestPortPort(String destPortPort) {
		this.destPortPort = destPortPort;
	}
	public Timestamp getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(Timestamp loadTime) {
		this.loadTime = loadTime;
	}
	public String getItemCount() {
		return itemCount;
	}
	public void setItemCount(String itemCount) {
		this.itemCount = itemCount;
	}
	
	public String getTotalGw() {
		return totalGw;
	}
	public void setTotalGw(String totalGw) {
		this.totalGw = totalGw;
	}
	public String getPackingType() {
		return packingType;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public String getPackingTypeCode() {
		return packingTypeCode;
	}
	public void setPackingTypeCode(String packingTypeCode) {
		this.packingTypeCode = packingTypeCode;
	}
	public String getTankNo() {
		return tankNo;
	}
	public void setTankNo(String tankNo) {
		this.tankNo = tankNo;
	}
	public String getBracketNo() {
		return bracketNo;
	}
	public void setBracketNo(String bracketNo) {
		this.bracketNo = bracketNo;
	}
	public Double getBracketWeight() {
		return bracketWeight;
	}
	public void setBracketWeight(Double bracketWeight) {
		this.bracketWeight = bracketWeight;
	}
	public Double getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(Double totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBuyerCompany() {
		return buyerCompany;
	}
	public void setBuyerCompany(String buyerCompany) {
		this.buyerCompany = buyerCompany;
	}
	public String getSellerCompany() {
		return sellerCompany;
	}
	public void setSellerCompany(String sellerCompany) {
		this.sellerCompany = sellerCompany;
	}
	public List<RoadOrderItem> getRoadOrderItems() {
		return roadOrderItems;
	}
	public void setRoadOrderItems(List<RoadOrderItem> roadOrderItems) {
		this.roadOrderItems = roadOrderItems;
	}
	
}
