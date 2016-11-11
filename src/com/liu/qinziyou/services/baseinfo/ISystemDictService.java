package com.liu.qinziyou.services.baseinfo;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface ISystemDictService extends IBaseService{
	   
    
    /**
     * 根据Type读取字典列表
     * @param type
     * @return
     * @throws Exception
     */
    public List selectDictByType(String type) throws ServiceException ;
    /**
     * 更改字典的状态
     * @param systemDictId
     * @param flag
     */
    public void updateFlag(Integer systemDictId,Integer flag) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSystemDict(String searchValue,String type,int currentPage, int pageSize) throws ServiceException;
    
}
