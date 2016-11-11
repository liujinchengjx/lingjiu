package com.liu.qinziyou.services.log;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface ILogService  extends IBaseService{
	public  PageHelper searchLogLogin(String startTime,String endTime,String searchValue,int currentPage, int pageSize) throws ServiceException;
}
