package com.liu.qinziyou.dao.qinziyou;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface IActivityTopicDao extends IBaseDAO{
	 
	public PageHelper searchActivityTopic(String searchValue, Integer status, Integer currentPage, Integer pageSize) throws Exception;
	
	public Integer changeActivityStatus(Integer status, Integer activityId) throws Exception;
    
}