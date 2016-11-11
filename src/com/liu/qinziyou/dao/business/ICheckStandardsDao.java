package com.liu.qinziyou.dao.business;


import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface ICheckStandardsDao  extends IBaseDAO{	    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
	 public  PageHelper searchCheckStandards(int currentPage, int pageSize) throws Exception;
}
