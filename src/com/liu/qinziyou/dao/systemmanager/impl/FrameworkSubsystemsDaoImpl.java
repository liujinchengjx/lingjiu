package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IFrameworkSubsystemsDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkSubsystems;

public class FrameworkSubsystemsDaoImpl extends BaseDAOImpl implements IFrameworkSubsystemsDao {
	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  List selectSubsystems(int systemId) throws Exception{
    	String sql="SELECT * FROM framework_subsystems A WHERE SYSTEM_ID=:systemId ORDER BY ID";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);    	
    	searchBean.setFilterMap(m); 
    	List ls=this.getListResultBySql(FrameworkSubsystems.class,sql, searchBean);
    	return ls; 
    }
    
    /**
	 * 根据用户权限获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  List selectSubsystems(Integer userId,int systemId) throws Exception{
    	String sql="SELECT A.* FROM framework_subsystems A WHERE SYSTEM_ID =:systemId AND LEFT(POPEDOM, 3) IN ";
    	sql=sql+"( SELECT LEFT(POPEDOM, 3) FROM ( ";		
    	sql=sql+" (SELECT POPEDOM FROM um_popedom_in_role WHERE ROLE_ID IN " +
    			"             (SELECT ROLE_ID FROM um_user_in_role WHERE USER_ID =:userId))";
    	sql=sql+" UNION "; 					
    	sql=sql+"(SELECT POPEDOM FROM um_popedom_in_user WHERE USER_ID =:userId )";
    	sql=sql+" ) AT ";
    	sql=sql+") ";
    	sql=sql+" ORDER BY ID ";	    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);
    	m.put("userId", userId);
    	searchBean.setFilterMap(m); 
    	List ls=this.getListResultBySql(FrameworkSubsystems.class,sql, searchBean);
    	return ls; 
    }
    
    //根据权限值 获取系统名称
    public String getDescription(Integer popedom) throws Exception{
    	String sql="SELECT DISTINCT A.DESCRIPTION FROM framework_subsystems A WHERE A.POPEDOM=:popedom";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("popedom", popedom);    	
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o==null ? "": o.toString(); 
    }
    /**
     * 获取子系统集合 有分页
     * @return
     * @throws Exception
     */
    public  PageHelper selectSubsystemsList(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM framework_subsystems A ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(FrameworkSubsystems.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSubsystems(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM framework_subsystems A WHERE A.NAME LIKE :name ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("name", "%"+searchValue+"%");    	
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(FrameworkSubsystems.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	FrameworkSubsystems fSubs = (FrameworkSubsystems)baseBean;
    	String sql="SELECT COUNT(*) FROM framework_subsystems A WHERE A.NAME LIKE :name ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("name", "%"+fSubs.getName()+"%");    	
    	if (fSubs.getId()!=null){
    		sql=sql+" AND a.ID=:id";
    		m.put("id",fSubs.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number)this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
