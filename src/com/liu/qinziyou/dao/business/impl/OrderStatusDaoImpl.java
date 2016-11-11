package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IOrderStatusDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.OrderStatus;

public class OrderStatusDaoImpl extends BaseDAOImpl implements IOrderStatusDao{
	
	public  PageHelper searchOrderStatus(Integer customerId,Integer orderType,Integer [] orderIds,
			String startTime,String endTime,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM order_status A WHERE 1=1 " ;
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((customerId!=null) && (customerId.intValue()>0)){
    		sql=sql+" AND A.CUSTOMER_ID =:customerId ";	
    		m.put("customerId", customerId);
    	}
    	if ((orderType!=null) && (orderType.intValue()>0)){
    		sql=sql+" AND A.ORDER_TYPE =:orderType ";	
    		m.put("orderType", orderType);
    	}
    	if ((orderIds!=null) && (orderIds.length>0)){
    		sql=sql+" AND A.ORDERID IN (:orderIds) ";	
    		m.put("orderIds", orderIds);
    	}
    	if((startTime!=null) && (!"".equals(startTime))){
    		sql=sql+" AND A.CREATE_TIME >= :startTime ";
    		m.put("startTime", startTime+" 00:00:00");
    	}
    	if ((endTime!=null) && (!"".equals(endTime))){
    		sql=sql+" AND A.CREATE_TIME <= :endTime ";
    		m.put("endTime", endTime+" 23:59:59");
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(OrderStatus.class, sql, searchBean, currentPage, pageSize);
    	return ph;	
    }
  
}
