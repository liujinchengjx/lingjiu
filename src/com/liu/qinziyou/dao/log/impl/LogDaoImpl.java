package com.liu.qinziyou.dao.log.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.log.ILogDao;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.log.LogLogin;
import com.liu.qinziyou.exception.DaoException;

public class LogDaoImpl extends BaseDAOImpl implements ILogDao{
	protected static final Log log = LogFactory.getLog(LogDaoImpl.class);
	
	public PageHelper searchLogLogin(String startTime,String endTime,String searchValue,int currentPage, int pageSize) throws DaoException {
		try{
			String sql="SELECT * FROM log_login A WHERE  1=1";
			Map<String, Object> m=new HashMap<String, Object>();
			if(!StringUtil.isEmpty(startTime)){
				sql = sql + " AND LOGIN_TIME >= :startTime";
				m.put("startTime", startTime);
			}
			
			if(!StringUtil.isEmpty(endTime)){
				sql = sql + " AND LOGIN_TIME <= :endTime";
				m.put("endTime", endTime);
			}
			if(!StringUtil.isEmpty(searchValue)){
				sql = sql + " AND (COMPANY_NAME LIKE :searchValue OR LOGIN_BROWSER LIKE :searchValue" +
						" OR LOGIN_IP LIKE :searchValue OR MOBILE LIKE :searchValue OR USER_NAME LIKE :searchValue)";
				m.put("searchValue", "%"+searchValue+"%");
			}
			
	    	SearchBean searchBean=new SearchBean();
	    	searchBean.setFilterMap(m);    	
	    	PageHelper ph=this.getPageHelperBySql(LogLogin.class,sql, searchBean, currentPage, pageSize);
	    	return ph;	
		}catch(Exception e){
			log.error("查询登录日志是出错！"+e.getMessage());
			throw new DaoException("查询登录日志是出错！");
			
		}
		
	}
	
}
