package com.liu.qinziyou.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoadCheckPrice2CacheJob {
	protected static  final Log logger = LogFactory.getLog(LoadCheckPrice2CacheJob.class);
	
	public void load() {
		try{
			//ICustomOrderService customOrderService = (ICustomOrderService)BeanLoader.getWebBean("customOrderService");
			//customOrderService.loadCheckPrice2Cache();
			//CheckPriceLoaderManager.getInstance().load();
		}catch(Exception e){
			logger.error("定时加载CheckPrice发生异常"+e);
		}
	}
}