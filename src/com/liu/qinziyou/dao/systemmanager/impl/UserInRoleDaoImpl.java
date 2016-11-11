package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IUserInRoleDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.UserInRole;

public class UserInRoleDaoImpl extends BaseDAOImpl implements IUserInRoleDao {
	/**
     * 获取用户角色
     * @return
     * @throws Exception
     */
    public  PageHelper selectUserInRole(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM um_user_in_role A ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(UserInRole.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 根据用户Id获取角色集
     * @param userId
     * @return
     * @throws Exception
     */
    public  List selectRolesByUserId(int userId) throws Exception{
    	String sql="SELECT * FROM um_user_in_role A WHERE A.USER_ID=:userId ORDER BY ID";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", userId);    	
    	searchBean.setFilterMap(m);
    	return this.getListResultBySql(UserInRole.class,sql, searchBean);
    }
    /**
     * 删除用户的某个角色
     * @return
     * @throws Exception
     */
    public boolean deleteUserInRole(Integer userId,Integer roleId) throws Exception{
    	String sql=" DELETE FROM um_user_in_role WHERE USER_ID=:userId AND ROLE_ID=:roleId ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", userId);
    	m.put("roleId", roleId);
    	searchBean.setFilterMap(m);
    	int rows=this.executeSQL(sql, searchBean);
    	return (rows>0)?true:false;
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUserInRole(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT * FROM um_user_in_role A WHERE A.USER_ID=:userId AND A.ROLE_ID=:roleId  ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId",searchValue);
    	m.put("roleId",searchValue);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(UserInRole.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	UserInRole ur = (UserInRole)baseBean;
    	String sql="SELECT COUNT(*) FROM um_user_in_role A WHERE A.USER_ID=:userId and a.roleId=:roleId ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId",ur.getUserId());
    	m.put("roleId",ur.getRoleId());
    	if (ur.getId()!=null){
    		sql=sql+" AND a.ID !=:id";
    		m.put("id",ur.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number) this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
