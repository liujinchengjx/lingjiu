package com.liu.qinziyou.dao.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IValueAddedServicesDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ChargesVariables;
import com.liu.qinziyou.entity.business.ValueAddedServices;
import com.liu.qinziyou.entity.business.ValueAddedServicesParam;

public class ValueAddedServicesDaoImpl extends BaseDAOImpl implements IValueAddedServicesDao{
   
    public  ValueAddedServices getValueAddedServicesByCode(String scode) throws Exception{
    	String sql=" SELECT A.* FROM value_added_services A,charges_variables B WHERE A.CHARGE_VARIABLES_ID=B.ID AND B.SCODE=:scode ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("scode",scode);
    	searchBean.setFilterMap(m); 
    	ValueAddedServices vas=(ValueAddedServices)this.getObjectResultBySql(ValueAddedServices.class, sql, searchBean);
    	ChargesVariables cv=(ChargesVariables)this.findById(ChargesVariables.class, vas.getChargesVariablesId());
    	vas.setChargesVariables(cv);
    	return vas;
    }
    public  Double getVariableValue(String scode) throws Exception{
    	String sql=" SELECT B.VARIABLE_VALUE FROM value_added_services A,charges_variables B " +
    			" WHERE A.CHARGE_VARIABLES_ID=B.ID AND B.SCODE=:scode ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("scode",scode);
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);    	
    	return o==null?0.0:Double.parseDouble(o.toString());
    }
    public  List<String> advNameList() throws Exception{ //没有选中的增值服务Items name
    	String sql=" SELECT DISTINCT A.SERVICE_NAME FROM value_added_services A,charges_variables B "; 
    	sql=sql+" WHERE A.CHARGE_VARIABLES_ID=B.ID ";
    	sql=sql+" ORDER BY A.TYPE,B.SCODE ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	
    	searchBean.setFilterMap(m); 
    	List<String> notIndAdValueList=this.getListResultBySql(null, sql, searchBean);
    	return notIndAdValueList;
    }
    /**
     * 根据代码前缀 如 AV0001-1 的AV0001 获取费用代码
     * @param preScode
     * @return
     * @throws Exception
     */
    public  List getVariableValueWithPreCode(String preScode) throws Exception{
    	String sql=" SELECT A.* FROM value_added_services A,charges_variables B WHERE A.CHARGE_VARIABLES_ID=B.ID ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((preScode!=null) && (!"".equals(preScode))){
    		sql=sql+" AND B.SCODE LIKE :preScode ";
    		m.put("preScode", "%"+preScode+"%");
    	}
    	sql=sql+" ORDER BY B.SCODE ";
    	searchBean.setFilterMap(m); 
    	List<ValueAddedServices> vasList=this.getListResultBySql(ValueAddedServices.class, sql, searchBean);
    	for(ValueAddedServices vas:vasList){
    		ChargesVariables cv=(ChargesVariables)this.findById(ChargesVariables.class, vas.getChargesVariablesId());
			vas.setChargesVariables(cv);
    	}
    	return vasList;
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchValueAddedServices(String searchValue,Integer ifShow,int currentPage, int pageSize) throws Exception{
    	String sql=" SELECT A.* FROM value_added_services A,charges_variables B WHERE A.CHARGE_VARIABLES_ID=B.ID ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+"( A.SERVICE_NAME LIKE :searchValue OR B.SCODE=:searchValue OR B.VARIABLE_NAME LIKE :searchValue )";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	if ((ifShow!=null) && (ifShow.intValue()>=0)){
    		sql=sql+" AND A.IF_SHOW =:ifShow ";
    		m.put("ifShow", ifShow);
    	}
    	sql=sql+" ORDER BY A.TYPE,B.SCODE ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(ValueAddedServices.class,sql, searchBean, currentPage, pageSize);
    	for(BaseBean bean:ph.getRows()){
    		ValueAddedServices vas=(ValueAddedServices)bean;
    		ChargesVariables cv=(ChargesVariables)this.findById(ChargesVariables.class, vas.getChargesVariablesId());
			vas.setChargesVariables(cv);
    	}
    	return ph;
    }    
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	ValueAddedServices vas = (ValueAddedServices)baseBean;
    	String sql="SELECT COUNT(*) FROM value_added_services A,charges_variables B WHERE A.CHARGE_VARIABLES_ID=B.ID ";
    	sql=sql+" AND B.SCODE=:scode ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("scode",vas.getChargesVariables().getScode()); 
    	if(baseBean.getId()!=null) {
    		sql = sql + " AND A.ID!=:id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    /**
     * 
     * 获取货值信息
     * 
     * */
	@SuppressWarnings("unchecked")
	@Override
	public List searchCoinValue(Integer id) {
		// TODO Auto-generated method stub
		String sql="SELECT A.* FROM value_added_services_param A WHERE A.SERVICE_ID=:serviceId";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("serviceId",id); 
    	searchBean.setFilterMap(m);
    	List<ValueAddedServicesParam> list=this.getListResultBySql(ValueAddedServicesParam.class, sql, searchBean);
		return list;
	}
	/***
	 * 
	 * 删除货值信息
	 * 
	 * */
	@Override
	public int deleteCoinValue(Integer id, Integer serviceID) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM value_added_services_param WHERE SERVICE_ID=:SEID AND ID=:IDS";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("SEID",serviceID); 
    	m.put("IDS",id); 
    	searchBean.setFilterMap(m);
    	int row=this.executeSQL(sql, searchBean);
    	return row;
	}
	
}
