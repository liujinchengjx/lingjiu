package com.liu.qinziyou.dao.log;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.exception.DaoException;

public interface ILogDao  extends IBaseDAO{
	
	public  PageHelper searchLogLogin(String startTime,String endTime,String searchValue,int currentPage, int pageSize) throws DaoException;
}
