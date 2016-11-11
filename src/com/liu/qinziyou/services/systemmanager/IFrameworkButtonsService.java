package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IFrameworkButtonsService extends IBaseService{

	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  PageHelper selectButtons(int systemId,String modules,int currentPage, int pageSize) throws ServiceException;  
    /**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  PageHelper selectButtons(String modules,int currentPage, int pageSize) throws ServiceException;
   
    /**
     *  加载页面工具栏
     * @param modules
     * @param toolBarSeq
     * @return
     * @throws ServiceException
     */
    public List loadButtonsList(Integer userId,String modules,Integer toolBarSeq) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchButtons(String searchValue,int currentPage, int pageSize) throws ServiceException;
   
    
}
