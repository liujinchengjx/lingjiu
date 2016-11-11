package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.LoginUserUtil;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.ICustomerDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Customer;
import com.liu.qinziyou.exception.ServiceException;

public class CustomerDaoImpl extends BaseDAOImpl implements ICustomerDao{
    
	
	 public Customer getCustomer(Integer companyId) throws Exception{
    	String sql="SELECT A.*  FROM customer A WHERE A.COMPANY_ID =:companyId";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("companyId", companyId);
    	searchBean.setFilterMap(m);
    	Customer cus =(Customer)this.getObjectResultBySql(Customer.class, sql, searchBean);
    	return cus;
	 }
	 public PageHelper searchCustomer(String cname,String ename,String respPerson,String ccode,Integer opType,Integer status,
			 String businessType,boolean isFilter,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM customer A,company B WHERE A.COMPANY_ID = B.ID  ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if (isFilter) {
    		
		}    	
    	if ((cname!=null) && (!"".equals(cname))){
    		sql=sql+" AND (B.CNAME LIKE :cname OR B.CSNAME LIKE :cname ) ";
    		m.put("cname", "%"+cname+"%");	 
    	}
    	if ((ename!=null) && (!"".equals(ename))){
    		sql=sql+" AND (B.ENAME LIKE :ename OR B.ESNAME LIKE :ename) ";
    		m.put("ename", "%"+ename+"%");	
    	}
    	if ((respPerson!=null) && (!"".equals(respPerson))){
    		sql=sql+" AND (A.BUSINESSMAN LIKE :respPerson OR A.SERVICEMAN LIKE :respPerson) ";
    		m.put("respPerson", "%"+respPerson+"%");	
    	}
    	if ((ccode!=null) && (!"".equals(ccode))){
    		sql=sql+" AND (B.CCODE LIKE :ccode) ";
    		m.put("ccode", "%"+ccode+"%");	
    	}
    	if ((opType!=null) && (opType.intValue()>0)){
    		sql=sql+" AND (A.OP_TYPE =:opType) ";
    		m.put("opType", opType);	
    	}
    	if ((status!=null) && (status.intValue()>0)){
    		sql=sql+" AND (B.STATUS =:status) ";
    		m.put("status",status);	
    	}
    	if ((businessType!=null) && (!"".equals(businessType))){
    		sql=sql+" AND (A.BUSINESSTYPE LIKE :businessType) ";
    		m.put("businessType","%"+businessType+"%");	
    	}
    	sql=sql+" ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m);
    	PageHelper ph=this.getPageHelperBySql(Customer.class, sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(IBean baseBean) throws Exception{
    	Customer cus = (Customer)baseBean;
    	String sql="SELECT COUNT(*)  FROM customer A INNER JOIN company B ON A.COMPANY_ID = B.ID  WHERE B.COMPANY_TYPE=:companyType AND " +
    			" ((B.CNAME =:cname) OR (B.ENAME =:ename) OR (B.CSNAME =:csname) OR (B.ESNAME =:esname) ) ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("cname", cus.getCompany().getCname());
    	m.put("ename", cus.getCompany().getEname());
    	m.put("csname",cus.getCompany().getCsname());
    	m.put("esname", cus.getCompany().getEsname());
    	m.put("companyType", cus.getCompany().getCompanyType());   	
    	if(baseBean.getId()!=null) {
    		sql = sql + "AND B.ID!=:id";
    		m.put("id", cus.getCompany().getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    /**
     * 审核客户
     * @param id 客户id
     * @param state 状态值
     * @return
     * @throws ServiceException
     */
    public  boolean updateCustomerStatus(Integer id,Integer status) throws Exception{
    	String sql="UPDATE customer A SET A.STATUS=:status WHERE A.ID=:id";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("status", status);
    	m.put("id",id);
    	searchBean.setFilterMap(m); 
    	Integer count=this.executeSQL(sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    public  boolean updateDiscountRateStatus(Integer companyId,Integer discountRateStatus) throws Exception{
    	String sql=" UPDATE customer A SET A.DISCOUNTRATE_STATUS=:discountRateStatus ";
    	sql=sql+" WHERE A.COMPANY_ID=:companyId " ;    			
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("discountRateStatus", discountRateStatus);
    	m.put("companyId",companyId);
    	searchBean.setFilterMap(m); 
    	Integer count=this.executeSQL(sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    public  boolean updateDiscountRate(Integer companyId,Double rentDiscountRate,
    		Double dnDiscountRate,Double mixDiscountRate,Double addDiscountRate) throws Exception{
    	String sql=" UPDATE customer A SET A.RENT_DISCOUNT_RATE=:rentDiscountRate,A.DN_DISCOUNT_RATE=:dnDiscountRate,";
    	sql=sql+" A.MIX_DISCOUNT_RATE=:mixDiscountRate,A.ADD_DISCOUNT_RATE=:addDiscountRate WHERE A.COMPANY_ID=:companyId " ;    			
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("rentDiscountRate", rentDiscountRate);
    	m.put("dnDiscountRate", dnDiscountRate);
    	m.put("mixDiscountRate", mixDiscountRate);
    	m.put("addDiscountRate", addDiscountRate);
    	m.put("companyId",companyId);
    	searchBean.setFilterMap(m); 
    	Integer count=this.executeSQL(sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    public  boolean updatePosition(Integer customerId,String positionCode) throws Exception{
    	String sql=" UPDATE customer A SET POSITION_CODE=:positionCode WHERE A.COMPANY_ID=:customerId ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("positionCode", positionCode);
    	m.put("customerId",customerId);
    	searchBean.setFilterMap(m); 
    	Integer count=this.executeSQL(sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    
	@Override
	public PageHelper searchCustomer(String positionCode,int currentPage, int pageSize) throws Exception {
		String sql="SELECT A.* FROM customer A,customer_position P WHERE A.COMPANY_ID = P.COMPANY_ID AND  P.POSITION_CODE = :positionCode ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();   
    	m.put("positionCode", positionCode);
    	searchBean.setFilterMap(m);
    	PageHelper ph=this.getPageHelperBySql(Customer.class, sql, searchBean, currentPage, pageSize);
    	return ph;
	}
  
}
