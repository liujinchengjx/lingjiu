package com.liu.qinziyou.dao.systemmanager;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;

public interface IUserDao  extends IBaseDAO{
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String searchValue,Integer userType,Integer flag,int currentPage, int pageSize) throws Exception;
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String positionCode,int currentPage, int pageSize) throws Exception;
    /**
     * 激活用户
     * @return
     * @throws Exception
     */
    public boolean activeUser(int userId,int flag) throws Exception;
    public boolean updatePosition(Integer userId,String positionCode) throws Exception;
    /**
     * 冻结用户
     * @return
     * @throws Exception
     */
    public boolean froze(int userId,int flag) throws Exception;
    /**
     * 删除用户
     * @return
     * @throws Exception
     */
    public boolean delete(int userId,int flag) throws Exception;
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception;
    /**
     * 根据用户名和密码取用户
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public  User getUserByUserNamePassword(String userName,String password,Integer userType) throws Exception;
    
    public  boolean updatePwd(String pwd,Integer userId) throws Exception;
    
    public String getMaxMemberCode() throws Exception ;
}
