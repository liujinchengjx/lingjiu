package com.liu.qinziyou.common.report;
/**
 * 付款通知书表头 委托单/发货单/仓租费 通用 的Item中
 * @author Administrator
 *
 */
public class PaymentCommonItem {
	  private String ditCode;//增值服务代码
	  private String orderDate;//日期
	  private Integer orderNo;//序号
	  private String refOrderNo;//参考号码
	  private Double count;//箱数
	  private String itemName;//费用名
	  private String fitemName;//父节点
	  private String unit;//单位
	  private String locAmt;// 本币金额   支付费用  一般是指港币
	  private String arAmt;//  应收金额   结算费用 港币以外的币种 比如人民币
	  
	  private String feeUnit;//单位
	  private String basis;//依据
	  private String remark;//备注
	  
	  private String receiveName;//客户收货方公司
	  private String receiveAddress = ""; //客户收货方公司地址
	  private String receiveTel = ""; //客户收货方公司电话
	  private String receiveFax = ""; //客户收货方公司传真
	  private String receiver = ""; //客户收货方公司收件人
	  private String refNo;//对应单号
	  
	  //总箱数
	  private double totalCount;
		

	public double getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}

	public String getFitemName() {
		return fitemName;
	}

	public void setFitemName(String fitemName) {
		this.fitemName = fitemName;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getDitCode() {
		return ditCode;
	}

	public void setDitCode(String ditCode) {
		this.ditCode = ditCode;
	}

	public PaymentCommonItem(){
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

		public Double getCount() {
			return count;
		}

		public void setCount(Double count) {
			this.count = count;
		}		


		public String getLocAmt() {
			return locAmt;
		}

		public void setLocAmt(String locAmt) {
			this.locAmt = locAmt;
		}

		public String getArAmt() {
			return arAmt;
		}

		public void setArAmt(String arAmt) {
			this.arAmt = arAmt;
		}

		public String getReceiveName() {
			return receiveName;
		}

		public void setReceiveName(String receiveName) {
			this.receiveName = receiveName;
		}

		public String getReceiveAddress() {
			return receiveAddress;
		}

		public void setReceiveAddress(String receiveAddress) {
			this.receiveAddress = receiveAddress;
		}

		public String getReceiveTel() {
			return receiveTel;
		}

		public void setReceiveTel(String receiveTel) {
			this.receiveTel = receiveTel;
		}

		public String getReceiveFax() {
			return receiveFax;
		}

		public void setReceiveFax(String receiveFax) {
			this.receiveFax = receiveFax;
		}

		public String getReceiver() {
			return receiver;
		}

		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}


		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getFeeUnit() {
			return feeUnit;
		}

		public void setFeeUnit(String feeUnit) {
			this.feeUnit = feeUnit;
		}

		public String getBasis() {
			return basis;
		}

		public void setBasis(String basis) {
			this.basis = basis;
		}		
	
		
		
}
