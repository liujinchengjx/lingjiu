package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.ILinkManDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.LinkMan;

public class LinkManDaoImpl extends BaseDAOImpl implements ILinkManDao{	
	/**
     * 搜索
     * @return
     * @throws Exception
     */
	 public  PageHelper searchLinkMan(Integer companyId,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM linkman A WHERE 1=1 " ;
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((companyId!=null) && (companyId.intValue()>0)){
    		sql=sql+" AND A.COMPANY_ID =:companyId ";	
    		m.put("companyId",companyId);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(LinkMan.class, sql, searchBean, currentPage, pageSize);
    	return ph;	
    }
    
  
}
