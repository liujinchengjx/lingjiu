package com.liu.qinziyou.dao.business;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface IValueAddedServicesDiscountDao extends IBaseDAO {
	/**
	 *搜索
	 * 
	 * @param companyId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageHelper searchValueAddedServicesDiscount(Integer customerId, int currentPage, int pageSize) throws Exception;
}
