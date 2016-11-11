package com.liu.qinziyou.common.report;

import java.util.List;

//用于保存月结账单信息
public class MonthPaymentSettlemt {
	// 单号
	private String refNo;
	// 操作日期
	private String opDate;
	// 送货地点
	private String deliverAdress;

	//总箱数目
	private double totalCount;
	// 收费项目list
	private List<PaymentCommonItem> paymentCommonItemList;

	
	public double getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(double totalCount) {
		this.totalCount = totalCount;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getOpDate() {
		return opDate;
	}

	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}

	public String getDeliverAdress() {
		return deliverAdress;
	}

	public void setDeliverAdress(String deliverAdress) {
		this.deliverAdress = deliverAdress;
	}

	public List<PaymentCommonItem> getPaymentCommonItemList() {
		return paymentCommonItemList;
	}

	public void setPaymentCommonItemList(
			List<PaymentCommonItem> paymentCommonItemList) {
		this.paymentCommonItemList = paymentCommonItemList;
	}

}
