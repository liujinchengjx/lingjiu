package com.liu.qinziyou.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BeanLoader implements ApplicationContextAware{
	private final static Log logger = LogFactory.getLog(BeanLoader.class);
	private static ApplicationContext applicationContext = null;
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		logger.info("set applicationContext for BeanLoader");
		this.applicationContext = arg0;
		 
	}
	public static Object getWebBean(String beanName) {
		if(applicationContext==null){
			initCxt();
		}
		return applicationContext.getBean(beanName);
	}
	
	public static Object getWebBean(String beanName, Class clazz) {
		return applicationContext.getBean(beanName, clazz);
	}
	
	private static void initCxt(){
		String configFileNames [] = {
				"applicationContext.xml"
		};
		applicationContext = new ClassPathXmlApplicationContext(configFileNames);;
	}
	
	
	
}
