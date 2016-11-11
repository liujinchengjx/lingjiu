package com.liu.qinziyou.dao.qinziyou.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.qinziyou.IActivityRegistratorDao;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.qinziyou.ActivityRegistrator;

public class ActivityRegistratorDaoImpl  extends BaseDAOImpl implements IActivityRegistratorDao{

	@Override
	public PageHelper searchActivityRegistrator(Integer topicId,
			Integer currentPage, Integer pageSize) throws Exception {
		String sql="SELECT A.* FROM activity_registrator A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	sql = sql +" AND A.TOPIC_ID=:topicId";
    	m.put("topicId",topicId);
    	
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(ActivityRegistrator.class,sql, searchBean, currentPage, pageSize);
    	return ph;	
	}
	
}
