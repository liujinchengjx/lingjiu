package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IPopedomDao  extends IBaseDAO{

	/**
     * 获取权限集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomList(int currentPage, int pageSize) throws Exception;
    
    /**
     * 获取所有权限集合
     * @return
     * @throws Exception
     */
    public  List selectPopedomList() throws Exception;
    /**
     * 获取所有权限集合
     * @return
     * @throws Exception
     */
    public  List selectPopedomList(Integer popedomGroup,Integer moduleGroup) throws Exception;
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedom(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
