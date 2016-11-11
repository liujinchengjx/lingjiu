package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.ITruckDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Truck;

public class TruckDaoImpl extends BaseDAOImpl implements ITruckDao{

	
	public  PageHelper searchTruck(String carno,Integer tranId,String tonnage,Integer truckStatus,
    		Integer truckType,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM truck A WHERE 1=1 " ;
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	if ((carno!=null) && (!"".equals(carno))){
    		sql=sql+" AND ((A.CARNO_HK LIKE :carno) OR (A.CARNO_CH LIKE :carno) ) ";
    		m.put("carno","%"+carno+"%");
    	}
    	if ((tranId!=null) && (tranId.intValue()>0)){
    		sql=sql+" AND A.TRANID =:tranId ";	
    		m.put("tranId", tranId);
    	}
    	if ((tonnage!=null) && (!"".equals(tonnage))){
    		sql=sql+" AND A.TONNAGE =:tonnage ";	
    		m.put("tonnage", tonnage);
    	}
    	if ((truckStatus!=null) && (truckStatus.intValue()>0)){
    		sql=sql+" AND A.STATUS =:truckStatus ";	
    		m.put("truckStatus", truckStatus);
    	}
    	if ((truckType!=null) && (truckType.intValue()>0)){
    		sql=sql+" AND A.TYPE =:truckType ";	
    		m.put("truckType", truckType);
    	}
    	sql=sql+" ORDER BY A.LAST_MODIFY_TIME,A.ID DESC";
    	searchBean.setFilterMap(m);    	
    	ph=this.getPageHelperBySql(Truck.class, sql, searchBean, currentPage, pageSize);
    	return ph;	
    }
    
    /**
     * 根据货运公司和货车id获取车牌号，
     * @param tranId
     * @param type 0 表示获取香港车牌号，1 表示获取大陆车牌号，2 表示，大陆车牌号和香港车牌号拼接在一起返回
     * @return
     * @throws Exception
     */
    public  String getTruckNo(Integer tranId,Integer truckId,Integer type) throws Exception{
    	if (truckId==null) return "";
    	String sql="";
    	if (type==0){
    		sql="SELECT A.CARNO_HK "; 
    	}else if (type==1){
        	sql="SELECT A.CARNO_CH ";
    	}else if (type==2){
        	sql="SELECT CONCAT(A.CARNO_CH,'/',A.CARNO_HK) CARNO ";
    	}
    	sql=sql+" FROM truck A WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	sql=sql+" AND A.ID=:truckId  ORDER BY ID";
    	m.put("truckId",truckId); 
    	searchBean.setFilterMap(m);    	
    	Object carNo=this.getAttributeValueBySql(sql, searchBean);
    	return (carNo!=null)?carNo.toString().trim():"";	
    }
    
    public  Map<Integer, String> getTruckNos(String truckIds) throws Exception{
    	String sql="SELECT A.ID, CONCAT(A.CARNO_CH,'/',A.CARNO_HK) CARNO FROM truck A WHERE A.ID in (SELECT TRUCK_ID FROM truck_bind d WHERE d.ID in ("+truckIds+")) ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	searchBean.setFilterMap(m);
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	List<Object[]> objects = this.getListResultBySql(null, sql, searchBean);
    	for (Object[] object : objects) {
    		map.put(Integer.valueOf(object[0].toString()), object[1].toString());
		}
    	return map;	
    }
    
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Truck tk = (Truck)baseBean;
    	String sql="SELECT COUNT(*) FROM truck A WHERE TRANID=:tranId AND ((A.CARNO_HK =:carnoHk) OR (A.CARNO_CH =:carnoCh))"; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("tranId", tk.getTranId());
    	m.put("carnoHk", tk.getCarnoHk());
    	m.put("carnoCh", tk.getCarnoCh());    
    	if(baseBean.getId()!=null) {
    		sql = sql + " AND A.ID!= :id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
  
    public Truck getTruckWithTranCompany() throws Exception{
    	String sql = "SELECT {truck.*},{transportCompany.*} FROM truck A,transport_company B WHERE A.TRANID = b.ID AND A.ID=1";
    	//Class[] clazs = {Truck.class,TransportCompany.class};
    	//Object[] objects = super.getObjectsBySql(sql, clazs, null);
    	//Truck t = (Truck)objects[0];
    	return null;
    }
}
