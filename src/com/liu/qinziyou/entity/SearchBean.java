package com.liu.qinziyou.entity;

import java.util.Map;

public class SearchBean {
	
	private Integer cacheType;
	/**
	 * 查询参数值映射
	 */
	private Map<String, Object> filterMap;
	
	public Integer getCacheType() {
		return cacheType;
	}
	public void setCacheType(Integer cacheType) {
		this.cacheType = cacheType;
	}
	public Map<String, Object> getFilterMap() {
		return filterMap;
	}
	public void setFilterMap(Map<String, Object> filterMap) {
		this.filterMap = filterMap;
	}

	
}
