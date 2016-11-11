package com.liu.qinziyou.services.systemmanager.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IFrameworkButtonsDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkButtons;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IFrameworkButtonsService;


public class FrameworkButtonsServiceImpl extends AbstractBaseService implements IFrameworkButtonsService{	
	protected static final Log log = LogFactory.getLog(FrameworkButtonsServiceImpl.class);
	private IFrameworkButtonsDao frameworkButtonsDao;	
	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			FrameworkButtons fb=(FrameworkButtons)baseBean;
			boolean isDuplicate=this.frameworkButtonsDao.checkForDuplicate(fb);
			if(isDuplicate){
				throw new ServiceException("已有重复的功能");
			}
			id=this.frameworkButtonsDao.save(fb);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加功能按钮时发生异常===="+e.getMessage());
			throw new ServiceException("添加功能按钮时失败");
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
			FrameworkButtons fb=(FrameworkButtons)baseBean;
			boolean isDuplicate=this.frameworkButtonsDao.checkForDuplicate(fb);
			if(isDuplicate){
				throw new ServiceException("已有重复的功能");
			}
			this.frameworkButtonsDao.update(fb);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新功能按钮时发生异常===="+e.getMessage());
			throw new ServiceException("更新功能按钮时失败");
		}
	}
	/**
	 * 获取功能集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
	 public  PageHelper selectButtons(int systemId,String modules,int currentPage, int pageSize)  throws ServiceException{
		 try{
			 return this.frameworkButtonsDao.selectButtons(systemId,modules,currentPage,pageSize);
		 }catch(Exception e){			
			 e.printStackTrace();
			 log.error("获取功能列表分页时发生异常===="+e.getMessage());
			 throw new ServiceException("获取功能列表分页出错");
		 }
    }
	 /**
     *  加载页面工具栏
     * @param modules
     * @param toolBarSeq
     * @return
     * @throws ServiceException
     */
    public List loadButtonsList(Integer userId,String modules,Integer toolBarSeq) throws ServiceException{
    	try {
			//List buttonsList = frameworkButtonsDao.loadButtonsList(modules,toolBarSeq);
			List buttonsList = frameworkButtonsDao.loadButtonsList(userId,modules, toolBarSeq);
			return buttonsList;
		}catch(Exception e){			
			 e.printStackTrace();
			 log.error("加载功能按钮时发生异常===="+e.getMessage());
			 throw new ServiceException("加载功能按钮时出错");
		 }
    }
	 /**
	 * 获取功能统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  PageHelper selectButtons(String modules,int currentPage, int pageSize) throws ServiceException{
    	 try{
    		 return this.frameworkButtonsDao.selectButtons(modules, currentPage, pageSize);
    	 }catch(Exception e){	
    		 e.printStackTrace();
    		 log.error("获取功能列表分页时发生异常===="+e.getMessage());
			 throw new ServiceException("获取功能列表分页出错");
		 }
    }
	 /**
     * 搜索
     * @return
     * @throws Exception
     */
  public  PageHelper searchButtons(String searchValue,int currentPage, int pageSize) throws ServiceException{
	  try{
		  return this.frameworkButtonsDao.searchButtons(searchValue, currentPage, pageSize);
	  }catch(Exception e){			
		  e.printStackTrace();
		  log.error("搜索功能列表分页时发生异常===="+e.getMessage());
		  throw new ServiceException("搜索功能列表分页出错");
	  }
  }
	 
	public IFrameworkButtonsDao getFrameworkButtonsDao() {
		return frameworkButtonsDao;
	}

	public void setFrameworkButtonsDao(IFrameworkButtonsDao frameworkButtonsDao) {
		this.frameworkButtonsDao = frameworkButtonsDao;
	}
	
  
}
