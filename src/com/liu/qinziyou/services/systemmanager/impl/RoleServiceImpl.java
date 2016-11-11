package com.liu.qinziyou.services.systemmanager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IRoleDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.Role;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IRoleService;

public class RoleServiceImpl extends AbstractBaseService implements IRoleService{
	protected static final Log log = LogFactory.getLog(RoleServiceImpl.class);
	private IRoleDao roleDao;
	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			Role r=(Role)baseBean;
			boolean isDuplicate=this.roleDao.checkForDuplicate(r);
			if(isDuplicate){
				throw new ServiceException("已有重复的角色");
			}
			id=this.roleDao.save(r);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加角色时发生异常===="+e.getMessage());
			throw new ServiceException("添加角色时失败");
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
			Role r=(Role)baseBean;
			boolean isDuplicate=this.roleDao.checkForDuplicate(r);
			if(isDuplicate){
				throw new ServiceException("已有重复的角色");
			}
			this.roleDao.update(r);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新角色时发生异常===="+e.getMessage());
			throw new ServiceException("更新角色时失败");
		}
	}
    /**
     * 删除角色
     * @return
     * @throws Exception
     */
    public boolean delete(int roleId,int flag) throws ServiceException{
	    try{	
	    	return this.roleDao.delete(roleId,flag);
	    }catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("删除角色时发生异常===="+e.getMessage());
			throw new ServiceException("删除角色时失败");
		}
    }
    
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchRole(String searchValue,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.roleDao.searchRole(searchValue,currentPage, pageSize);
	    }catch (Exception e){
			e.printStackTrace();
			log.error("搜索角色分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索角色分页时失败");
		}
   }

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}    
}
