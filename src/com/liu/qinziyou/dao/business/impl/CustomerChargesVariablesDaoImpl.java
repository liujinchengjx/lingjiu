package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.ICustomerChargesVariablesDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.CustomerChargesVariables;

public class CustomerChargesVariablesDaoImpl extends BaseDAOImpl implements ICustomerChargesVariablesDao{	
	/**
     * 根据客户获取 根据代码值
     * @param scode
     * @return
     * @throws Exception
     */
    public Double getVariableValueByCode(Integer customerId,String scode)throws Exception{
    	String sql="SELECT A.VARIABLE_VALUE FROM customer_charges_variables A  WHERE A.CUSTOMERID=:customerId ";
    	sql=sql+" AND A.SCODE=:scode ORDER BY A.LAST_MODIFY_TIME DESC LIMIT 0,1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("customerId", customerId);
    	m.put("scode", scode);   
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o==null?null:Double.parseDouble(o.toString());
    }
    public String getVariableNameByCode(Integer customerId,String scode)throws Exception{
    	String sql="SELECT A.VARIABLE_NAME FROM customer_charges_variables A  WHERE A.CUSTOMERID=:customerId ";
    	sql=sql+" AND A.SCODE=:scode ORDER BY A.LAST_MODIFY_TIME DESC LIMIT 0,1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("customerId", customerId);
    	m.put("scode", scode);   
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o==null?"":o.toString();
    }
    /**
     * 根据客户获取 根据代码值地图
     * @param scode
     * @return
     * @throws Exception
     */
    public Map<String, Double> getVariableValueMap(Integer customerId,String preCode)throws Exception{
    	String sql="SELECT A.* FROM customer_charges_variables A  WHERE A.CUSTOMERID=:customerId ";
    	sql=sql+" AND A.SCODE LIKE :preCode ORDER BY A.SCODE ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("customerId", customerId);
    	m.put("preCode", "%"+preCode+"%");   
    	searchBean.setFilterMap(m); 
    	List<CustomerChargesVariables> ccvList=this.getListResultBySql(CustomerChargesVariables.class, sql, searchBean);
    	Map<String, Double> mapCodes=new HashMap<String,Double>();
    	for(CustomerChargesVariables ccv:ccvList){
    		mapCodes.put(ccv.getScode(), ccv.getVariableValue());
    	}
    	return mapCodes;
    }
    /**
     * 判断该编码是否有效
     * @param customerId
     * @param scode
     * @return
     * @throws Exception
     */
    public boolean isValidCode(Integer customerId,String scode)throws Exception{
    	String sql="SELECT A.FLAG FROM customer_charges_variables A  WHERE A.CUSTOMERID=:customerId AND A.SCODE=:scode ";
    	sql=sql+"  ORDER BY LAST_MODIFY_TIME DESC LIMIT 0,1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("customerId", customerId);
    	m.put("scode", scode);   
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o==null?false:"0".equals(o.toString());
    }
    /**
     * 是否启用 判断 VARIABLE_VALUE值 0 表示启用 1 表示禁用
     */
    public boolean isEnable(Integer customerId,String scode)throws Exception{
    	String sql="SELECT A.VARIABLE_VALUE FROM customer_charges_variables A  WHERE A.CUSTOMERID=:customerId AND A.SCODE=:scode ";
    	sql=sql+"  ORDER BY LAST_MODIFY_TIME DESC LIMIT 0,1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("customerId", customerId);
    	m.put("scode", scode);   
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	if (o==null ) return false;
    	if ("0.0".equals(o.toString()) || 
    			"0".equals(o.toString()) ){
    	   return true;
    	}else{
    	   return false;
    	}
    }
    /**
     * 
     * @param id
     * @param chargesValue
     * @return
     * @throws Exception
     */
    public int updateCustomerChargesVariables(Integer id,Double chargesValue,String description) throws Exception{
    	String sql="UPDATE customer_charges_variables A SET A.VARIABLE_VALUE=:chargesValue,A.DESCRIPTION=:description WHERE A.ID=:id ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("id", id);
    	m.put("chargesValue", chargesValue);
    	m.put("description", description);
    	searchBean.setFilterMap(m); 
    	int row=this.executeSQL(sql, searchBean);
    	return row;	
    }
	
    /**
     * 删除cusId 对应的费用关联记录
     * @param cusId
     * @return
     * @throws Exception
     */
    public  boolean deleteCustomerChargesVariablesByCusId(int customerId) throws Exception{
    	String sql="DELETE FROM customer_charges_variables A WHERE A.CUSTOMERID=:customerId";
    	int rows=0;
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("customerId", customerId);
    	searchBean.setFilterMap(m);
    	rows=this.executeSQL(sql, searchBean);
    	return rows>0?true:false;
    }
    public  PageHelper searchCusInCharges(String searchValue,Integer customerId,
    		int currentPage, int pageSize)throws Exception{
    	String sql="SELECT A.* FROM customer_charges_variables A WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	PageHelper ph=new PageHelper();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
     	   sql=sql+" AND (A.VARIABLE_NAME LIKE :searchValue or A.SCODE LIKE :searchValue) ";
     	   m.put("searchValue","%"+searchValue+"%");
     	}
    	if ((customerId!=null) && (customerId.intValue()>0)){
    	   sql=sql+" AND A.CUSTOMERID =:customerId ";
    	   m.put("customerId", customerId);
    	}
    	sql=sql+" ORDER BY A.ID ";
    	m.put("customerId", customerId);    	
    	searchBean.setFilterMap(m);
    	ph=this.getPageHelperBySql(CustomerChargesVariables.class, sql, searchBean, currentPage, pageSize);
    	return ph;
    	
    }
}
