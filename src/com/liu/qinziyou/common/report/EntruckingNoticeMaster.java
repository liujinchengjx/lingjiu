package com.liu.qinziyou.common.report;

import java.util.Date;
import java.util.List;
/**
 * 捆绑通知
 * @author Administrator
 *
 */
public class EntruckingNoticeMaster {
	private String transCompanyName; //运输公司
	private String opCompanyName; //经营公司	
	private Integer cfCounts; //报关单数
	private Integer cartonCounts; //件数单数
	private Integer cusCounts; //客户数量
	private Date customDate; //报关日期
	private String blNo;//六联单号
	private Double gw;//毛重
	private String truckNo;//车牌 
	private String driverName;//司机
	private String codeTs;//海关编码
	private String customsPort;//报关口岸
	
	List<EntruckingNoticeItem> enItems;
	public String getTransCompanyName() {
		return transCompanyName;
	}
	public void setTransCompanyName(String transCompanyName) {
		this.transCompanyName = transCompanyName;
	}
	public String getOpCompanyName() {
		return opCompanyName;
	}
	public void setOpCompanyName(String opCompanyName) {
		this.opCompanyName = opCompanyName;
	}
	public Integer getCfCounts() {
		return cfCounts;
	}
	public void setCfCounts(Integer cfCounts) {
		this.cfCounts = cfCounts;
	}
	public Integer getCusCounts() {
		return cusCounts;
	}
	public void setCusCounts(Integer cusCounts) {
		this.cusCounts = cusCounts;
	}
	
	public Date getCustomDate() {
		return customDate;
	}
	public void setCustomDate(Date customDate) {
		this.customDate = customDate;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public Double getGw() {
		return gw;
	}
	public void setGw(Double gw) {
		this.gw = gw;
	}
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getCodeTs() {
		return codeTs;
	}
	public void setCodeTs(String codeTs) {
		this.codeTs = codeTs;
	}
	public String getCustomsPort() {
		return customsPort;
	}
	public void setCustomsPort(String customsPort) {
		this.customsPort = customsPort;
	}
	public List<EntruckingNoticeItem> getEnItems() {
		return enItems;
	}
	public void setEnItems(List<EntruckingNoticeItem> enItems) {
		this.enItems = enItems;
	}
	public Integer getCartonCounts() {
		return cartonCounts;
	}
	public void setCartonCounts(Integer cartonCounts) {
		this.cartonCounts = cartonCounts;
	}

}
