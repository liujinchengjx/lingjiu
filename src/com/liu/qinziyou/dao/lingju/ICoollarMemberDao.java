package com.liu.qinziyou.dao.lingju;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.exception.ServiceException;

public interface ICoollarMemberDao extends  IBaseDAO{
	public PageHelper searchCoollarMember(String searchValue, Integer state, int currentPage, int pageSize)
			throws ServiceException ;
}
