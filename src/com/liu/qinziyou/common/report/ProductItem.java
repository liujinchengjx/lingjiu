package com.liu.qinziyou.common.report;

/**
 * 用在预览关税，增值税，付款通知结算的Item中
 * @author Administrator
 *
 */
public class ProductItem {
	  private Integer productId;//产品ID
	  private Integer orderNo;//序号
	  private String productName = ""; //产品名称
	  private String pn ; // 型号
	  private Double qty; //数量
	  private String unit; //单位
	  private Double price; //单价
	  private String currency; //币种
	  private Double goodAmt; //报关货值  qty*price
	  private Double goodsRmbAmt; //人民币货值
	  private Double serviceRate; //服务费率
	  private Double serviceAmt; //服务费(RMB)
	  
	  private Integer proveOrg=1; //是否提供原产地证明 0 是 1 否 默认是1
	  
	  private Double tariffRate; //关税率
	  private Double tariffAmt; //关税(RMB)
	  
	  private Double vatRate; //增值税率
	  private Double vatAmt; //增值税(RMB)
	  
	  private Double deliveryAmt;//配送费用RMB
	  private Double inspectionAmt;//商检费用RMB
	  private Double disinfectionAmt;//消毒费用RMB
	  private Double ddiAmt;//消毒费用RMB
	  
	 //开票金额列 这一列是在开票显示的时候用的 一般只用在单台头  它对于 serviceAmt+goodsRmbAmt+ddiAmt+
	 //  tariffAmt+vatAmt;
	  private Double invoiceAmt;
	  
	  private String invoiceNo="";//原始发票号
	  
	  private Integer customFormId; //报关单
	  
	  public ProductItem(){
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
	
		public String getPn() {
			return pn;
		}
	
		public void setPn(String pn) {
			this.pn = pn;
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
	
		public String getCurrency() {
			return currency;
		}
	
		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public Double getServiceRate() {
			return serviceRate;
		}

		public void setServiceRate(Double serviceRate) {
			this.serviceRate = serviceRate;
		}

		public Double getServiceAmt() {
			return serviceAmt;
		}

		public void setServiceAmt(Double serviceAmt) {
			this.serviceAmt = serviceAmt;
		}

		public Double getTariffAmt() {
			return tariffAmt;
		}

		public void setTariffAmt(Double tariffAmt) {
			this.tariffAmt = tariffAmt;
		}	

		public Double getVatRate() {
			return vatRate;
		}

		public void setVatRate(Double vatRate) {
			this.vatRate = vatRate;
		}

		public Double getVatAmt() {
			return vatAmt;
		}

		public void setVatAmt(Double vatAmt) {
			this.vatAmt = vatAmt;
		}

		public Double getTariffRate() {
			return tariffRate;
		}

		public void setTariffRate(Double tariffRate) {
			this.tariffRate = tariffRate;
		}

		
		public Double getDeliveryAmt() {
			return deliveryAmt;
		}

		public void setDeliveryAmt(Double deliveryAmt) {
			this.deliveryAmt = deliveryAmt;
		}

		public Double getInspectionAmt() {
			return inspectionAmt;
		}

		public void setInspectionAmt(Double inspectionAmt) {
			this.inspectionAmt = inspectionAmt;
		}

		public Double getDisinfectionAmt() {
			return disinfectionAmt;
		}

		public void setDisinfectionAmt(Double disinfectionAmt) {
			this.disinfectionAmt = disinfectionAmt;
		}

		public Double getDdiAmt() {
			return ddiAmt;
		}

		public void setDdiAmt(Double ddiAmt) {
			this.ddiAmt = ddiAmt;
		}

		public Double getGoodAmt() {
			return goodAmt;
		}

		public void setGoodAmt(Double goodAmt) {
			this.goodAmt = goodAmt;
		}

		public Double getGoodsRmbAmt() {
			return goodsRmbAmt;
		}

		public void setGoodsRmbAmt(Double goodsRmbAmt) {
			this.goodsRmbAmt = goodsRmbAmt;
		}

		public Integer getProductId() {
			return productId;
		}

		public void setProductId(Integer productId) {
			this.productId = productId;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public Double getInvoiceAmt() {
			return invoiceAmt;
		}

		public void setInvoiceAmt(Double invoiceAmt) {
			this.invoiceAmt = invoiceAmt;
		}

		public String getInvoiceNo() {
			return invoiceNo;
		}

		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
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

		public Integer getCustomFormId() {
			return customFormId;
		}

		public void setCustomFormId(Integer customFormId) {
			this.customFormId = customFormId;
		}

		
		
}
