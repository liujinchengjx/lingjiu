package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.systemmanager.PopedomInUser;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IPopedomInUserService extends IBaseService{

	/**
     * 获取用户权限
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInUser(int currentPage, int pageSize) throws ServiceException;
    /**
     * 保存角色权限
     * @param popedomInUsers
     * @throws ServiceException
     */
    public  void savePopedomInUser(String popedomInUsers) throws ServiceException;
    /**
     * 根据用户用户Id,读取到对应的用户单独的权限
     * @param userId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByUserId(int userId) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInUser(String searchValue,int currentPage, int pageSize) throws ServiceException;    
    
}
