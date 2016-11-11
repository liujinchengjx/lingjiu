package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.Constants;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.business.IContractDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.Contract;

public class ContractDaoImpl extends BaseDAOImpl implements IContractDao{	
	
	public  PageHelper searchContract(String ctCode,Integer customerId,Integer businessType,Integer contractStatus,
    		String startTime,String endTime,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM contract A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((ctCode!=null) && (!"".equals(ctCode))){
    		sql=sql+" AND (A.CTCODE LIKE :ctCode) ";
    		m.put("ctCode", "%"+ctCode+"%");
    	}
    	if ((customerId!=null) && (customerId.intValue()>0)){
    		sql=sql+" AND A.SELLER_ID =:customerId ";
    		m.put("customerId",customerId);
    	}
    	if ((businessType!=null) && (businessType.intValue()>0)){
    		sql=sql+" AND A.BUSINESSTYPE =:businessType ";
    		m.put("businessType",businessType);
    	}
    	if ((contractStatus!=null) && (contractStatus.intValue()>0)){
    		sql=sql+" AND A.STATUS =:contractStatus ";
    		m.put("contractStatus",contractStatus);
    	}
    	if ((startTime!=null) && (!"".equals(startTime))){
    		sql=sql+" AND A.CREATE_TIME >= :startTime ";
    		m.put("startTime", startTime+" 00:00:00");
    	}
    	if((endTime!=null) && (!"".equals(endTime))){
    		sql=sql+" AND A.CREATE_TIME <= :endTime ";
    		m.put("endTime", endTime+" 23:59:59");
    	}
    	sql=sql+"  ORDER BY A.ID DESC";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Contract.class, sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Contract con = (Contract)baseBean;
    	String sql="SELECT COUNT(*) FROM contract A WHERE (A.CTCODE LIKE :ctCode) ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("ctCode", "%"+con.getCtCode()+"%"); 
    	if(baseBean.getId()!=null) {
    		sql = sql + "AND A.ID!= :id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    /**
     * 获取Contract表最大的code编码
     * @param
     * @return
     * @throws Exception
     */
    public  String getMaxCode() throws Exception{   	
     	String sql="SELECT MAX(A.CTCODE) FROM contract A";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	searchBean.setFilterMap(m);
    	Object  o =this.getObjectResultBySql(null, sql, searchBean);
    	String tempnum="",pre="";    	
    	pre=Constants.CodePre.CONTRACT_TITLE.getValue();
    	if ((o==null) || ("".equals(o))){ //不存在 则去字典里面找customer 编码的前缀
    		tempnum =pre+"0000";
    	}else{
    		tempnum=o.toString();
    	}
    	tempnum=StringUtil.increaseOne(tempnum,4);
    	return tempnum;
    }
}
