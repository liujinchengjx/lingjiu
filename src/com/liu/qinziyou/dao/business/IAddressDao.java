package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface IAddressDao extends IBaseDAO {
	
	/**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchAddress(Integer companyId,String address,int currentPage, int pageSize) throws Exception;
    
    public  void makeIfNotAddress(Integer companyId,String address) throws Exception;
    
    public int delAddress(Integer companyId)throws Exception;
    
    public String getAddress(Integer companyId)throws Exception;
    
	
}
