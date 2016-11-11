package com.liu.qinziyou.services.baseinfo.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.IAreaDictDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.baseinfo.AreaDict;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.baseinfo.IAreaDictService;

public class AreaDictServiceImpl extends AbstractBaseService implements IAreaDictService{
	protected static final Log log = LogFactory.getLog(AreaDictServiceImpl.class);
	
	private IAreaDictDao areaDictDao;
	
	public Integer add(IBean baseBean) throws ServiceException{
		Integer id=0;
		try{
			AreaDict ad = (AreaDict) baseBean;
			if(areaDictDao.checkForDuplicate(ad)){
				throw new ServiceException("字典重复");
			}
			id=areaDictDao.save(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存时发生异常===="+e.getMessage());
			throw new ServiceException("保存时失败");
		}
		return id;
	}
	/**
     * 获取字典集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectAreaDict(int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.areaDictDao.selectAreaDict(currentPage, pageSize);
    	}catch(Exception e){
    		e.printStackTrace();
			log.error("查询字典发生异常===="+e.getMessage());
			throw new ServiceException("查询字典发生异常");
    	}
    	
    }
    /**
     * 根据级别和父级Id读取地区
     * @param level
     * @param parentId
     * @return
     * @throws Exception
     */
     public List selectAreaByPLevel(int level,int parentId) throws ServiceException{
    	 try{
    		 return this.areaDictDao.selectAreaByPLevel(level,parentId);
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 log.error("根据级别和父级Id读取地区发生异常===="+e.getMessage());
    		 throw new ServiceException("根据级别和父级Id读取地区异常"+e.getMessage());
    	 }
     }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchAreaDict(String searchValue,int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.areaDictDao.searchAreaDict(searchValue,currentPage, pageSize);
    	}catch(Exception e){
   		 	e.printStackTrace();
   		 	log.error("搜索取地区发生异常===="+e.getMessage());
   		 	throw new ServiceException("搜索取地区异常");
    	}
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws ServiceException{
    	try{
    		return this.areaDictDao.checkForDuplicate(baseBean);
    	}catch(Exception e){
   		 	e.printStackTrace();
   		 	log.error("搜索取地区发生异常===="+e.getMessage());
   		 	throw new ServiceException("搜索取地区异常");
    	}
    	
    }

	public IAreaDictDao getAreaDictDao() {
		return areaDictDao;
	}

	public void setAreaDictDao(IAreaDictDao areaDictDao) {
		this.areaDictDao = areaDictDao;
	}
    
}
