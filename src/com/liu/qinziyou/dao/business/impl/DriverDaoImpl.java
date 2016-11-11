package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.Constants;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.business.IDriverDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Driver;

public class DriverDaoImpl extends BaseDAOImpl implements IDriverDao{
	
	public  boolean updateDriverStatus(Integer driverId,Integer status) throws Exception{
    	String sql="UPDATE driver A SET A.STATUS=:status WHERE A.ID=:driverId ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("driverId", driverId);
    	m.put("status", status);
    	searchBean.setFilterMap(m);
    	int row=this.executeSQL(sql, searchBean);
    	return (row>0)?true:false;
	}
    /**
     * 根据司机ID获取司机名称
     * @return
     * @throws Exception
     */
    public  String getDriverName(Integer driverId) throws Exception{
    	if (driverId==null) return "";
    	String sql="SELECT NAME FROM driver A WHERE A.ID=:driverId ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("driverId", driverId);
    	sql=sql+" ORDER BY ID ";
    	searchBean.setFilterMap(m);
    	Object driverName=this.getAttributeValueBySql(sql, searchBean);
    	return (driverName!=null)?driverName.toString():"";	
    }
    /**
     * 获取customer表最大的ccode编码
     * @param
     * @return
     * @throws Exception
     */
    public  String getMaxCode() throws Exception{
    	String sql="SELECT MAX(A.DRIVER_NO) FROM driver A ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	searchBean.setFilterMap(m);
    	Object  o =this.getObjectResultBySql(null, sql, searchBean);
    	String tempnum="",pre="";    	
    	pre=Constants.CodePre.DRIVER_NO_TITLE.getValue();
    	if ((o==null) || ("".equals(o))){ //不存在 则去字典里面找customer 编码的前缀
    		tempnum =pre+"0000";
    	}else{
    		tempnum=o.toString();
    	}
    	tempnum=StringUtil.increaseOne(tempnum,4);
    	return tempnum;
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchDriver(String searchValue,String phone,Integer status,
    		int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM driver A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (A.NAME LIKE :searchValue OR A.DRIVER_NO LIKE :searchValue )";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	if ((phone!=null) && (!"".equals(phone))){
    		sql=sql+" AND A.PHONE LIKE :phone ";
    		m.put("phone", "%"+phone+"%");
    	}
    	if ((status!=null) && (status.intValue()>0)){
    		sql=sql+" AND A.STATUS =:status ";
    		m.put("status",status);
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(Driver.class,sql, searchBean, currentPage, pageSize);
    	return ph;	
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Driver d = (Driver)baseBean;
    	String sql="SELECT COUNT(*) FROM driver A WHERE TRANID=:tranId AND A.NAME =:name ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("tranId", d.getTranId());
    	m.put("name", "%"+d.getName()+"%"); 
    	if(baseBean.getId()!=null) {
    		sql = sql + "AND A.ID!=:id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
}
