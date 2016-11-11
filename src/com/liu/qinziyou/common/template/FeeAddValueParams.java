package com.liu.qinziyou.common.template;


/**
 * 结算的时候，传递的参数对象
 * @author Administrator
 *
 */
public class FeeAddValueParams {
	  private Double count=0.0D;
	  private String scode="";
	  private Double amt = 0.0D; //金额
	  private String feeName = ""; //费用名称
	  private String basis = ""; //费用依据
	  
	  private Double rentDiscountRate=1.0D;//费用折扣率  仓库租赁及库存管理费用折扣率	
	  private Double dnDiscountRate=1.0D;//费用折扣率  提货/送货费用折扣率
	  private Double mixDiscountRate=1.0D;//费用折扣率  杂费（隧道、停车、转单、入场）费用折扣率	
	  private Double addDiscountRate=1.0D;// 增值服务费用折扣率
	  
	  private Double discountRate=1.0D;////实际采用的折扣率
	  
	  //收费币种
	  private String currencyCode;
	  
	  
	  
	  
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public FeeAddValueParams(){
		  
	  }
	  
	  public Double getCount() {
		return count;
	  }

	public void setCount(Double count) {
		this.count = count;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public void setAmt(Double amt) {
		 this.amt = amt;
	  }
	  public String getFeeName() {
		 return feeName;
	  }
	  public void setFeeName(String feeName) {
		 this.feeName = feeName;
	  }
		public String getBasis() {
			return basis;
		}
		public void setBasis(String basis) {
			this.basis = basis;
		}
	
		public Double getAmt() {
			return amt;
		}

		public Double getRentDiscountRate() {
			return rentDiscountRate;
		}

		public void setRentDiscountRate(Double rentDiscountRate) {
			this.rentDiscountRate = rentDiscountRate;
		}

		public Double getDnDiscountRate() {
			return dnDiscountRate;
		}

		public void setDnDiscountRate(Double dnDiscountRate) {
			this.dnDiscountRate = dnDiscountRate;
		}

		public Double getMixDiscountRate() {
			return mixDiscountRate;
		}

		public void setMixDiscountRate(Double mixDiscountRate) {
			this.mixDiscountRate = mixDiscountRate;
		}

		public Double getAddDiscountRate() {
			return addDiscountRate;
		}

		public void setAddDiscountRate(Double addDiscountRate) {
			this.addDiscountRate = addDiscountRate;
		}

		public Double getDiscountRate() {
			return discountRate;
		}

		public void setDiscountRate(Double discountRate) {
			this.discountRate = discountRate;
		}
	
	
}
