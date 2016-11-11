package com.liu.qinziyou.dao.lingju;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.exception.ServiceException;

public interface ICoollarRecordDao extends  IBaseDAO{
	public PageHelper searchCoollarRecord(String searchValue,String  memberCode,Integer state,Integer fleightState, int currentPage, int pageSize)
			throws ServiceException ;
	
	public String getMaxOrderCodeOfCurrentDay() throws ServiceException ;
	
	public int changeState(Integer state, Integer id) throws ServiceException ;
	
	public int fleight(String fleightCompany,String fleightNumber,Integer id) throws ServiceException;
}
