package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IFrameworkSubsystemsService extends IBaseService{

	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  List selectSubsystems(int systemId) throws ServiceException;    
    
    /**
     * 获取子系统集合 有分页
     * @return
     * @throws Exception
     */
    public  PageHelper selectSubsystemsList(int currentPage, int pageSize) throws ServiceException;    
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSubsystems(String searchValue,int currentPage, int pageSize) throws ServiceException;

}
