package com.liu.qinziyou.dao.business;


import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;


public interface ILinkManDao  extends IBaseDAO{	    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
	 public  PageHelper searchLinkMan(Integer companyId,int currentPage, int pageSize) throws Exception;
    
}
