package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IFrameworkButtonsDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.FrameworkButtons;
import com.liu.qinziyou.exception.ServiceException;

public class FrameworkButtonsDaoImpl extends BaseDAOImpl implements IFrameworkButtonsDao {
	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
	public  PageHelper selectButtons(int systemId,String modules,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM framework_buttons A WHERE A.MODULES=:modules AND SYSTEM_ID=:systemId ORDER BY ID";    	
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("systemId", systemId);
    	m.put("modules", modules);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(FrameworkButtons.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    
	/**
	 * 获取子系统集合
	 * @param systemId 系统id 默认是1
	 * @return
	 * @throws Exception
	 */
    public  PageHelper selectButtons(String modules,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM framework_buttons A WHERE A.MODULES=:modules ORDER BY ID";    	
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("modules", modules);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(FrameworkButtons.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     *  加载页面工具栏
     * @param modules
     * @param toolBarSeq
     * @return
     * @throws ServiceException
     */
    public List loadButtonsList(String modules,Integer toolBarSeq) throws Exception{
    	String sql="SELECT A.* FROM framework_buttons A WHERE A.MODULES=:modules AND A.IN_TOOLBAR=:toolBarSeq ORDER BY SEQUENCE";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("modules", modules);
    	m.put("toolBarSeq", toolBarSeq);    	
    	searchBean.setFilterMap(m); 
    	List lst=this.getListResultBySql(FrameworkButtons.class, sql, searchBean);
    	return lst; 
    }
    /**
     *  加载页面工具栏
     * @param modules
     * @param toolBarSeq
     * @return
     * @throws ServiceException
     */
    public List loadButtonsList(Integer userId,String modules,Integer toolBarSeq) throws Exception{
    	String sql="SELECT A.* FROM framework_buttons A WHERE A.MODULES=:modules AND A.IN_TOOLBAR=:toolBarSeq AND POPEDOM IN ";
    	sql=sql+"( SELECT POPEDOM POPEDOM FROM ( ";		
    	sql=sql+" (SELECT PR.POPEDOM FROM	um_popedom_in_role PR,um_user_in_role UR WHERE  PR.ROLE_ID=UR.ROLE_ID AND UR.USER_ID=:userId )";
    	sql=sql+" UNION "; 					
    	sql=sql+"(SELECT POPEDOM FROM um_popedom_in_user WHERE USER_ID =:userId )";
    	sql=sql+" ) AT ";
    	sql=sql+") ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", userId);
    	m.put("modules", modules);
    	m.put("toolBarSeq", toolBarSeq);
    	sql=sql+" ORDER BY SEQUENCE";
    	searchBean.setFilterMap(m); 
    	List lst=this.getListResultBySql(FrameworkButtons.class, sql, searchBean);
    	return lst; 
    }
	/**
     * 搜索
     * @return
     * @throws Exception
     */
  public  PageHelper searchButtons(String searchValue,int currentPage, int pageSize) throws Exception{
	  	String sql="SELECT A.* FROM framework_buttons A WHERE 1=1 ";
	  	PageHelper ph=new PageHelper();
	  	SearchBean searchBean=new SearchBean();
	  	Map<String, Object> m=new HashMap<String, Object>();
	  	if ((searchValue!=null) && (!"".equals(searchValue))){
           sql=sql+"AND (A.NAME LIKE :searchValue) OR (A.TEXT LIKE :searchValue) OR (A.TOOLTIP LIKE :searchValue) ";
           m.put("searchValue", "%"+searchValue+"%");
	  	}
	  	sql=sql+"  ORDER BY A.ID ";
	  	searchBean.setFilterMap(m); 
	  	ph=this.getPageHelperBySql(FrameworkButtons.class,sql, searchBean,currentPage, pageSize);
	  	return ph; 
	}
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
	public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
		FrameworkButtons fun = (FrameworkButtons)baseBean;
    	String sql="SELECT COUNT(*) FROM framework_buttons A WHERE A.NAME LIKE :name ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("name", "%"+fun.getName()+"%");  
    	if (fun.getId()!=null){
    		sql=sql+" AND a.ID=:id";
    		m.put("id",fun.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number)this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
	}
  
}
