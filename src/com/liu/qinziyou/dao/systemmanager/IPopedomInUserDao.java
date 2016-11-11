package com.liu.qinziyou.dao.systemmanager;

import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;

public interface IPopedomInUserDao  extends IBaseDAO{

	/**
     * 获取用户权限
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInUser(int currentPage, int pageSize) throws Exception;
    /**
     *  删除userId 对应的权限popedom值
     * @param userId
     * @param popedom
     * @return
     * @throws Exception
     */
    public  boolean deletePopedomInUser(Integer userId,Integer popedom) throws Exception;
    /**
     * 根据用户用户userId,读取到对应的用户权限
     * @param userId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByUserId(int userId) throws Exception;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInUser(String searchValue,int currentPage, int pageSize) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
}
