package com.liu.qinziyou.common.report;
/**
 * 账单统计中的Items
 * @author Administrator
 *
 */
public class PaymentCountItem {
	  private String orderDate;//日期
	  private Integer orderNo;//序号
	  private String refOrderNo;//参考号码
	  private Double stockInOutAmt;//出入仓费用
	  private Double rentAmt;//租金
	  private Double deliveryAmt;//送货费用
	  private Double disbursementAmt;//代垫费用
	  private Double addValueAmt;//增值服务费用
	  
	  private Double mixAmt;//杂费
	  
	  public PaymentCountItem(){
	  }
	  
	 public Integer getOrderNo() {
			return orderNo;
		}
	
	
		public void setOrderNo(Integer orderNo) {
			this.orderNo = orderNo;
		}

		

		public String getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(String orderDate) {
			this.orderDate = orderDate;
		}

		public String getRefOrderNo() {
			return refOrderNo;
		}

		public void setRefOrderNo(String refOrderNo) {
			this.refOrderNo = refOrderNo;
		}

		public Double getRentAmt() {
			return rentAmt;
		}

		public void setRentAmt(Double rentAmt) {
			this.rentAmt = rentAmt;
		}

		public Double getDisbursementAmt() {
			return disbursementAmt;
		}

		public void setDisbursementAmt(Double disbursementAmt) {
			this.disbursementAmt = disbursementAmt;
		}

		public Double getDeliveryAmt() {
			return deliveryAmt;
		}

		public void setDeliveryAmt(Double deliveryAmt) {
			this.deliveryAmt = deliveryAmt;
		}

		public Double getStockInOutAmt() {
			return stockInOutAmt;
		}

		public void setStockInOutAmt(Double stockInOutAmt) {
			this.stockInOutAmt = stockInOutAmt;
		}

		public Double getAddValueAmt() {
			return addValueAmt;
		}

		public void setAddValueAmt(Double addValueAmt) {
			this.addValueAmt = addValueAmt;
		}

		public Double getMixAmt() {
			return mixAmt;
		}

		public void setMixAmt(Double mixAmt) {
			this.mixAmt = mixAmt;
		}		
		

		
}
