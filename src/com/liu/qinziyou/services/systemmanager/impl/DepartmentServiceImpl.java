package com.liu.qinziyou.services.systemmanager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.systemmanager.IDepartmentDao;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.systemmanager.Department;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.systemmanager.IDepartmentService;

public class DepartmentServiceImpl extends AbstractBaseService implements IDepartmentService{
	protected static final Log log = LogFactory.getLog(DepartmentServiceImpl.class);
	private IDepartmentDao departmentDao;
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException{
		int id=0;
		try{
			Department d=(Department)baseBean;
			boolean isDuplicate=this.departmentDao.checkForDuplicate(d);
			if(isDuplicate){
				throw new ServiceException("已有重复的部门");
			}
			id=this.departmentDao.save(d);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("添加部门时发生异常===="+e.getMessage());
			throw new ServiceException("添加部门时失败");
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
			Department d=(Department)baseBean;
			boolean isDuplicate=this.departmentDao.checkForDuplicate(d);
			if(isDuplicate){
				throw new ServiceException("已有重复的功能");
			}
			this.departmentDao.update(d);
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("更新部门时发生异常===="+e.getMessage());
			throw new ServiceException("更新部门时失败");
		}
	}
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchDepartment(String searchValue,Integer opComanyId, int currentPage, int pageSize) throws ServiceException{
	    try{
	    	return this.departmentDao.searchDepartment(searchValue,opComanyId,currentPage, pageSize);
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.error("搜索部门列表分页时发生异常===="+e.getMessage());
			throw new ServiceException("搜索部门列表分页异常");
		}
    }

	public IDepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public void setDepartmentDao(IDepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
    
}
