package com.liu.qinziyou.services.systemmanager.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.JsonUtil;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IUserInRoleDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.UserInRole;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IUserInRoleService;


public class UserInRoleServiceImpl extends AbstractBaseService implements IUserInRoleService{
	protected static final Log log = LogFactory.getLog(UserInRoleServiceImpl.class);
	private IUserInRoleDao userInRoleDao;	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			UserInRole uir=(UserInRole)baseBean;
			boolean isDuplicate=this.userInRoleDao.checkForDuplicate(uir);
			if(isDuplicate){
				throw new ServiceException("已有重复的用户角色");
			}
			id=this.userInRoleDao.save(uir);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加用户角色时发生异常===="+e.getMessage());
			throw new ServiceException("添加用户角色失败");
		}
		return id;
	}
	/**
	 * 修改
	 * @param baseBean
	 * @throws ServiceException
	 */
	public void update(IBean baseBean) throws ServiceException{
		try{
			UserInRole uir=(UserInRole)baseBean;
			boolean isDuplicate=this.userInRoleDao.checkForDuplicate(uir);
			if(isDuplicate){
				throw new ServiceException("已有重复的用户角色");
			}
			this.userInRoleDao.update(uir);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加用户角色时发生异常===="+e.getMessage());
			throw new ServiceException("更新用户角色时失败");
		}
	}
	/**
     *获取用户角色
     * @return
     * @throws Exception
     */
    public  PageHelper selectUserInRole(int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.userInRoleDao.selectUserInRole(currentPage, pageSize);
    	}catch (Exception e){
			e.printStackTrace();
			log.error("获取用户角色分页时发生异常===="+e.getMessage());
			throw new ServiceException("获取用户角色分页时失败");
		}
    }
    /**
     * 根据用户Id获取角色集
     * @param userId
     * @return
     * @throws Exception
     */
    public  List selectRolesByUserId(int userId) throws ServiceException{
    	try{
    		return this.userInRoleDao.selectRolesByUserId(userId);
    	}catch (Exception e){
			e.printStackTrace();
			log.error("获取用户角色时发生异常===="+e.getMessage());
			throw new ServiceException("获取用户角色时失败");
		}
    }
    /**
     * 保存用户角色
     * @return 
     * @throws Exception
     */
    public  void saveUserInRole(String userInRoles) throws ServiceException{	
	    try{
	    	List<UserInRole> uRolelst=(List<UserInRole>)JsonUtil.getListFromJsonArrStr(userInRoles,UserInRole.class);
	    	Integer flag;
	    	if (uRolelst!=null){
	    		for(BaseBean bean:uRolelst){
	    			UserInRole ur=(UserInRole)bean;
	    			flag=ur.getFlag();
		    		if (flag.equals(3)){ //要删除
		    			userInRoleDao.deleteUserInRole(ur.getUserId(),ur.getRoleId());// 批量保存之前，先删掉权限
		    		}else if (flag.equals(2)){//要新增
		    			userInRoleDao.save(ur);
		    		}
	    		}
	    	}
	    }catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存用户角色时发生异常===="+e.getMessage());
			throw new ServiceException("保存角色时失败");
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUserInRole(String searchValue,int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.userInRoleDao.searchUserInRole(searchValue,currentPage, pageSize);
    	}catch (Exception e){
			e.printStackTrace();
			log.error("搜索用户角色分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索用户角色分页时是不");
		}
    }

	public IUserInRoleDao getUserInRoleDao() {
		return userInRoleDao;
	}

	public void setUserInRoleDao(IUserInRoleDao userInRoleDao) {
		this.userInRoleDao = userInRoleDao;
	}

	
}
