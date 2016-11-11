package com.liu.qinziyou.common.report;

import java.util.List;

/**
 * 国内配送结算单头部
 * @author Administrator
 *
 */
public class DeliverSettlementMaster {
	  private String settlementDte;//账单日期
	  
	  private String opCompanyName; //经营公司
	  private String opCompanyEnName; //经营公司(en)
	  
	  private String customerName; //客户名
	  
	  private Double totalArAmt=0.0D; //应收
	  private Double totalApAmt=0.0D; //应付
	  private Double totalGw=0.0D; //总毛重
	  
	  private String startTime; //开始时间
	  private String endTime; //截止时间
	  
	  private List<DeliverSettlementItem> arApList; //应收应付	  
	  
	  public DeliverSettlementMaster(){
		 	  
	  }

		public List<DeliverSettlementItem> getArApList() {
			return arApList;
		}
	
	
	
		public void setArApList(List<DeliverSettlementItem> arApList) {
			this.arApList = arApList;
		}
	
	
	
		public String getSettlementDte() {
			return settlementDte;
		}
	
		public void setSettlementDte(String settlementDte) {
			this.settlementDte = settlementDte;
		}
	
		public String getOpCompanyName() {
			return opCompanyName;
		}
	
		public void setOpCompanyName(String opCompanyName) {
			this.opCompanyName = opCompanyName;
		}
	
		
	
		public String getOpCompanyEnName() {
			return opCompanyEnName;
		}
	
		public void setOpCompanyEnName(String opCompanyEnName) {
			this.opCompanyEnName = opCompanyEnName;
		}
	
		public String getCustomerName() {
			return customerName;
		}
	
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public Double getTotalArAmt() {
			return totalArAmt;
		}

		public void setTotalArAmt(Double totalArAmt) {
			this.totalArAmt = totalArAmt;
		}

		public Double getTotalApAmt() {
			return totalApAmt;
		}

		public void setTotalApAmt(Double totalApAmt) {
			this.totalApAmt = totalApAmt;
		}

		public Double getTotalGw() {
			return totalGw;
		}

		public void setTotalGw(Double totalGw) {
			this.totalGw = totalGw;
		}
	  
		
}

