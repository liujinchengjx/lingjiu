package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.business.IProductTaxCodeDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ProductTaxCode;

public class ProductTaxCodeDaoImpl extends BaseDAOImpl implements IProductTaxCodeDao{
	
    public  List selectProductTaxCodeForUpdateOnLineList(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM producttaxcode A ORDER BY A.ID ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(ProductTaxCode.class, sql, searchBean, currentPage, pageSize);
    	return ph.getRows();
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchProductTaxCode(String searchValue,String unit1,String unit2,
    		int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM producttaxcode A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    	   sql=sql+" AND (A.PRODUCT_NAME LIKE :searchValue) OR " +
    	   		" (A.CODE_TS LIKE :searchValue) OR (A.CODE_T LIKE :searchValue) OR " +
    	   		" (A.CRITERIONDECLARE LIKE:searchValue) OR (A.CONTROL_MA LIKE :searchValue) OR (A.QUARANTINE_MA LIKE :searchValue) ";
    	   m.put("searchValue", "%"+searchValue+"%");   
    	}
    	if ((unit1!=null) && (!"".equals(unit1))){
    		sql=sql+" AND (A.UNIT1 =:unit1) ";
    		m.put("unit1",unit1);
    	}
    	if ((unit2!=null) && (!"".equals(unit2))){
    		sql=sql+" AND (A.UNIT2 =:unit2) ";
    		m.put("unit2", unit2);
    	}
    	sql=sql+" ORDER BY A.ID DESC ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(ProductTaxCode.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    /**
     * 获得申报要素的顺序
     * @param codeTs
     * @return
     * @throws Exception
     */
    public String getCustomsCriterionTips(String codeTs) throws Exception{
    	String sql="SELECT A.CRITERIONDECLARE FROM producttaxcode A WHERE A.CODE_TS =:codeTs";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("codeTs", codeTs);
    	searchBean.setFilterMap(m); 
    	return (String)this.getAttributeValueBySql(sql, searchBean);
    }
    /**
     * 根据商品编码获取对象
     * @param codeTs
     * @return
     * @throws Exception
     */
    public ProductTaxCode getProductTaxCode(String codeTs) throws Exception{
    	if (codeTs==null || ("".equals(codeTs))) return null;
    	String sql="SELECT A.* FROM producttaxcode A WHERE RPAD(A.CODE_TS,10,0) =:codeTs ORDER BY A.ID DESC LIMIT 0,1";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("codeTs",StringUtil.rPad(codeTs,10,"0"));
    	searchBean.setFilterMap(m); 
    	return (ProductTaxCode)this.getObjectResultBySql(ProductTaxCode.class, sql, searchBean);
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	ProductTaxCode ptc = (ProductTaxCode)baseBean;
    	String sql="SELECT COUNT(*) FROM producttaxcode A WHERE (A.CODE_TS LIKE :codeTs)";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("codeTs", "%"+ptc.getCodeTs()+"%");
    	if(baseBean.getId()!=null) {
    		sql = sql + "AND A.ID!= :id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
}
