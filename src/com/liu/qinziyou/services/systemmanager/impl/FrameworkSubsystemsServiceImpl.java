package com.liu.qinziyou.services.systemmanager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IFrameworkSubsystemsDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkSubsystems;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IFrameworkSubsystemsService;


public class FrameworkSubsystemsServiceImpl extends AbstractBaseService implements IFrameworkSubsystemsService{
	protected static final Log log = LogFactory.getLog(FrameworkSubsystemsServiceImpl.class);
	private IFrameworkSubsystemsDao frameworkSubsystemsDao;	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			FrameworkSubsystems fs=(FrameworkSubsystems)baseBean;
			boolean isDuplicate=this.frameworkSubsystemsDao.checkForDuplicate(fs);
			if(isDuplicate){
				throw new ServiceException("已有重复的模块");
			}
			id=this.frameworkSubsystemsDao.save(fs);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加模块时发生异常===="+e.getMessage());
			throw new ServiceException("添加模块时失败");
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
			FrameworkSubsystems fs=(FrameworkSubsystems)baseBean;
			boolean isDuplicate=this.frameworkSubsystemsDao.checkForDuplicate(fs);
			if(isDuplicate){
				throw new ServiceException("已有重复的模块");
			}
			this.frameworkSubsystemsDao.update(fs);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新模块时发生异常===="+e.getMessage());
			throw new ServiceException("更新模块时失败");
		}
	}
	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  List selectSubsystems(int systemId) throws ServiceException{
		try{
			return this.frameworkSubsystemsDao.selectSubsystems(systemId);
		 }catch(Exception e){
			 e.printStackTrace();
			 log.error("获取模块列表时发生异常===="+e.getMessage());
			 throw new ServiceException("获取模块列表时出错");
		 }
    }

    /**
     * 获取子系统集合 有分页
     * @return
     * @throws Exception
     */
    public  PageHelper selectSubsystemsList(int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.frameworkSubsystemsDao.selectSubsystemsList(currentPage,pageSize);
    	}catch(Exception e){			
    		e.printStackTrace();
    		log.error("获取模块列表分页时发生异常===="+e.getMessage());
			throw new ServiceException("获取模块列表分页出错");
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSubsystems(String searchValue,int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.frameworkSubsystemsDao.searchSubsystems(searchValue,currentPage,pageSize);
	    }catch(Exception e){			
	    	e.printStackTrace();
	    	log.error("搜索模块列表分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索模块列表分页时出错");
		}
    }
    
	public IFrameworkSubsystemsDao getFrameworkSubsystemsDao() {
		return frameworkSubsystemsDao;
	}

	public void setFrameworkSubsystemsDao(
			IFrameworkSubsystemsDao frameworkSubsystemsDao) {
		this.frameworkSubsystemsDao = frameworkSubsystemsDao;
	}
	
  
}
