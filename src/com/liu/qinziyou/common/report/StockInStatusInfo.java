package com.liu.qinziyou.common.report;

import java.util.List;

public class StockInStatusInfo {
	// 状态--00000全部OK入库00001，未入库00001
	private String Code;
	// 未入库单号与目前状态
	private List<StockInGoodStatus> status;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public List<StockInGoodStatus> getStatus() {
		return status;
	}

	public void setStatus(List<StockInGoodStatus> status) {
		this.status = status;
	}

	
}
