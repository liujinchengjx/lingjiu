package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IFrameworkModulesDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkModules;

public class FrameworkModulesDaoImpl extends BaseDAOImpl implements IFrameworkModulesDao {
	
	/**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM framework_modules A ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(FrameworkModules.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModules(int systemId) throws Exception{
    	String sql="SELECT * FROM framework_modules A WHERE A.SYSTEM_ID=:systemId ORDER BY ID";   
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);
    	searchBean.setFilterMap(m); 
    	return this.getListResultBySql(FrameworkModules.class,sql,searchBean);
    }
    //根据权限值 获取系统名称
    public String getDescription(Integer popedom) throws Exception{
    	String sql=" SELECT DISTINCT A.DESCRIPTION FROM framework_modules A WHERE A.POPEDOM=:popedom";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("popedom", popedom);    	
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o==null ? "": o.toString();
    }
	/**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectModules(int systemId,String modulesName,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM framework_modules A WHERE A.SYSTEM_ID =:systemId AND A.SUBSYSTEM =:subSystem ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);
    	m.put("subSystem", modulesName);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(FrameworkModules.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModulesList(Integer systemId,String modulesName,Integer inMenu) throws Exception{
    	String sql="SELECT * FROM framework_modules A WHERE A.SYSTEM_ID =:systemId AND A.SUBSYSTEM =:subSystem ";   
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);
    	m.put("subSystem", modulesName);
    	if (inMenu!=null){
    		sql=sql+" AND A.IN_MENU=:inMenu ";
    		m.put("inMenu", inMenu);
    	}
    	sql=sql+" ORDER BY SEQUENCE";
    	searchBean.setFilterMap(m); 
    	return this.getListResultBySql(FrameworkModules.class,sql,searchBean);
    }
    /**
     * 根据用户权限获取系统模块集合
     * @return
     * @throws Exception
     */
    public  List selectModulesList(Integer userId,Integer systemId,String modulesName,Integer inMenu) throws Exception{
     	String sql="SELECT A.* FROM framework_modules A WHERE A.SYSTEM_ID =:systemId AND A.SUBSYSTEM =:subSystem  AND LEFT(POPEDOM, 5) IN ";
    	sql=sql+"( SELECT LEFT(POPEDOM, 5) POPEDOM FROM ( ";		
    	sql=sql+" (SELECT PR.POPEDOM FROM	um_popedom_in_role PR,um_user_in_role UR WHERE  PR.ROLE_ID=UR.ROLE_ID AND UR.USER_ID=:userId ) ";
    	sql=sql+" UNION "; 					
    	sql=sql+"(SELECT POPEDOM FROM um_popedom_in_user WHERE USER_ID =:userId )";
    	sql=sql+" ) AT ";
    	sql=sql+") ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);
    	m.put("subSystem", modulesName);
    	m.put("userId", userId);
    	if (inMenu!=null){
    		sql=sql+" AND A.IN_MENU=:inMenu ";
    		m.put("inMenu", inMenu);
    	}
    	sql=sql+" ORDER BY SEQUENCE";
    	searchBean.setFilterMap(m); 
    	return this.getListResultBySql(FrameworkModules.class,sql,searchBean);
    }
 
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchModules(String searchValue,int currentPage, int pageSize) throws Exception{
    	String hql="SELECT * FROM framework_modules A WHERE A.NAME LIKE :name ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("name", "%"+searchValue+"%");    	
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(FrameworkModules.class,hql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    
    
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	FrameworkModules fm = (FrameworkModules)baseBean;
    	String sql="SELECT COUNT(*) FROM framework_modules A WHERE A.NAME LIKE :name ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("name", "%"+fm.getName()+"%"); 
    	if (fm.getId()!=null){
    		sql=sql+" AND a.ID=:id";
    		m.put("id",fm.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number) this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
