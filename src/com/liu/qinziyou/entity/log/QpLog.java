package com.liu.qinziyou.entity.log;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "qplog")
public class QpLog extends BaseBean{
	/**
	 * qp日志
	 */
	
	@Column(name="QPTYPE",nullable=false)	
	private String qpType;//qp类型  0 qp助手方请求qpxml数据， 1 qp助手方返回处理结果
	
	@Column(name="ORDERID",nullable=false)	
	private String orderId;//合同id
	
	@Column(name="QPXML",nullable=true)
	private String qpXml; //qp数据
	
	@Column(name="QPDTE",nullable=true)
	private Timestamp qpDte;//上传日期

	public String getQpType() {
		return qpType;
	}

	public void setQpType(String qpType) {
		this.qpType = qpType;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getQpXml() {
		return qpXml;
	}

	public void setQpXml(String qpXml) {
		this.qpXml = qpXml;
	}

	public Timestamp getQpDte() {
		return qpDte;
	}

	public void setQpDte(Timestamp qpDte) {
		this.qpDte = qpDte;
	}
    
	
	
	
}
