package com.liu.qinziyou.entity.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 订单状态 记录 贸易订单和报关单或其他订单(暂时记录贸易订单和报关订单) 状态变化情况
 */
@Entity
@Table(name = "order_status")
public class OrderStatus extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771148853105189451L;

	@Column(name = "CUSTOMER_ID", nullable = true)
	private Integer customerId;

	@Column(name = "ORDER_TYPE", nullable = true)
	private Integer orderType; // 订单类型 1 贸易订单 2 报关单 3 装车 4 开票 5委托单 6 入库单 7 发货单 8
	// 车辆安排 9 结算单审核

	@Column(name = "ORDERID", nullable = true)
	private Integer orderId;

	@Column(name = "ORDERNO", nullable = true)
	private String orderNo;

	@Column(name = "STATUS", nullable = true)
	private Integer status; // 记录的状态

	@Column(name = "REMARK", nullable = true)
	private String remark; // 备注
	
	@Transient
	private String customerName;// 客户名称

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
