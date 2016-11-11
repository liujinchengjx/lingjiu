package com.liu.qinziyou.dao.business;


import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface IOrderStatusDao  extends IBaseDAO{	    
	public  PageHelper searchOrderStatus(Integer customerId,Integer orderType,Integer [] orderIds,
			String startTime,String endTime,int currentPage, int pageSize) throws Exception;
}
