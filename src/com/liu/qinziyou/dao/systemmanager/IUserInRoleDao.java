package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.systemmanager.UserInRole;

public interface IUserInRoleDao  extends IBaseDAO{

	/**
     * 获取用户角色
     * @return
     * @throws Exception
     */
    public  PageHelper selectUserInRole(int currentPage, int pageSize) throws Exception; 
    /**
     * 根据用户Id获取角色集
     * @param userId
     * @return
     * @throws Exception
     */
    public  List selectRolesByUserId(int userId) throws Exception;
    /**
     * 删除用户的某个角色
     * @return
     * @throws Exception
     */
    public boolean deleteUserInRole(Integer userId,Integer roleId) throws Exception;    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUserInRole(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
