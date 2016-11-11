package com.liu.qinziyou.dao.lingju.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.lingju.ICoollarMemberDao;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.lingju.CoollarGoods;
import com.liu.qinziyou.exception.ServiceException;

public class CoollarMemberDao  extends BaseDAOImpl implements ICoollarMemberDao{

	@Override
	public PageHelper searchCoollarMember(String searchValue, Integer state, int currentPage, int pageSize)
			throws ServiceException {
		String sql="SELECT A.* FROM coollar_member A  WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (A.MEMBER_CODE LIKE :searchValue OR A.MEMBER_NAME LIKE :searchValue OR A.PHONE_NUMBER LIKE :searchValue OR A.EMAIL LIKE :searchValue )";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	
    	if ((state!=null) && (state.intValue()>0)){
    		sql=sql+" AND A.STATE =:state ";
    		m.put("state",state);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(CoollarGoods.class,sql, searchBean, currentPage, pageSize);
    	return ph;	
	}
	
}
