package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;


public interface IProductTaxCodeHisDao  extends IBaseDAO{

	/**
     * 
     * @return
     * @throws Exception
     */
    public  PageHelper selectProductTaxCodeHisList(int currentPage, int pageSize) throws Exception;
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchProductTaxCodeHis(String searchValue,int currentPage, int pageSize) throws Exception;
    
}
