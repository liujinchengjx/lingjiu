package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IFrameworkSubsystemsDao  extends IBaseDAO{

	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  List selectSubsystems(int systemId) throws Exception;
    /**
	 * 根据用户权限获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  List selectSubsystems(Integer userId,int systemId) throws Exception;
    
    /**
     * 获取子系统集合 有分页
     * @return
     * @throws Exception
     */
    public  PageHelper selectSubsystemsList(int currentPage, int pageSize) throws Exception;
    
    //根据权限值 获取系统名称
    public String getDescription(Integer popedom) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSubsystems(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
