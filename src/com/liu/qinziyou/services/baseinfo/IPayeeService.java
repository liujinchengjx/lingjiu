package com.liu.qinziyou.services.baseinfo;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IPayeeService extends IBaseService{
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPayee(String searchValue,int currentPage, int pageSize) throws ServiceException;
}
