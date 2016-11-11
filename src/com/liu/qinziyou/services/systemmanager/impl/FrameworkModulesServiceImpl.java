package com.liu.qinziyou.services.systemmanager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IFrameworkModulesDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkModules;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IFrameworkModulesService;


public class FrameworkModulesServiceImpl extends AbstractBaseService implements IFrameworkModulesService{
	protected static final Log log = LogFactory.getLog(FrameworkModulesServiceImpl.class);
	private IFrameworkModulesDao frameworkModulesDao;	
	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			FrameworkModules fm=(FrameworkModules)baseBean;
			boolean isDuplicate=this.frameworkModulesDao.checkForDuplicate(fm);
			if(isDuplicate){
				throw new ServiceException("已有重复的子模块");
			}
			id=this.add(fm);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加子模块时发生异常===="+e.getMessage());
			throw new ServiceException("添加子模块时失败");
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
			FrameworkModules fm=(FrameworkModules)baseBean;
			boolean isDuplicate=this.frameworkModulesDao.checkForDuplicate(fm);
			if(isDuplicate){
				throw new ServiceException("已有重复的子模块");
			}
			this.frameworkModulesDao.update(fm);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新子模块时发生异常===="+e.getMessage());
			throw new ServiceException("更新子模块时失败");
		}
	}
	/**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.frameworkModulesDao.selectModules(currentPage, pageSize);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("查询子模块分页时发生异常===="+e.getMessage());
			throw new ServiceException("查询子模块分页异常");
		}
    }
	 /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int systemId,String modulesName,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.frameworkModulesDao.selectModules(systemId,modulesName,currentPage, pageSize);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("查询子模块分页时发生异常===="+e.getMessage());
			throw new ServiceException("查询子模块分页异常");
		}
    }

    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModules(int systemId) throws ServiceException{
	    try{
	    	return this.frameworkModulesDao.selectModules(systemId);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("查询子模块时发生异常===="+e.getMessage());
			throw new ServiceException("查询子模块异常");
		}
    }
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModulesList(int systemId,String modulesName) throws ServiceException{
	    try{
	    	return this.frameworkModulesDao.selectModulesList(systemId,modulesName,1);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("查询子模块列表时发生异常===="+e.getMessage());
			throw new ServiceException("查询子模块列表时异常");
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchModules(String searchValue,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.frameworkModulesDao.searchModules(searchValue,currentPage, pageSize);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("搜索子模块列表分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索子模块列表分页时异常");
		}
    }
    
	public IFrameworkModulesDao getFrameworkModulesDao() {
		return frameworkModulesDao;
	}

	public void setFrameworkModulesDao(IFrameworkModulesDao frameworkModulesDao) {
		this.frameworkModulesDao = frameworkModulesDao;
	}
  
}
