package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.IBean;


public interface IProductTaxCodeWebGrabDao  extends IBaseDAO{
	public  PageHelper selectProductTaxCodeWebGrabList(String startDate,String endDate,int currentPage, int pageSize) 
	     throws Exception;
	/**
	 * 获取最新的商品编码
	 * @param codeTs
	 * @return
	 * @throws Exception
	 */
	public  IBean getProductTaxCodeWebGrabLatest(String codeTs)throws Exception; 
    
}
