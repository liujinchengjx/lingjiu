package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.dao.business.ICompanyRelationDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;

public class CompanyRelationDaoImpl extends BaseDAOImpl implements ICompanyRelationDao{

	/**
	 * 根据seller供方的Id获取需方公司的Id
	 * @param sellerId
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public Integer getBuyerId(Integer sellerId)  throws Exception{
		String sql="SELECT DISTINCT A.BUYER_ID FROM company_relation A WHERE SELLER_ID=:sellerId";	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("sellerId", sellerId);
    	searchBean.setFilterMap(m);
    	Integer tempId =(Integer)this.getAttributeValueBySql(sql, searchBean);
    	return tempId.intValue();
	}
	/**
	 * 删除关系 ，根据供方，需方 id
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteCompanyRelation(Integer sellerId,Integer buyerId)  throws Exception{
		String sql="DELETE FROM company_relation WHERE SELLER_ID=:sellerId AND BUYER_ID=:buyerId ";	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("sellerId", sellerId);
    	m.put("buyerId", buyerId);
    	searchBean.setFilterMap(m);
    	Integer tempId =this.executeSQL(sql, searchBean);
    	return tempId.intValue();
	}
	/**
	 * 根据buyer需方的Id获取供方公司的Id
	 * @param sellerId
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public Integer getSellerId(Integer buyerId)  throws Exception{
		String sql="SELECT DISTINCT A.SELLER_ID FROM company_relation A WHERE BUYER_ID=:buyerId";	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("buyerId", buyerId);
    	searchBean.setFilterMap(m);
    	Integer tempId =(Integer)this.getAttributeValueBySql(sql, searchBean);
    	return tempId.intValue();
	}
}
