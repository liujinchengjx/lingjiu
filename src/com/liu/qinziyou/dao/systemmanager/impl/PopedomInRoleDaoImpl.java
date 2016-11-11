package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IPopedomInRoleDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.PopedomInRole;

public class PopedomInRoleDaoImpl extends BaseDAOImpl implements IPopedomInRoleDao {
	/**
     * 获取角色权限
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomInRole(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM um_popedom_in_role A ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(PopedomInRole.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     *  删除roleId 对应的权限popedom值
     * @param roleId
     * @param popedom
     * @return
     * @throws Exception
     */
    public  boolean deletePopedomInRole(Integer roleId,Integer popedom) throws Exception{
    	String sql="DELETE FROM um_popedom_in_role WHERE ROLE_ID=:roleId AND POPEDOM=:popedom ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("roleId", roleId);
    	m.put("popedom", popedom);
    	searchBean.setFilterMap(m);
    	int rows=this.executeSQL(sql, searchBean);
    	return (rows>0)?true:false;
    }
    /**
     * 根据用户角色roleId,读取到对应的角色权限
     * @param roleId
     * @return
     * @throws Exception
     */
    public List selectPopedomsByRoleId(int roleId) throws Exception{
    	String sql="SELECT * FROM um_popedom_in_role A WHERE A.ROLE_ID=:roleId ORDER BY ID";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("roleId", roleId);    	
    	searchBean.setFilterMap(m);
    	return this.getListResultBySql(PopedomInRole.class,sql, searchBean);
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedomInRole(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM um_popedom_in_role A WHERE A.ROLE_ID=:roleId AND A.POPEDOM=:popedom  ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("roleId", searchValue);
    	m.put("popedom", searchValue);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(PopedomInRole.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	PopedomInRole pr = (PopedomInRole)baseBean;
    	String sql="SELECT COUNT(*) FROM um_popedom_in_role A WHERE ROLE_ID=:roleId and popedom=:popedom ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("roleId", pr.getRoleId());
    	m.put("popedom", pr.getPopedom());
    	if (pr.getId()!=null){
    		sql=sql+" AND a.ID=:id";
    		m.put("id",pr.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number)this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
