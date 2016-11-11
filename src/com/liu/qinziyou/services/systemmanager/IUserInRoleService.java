package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IUserInRoleService extends IBaseService{
	 
	 /**
     * 获取用户角色
     * @return
     * @throws Exception
     */
    public  PageHelper selectUserInRole(int currentPage, int pageSize) throws ServiceException;
    
    /**
     * 根据用户Id获取角色集
     * @param userId
     * @return
     * @throws Exception
     */
    public  List selectRolesByUserId(int userId) throws ServiceException;
    /**
     * 保存用户角色
     * @return 
     * @throws Exception
     */
    public  void saveUserInRole(String userInRoles) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUserInRole(String searchValue,int currentPage, int pageSize) throws ServiceException;
    
}
