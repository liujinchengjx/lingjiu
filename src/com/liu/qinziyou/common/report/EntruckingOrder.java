package com.liu.qinziyou.common.report;

import java.util.List;

/**
 * 装车单用，每个报关单的表头部分
 * @author Administrator
 *
 */
public class EntruckingOrder {
	  private String orgContractNo;//原合同号
	  private String tearContractNo; //拆单之后的合同号
	  private String customerName ; // 客户
	  private String customOrderNo ; //报关单号	  
	  private String refNo ; //客户引用编号，暂时不用
	  private Integer qpPostFlag; //qp递单状态 1 待递单  2 已递单 
	  
	  public EntruckingOrder(){
		  
	  }

		public String getOrgContractNo() {
			return orgContractNo;
		}
	
		public void setOrgContractNo(String orgContractNo) {
			this.orgContractNo = orgContractNo;
		}
	
		public String getTearContractNo() {
			return tearContractNo;
		}
	
		public void setTearContractNo(String tearContractNo) {
			this.tearContractNo = tearContractNo;
		}
	
		public String getCustomerName() {
			return customerName;
		}
	
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
	
		public String getCustomOrderNo() {
			return customOrderNo;
		}
	
		public void setCustomOrderNo(String customOrderNo) {
			this.customOrderNo = customOrderNo;
		}
	
		public String getRefNo() {
			return refNo;
		}
	
		public void setRefNo(String refNo) {
			this.refNo = refNo;
		}

		

		public Integer getQpPostFlag() {
			return qpPostFlag;
		}

		public void setQpPostFlag(Integer qpPostFlag) {
			this.qpPostFlag = qpPostFlag;
		}

		
	  
}
