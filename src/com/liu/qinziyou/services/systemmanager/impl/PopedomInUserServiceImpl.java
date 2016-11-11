package com.liu.qinziyou.services.systemmanager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.JsonUtil;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IPopedomInUserDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.PopedomInUser;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IPopedomInUserService;

public class PopedomInUserServiceImpl extends AbstractBaseService implements IPopedomInUserService{	
	protected static final Log log = LogFactory.getLog(PopedomServiceImpl.class);
	private IPopedomInUserDao popedomInUserDao;
	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			PopedomInUser piu=(PopedomInUser)baseBean;
			boolean isDuplicate=this.popedomInUserDao.checkForDuplicate(piu);
			if(isDuplicate){
				throw new ServiceException("已有重复的用户权限");
			}
			id=this.popedomInUserDao.save(piu);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加权限用户时发生异常===="+e.getMessage());
			throw new ServiceException("添加权限用户时失败");
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
			PopedomInUser piu=(PopedomInUser)baseBean;
			boolean isDuplicate=this.popedomInUserDao.checkForDuplicate(piu);
			if(isDuplicate){
				throw new ServiceException("已有重复的用户权限");
			}
			this.popedomInUserDao.update(piu);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新权限用户时发生异常===="+e.getMessage());
			throw new ServiceException("更新权限用户时失败");
		}
	}
	/**
     * 获取用户
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInUser(int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.popedomInUserDao.selectPopedomInUser(currentPage, pageSize);
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("查询权限用户分页时发生异常===="+e.getMessage());
    		throw new ServiceException("查询权限用户分页时异常");
    	}
    }
    /**
     * 保存角色权限
     * @param popedomInUsers
     * @throws ServiceException
     */
    public  void savePopedomInUser(String popedomInUsers) throws ServiceException{    	
    	try{
	    	List<PopedomInUser> popedomlst=new ArrayList<PopedomInUser>();
	    	popedomlst=(List<PopedomInUser>)JsonUtil.getListFromJsonArrStr(popedomInUsers,PopedomInUser.class);
	    	Integer flag;
	    	for(int i=0;i < popedomlst.size();i++){
	    		flag=popedomlst.get(i).getFlag();
	    		if (flag.equals(3)){ //要删除
	    			popedomInUserDao.deletePopedomInUser(popedomlst.get(i).getUserId(),popedomlst.get(i).getPopedom());//
	    		}else if (flag.equals(2)){//要新增
	    			popedomInUserDao.save(popedomlst.get(i));
	    		}
	    	}	    	    
	    }catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存用户权限时发生异常===="+e.getMessage());
			throw new ServiceException("保存用户权限时失败");
		}
    }
    /**
     * 根据用户角色roleId,读取到对应的角色权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByUserId(int userId) throws ServiceException{
    	try{
    		return this.popedomInUserDao.selectPopedomsByUserId(userId);
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("获取权限根据用户ID时发生异常===="+e.getMessage());
    		throw new ServiceException("获取权限根据用户ID时异常");
    	}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInUser(String searchValue,int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.popedomInUserDao.searchPopedomInUser(searchValue,currentPage, pageSize);
    	}catch(Exception e){
    		e.printStackTrace();
    		log.error("搜索去权限用户分页时发生异常===="+e.getMessage());
    		throw new ServiceException("搜索去权限用户分页时异常");
    	}
    }
	public IPopedomInUserDao getPopedomInUserDao() {
		return popedomInUserDao;
	}
	public void setPopedomInUserDao(IPopedomInUserDao popedomInUserDao) {
		this.popedomInUserDao = popedomInUserDao;
	}
	
}
