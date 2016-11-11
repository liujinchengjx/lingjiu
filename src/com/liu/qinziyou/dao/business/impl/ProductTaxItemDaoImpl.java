package com.liu.qinziyou.dao.business.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.business.IProductTaxItemDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.business.ProductTaxItem;

public class ProductTaxItemDaoImpl extends BaseDAOImpl implements IProductTaxItemDao{

	
    public  PageHelper searchProductTaxItem(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM producttaxitem A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND (A.CNAME LIKE :searchValue OR A.ENAME LIKE :searchValue OR A.PN LIKE :searchValue OR ";
    		sql=sql+" A.BRAND LIKE :searchValue OR A.CODE_TS LIKE :searchValue OR A.DECLAREELEMENTS LIKE :searchValue) ";
    		m.put("searchValue", "%"+searchValue+"%");
    	}	
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m);
    	ph=this.getPageHelperBySql(ProductTaxItem.class,sql, searchBean, currentPage, pageSize);
    	return ph;
    }
    
    
    @Override
	public boolean updateStatus(Integer id, Integer flag) throws Exception {
    	String sql="UPDATE producttaxitem A SET A.FLAG=:flag WHERE A.ID = :id ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("id", id);    	
    	m.put("flag",flag);
    	searchBean.setFilterMap(m);
    	int num = this.executeSQL(sql, searchBean);
    	if (num >0 ) {
			return true;
		}
    	return false;
	}


	/**
     * 根据型号，品牌获取 product的中文品名 和 申报要素信息， 并以数组的形式返回
     * @param pn
     * @param brand
     * @return
     * @throws Exception
     */
    public String [] getProductNameAndElements(String pn,String brand,String productName) throws Exception{
    	String sql=" SELECT A.CNAME,A.DECLAREELEMENTS,A.CODE_TS FROM producttaxitem A WHERE A.PN=:pn AND A.BRAND=:brand " ;
    	Object[] arrayObject=new Object[3];
    	String[] tempArrayObject=new String[3];
    	SearchBean searchBean=new SearchBean();
     	Map<String, Object> m=new HashMap<String, Object>();
     	if (("无型号".equals(pn)) || ("无牌".equals(brand)) ){
     	   sql=sql+" AND A.CNAME=:productName ";
     	   m.put("productName", productName);
     	}
     	//sql=sql+" AND A.CNAME=:productName ";
     	//m.put("productName", productName);
     	m.put("pn", pn);
    	m.put("brand",brand);
     	sql=sql+" ORDER BY A.LAST_MODIFY_TIME DESC LIMIT 1 ";
    	searchBean.setFilterMap(m);
     	arrayObject=this.getArrayObjectBySql(sql, searchBean);
     	if (arrayObject!=null){
     		tempArrayObject[0]=arrayObject[0].toString();
     		tempArrayObject[1]=arrayObject[1].toString();
     		tempArrayObject[2]=arrayObject[2].toString();
     		return tempArrayObject;
     	}else{
     		return null;
     	}     	
    }
    
    public ProductTaxItem getProuctTaxItem(String pn,String brand,String productName) throws Exception{
    	String sql="SELECT A.* FROM producttaxitem A WHERE A.PN=:pn AND A.BRAND=:brand AND A.CNAME=:productName LIMIT 0,1";
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("pn", pn);
    	m.put("brand", brand);
    	m.put("productName", productName);
    	SearchBean searchBean=new SearchBean();
    	searchBean.setFilterMap(m);
    	ProductTaxItem productTaxItem = (ProductTaxItem)this.getObjectResultBySql(ProductTaxItem.class, sql, searchBean);
    	return productTaxItem;
    }
    
    public void updateElements(String pn,String brand,String productName,String declareElements,String codeTs) throws Exception{
    	String sql="UPDATE producttaxitem A SET A.DECLAREELEMENTS=:declareElements,A.CODE_TS=:codeTs " +
    			"WHERE A.PN=:pn AND A.BRAND=:brand AND A.CNAME=:productName ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("productName", productName);	 
    	m.put("pn", pn);    	
    	m.put("brand",brand);
    	m.put("declareElements",declareElements);
    	m.put("codeTs",codeTs);
    	searchBean.setFilterMap(m);
    	this.executeSQL(sql, searchBean);
    	
    }
    /**
     * 加载申报要素信息 
     * @param codeTs
     * @return String [] 参数顺序说明：0 申报要素提示信息，1 申报要素，2 flag标识：值1 表示 申报要素没有找到 0 则表示有找到
     *  3 表示监管条件
     * @throws Exception
     */
//    public String [] getProductElements(String codeTs,String pn,String brand,String productName) throws Exception{
//    	String [] elements = new String []{"","","1",""};;
//    	String sql="SELECT A.CRITERIONDECLARE,IFNULL(A.CONTROL_MA,'') FROM producttaxcode A WHERE A.CODE_TS=:codeTs OR CODE_T=:codet";
//    	Object [] arrayObject=null;  
//    	SearchBean searchBean=new SearchBean();
//     	Map<String, Object> m=new HashMap<String, Object>();
//     	m.put("codeTs", codeTs);
//     	m.put("codet", codeTs);
//     	searchBean.setFilterMap(m);
//     	arrayObject=this.getArrayObjectBySql(sql, searchBean);
//    	if (arrayObject!=null){
//    		elements[0]=(arrayObject[0].toString()); //申报要素提示信息    	
//    		elements[3]=(arrayObject[1].toString()); //监管条件    		
//    		//sql="SELECT A.DECLAREELEMENTS FROM producttaxitem A WHERE RPAD(A.CODE_TS,10,0)=:codeTs AND A.PN=:pn AND A.BRAND=:brand ";
//    		sql="SELECT A.DECLAREELEMENTS FROM producttaxitem A WHERE  A.PN=:pn AND A.BRAND=:brand ";
//    		m.remove("codet");
//    		m.remove("codeTs");
//    		//if (("无型号".equals(pn)) || ("无牌".equals(brand)) ){
//    			sql=sql+" AND A.CNAME=:productName ";
//    			m.put("productName", productName);
//    		//}
//    		m.put("pn", pn);
//    		m.put("brand", brand);
//    		sql=sql+" LIMIT 0,1 ";
//    		searchBean.setFilterMap(m);
//         	Object o=this.getAttributeValueBySql(sql, searchBean);
//         	if (o!=null){
//         		elements[2]="0"; //找到该申报要素
//         		elements[1]=o.toString(); //申报要素
//         	}
//    	}
//    	return elements;
//    }
    
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	ProductTaxItem pti = (ProductTaxItem)baseBean;
    	String sql="SELECT COUNT(*) FROM producttaxitem A WHERE A.CNAME=:cname AND A.PN=:pn AND A.BRAND=:brand ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("cname",  pti.getCname());
    	m.put("pn", pti.getPn());
    	m.put("brand", pti.getBrand());
    	if(baseBean.getId()!=null) {
    		sql = sql + " AND A.ID!= :id";
    		m.put("id", baseBean.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
        
    }
}
