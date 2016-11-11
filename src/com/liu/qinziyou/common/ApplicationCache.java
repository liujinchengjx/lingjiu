package com.liu.qinziyou.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.liu.qinziyou.exception.ServiceException;




public class ApplicationCache {
	private static final Logger logger = Logger.getLogger(ApplicationCache.class);
	private static ApplicationCache cache  = new ApplicationCache();
	private boolean checkPriceCacheReady=false;//价格比对缓存数据加载完成标志
	private String checkPriceCacheVersion;//价格比对缓存版本  用于数据跟踪分析
	public static ApplicationCache getInstance() {
		return cache;
	}

	
	
	
	public boolean isCheckPriceCacheReady() {
		return checkPriceCacheReady;
	}
	public void setCheckPriceCacheReady(boolean checkPriceCacheReady) {
		this.checkPriceCacheReady = checkPriceCacheReady;
	}
	public String getCheckPriceCacheVersion() {
		return checkPriceCacheVersion;
	}
	public void setCheckPriceCacheVersion(String checkPriceCacheVersion) {
		this.checkPriceCacheVersion = checkPriceCacheVersion;
	}
	
	
	
}
