package com.liu.qinziyou.services.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IPopedomService extends IBaseService{

	 /**
     * 获取字典集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomList(int currentPage, int pageSize) throws ServiceException;
    /**
     * 读取权限树
     * @return
     * @throws ServiceException
     */
    public  List loadPopedomTree() throws ServiceException;    
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedom(String searchValue,int currentPage, int pageSize) throws ServiceException;
    
}
