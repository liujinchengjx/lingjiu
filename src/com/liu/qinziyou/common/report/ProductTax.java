package com.liu.qinziyou.common.report;

/**
 * 用在预览关税，增值税的Item中
 * @author Administrator
 *
 */
public class ProductTax {
	  private Integer orderNo;//序号
	  private String codeTs;//商品编码
	  private String productName = ""; //产品名称
	  private String pn = ""; //型号
	  private Double qty; //数量
	  private String unit;// 单位
	  private Double price; //单价
	  private Double tariffRate;// 关税率 最惠国取最低税率，非最惠国取普通税率 根据原产国取
	  private Double goodsAmt;//报关货值
	  private Double goodsRmbAmt; //人民币货值 goodsAmt
	  private Double addTaxRate; //增值税率
	  private Double consumeRate; //消费税率
	  private Double payTaxAmt; //完税金额
	  private Double levyTaxAmt; //实征税额
	  
	  private Integer proveOrg=1; //是否提供原产地证明 0 是 1 否 默认是1
	  
	  public ProductTax(){
	  }
		public String getCodeTs() {
			return codeTs;
		}
		
		
		public void setCodeTs(String codeTs) {
			this.codeTs = codeTs;
		}
		
		
		public Integer getOrderNo() {
			return orderNo;
		}
		
		public void setOrderNo(Integer orderNo) {
			this.orderNo = orderNo;
		}
		
		public String getProductName() {
			return productName;
		}
		
		public void setProductName(String productName) {
			this.productName = productName;
		}
		
		public Double getQty() {
			return qty;
		}
		
		public void setQty(Double qty) {
			this.qty = qty;
		}
		
	
		
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		
		public Double getAddTaxRate() {
			return addTaxRate;
		}
		public void setAddTaxRate(Double addTaxRate) {
			this.addTaxRate = addTaxRate;
		}
		public Double getPayTaxAmt() {
			return payTaxAmt;
		}
		public void setPayTaxAmt(Double payTaxAmt) {
			this.payTaxAmt = payTaxAmt;
		}
		public Double getLevyTaxAmt() {
			return levyTaxAmt;
		}
		
		public void setLevyTaxAmt(Double levyTaxAmt) {
			this.levyTaxAmt = levyTaxAmt;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public Double getTariffRate() {
			return tariffRate;
		}
		public void setTariffRate(Double tariffRate) {
			this.tariffRate = tariffRate;
		}
		
		public Double getGoodsRmbAmt() {
			return goodsRmbAmt;
		}
		public void setGoodsRmbAmt(Double goodsRmbAmt) {
			this.goodsRmbAmt = goodsRmbAmt;
		}
		public String getPn() {
			return pn;
		}
		public void setPn(String pn) {
			this.pn = pn;
		}
		public Double getGoodsAmt() {
			return goodsAmt;
		}
		public void setGoodsAmt(Double goodsAmt) {
			this.goodsAmt = goodsAmt;
		}
		public Integer getProveOrg() {
			if (proveOrg==null){
				proveOrg=1;
			}
			return proveOrg;
		}
		public void setProveOrg(Integer proveOrg) {
			this.proveOrg = proveOrg;
		}
		public Double getConsumeRate() {
			return consumeRate;
		}
		public void setConsumeRate(Double consumeRate) {
			this.consumeRate = consumeRate;
		}
	  
		
}
