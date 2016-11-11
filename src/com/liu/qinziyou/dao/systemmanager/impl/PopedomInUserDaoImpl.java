package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IPopedomInUserDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.PopedomInUser;

public class PopedomInUserDaoImpl extends BaseDAOImpl implements IPopedomInUserDao {
	/**
     * 获取用户权限
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInUser(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_popedom_in_user A ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(PopedomInUser.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     *  删除userId 对应的权限popedom值
     * @param userId
     * @param popedom
     * @return
     * @throws Exception
     */
    public  boolean deletePopedomInUser(Integer userId,Integer popedom) throws Exception{
    	String sql=" DELETE FROM um_popedom_in_user WHERE USER_ID=:userId AND POPEDOM=:popedom";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", userId);
    	m.put("popedom", popedom);
    	searchBean.setFilterMap(m);
    	int rows=this.executeSQL(sql, searchBean);
    	return rows > 0 ? true:false;
    }
    /**
     * 根据用户userId,读取到对应的角色权限
     * @param userId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByUserId(int userId) throws Exception{
    	String sql="SELECT A.* FROM um_popedom_in_user A WHERE A.USER_ID=:userId ORDER BY ID";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", userId);    	
    	searchBean.setFilterMap(m);
    	return this.getListResultBySql(PopedomInUser.class,sql, searchBean);
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInUser(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM um_popedom_in_user A WHERE A.USER_ID=:userId ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", searchValue);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(PopedomInUser.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	PopedomInUser pu = (PopedomInUser)baseBean;
    	String sql="SELECT COUNT(*) FROM um_popedom_in_user A WHERE A.USER_ID=:userId AND A.POPEDOM=:popedom ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", pu.getUserId());
    	m.put("popedom", pu.getPopedom());
    	if (pu.getId()!=null){
    		sql=sql+" AND a.ID=:id";
    		m.put("id",pu.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number)this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
