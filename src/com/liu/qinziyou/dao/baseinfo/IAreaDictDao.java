package com.liu.qinziyou.dao.baseinfo;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IAreaDictDao extends IBaseDAO{

	/**
     * 获取字典集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectAreaDict(int currentPage, int pageSize) throws Exception;
    /**
     * 根据级别和父级Id读取地区
     * @param level
     * @param parentId
     * @return
     * @throws Exception
     */
     public List selectAreaByPLevel(int level,int parentId) throws Exception;
     /**
      * 获取地区名称
      * @param areaId
      * @return
      * @throws Exception
      */
     public String getAreaName(Integer areaId) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchAreaDict(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
