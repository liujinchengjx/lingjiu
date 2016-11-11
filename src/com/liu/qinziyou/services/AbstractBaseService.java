package com.liu.qinziyou.services;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.exception.ServiceException;

public abstract class AbstractBaseService implements IBaseService {
	private IBaseDAO baseDAO;
	
	public IBaseDAO getBaseDAO() {
		return baseDAO;
	}

	public void setBaseDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	public Integer add(IBean baseBean) throws ServiceException{
		Integer id=0;
		try{
			id=baseDAO.save(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			throw new ServiceException("保存时失败");
		}
		return id;
	}
	
	public void del(IBean baseBean) throws ServiceException {
		try{
			baseDAO.delete(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("删除时失败");
		}
	}
	public IBean getBeanById(Class claz,Integer id) throws ServiceException {
		IBean bean = null;
		try{
			bean = baseDAO.findById(claz, id);
		}catch(Exception e){
			throw new ServiceException("取数据时失败");
		}
		return bean;
	}
	
	public void update(IBean baseBean) throws ServiceException {
		try{
			baseDAO.update(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("更新时候失败");
		}
		
	}
	
	/**
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities   
     * @author 
     */
    public void saveOrUpdateCollection(Collection entities){
    	try{
			baseDAO.saveOrUpdateCollection(entities);
		}catch (ServiceException e){
			throw e;
		}catch(Exception e){
			throw new ServiceException("更新时候失败");
		}
    }
    /**
     * 保存（插入或更新）单个对象
     *
     * @param BaseBean baseBean
     * @throws DataAccessException   
     */
    public Integer saveOrUpdateObject(IBean baseBean){
    	Integer id=0;
    	try{
    		id=baseDAO.saveOrUpdateObject(baseBean);
		}catch (ServiceException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("更新时候失败");
		}
		return id;
    }
}
