package com.liu.qinziyou.dao.lingju;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.exception.ServiceException;

public interface ICoollarGoodsDao extends  IBaseDAO{
	public PageHelper searchCoollarGoods(String searchValue, Integer state, int currentPage, int pageSize)
			throws ServiceException ;
	
	public int updateCoollaredQty(Integer coollarQty,Integer coollaredCnt,Integer goodsId,Integer type);
}
