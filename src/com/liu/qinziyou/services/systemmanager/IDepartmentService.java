package com.liu.qinziyou.services.systemmanager;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IDepartmentService extends IBaseService{
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchDepartment(String searchValue,Integer opComanyId,int currentPage, int pageSize) throws ServiceException;
    
}
