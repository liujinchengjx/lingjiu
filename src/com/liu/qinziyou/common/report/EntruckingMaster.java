package com.liu.qinziyou.common.report;

import java.util.Date;
import java.util.List;

/**
 * 装车单的主表信息
 * @author Administrator
 *
 */
public class EntruckingMaster {
	  private String entruckingNo;//装车单编码
	  private String truckNo;//运货车辆(车牌)
	  private String fromWh;//发货仓库
	  private Date deliverDate;//发货时间
	  private String blNo;//提运单号
	  private String transCompanyName; //运输公司
	  private String driverName;//司机
	  private String driverContact;//司机联系方式
	  private String customsCode;//海关编码
	  private String oper;//制单人
	  private Date opDate;//制单日期
	  private String customPort;//通关口岸
	  private Integer contactCount;//合同数量
	  private Integer cartonCount;//合同箱子数量
	  private Integer actualCartonCount;//实际箱子数量
	  private double nw;//总净重
	  private double gw;//总毛重
	  private double qty;//总数量
	  private String remark;//备注
	  private List<EntruckingOrder> ordersList;	  
	  public EntruckingMaster(){
		 	  
	  }
		public String getEntruckingNo() {
			return entruckingNo;
		}
		public void setEntruckingNo(String entruckingNo) {
			this.entruckingNo = entruckingNo;
		}
		public String getTruckNo() {
			return truckNo;
		}
		public void setTruckNo(String truckNo) {
			this.truckNo = truckNo;
		}
		public String getFromWh() {
			return fromWh;
		}
		public void setFromWh(String fromWh) {
			this.fromWh = fromWh;
		}
		public Date getDeliverDate() {
			return deliverDate;
		}
		public void setDeliverDate(Date deliverDate) {
			this.deliverDate = deliverDate;
		}
		public String getBlNo() {
			return blNo;
		}
		public void setBlNo(String blNo) {
			this.blNo = blNo;
		}
		public String getTransCompanyName() {
			return transCompanyName;
		}
		public void setTransCompanyName(String transCompanyName) {
			this.transCompanyName = transCompanyName;
		}
		public String getDriverName() {
			return driverName;
		}
		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}
		public String getDriverContact() {
			return driverContact;
		}
		public void setDriverContact(String driverContact) {
			this.driverContact = driverContact;
		}
		public String getOper() {
			return oper;
		}
		public void setOper(String oper) {
			this.oper = oper;
		}
		public Date getOpDate() {
			return opDate;
		}
		public void setOpDate(Date opDate) {
			this.opDate = opDate;
		}
		public String getCustomPort() {
			return customPort;
		}
		public void setCustomPort(String customPort) {
			this.customPort = customPort;
		}
		public Integer getContactCount() {
			return contactCount;
		}
		public void setContactCount(Integer contactCount) {
			this.contactCount = contactCount;
		}
		public Integer getCartonCount() {
			return cartonCount;
		}
		public void setCartonCount(Integer cartonCount) {
			this.cartonCount = cartonCount;
		}
		public double getNw() {
			return nw;
		}
		public void setNw(double nw) {
			this.nw = nw;
		}
		public double getGw() {
			return gw;
		}
		public void setGw(double gw) {
			this.gw = gw;
		}
		
		public double getQty() {
			return qty;
		}
		public void setQty(double qty) {
			this.qty = qty;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		public String getCustomsCode() {
			return customsCode;
		}
		public void setCustomsCode(String customsCode) {
			this.customsCode = customsCode;
		}
		public List<EntruckingOrder> getOrdersList() {
			return ordersList;
		}
		public void setOrdersList(List<EntruckingOrder> ordersList) {
			this.ordersList = ordersList;
		}
		public Integer getActualCartonCount() {
			return actualCartonCount;
		}
		public void setActualCartonCount(Integer actualCartonCount) {
			this.actualCartonCount = actualCartonCount;
		}

	
}
