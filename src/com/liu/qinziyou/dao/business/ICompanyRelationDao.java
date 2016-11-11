package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.dao.IBaseDAO;
public interface ICompanyRelationDao extends IBaseDAO{

	/**
	 * 根据seller供方的Id获取需方公司的Id
	 * @param sellerId
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public Integer getBuyerId(Integer sellerId)  throws Exception;
	/**
	 * 删除关系 ，根据供方，需方 id
	 * @param sellerId
	 * @return
	 * @throws Exception
	 */
	public Integer deleteCompanyRelation(Integer sellerId,Integer buyerId)  throws Exception;
	/**
	 * 根据buyer需方的Id获取供方公司的Id
	 * @param sellerId
	 * @return BaseBean
	 * @throws ServiceException
	 */
	public Integer getSellerId(Integer buyerId)  throws Exception;
	
}
