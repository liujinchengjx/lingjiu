package com.liu.qinziyou.common.util;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 初始化参数类，这是个单例。在application-const.xml中配置，交给spring容器管理
 * liujc
 * 2013-3-20
 */
public final class InitParameter {
	private static Log logger = LogFactory.getLog(InitParameter.class);

	private  HashMap<String, String> systemConfigDatas;

	public HashMap<String, String> getSystemConfigDatas() {
		return systemConfigDatas;
	}

	public void setSystemConfigDatas(HashMap<String, String> systemConfigDatas) {
		this.systemConfigDatas = systemConfigDatas;
	}
	



	
}
