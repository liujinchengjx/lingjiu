package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IValueAddedServicesDiscountDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ValueAddedServicesDiscount;

public class ValueAddedServicesDiscountDaoImpl extends BaseDAOImpl implements IValueAddedServicesDiscountDao {

	@Override
	public PageHelper searchValueAddedServicesDiscount(Integer customerId, int currentPage, int pageSize) throws Exception {
		String sql="SELECT A.* FROM value_added_services_discount A WHERE 1=1 " ;
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((customerId!=null) && (customerId.intValue()>0)){
    		sql=sql+" AND A.CUSTOMER_ID =:companyId ";
    		m.put("companyId",customerId);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(ValueAddedServicesDiscount.class, sql, searchBean, currentPage, pageSize);
    	return ph;	
	}

}
