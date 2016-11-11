package com.liu.qinziyou.services.baseinfo;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IAreaDictService extends IBaseService{

	 /**
     * 获取字典集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectAreaDict(int currentPage, int pageSize) throws ServiceException;    
   /**
    * 根据级别和父级Id读取地区
    * @param level
    * @param parentId
    * @return
    * @throws Exception
    */
    public List selectAreaByPLevel(int level,int parentId) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchAreaDict(String searchValue,int currentPage, int pageSize) throws ServiceException;
    
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws ServiceException;
    
}
