package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IFrameworkModulesService extends IBaseService{

	/**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int currentPage, int pageSize) throws ServiceException;
	 /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int systemId,String modulesName,int currentPage, int pageSize) throws ServiceException;    
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModulesList(int systemId,String modulesName) throws ServiceException;
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModules(int systemId) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchModules(String searchValue,int currentPage, int pageSize) throws ServiceException;
}
