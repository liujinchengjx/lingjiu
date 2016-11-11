package com.liu.qinziyou.services.systemmanager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.JsonUtil;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IPopedomInRoleDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.PopedomInRole;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IPopedomInRoleService;

public class PopedomInRoleServiceImpl extends AbstractBaseService implements IPopedomInRoleService{	
	protected static final Log log = LogFactory.getLog(PopedomInRoleServiceImpl.class);
	private IPopedomInRoleDao popedomInRoleDao;
	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			PopedomInRole pir=(PopedomInRole)baseBean;
			boolean isDuplicate=this.popedomInRoleDao.checkForDuplicate(pir);
			if(isDuplicate){
				throw new ServiceException("已有重复的权限角色");
			}
			id=this.popedomInRoleDao.save(pir);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加权限角色时发生异常===="+e.getMessage());
			throw new ServiceException("添加权限角色时失败");
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
			PopedomInRole fb=(PopedomInRole)baseBean;
			boolean isDuplicate=this.popedomInRoleDao.checkForDuplicate(fb);
			if(isDuplicate){
				throw new ServiceException("已有重复的权限角色");
			}
			this.popedomInRoleDao.update(fb);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新权限角色时发生异常===="+e.getMessage());
			throw new ServiceException("更新权限角色时失败");
		}
	}
	
	/**
     * 获取用户
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInRole(int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.popedomInRoleDao.selectPopedomInRole(currentPage, pageSize);
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 log.error("获取权限角色分页时发生异常===="+e.getMessage());
			 throw new ServiceException("获取权限角色分页时出错");
		 }
    }
    /**
     * 保存角色权限
     * @return boolean true， 保存成功，false保存失败
     * @throws Exception
     */
    public  void savePopedomInRoles(String popedomInRoles) throws ServiceException{    	
	    try{
	    	List<PopedomInRole> popedomlst=new ArrayList<PopedomInRole>();
	    	popedomlst=(List<PopedomInRole>)JsonUtil.getListFromJsonArrStr(popedomInRoles,PopedomInRole.class);
	    	Integer flag;
	    	for(int i=0;i < popedomlst.size();i++){
	    		flag=popedomlst.get(i).getFlag();
	    		if (flag.equals(3)){ //要删除
		    		popedomInRoleDao.deletePopedomInRole(popedomlst.get(i).getRoleId(),
		    				 popedomlst.get(i).getPopedom());//
	    		}else if (flag.equals(2)){//要新增
	    			popedomInRoleDao.save(popedomlst.get(i));
	    		}
	    	}	    	    	
	    }catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存权限角色时发生异常===="+e.getMessage());
			throw new ServiceException("保存权限角色时失败");
		}
    }
    /**
     * 根据用户角色roleId,读取到对应的角色权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByRoleId(int roleId) throws ServiceException{
	    try{
	        return this.popedomInRoleDao.selectPopedomsByRoleId(roleId);	
	    }catch (Exception e){
			e.printStackTrace();
			log.error("获取权限角色时发生异常===="+e.getMessage());
			throw new ServiceException("获取权限角色时失败");
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInRole(String searchValue,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.popedomInRoleDao.searchPopedomInRole(searchValue,currentPage, pageSize);
	    }catch (Exception e){
			e.printStackTrace();
			log.error("搜索权限角色分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索权限角色分页时失败");
		}
    }
	public IPopedomInRoleDao getPopedomInRoleDao() {
		return popedomInRoleDao;
	}

	public void setPopedomInRoleDao(IPopedomInRoleDao popedomInRoleDao) {
		this.popedomInRoleDao = popedomInRoleDao;
	}

	

	
}
