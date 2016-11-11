package com.liu.qinziyou.services;

import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.exception.ServiceException;


public interface IBaseService {
	
	/**
	 * 增加 ,返回添加后的主键
	 * @param baseBean
	 * @throws ServiceException
	 */
	public Integer add(IBean baseBean) throws ServiceException;
	/**
	 * 删除
	 * @param baseBean
	 * @throws ServiceException
	 */
	public void del(IBean baseBean) throws ServiceException;
	
	/**
	 * 修改
	 * @param baseBean
	 * @throws ServiceException
	 */
	public void update(IBean baseBean) throws ServiceException;
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public IBean getBeanById(Class cls,Integer id)  throws ServiceException;
	
	
	
	
}
