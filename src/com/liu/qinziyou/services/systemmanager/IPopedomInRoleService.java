package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IPopedomInRoleService extends IBaseService{

	/**
     * 获取角色权限
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInRole(int currentPage, int pageSize) throws ServiceException;
    /**
     * 保存角色权限
     * @return 
     * @throws Exception
     */
    public  void savePopedomInRoles(String popedomInRoles) throws ServiceException;
    /**
     * 根据用户角色roleId,读取到对应的角色权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByRoleId(int roleId) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInRole(String searchValue,int currentPage, int pageSize) throws ServiceException;    
    
}
