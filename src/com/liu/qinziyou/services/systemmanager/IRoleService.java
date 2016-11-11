package com.liu.qinziyou.services.systemmanager;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IRoleService extends IBaseService{
    /**
     * 删除角色
     * @return
     * @throws Exception
     */
    public boolean delete(int roleId,int flag) throws ServiceException;
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchRole(String searchValue,int currentPage, int pageSize) throws ServiceException;
    
   
    
}
