package com.liu.qinziyou.common.template;

/**
 * 箱单 FWA模版的箱单部分 导单的时候用
 * @author Administrator
 *
 */
public class Packing {
	private int type=0;
	private String carton=""; //箱数
	private String orgCarton="";//原始箱号 即客户的箱号
	private String productName;//产品名称	
	private String invoiceNo;//客户发票号
	private String customerNo;//客户识别号
	private String pn="";//型号
	private Double qty;//数量	
	private String origin="";//原产地
	private Double nw; //净重
	private Double gw; //毛重
	private String size;//尺寸
	private String lotNum="";//lot num
	private String dateCode="";//dataCode
	public Packing(){
		  
	}
	public String getCarton() {
		return carton;
	}
	public void setCarton(String carton) {
		this.carton = carton;
	}
	public String getOrgCarton() {
		return orgCarton;
	}
	public void setOrgCarton(String orgCarton) {
		this.orgCarton = orgCarton;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getNw() {
		return nw;
	}
	public void setNw(Double nw) {
		this.nw = nw;
	}
	public Double getGw() {
		return gw;
	}
	public void setGw(Double gw) {
		this.gw = gw;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getLotNum() {
		return lotNum;
	}
	public void setLotNum(String lotNum) {
		this.lotNum = lotNum;
	}
	public String getDateCode() {
		return dateCode;
	}
	public void setDateCode(String dateCode) {
		this.dateCode = dateCode;
	}
			 
}
