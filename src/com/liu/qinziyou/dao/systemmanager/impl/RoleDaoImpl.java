package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IRoleDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.Role;

public class RoleDaoImpl extends BaseDAOImpl implements IRoleDao {
    /**
     * 删除角色
     * @return
     * @throws Exception
     */
    public boolean delete(int roleId,int flag) throws Exception{
    	int row=0;
    	String sql=" UPDATE um_role A SET A.IS_DELETE=:isDelete WHERE ID=:roleId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("isDelete", flag);
    	m.put("roleId", roleId);
    	searchBean.setFilterMap(m);
    	row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    	
    /**
     * 冻结角色
     * @return
     * @throws Exception
     */
    public boolean froze(int roleId,int flag) throws Exception{
    	int row=0;
    	String sql=" UPDATE um_role A SET A.IS_FROZE=:isFroze WHERE ID=:roleId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("isFroze", flag);
    	m.put("roleId", roleId);
    	searchBean.setFilterMap(m);
    	row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    /**
     * 复核角色
     * @return
     * @throws Exception
     */
    public boolean approved(int roleId,int flag) throws Exception{
    	int row=0;
    	String sql=" UPDATE um_role A SET A.IS_APPROVED=:isApproved WHERE ID=:roleId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("isApproved", flag);
    	m.put("roleId", roleId);
    	searchBean.setFilterMap(m);
    	row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchRole(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_role A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    	   sql=sql+" AND (A.ROLE_NAME LIKE :searchValue) OR (A.DESCRIPTION LIKE :searchValue) ";
    	   m.put("searchValue", "%"+searchValue+"%");
    	}
    	sql=sql+" ORDER BY A.ID DESC ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Role.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Role role = (Role)baseBean;
    	String sql="SELECT COUNT(*) FROM um_role A WHERE A.ROLE_NAME LIKE :roleName ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("roleName", "%"+role.getRoleName()+"%"); 
    	if (role.getId()!=null){
    		sql=sql+" AND A.ID!=:id";
    		m.put("id",role.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number) this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
