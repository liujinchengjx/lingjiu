package com.liu.qinziyou.dao.baseinfo;


import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;


public interface IPayeeDao  extends IBaseDAO{	    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
	 public  PageHelper searchPayee(String searchValue,int currentPage, int pageSize) throws Exception;       
}
