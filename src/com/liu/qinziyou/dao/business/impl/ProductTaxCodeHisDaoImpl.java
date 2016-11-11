package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IProductTaxCodeHisDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ProductTaxCode;

public class ProductTaxCodeHisDaoImpl extends BaseDAOImpl implements IProductTaxCodeHisDao{
	/**
     * 
     * @return
     * @throws Exception
     */
    public  PageHelper selectProductTaxCodeHisList(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM producttaxcode_his A ORDER BY A.ID ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(ProductTaxCode.class, sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchProductTaxCodeHis(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM producttaxcode_his A WHERE (A.PRODUCT_NAME LIKE :productName) OR (A.CODE_TS LIKE :codeTs) OR (A.CODE_T LIKE :codet) ORDER BY A.ID DESC";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("productName", "%"+searchValue+"%");
    	m.put("codeTs", "%"+searchValue+"%");
    	m.put("codet", "%"+searchValue+"%");
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(ProductTaxCode.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    }
