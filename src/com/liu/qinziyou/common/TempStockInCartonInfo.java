package com.liu.qinziyou.common;
/**
 * 临时出库信息，做出库单的时候，点击暂存，数据暂时保存到这个对象，这个会存放到 session中去
 * @author Administrator
 *
 */

public class TempStockInCartonInfo {
    //	
		
	private Integer customerId;//对应的客户Id
	
	private String wh;//仓库代码
	
	private Integer cartonId;//箱号id
	
	private Integer stockInPackingId;//packing的id
	
	private Double qty;
	
	private Integer index;//排序
	

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getWh() {
		return wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public Integer getCartonId() {
		return cartonId;
	}

	public void setCartonId(Integer cartonId) {
		this.cartonId = cartonId;
	}

	public Integer getStockInPackingId() {
		return stockInPackingId;
	}

	public void setStockInPackingId(Integer stockInPackingId) {
		this.stockInPackingId = stockInPackingId;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	
}
