package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IFrameworkModulesDao  extends IBaseDAO{

	/**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int currentPage, int pageSize) throws Exception;
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int systemId,String modulesName,int currentPage, int pageSize) throws Exception;
    
    //根据权限值 获取系统名称
    public String getDescription(Integer popedom) throws Exception;
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModulesList(Integer systemId,String modulesName,Integer inMenu) throws Exception;
    /**
     * 根据用户权限获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModulesList(Integer userId,Integer systemId,String modulesName,Integer inMenu) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchModules(String searchValue,int currentPage, int pageSize) throws Exception;
    
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModules(int systemId) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
