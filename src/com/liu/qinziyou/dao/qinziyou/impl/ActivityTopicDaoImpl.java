package com.liu.qinziyou.dao.qinziyou.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.qinziyou.IActivityTopicDao;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.qinziyou.ActivityTopic;

public class ActivityTopicDaoImpl extends BaseDAOImpl implements IActivityTopicDao{

	@Override
	public PageHelper searchActivityTopic(String searchValue, Integer status,
			Integer currentPage, Integer pageSize) throws Exception {
		String sql="SELECT A.* FROM activiy_topic A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (A.TOPIC_NAME LIKE :searchValue OR A.CITY LIKE :searchValue  OR A.ADDRESS LIKE :searchValue)";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	
    	if ((status!=null) && (status.intValue()>0)){
    		sql=sql+" AND A.ACTIVITY_STATUS =:status ";
    		m.put("status",status);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(ActivityTopic.class,sql, searchBean, currentPage, pageSize);
    	return ph;	
	}
	
	public Integer changeActivityStatus(Integer status, Integer activityId) throws Exception{
    	String sql=" UPDATE activiy_topic A SET ACTIVITY_STATUS=:status WHERE A.ID=:activityId ";
    	SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("activityId", activityId);
		m.put("status", status);
		searchBean.setFilterMap(m);
		int rows =this.executeSQL(sql, searchBean);
    	return rows; 
    }
	
}
