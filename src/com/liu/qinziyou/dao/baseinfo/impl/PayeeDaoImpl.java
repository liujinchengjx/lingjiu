package com.liu.qinziyou.dao.baseinfo.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.IPayeeDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Payee;

public class PayeeDaoImpl extends BaseDAOImpl implements IPayeeDao{

	
	 public  PageHelper searchPayee(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM payee A WHERE 1=1 " ;
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND ((A.CNAME LIKE :searchValue) OR (A.ENAME LIKE :searchValue) ) ";
    		m.put("searchValue","%"+searchValue+"%");
    	}    	
    	sql=sql+" ORDER BY A.LAST_MODIFY_TIME,A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(Payee.class, sql, searchBean, currentPage, pageSize);
    	return ph;	
    }    
    
}
