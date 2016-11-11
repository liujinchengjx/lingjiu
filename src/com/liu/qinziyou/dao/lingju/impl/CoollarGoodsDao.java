package com.liu.qinziyou.dao.lingju.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.lingju.ICoollarGoodsDao;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.lingju.CoollarGoods;
import com.liu.qinziyou.exception.ServiceException;

public class CoollarGoodsDao  extends BaseDAOImpl implements ICoollarGoodsDao{

	@Override
	public PageHelper searchCoollarGoods(String searchValue, Integer state, int currentPage, int pageSize)
			throws ServiceException {
		String sql="SELECT A.* FROM coollar_goods A  WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (A.GOODS_NAME LIKE :searchValue )";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	
    	if ((state!=null) && (state.intValue()>0)){
    		sql=sql+" AND A.STATE =:state ";
    		m.put("state",state);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(CoollarGoods.class,sql, searchBean, currentPage, pageSize);
    	return ph;	
	}

	@Override
	public int updateCoollaredQty(Integer coollarQty, Integer coollaredCnt,
			Integer goodsId,Integer type) {
		SearchBean searchBean = new SearchBean();
		Map<String, Object> m = new HashMap<String, Object>();
		String sql = " UPDATE coollar_goods A SET A.COOLARED_CNT= A.COOLARED_CNT+ :coollarQty WHERE  A.COOLARED_CNT = :coollaredCnt AND A.ID = :goodsId  ";
		if(type==null){
			type=1;
		}
		if(type.intValue()==2){
			sql = " UPDATE coollar_goods A SET A.COOLARED_CNT= A.COOLARED_CNT- :coollarQty WHERE  A.COOLARED_CNT = :coollaredCnt AND A.ID = :goodsId  ";
		}
		m.put("coollarQty", coollarQty);
		m.put("coollaredCnt", coollaredCnt);
		m.put("goodsId", goodsId);
		searchBean.setFilterMap(m);
		int rows = this.executeSQL(sql, searchBean);
		return rows;

	}
	
}
