package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.DateTool;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IUserDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.User;
import com.liu.qinziyou.exception.ServiceException;

public class UserDaoImpl extends BaseDAOImpl implements IUserDao {
    
	/**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String searchValue,Integer userType,Integer flag,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_user A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (A.USER_NAME LIKE :searchValue) OR (A.LOWERED_NAME LIKE :searchValue) OR (A.REAL_NAME LIKE :searchValue) OR (A.ENAME LIKE :searchValue) ";
	    	m.put("searchValue", "%"+searchValue+"%");
    	}
    	if ((flag!=null) && (flag.intValue()>-1)){
    		sql=sql+" AND A.FLAG=:flag ";
	    	m.put("flag", flag);
    	}
    	if ((userType!=null) && (userType.intValue()>0)){
    		sql=sql+" AND A.USER_TYPE=:userType ";
	    	m.put("userType", userType);
    	}
    	sql=sql+" ORDER BY A.ID DESC ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(User.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchUser(String positionCode,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_user A WHERE A.POSITION_CODE = :positionCode ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("positionCode", positionCode);
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(User.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 激活用户
     * @return
     * @throws Exception
     */
    public boolean activeUser(int userId,int flag) throws Exception{
    	String sql=" UPDATE um_user A SET A.FLAG=:flag WHERE A.ID=:userId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("flag", flag);
    	m.put("userId", userId);
    	searchBean.setFilterMap(m);
    	int row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    public boolean updatePosition(Integer userId,String positionCode) throws Exception{
    	String sql=" UPDATE um_user A SET POSITION_CODE=:positionCode WHERE A.ID=:userId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userId", userId);
    	if (positionCode != null) {
        	m.put("positionCode", positionCode);
		} else {
			m.put("positionCode", "");			
		}
    	searchBean.setFilterMap(m);
    	int row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    /**
     * 冻结用户 -1-无效；0-有效；1-冻结
     * @return
     * @throws Exception
     */
    public boolean froze(int userId,int flag) throws Exception{
    	int row=0;
    	String sql=" UPDATE um_user A SET A.IS_FROZE=:isFroze where A.ID=:userId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("isFroze", flag);
    	m.put("userId", userId);
    	searchBean.setFilterMap(m);
    	row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    /**
     * 删除用户 是否删除 0 否 1 是
     * @return
     * @throws Exception
     */
    public boolean delete(int userId,int flag) throws Exception{
    	int row=0;
    	String sql=" UPDATE um_user A SET A.IS_DELETE=:isDelete where id=:userId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("isDelete", flag);
    	m.put("userId", userId);
    	searchBean.setFilterMap(m);
    	row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	User usr = (User)baseBean;
    	String sql="SELECT COUNT(*) FROM um_user A WHERE ((A.USER_NAME =:userName)  "
    			+ "  OR (A.TEL =:tel)) ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userName", usr.getUserName());
    	m.put("tel",usr.getTel());
    	if (usr.getId()!=null){
    		sql=sql+" AND A.ID!=:id";
    		m.put("id",usr.getId());
    	}
    	sql=sql+" ORDER BY ID ";
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
    
    public  User getUserByUserNamePassword(String userName,String password,Integer userType) throws Exception{
    	String sql="SELECT * FROM um_user A WHERE (A.USER_NAME = :userName OR A.TEL= :telphone) AND A.PASSWORD = :password  AND A.USER_TYPE = :userType ORDER BY ID";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("userName", userName);
    	m.put("telphone", userName);
    	m.put("password", password);
    	m.put("userType", userType);
    	
    	searchBean.setFilterMap(m); 
    	User user =(User)this.getObjectResultBySql(User.class, sql, searchBean);
    	return user; 
    }
	@Override
	public boolean updatePwd(String pwd, Integer userId) throws Exception {

    	int row=0;
    	String sql=" UPDATE um_user A SET A.PASSWORD=:pwd where id=:userId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("pwd", pwd);
    	m.put("userId", userId);
    	searchBean.setFilterMap(m);
    	row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false; 
    
	}
    
	@Override
	public String getMaxMemberCode() throws Exception {
		String mcode = null;
		String sql = "SELECT max(A.USER_NAME) FROM um_user A WHERE A.USER_NAME like :mcode AND A.USER_TYPE=2" ;
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("mcode", "M%");
		searchBean.setFilterMap(m);
		
		Object o =  this.getAttributeValueBySql(sql, searchBean);
		if(null!=o){
			mcode = (String)o;
		}
		return mcode;
	}
}
