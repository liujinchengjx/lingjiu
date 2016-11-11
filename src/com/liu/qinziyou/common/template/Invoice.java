package com.liu.qinziyou.common.template;

/**
 * 发票 FWA模版的发票部分 导单的时候用
 * @author Administrator
 *
 */
public class Invoice {
	  private Integer id; //id
	  private String productName = ""; //产品名称
	  private String pn ; // 型号
	  private String brand ; //品牌
	  private String origin="";//原产地
	  private Double qty; //数量
	  private Double price; //单价
	  private String unit; //数量单位
	  private String invoiceNo;//客户发票号
	  private String customerNo;//客户客户识别号
	  private String customerPn="";//客户型号
	  private String dateCode="";//datacode
	  private String lotNum="";//lot num
	  private String mpq="";//Mpq
	  private Double totalPrice; //总价
	  private String usages=""; //用途
	  private String remark; //备注
	  public Invoice(){
		  
	  }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUsages() {
		return usages;
	}
	public void setUsages(String usages) {
		this.usages = usages;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerPn() {
		return customerPn;
	}
	public void setCustomerPn(String customerPn) {
		this.customerPn = customerPn;
	}
	public String getDateCode() {
		return dateCode;
	}
	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getLotNum() {
		return lotNum;
	}
	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	public String getMpq() {
		return mpq;
	}
	public void setMpq(String mpq) {
		this.mpq = mpq;
	}
	 
}
