package com.liu.qinziyou.dao.qinziyou;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface IActivityRegistratorDao  extends IBaseDAO{
	/**
	 * 根据主题ID分页查询该主题下的报名情况
	 * @param topicId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageHelper searchActivityRegistrator(Integer topicId,
			Integer currentPage, Integer pageSize) throws Exception ;
}
