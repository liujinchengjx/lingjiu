package com.liu.qinziyou.services.baseinfo.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.Constants;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.ISystemDictDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.baseinfo.SystemDict;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.baseinfo.ISystemDictService;

public class SystemDictServiceImpl extends AbstractBaseService implements ISystemDictService{
	protected static final Log log = LogFactory.getLog(SystemDictServiceImpl.class);
	private ISystemDictDao systemDictDao;
	
	
	public Integer add(IBean baseBean) throws ServiceException{
		Integer id=0;
		try{
			SystemDict sd = (SystemDict)baseBean;
			boolean isDuplicate = systemDictDao.checkForDuplicate(sd);
			if(isDuplicate){
				throw new ServiceException("已有重复的字典");
			}
			id=systemDictDao.save(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("保存时发生异常===="+e.getMessage());
			throw new ServiceException("保存时发生异常");
		}
		return id;
	}
	
	public void update(IBean baseBean) throws ServiceException{
		Integer id=0;
		try{
			SystemDict sd = (SystemDict)baseBean;
			boolean isDuplicate = systemDictDao.checkForDuplicate(sd);
			if(isDuplicate){
				throw new ServiceException("已有重复的字典");
			}
			systemDictDao.update(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新时发生异常===="+e.getMessage());
			throw new ServiceException("更新时发生异常");
		}
	}
    /**
     * 根据Type读取字典列表
     * @param type
     * @return
     * @throws Exception
     */
    public List selectDictByType(String type) throws ServiceException{
    	try{
    		return this.systemDictDao.selectDictByType(type,Constants.IsValid.FLAG_YES.getValue());
    	}catch(Exception e){
    		e.printStackTrace();
			log.error("根据Type读取字典列表异常===="+e.getMessage());
    		throw new ServiceException("根据Type读取字典列表异常");
    	}
    }
    /**
     * 更改字典的状态
     * @param systemDictId
     * @param flag
     */
    public void updateFlag(Integer systemDictId,Integer flag) throws ServiceException{
		try{
		   systemDictDao.updateFlag(systemDictId,flag);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新时flag状态之后发生异常===="+e.getMessage());
			throw new ServiceException("更新flag状态之后发生异常");
		}
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSystemDict(String searchValue,String type,int currentPage, int pageSize) throws ServiceException{
    	try{
    		return this.systemDictDao.searchSystemDict(searchValue,type,currentPage, pageSize);
    	}catch(Exception e){
    		e.printStackTrace();
			log.error("分页搜索字典列表异常===="+e.getMessage());
    		throw new ServiceException("分页搜索字典列表异常");
    	}
    }
  
	public ISystemDictDao getSystemDictDao() {
		return systemDictDao;
	}

	public void setSystemDictDao(ISystemDictDao systemDictDao) {
		this.systemDictDao = systemDictDao;
	}

    
}
