package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IPopedomInRoleDao  extends IBaseDAO{

	/**
     * 获取角色权限
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInRole(int currentPage, int pageSize) throws Exception;
    /**
     *  删除roleId 对应的权限popedom值
     * @param roleId
     * @param popedom
     * @return
     * @throws Exception
     */
    public  boolean deletePopedomInRole(Integer roleId,Integer popedom) throws Exception;
    
    /**
     * 根据用户角色roleId,读取到对应的角色权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByRoleId(int roleId) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInRole(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
