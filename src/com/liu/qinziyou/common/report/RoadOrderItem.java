package com.liu.qinziyou.common.report;

/**
 * 公路舱单信息
 * @author Administrator
 *
 */
public class RoadOrderItem {
	private String productName = ""; //产品名称
	private Integer count = 0; //件数
	private String gw; //毛重	
	private String packingType="";//包装种类
	private Double amt;//货值
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getGw() {
		return gw;
	}
	public void setGw(String gw) {
		this.gw = gw;
	}
	public String getPackingType() {
		return packingType;
	}
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}
	public Double getAmt() {
		return amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}
	
}
