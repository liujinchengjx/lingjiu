package com.liu.qinziyou.services.systemmanager;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IUserService extends IBaseService{
	/**
     * 保存用户
     * @return
     * @throws Exception
     */
    public void saveUser(IBean baseBean) throws ServiceException;
    
    public void updatePosition(Integer userId,String positionCode) throws ServiceException;
    /**
     * 激活用户
     * @return
     * @throws Exception
     */
    public boolean activeUser(int userId,int flag) throws ServiceException;
    /**
     * 删除用户
     * @return
     * @throws Exception
     */
    public boolean delete(int userId,int flag) throws ServiceException;
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String searchValue,Integer userType,Integer flag,
    		int currentPage, int pageSize) throws ServiceException;
    
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String positionCode,int currentPage, int pageSize) throws ServiceException;
    
    
    public boolean changePassword(Integer userId,String orgPasswod,String newPassword) throws ServiceException;
    public boolean resetPwd(Integer userId) throws ServiceException;
    
    /**
     * 用户登录
     * @param userName
     * @param password
     * @return 登录成功返回对应的user信息 否则，抛异常。
     * @throws ServiceException
     */
    public User userLogin(String userName,String password,Integer userType) throws ServiceException;
    
    /**
     * 根据ID取用户信息
     * @param userId
     * @return
     * @throws ServiceException
     */
    public User getUserInfo(Integer userId) throws ServiceException;
    
}
