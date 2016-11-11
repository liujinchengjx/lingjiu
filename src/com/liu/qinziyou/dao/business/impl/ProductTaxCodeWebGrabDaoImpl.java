package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.business.IProductTaxCodeWebGrabDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ProductTaxCode;

public class ProductTaxCodeWebGrabDaoImpl extends BaseDAOImpl implements IProductTaxCodeWebGrabDao{

	/**
     * 获取商品编码列表
     * @return
     * @throws Exception
     */
    public  PageHelper selectProductTaxCodeWebGrabList(String startDate,String endDate,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM producttaxcode_web_grab A WHERE 1=1";
    	Map<String, Object> m=new HashMap<String, Object>();
    	if(!StringUtil.isNull(startDate)){
    		sql = sql + " AND A.GRABTIME >=:startDate";
    		m.put("startDate", startDate);
    	}
    	if(!StringUtil.isNull(endDate)){
    		sql = sql + " AND A.GRABTIME <= :endDate";
    		m.put("endDate", startDate);

    	}
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(ProductTaxCode.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    /**
	 * 获取最新的商品编码
	 * @param codeTs
	 * @return
	 * @throws Exception
	 */
	public  IBean getProductTaxCodeWebGrabLatest(String codeTs)throws Exception{
		
		return null;
	}
    
}
