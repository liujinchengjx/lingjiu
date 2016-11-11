package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IChargesVariablesDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ChargesVariables;

public class ChargesVariablesDaoImpl extends BaseDAOImpl implements IChargesVariablesDao{

	/**
     * 获取最大的序列号
     * @param feeType
     * @param businessType
     * @return
     * @throws Exception
     */
    public Integer getMaxScode(Integer feeType,Integer businessType,Integer opType) throws Exception{
    	String sql="SELECT MAX(SCODE) SEQUENCE FROM charges_variables A WHERE A.BUSINESSTYPE=:businessType AND A.FEE_TYPE=:feeType ";
    	sql=sql+" AND A.OP_TYPE=:opType ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("feeType", feeType);
    	m.put("businessType", businessType);
    	m.put("opType", opType);    	
    	searchBean.setFilterMap(m);
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o==null?1:(Integer.parseInt(o.toString())+1);
    }   
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchChargesVariables(String searchValue,Integer [] businessTypes,Integer opType,String preCode,
    		int currentPage, int pageSize) throws Exception{
    	String sql=" SELECT A.* FROM charges_variables A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if((searchValue!=null) && (!"".equals(searchValue))){
           sql=sql+" AND ( A.VARIABLE_NAME LIKE :searchValue OR A.SCODE LIKE :searchValue ) ";
           m.put("searchValue", "%"+searchValue+"%");
    	}
    	if ((businessTypes!=null) && (businessTypes.length>0)){
    		sql=sql+" AND A.BUSINESSTYPE IN (:businessTypes) ";
    		m.put("businessTypes", businessTypes);	
    	}
    	if ((preCode!=null) && (!"".equals(preCode))){
    		sql=sql+" AND A.SCODE LIKE :preCode ";
    		m.put("preCode", preCode+"%");	
    	}
    	if ((opType!=null) && (opType.intValue()>0)){
    		sql=sql+" AND (A.OP_TYPE=:opType OR A.OP_TYPE=5) "; //非抬头模式的也要读取出来	
    		m.put("opType", opType);
    	}
    	sql=sql+" ORDER BY BUSINESSTYPE,SEQUENCE,SCODE ";   	
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(ChargesVariables.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }    
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	ChargesVariables cv = (ChargesVariables)baseBean;
    	String sql="SELECT COUNT(*) FROM charges_variables A WHERE A.BUSINESSTYPE=:businessType AND A.OP_TYPE=:opType ";
    	sql=sql+" AND A.FEE_TYPE=:feeType AND A.VARIABLE_NAME =:variableName ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("businessType", cv.getBusinessType());
    	m.put("opType", cv.getOpType());
    	m.put("feeType", cv.getFeeType());
    	m.put("variableName",cv.getVariableName()); 
    	if(baseBean.getId()!=null) {
    		sql = sql + "AND A.ID!=:id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
}
