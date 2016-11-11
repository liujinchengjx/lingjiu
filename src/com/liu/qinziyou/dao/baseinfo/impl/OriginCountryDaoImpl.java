package com.liu.qinziyou.dao.baseinfo.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.IOriginCountryDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.baseinfo.OriginCountry;

public class OriginCountryDaoImpl extends BaseDAOImpl implements IOriginCountryDao {
	
    public  String getOriginCountryCode(String originName,boolean isCname) throws Exception{
    	String sql="SELECT A.CCODE FROM origin_country A WHERE 1=1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if (isCname){
    		sql=sql+" AND A.CSNAME=:originName ";
    	}else{
    		sql=sql+" AND A.ESNAME=:originName ";
    	}
    	m.put("originName",originName);
    	searchBean.setFilterMap(m);
    	Object o=getAttributeValueBySql(sql, searchBean);
    	return (o==null)?"":o.toString();

    }
    
    /**
     * 获取是否最惠国 true 是最惠国 false 非最惠国
     * @param origin 原产地
     * @return
     * @throws Exception
     */
    public boolean getDiscountFlag(String  origin) throws Exception{
    	String sql="SELECT A.DISCOUNT_FLAG FROM origin_country A  WHERE A.CSNAME=:origin  LIMIT 0,1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("origin",origin);
    	searchBean.setFilterMap(m);
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	int flag=Integer.parseInt( (o==null)? "0": o.toString());
    	return (flag==0)?true:false;
    }
    /**
     * 获取是否最惠国 true 是最惠国 false 非最惠国
     * @param code
     * @param cnName
     * @return
     * @throws Exception
     */
    @Deprecated
    public boolean getDiscountFlag(Integer productId) throws Exception{
    	String sql="SELECT A.DISCOUNT_FLAG FROM origin_country A,product B WHERE A.CSNAME=B.ORIGIN AND B.ID=:productId LIMIT 0,1 ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("productId",productId);
    	searchBean.setFilterMap(m);
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	int flag=Integer.parseInt( (o==null)? "0": o.toString());
    	return (flag==0)?true:false;
    }
    /**
     * 获取是否疫区 true 是疫区 false 非疫区
     * @param code
     * @param cnName
     * @return
     * @throws Exception
     */
    @Deprecated
    public boolean getEpidemicFlag(Integer productId) throws Exception{
    	String sql="SELECT COUNT(*) FROM origin_country A,product B WHERE A.EPIDEMIC_FLAG=1 AND A.CSNAME=B.ORIGIN AND B.ID =:productId ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("productId",productId);
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    
    /**
     * 获取是否疫区 true 是疫区 false 非疫区
     * @param code
     * @param cnName
     * @return
     * @throws Exception
     */
    public boolean getEpidemicFlag(String origin) throws Exception{
    	String sql="SELECT COUNT(*) FROM origin_country A WHERE A.EPIDEMIC_FLAG=1 AND A.CSNAME=:origin ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("origin",origin);
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
    
    /**
     * 根据产品ids查看是否有来自疫区
     * @param productIds
     * @return
     * @throws Exception
     */
    public boolean getEpidemicFlag(Integer [] productIds) throws Exception{
    	boolean flag=false;
    	for(Integer productId:productIds){
    		flag=this.getEpidemicFlag(productId);
    		if (flag){
    			return true;
    		}
    	}
    	return flag;
    }
    /**
     * 获取原产国的中英文名称
     * @return
     * @throws Exception
     */
    public  String getOriginCountryName(String code,boolean isCnName) throws Exception{
    	String sql="";
    	if (isCnName){
    		sql="SELECT DISTINCT CSNAME "; 
    	}else {
        	sql="SELECT DISTINCT ESNAME ";
    	}
    	sql=sql+" FROM origin_country A WHERE A.CCODE=:code ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("code",code);
    	searchBean.setFilterMap(m);    	
    	Object name=this.getAttributeValueBySql(sql, searchBean);
    	return (name!=null)?name.toString():"";	
    }
    public  Integer updateDiscountOrEpidemicFlag(Integer originCountryId,Integer discountFlag,Integer epidemicFlag) 
               throws Exception{
    	String sql="";
    	SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		if ((discountFlag != null) && discountFlag.intValue()>=0){
			sql=" UPDATE origin_country A SET A.DISCOUNT_FLAG=:discountFlag WHERE A.ID=:originCountryId ";
			m.put("discountFlag", discountFlag);
			m.put("originCountryId", originCountryId);	
		}
		if ((epidemicFlag != null) && epidemicFlag.intValue()>=0){
			sql=" UPDATE origin_country A SET A.EPIDEMIC_FLAG=:epidemicFlag WHERE A.ID=:originCountryId ";
			m.put("epidemicFlag", epidemicFlag);
			m.put("originCountryId", originCountryId);
		}
		if (!"".equals(sql)){
			searchBean.setFilterMap(m);
			int rows = this.executeSQL(sql, searchBean);
			return rows;
		}else{
			throw new Exception("发生异常开启/关闭 发生异常！");
		}
    }
    /**
     * 检测某个原产国是否存在
     * @param origin
     * @return
     * @throws Exception
     */
    public boolean isExist(String origin) throws Exception{
    	String sql="SELECT COUNT(*) FROM origin_country A WHERE A.CSNAME=:origin ORDER BY A.ID";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("origin", origin);
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    	
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchOriginCountry(String searchValue,int discountFlag,int epidemicFlag,int currentPage, int pageSize) throws Exception{
    	String sql=" SELECT A.* FROM origin_country A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((discountFlag==0) || (discountFlag==1)){
    	   sql=sql+" AND A.DISCOUNT_FLAG=:discountFlag ";
    	   m.put("discountFlag",discountFlag);
    	}
    	if ((epidemicFlag==0) || (epidemicFlag==1)){
    	   sql=sql+" AND A.EPIDEMIC_FLAG =:epidemicFlag ";
     	   m.put("epidemicFlag",epidemicFlag);
     	}  
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+ " AND ((A.CSNAME LIKE :searchValue) OR (A.ESNAME LIKE :searchValue) OR (A.CCODE LIKE :searchValue) OR (A.ALIAS LIKE :searchValue) ) ";
    		m.put("searchValue", "%"+searchValue+"%");
    	}
    	sql=sql+" ORDER BY ID ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(OriginCountry.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	OriginCountry oc = (OriginCountry)baseBean;
    	String sql="SELECT COUNT(*) FROM origin_country A WHERE ((A.CSNAME=:csname) OR (A.ESNAME =:esname))";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("csname", oc.getCsname());
    	m.put("esname", oc.getEsname());
    	if(baseBean.getId()!=null){
    		sql = sql + " AND ID !=:id";
    		m.put("id", oc.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
}
