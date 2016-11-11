package com.liu.qinziyou.common.tree;
/**
 * 这个是增值服务树类，他的数据用来以树状显示所有的增值服务选项
 * @author Administrator
 *
 */
public class ValueAddedServicesTree {
	
	  private String id = "";//本级Id
	  private String pid = ""; //父级ID
	  private String pidName = ""; //父级名称
	  private String text = ""; //模块名称
	  private boolean expanded = false; //是否展开
	  private Integer status;// 状态值
	  private Integer addValueId;//增值服务id
	  private String feeUnit;//增值服务收费单位 
	  private Double count;//增值服务收货单位
	  private Double payAmt;//应付金额 这个主要是做这项工作付了多少钱  
	  private String remark; //备注 这个字段主要是用来填写一些送货地址 比如提货 和送货的时候，会涉及到地址
	  
	  private String showFee;//是否显示应收费用
	  
	  private Integer workOrderDetailId;//工作单detailId
	  
	  
	  
	  public String getShowFee() {
		return showFee;
	}
	public void setShowFee(String showFee) {
		this.showFee = showFee;
	}
	public ValueAddedServicesTree(){		  	  
	  }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAddValueId() {
		return addValueId;
	}
	public void setAddValueId(Integer addValueId) {
		this.addValueId = addValueId;
	}
	public String getFeeUnit() {
		return feeUnit;
	}
	public void setFeeUnit(String feeUnit) {
		this.feeUnit = feeUnit;
	}
	public String getPidName() {
		return pidName;
	}
	public void setPidName(String pidName) {
		this.pidName = pidName;
	}
	public Integer getWorkOrderDetailId() {
		return workOrderDetailId;
	}
	public void setWorkOrderDetailId(Integer workOrderDetailId) {
		this.workOrderDetailId = workOrderDetailId;
	}
	public Double getCount() {
		return count;
	}
	public void setCount(Double count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}


}
