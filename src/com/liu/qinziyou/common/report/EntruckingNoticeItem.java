package com.liu.qinziyou.common.report;

/**
 * 捆绑通知书的items
 * @author Administrator
 *
 */
public class EntruckingNoticeItem {
 	  private Integer orderNo;//序号
	  private String customOrderNo;//报关单号
	  private Integer cartons;//箱数
	  private Double gw;//毛重
	  
	  public EntruckingNoticeItem(){
		  
	  }

  	  public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomOrderNo() {
			return customOrderNo;
	  }
	
	  public void setCustomOrderNo(String customOrderNo) {
			this.customOrderNo = customOrderNo;
	  }
	
	  public Integer getCartons() {
			return cartons;
		}
	
		public void setCartons(Integer cartons) {
			this.cartons = cartons;
		}
	
		public Double getGw() {
			return gw;
		}
	
		public void setGw(Double gw) {
			this.gw = gw;
		}
		  
}
