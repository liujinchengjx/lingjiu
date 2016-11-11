package com.liu.qinziyou.common.aop;

import org.apache.log4j.Logger;

/**
 * Action 工厂用于生产action
 * @author liujc
 *2013-3-1
 */
public class ActionFactory {

	private static final Logger logger = Logger.getLogger(ActionFactory.class);

	public static Object getAction(String clzName) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		Object obj = null;
		try {
			Class cls = Class.forName(clzName);
			obj = (Object) cls.newInstance();
		} catch (ClassNotFoundException e) {
			logger.error(e.getStackTrace());
			throw e;
		} catch (InstantiationException e) {
			logger.error(e.getStackTrace());
			throw e;
		} catch (IllegalAccessException e) {
			logger.error(e.getStackTrace());
			throw e;
		}
		return obj;
	}
}
