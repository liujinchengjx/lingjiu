package com.liu.qinziyou.services.log.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.log.ILogDao;
import com.liu.qinziyou.exception.DaoException;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.log.ILogService;

public class LogServiceImpl extends AbstractBaseService implements  ILogService{
	protected static final Log log = LogFactory.getLog(LogServiceImpl.class);
	private ILogDao logDao;
	
	public ILogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	public PageHelper searchLogLogin(String startTime,String endTime,String searchValue,int currentPage, int pageSize) throws ServiceException {
		try{
			return logDao.searchLogLogin(startTime, endTime, searchValue, currentPage, pageSize);
		}catch(DaoException e){
			log.equals("查询登录日志出错！");
			throw new ServiceException("查询登录日志出错！");
		}
	}
	
}
