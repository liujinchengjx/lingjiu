package com.liu.qinziyou.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.util.LoginCache;


public class ClientContextListener implements ServletContextListener{

	private final static Log logger = LogFactory.getLog(ClientContextListener.class);
	
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.info("ClientContextListener Destroyed");
		LoginCache.getInstance().endCheckThread();
		
		
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		logger.info("ClientContextListener contextInitialized");
		LoginCache.getInstance().startCheckThread();
	}
	

}
