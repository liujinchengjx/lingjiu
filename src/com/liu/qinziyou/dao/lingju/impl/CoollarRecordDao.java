package com.liu.qinziyou.dao.lingju.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.DateTool;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.lingju.ICoollarRecordDao;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.lingju.CoollarRecord;
import com.liu.qinziyou.exception.ServiceException;

public class CoollarRecordDao  extends BaseDAOImpl implements  ICoollarRecordDao{

	@Override
	public PageHelper searchCoollarRecord(String searchValue, String memberCode,Integer state, Integer fleightState,int currentPage, int pageSize)
			throws ServiceException {
		String sql="SELECT A.* FROM coollar_record A LEFT JOIN coollar_goods B ON A.GOODS_ID=B.ID WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (B.GOODS_NAME LIKE :searchValue )";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	if (!StringUtil.isEmpty(memberCode)){
    		sql=sql+" AND A.MEMBER_CODE =:memberCode ";
    		m.put("memberCode",memberCode);
    	}
    	if ((state!=null) && (state.intValue()>0)){
    		sql=sql+" AND A.STATE =:state ";
    		m.put("state",state);
    	}
    	if ((fleightState!=null) && (fleightState.intValue()>0)){
    		sql=sql+" AND A.FLEIGHT_STATE =:fleightState ";
    		m.put("fleightState",fleightState);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(CoollarRecord.class,sql, searchBean, currentPage, pageSize);
    	return ph;	
	}

	@Override
	public String getMaxOrderCodeOfCurrentDay() throws ServiceException {
		String maxOrderCodeOfCurrentDay = null;
		String sql = "SELECT max(A.ORDER_CODE) FROM coollar_record A WHERE A.ORDER_CODE LIKE :DAY" ;
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		String day = DateTool.getCurDateTime("yyMMdd");
		m.put("DAY", day+"%");
		searchBean.setFilterMap(m);
		
		Object o =  this.getAttributeValueBySql(sql, searchBean);
		if(null!=o){
			maxOrderCodeOfCurrentDay = (String)o;
		}
		return maxOrderCodeOfCurrentDay;
	}

	@Override
	public int changeState(Integer state, Integer id) throws ServiceException {
		
		String	sql = " UPDATE coollar_record A SET A.STATE= :state WHERE A.ID = :id  ";
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("state", state);
		m.put("id", id);
		searchBean.setFilterMap(m);
		int rows = this.executeSQL(sql, searchBean);
		return rows;
		
	}

	@Override
	public int fleight(String fleightCompany, String fleightNumber, Integer id) throws ServiceException {
		String	sql = " UPDATE coollar_record A SET A.FLEIGHT_STATE=20,A.FLEIGHT_COMPANY=:fleightCompany,"
				+ "A.FLEIGHT_TIME=:fleightTime,A.FLEIGHT_NUMBER=:fleightNumber WHERE A.ID = :id  ";
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("fleightCompany", fleightCompany);
		m.put("fleightNumber", fleightNumber);
		m.put("fleightTime", new Timestamp(new Date().getTime()));
		
		m.put("id", id);
		searchBean.setFilterMap(m);
		int rows = this.executeSQL(sql, searchBean);
		return rows;
		
	}
	
	
	
	
	
}
