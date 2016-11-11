package com.liu.qinziyou.common.report;


/**
 * 国内配送结算单头部
 * @author Administrator
 *
 */
public class DeliverSettlementItem {
	  private String createWbDate;//生成运单的时间
	  private String contractNo;//合同号
	  private String agreementNo;//协议号
	  private String stockOutNo;; //出库单号
	  private String deliverNo; //送货单号
	  private String customName;//客户名称
	  private String receiveCompanyName = ""; //收货公司
	  private String customerNo="";//客户识别号
	  private String bulkyCargo="";// 是否泡货 0 是 1 不是 泡货
	  private String size="";// 尺寸
	  private Integer cartons;//箱数
	  private Double gw; //毛重
	  private String payWay; //收款方式
	  private String shipWay; //送达方式
	  private String transCompanyName; //货运公司
	  private String wayBillNo;//运单号 快递单号
	  private Double payAmt;//实际应付费用
	  private Double arAmt;//实际收费用
	  private String remark;//备注
	  private String deliverAddress;//送货地址
	  private String backNo;
	  
	  private String asTradeFee=""; //0 是 1 否 是否作为一般贸易结算
	  
	  public DeliverSettlementItem(){
		 	  
	  }
		public String getCreateWbDate() {
			return createWbDate;
		}
		public void setCreateWbDate(String createWbDate) {
			this.createWbDate = createWbDate;
		}
		public String getContractNo() {
			return contractNo;
		}
		public void setContractNo(String contractNo) {
			this.contractNo = contractNo;
		}
		public String getStockOutNo() {
			return stockOutNo;
		}
		public void setStockOutNo(String stockOutNo) {
			this.stockOutNo = stockOutNo;
		}
		
		public String getReceiveCompanyName() {
			return receiveCompanyName;
		}
		public void setReceiveCompanyName(String receiveCompanyName) {
			this.receiveCompanyName = receiveCompanyName;
		}
		public String getCustomerNo() {
			return customerNo;
		}
		public void setCustomerNo(String customerNo) {
			this.customerNo = customerNo;
		}
		public Integer getCartons() {
			return cartons;
		}
		public void setCartons(Integer cartons) {
			this.cartons = cartons;
		}		
		public String getAgreementNo() {
			return agreementNo;
		}
		public void setAgreementNo(String agreementNo) {
			this.agreementNo = agreementNo;
		}
		public Double getGw() {
			return gw;
		}
		public void setGw(Double gw) {
			this.gw = gw;
		}
		public String getShipWay() {
			return shipWay;
		}
		public void setShipWay(String shipWay) {
			this.shipWay = shipWay;
		}
		public String getTransCompanyName() {
			return transCompanyName;
		}
		public void setTransCompanyName(String transCompanyName) {
			this.transCompanyName = transCompanyName;
		}
		public String getWayBillNo() {
			return wayBillNo;
		}
		public void setWayBillNo(String wayBillNo) {
			this.wayBillNo = wayBillNo;
		}
		public Double getPayAmt() {
			return payAmt;
		}
		public void setPayAmt(Double payAmt) {
			this.payAmt = payAmt;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getDeliverAddress() {
			return deliverAddress;
		}
		public void setDeliverAddress(String deliverAddress) {
			this.deliverAddress = deliverAddress;
		}
		public String getAsTradeFee() {
			return asTradeFee;
		}
		public void setAsTradeFee(String asTradeFee) {
			this.asTradeFee = asTradeFee;
		}
		public String getPayWay() {
			return payWay;
		}
		public void setPayWay(String payWay) {
			this.payWay = payWay;
		}
		public Double getArAmt() {
			return arAmt;
		}
		public void setArAmt(Double arAmt) {
			this.arAmt = arAmt;
		}
		public String getDeliverNo() {
			return deliverNo;
		}
		public void setDeliverNo(String deliverNo) {
			this.deliverNo = deliverNo;
		}
		public String getBulkyCargo() {
			return bulkyCargo;
		}
		public void setBulkyCargo(String bulkyCargo) {
			this.bulkyCargo = bulkyCargo;
		}
		public String getSize() {
			return size;
		}
		public void setSize(String size) {
			this.size = size;
		}
		public String getCustomName() {
			return customName;
		}
		public void setCustomName(String customName) {
			this.customName = customName;
		}
		public String getBackNo() {
			return backNo;
		}
		public void setBackNo(String backNo) {
			this.backNo = backNo;
		}
		
}

