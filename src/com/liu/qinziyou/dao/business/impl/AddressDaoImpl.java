package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IAddressDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Address;

public class AddressDaoImpl extends BaseDAOImpl implements IAddressDao {

	
	public PageHelper searchAddress(Integer companyId,String address,int currentPage, int pageSize) throws Exception {
		String sql=" SELECT A.* FROM address A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if(companyId != null && companyId.intValue()>0){
    		sql = sql + " AND A.COMPANY_ID=:companyId";
    		m.put("companyId",companyId);
    	}
    	if((address != null) && (!"".equals(address)) ){
    		sql = sql + " AND A.ADDRESS=:address";
    		m.put("address",address);
    	}
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Address.class,sql, searchBean, currentPage, pageSize);
    	return ph;
	}
	public  void makeIfNotAddress(Integer companyId,String address) throws Exception{
		if ((address==null) || (!"".equals(address))) return ;
		String sql="SELECT * FROM address A WHERE A.COMPANY_ID=:companyId AND A.ADDRESS=:address";
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("companyId",companyId);
		m.put("address",address);
		SearchBean searchBean=new SearchBean();
		searchBean.setFilterMap(m); 
		Address a = (Address) this.getObjectResultBySql(Address.class, sql, searchBean);
		if(a == null){
			Address a1 = new Address();
			a1.setAddress(address);
			a1.setCompanyId(companyId);
			this.save(a1);
		}
	}
	public int delAddress(Integer companyId)throws Exception{
		String sql="DELETE FROM address WHERE COMPANY_ID=:companyId"; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("companyId",companyId);    
    	searchBean.setFilterMap(filterMap);
        int row=this.executeSQL(sql, searchBean);
    	return row;		
	}
	
	public String getAddress(Integer companyId)throws Exception{
		String sql="SELECT ADDRESS FROM address WHERE COMPANY_ID=:companyId ORDER BY ID ASC LIMIT 1"; 
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>();
    	filterMap.put("companyId",companyId);    
    	searchBean.setFilterMap(filterMap);
        String address = (String) this.getObjectResultBySql(null, sql, searchBean);
    	return address;		
	}

}
