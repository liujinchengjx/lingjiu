package com.liu.qinziyou.entity.lingju;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;
/**
 * 活动商品表
 * @author liujincheng
 *
 */
@Entity
@Table(name = "coollar_goods")
public class CoollarGoods extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5378346723689556766L;

	@Column(name="GOODS_NAME",nullable=false)	
	private String goodsName;//商品名称
	
	@Column(name="GOODS_DESC",nullable=false)	
	private String goodsDesc;//商品描述
	
	@Column(name="STOCK_CNT",nullable=false)	
	private Integer stockCnt;//库存数量
	
	@Column(name="COOLARED_CNT",nullable=false)	
	private Integer coolaredCnt;//已经领用的数量
	
	@Column(name="MARKET_PRICE",nullable=false)	
	private Integer marketPrice;//领用价
	
	@Column(name="COOLAR_PRICE",nullable=false)	
	private Integer coollarPrice;//领用价
	
	
	@Column(name="LOGISTICS_FEE",nullable=false)	
	private Integer logisticsFee;//物流单价
	
	@Column(name="ACTIVITY_CODE",nullable=true)
	private String activityCode;//活动编号，以这个来区分
	
	@Column(name="PRIZE",nullable=true)
	private Integer prize;//此订单会员获得的奖励 单位是分

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public Integer getStockCnt() {
		return stockCnt;
	}

	public void setStockCnt(Integer stockCnt) {
		this.stockCnt = stockCnt;
	}

	public Integer getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Integer marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Integer getLogisticsFee() {
		return logisticsFee;
	}

	public void setLogisticsFee(Integer logisticsFee) {
		this.logisticsFee = logisticsFee;
	}

	public Integer getCoolaredCnt() {
		return coolaredCnt;
	}

	public void setCoolaredCnt(Integer coolaredCnt) {
		this.coolaredCnt = coolaredCnt;
	}

	public Integer getCoollarPrice() {
		return coollarPrice;
	}

	public void setCoollarPrice(Integer coollarPrice) {
		this.coollarPrice = coollarPrice;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public Integer getPrize() {
		return prize;
	}

	public void setPrize(Integer prize) {
		this.prize = prize;
	}
		
	
}
