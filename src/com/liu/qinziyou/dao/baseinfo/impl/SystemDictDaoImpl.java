package com.liu.qinziyou.dao.baseinfo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.ISystemDictDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.baseinfo.SystemDict;

public class SystemDictDaoImpl extends BaseDAOImpl implements ISystemDictDao {
    /**
     * 更改字典的状态
     * @param systemDictId
     * @param flag
     */
    public Integer updateFlag(Integer systemDictId,Integer flag) throws Exception{
    	String sql=" UPDATE system_dict A SET A.FLAG=:flag WHERE A.ID=:systemDictId ";
    	SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("systemDictId", systemDictId);
		m.put("flag", flag);
		searchBean.setFilterMap(m);
		int rows =this.executeSQL(sql, searchBean);
    	return rows; 
    }
    /**
     * 根据Type读取字典列表
     * @param type
     * @return
     * @throws Exception
     */
    public List selectDictByType(String type,Integer flag) throws Exception{
    	String sql="SELECT A.* FROM system_dict A WHERE A.TYPE=:type AND A.FLAG=:flag ";		
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("type", type);
		m.put("flag", flag);
		sql=sql+" ORDER BY A.SEQUENCE,A.DICT_CODE,A.ID ";
		searchBean.setFilterMap(m); 
		return  this.getListResultBySql(SystemDict.class, sql, searchBean); 
    }
    /**
     * 根据字典类型和字典子项值，获得子项的名称
     * @param dictType 字典大类型
     * @param dictCode 字典字典子项代码
     * @param isCnName 是否输出中文名称，否则输出因为名称
     * @return
     * @throws Exception
     */
    public  String getDictName(String dictType,String dictCode,boolean isCnName) throws Exception{
    	if (dictCode==null) return "";
    	String sql="";
    	if (isCnName){
    		sql=" SELECT DICT_NAME "; 
    	}else {
        	sql="SELECT DICT_ENAME ";
    	}
    	sql=sql+" FROM system_dict A WHERE A.TYPE=:dictType AND A.DICT_CODE=:dictCode ORDER BY A.ID LIMIT 0,1";
    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("dictType",dictType);
    	m.put("dictCode",dictCode); 
    	searchBean.setFilterMap(m);    	
    	Object name=this.getAttributeValueBySql(sql, searchBean);
    	return (name!=null)?name.toString():"";	
    }
    public  String getDictCnName(String dictType,String enName) throws Exception{
    	if (enName==null) return "";
    	String sql=" SELECT A.DICT_NAME FROM system_dict A WHERE A.TYPE=:dictType AND A.DICT_ENAME=:enName ORDER BY A.ID LIMIT 0,1";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("dictType",dictType);
    	m.put("enName",enName); 
    	searchBean.setFilterMap(m);    	
    	Object name=this.getAttributeValueBySql(sql, searchBean);
    	return (name!=null)?name.toString():"";
    }
    /**
     * 检测字典是否存在
     * @param type
     * @param dictName
     * @return
     * @throws Exception
     */
    public boolean isExist(String type,String dictName) throws Exception{
    	String sql="SELECT A.* FROM system_dict A WHERE A.TYPE=:type AND (A.DICT_NAME=:dictName OR A.DICT_ENAME=:dictName)  ORDER BY A.ID";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("type", type);
    	m.put("dictName", dictName);
    	searchBean.setFilterMap(m);
    	List ls=this.getListResultBySql(SystemDict.class, sql, searchBean);
    	return ls!=null && ls.size()>0?true:false;
    	
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchSystemDict(String searchValue,String type,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM system_dict A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((type!=null) && (!"".equals(type))){
    		sql=sql+" AND (A.TYPE =:type) ";
    		m.put("type", type);
    	}
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    		sql=sql+" AND ((A.DICT_CODE LIKE :searchValue) OR (A.DICT_NAME LIKE :searchValue) OR " +
    			" (A.DICT_ENAME LIKE :searchValue) OR (A.TYPE_NAME LIKE :searchValue))";
    		m.put("searchValue", "%"+searchValue+"%");
    	}    	
    	sql=sql+" ORDER BY ID,DICT_CODE";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(SystemDict.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     *  根据名称找到代码
     * @param dictName
     * @return
     * @throws Exception
     */
    public  String getDictCode(String dictName,String dicType) throws Exception{
    	if (dictName==null) return "";
    	String sql="SELECT A.DICT_CODE FROM system_dict A WHERE A.DICT_NAME=:dictName OR A.DICT_ENAME=:dictName";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("dictName", dictName);
    	searchBean.setFilterMap(m);
    	Object o = this.getAttributeValueBySql(sql, searchBean);
    	return (o==null)?"":o.toString();
    }
    /**
     * 获取外键引用值
     * @param type
     * @param dictCode
     * @return
     */
    public String getRefKeyValueBy(String type,String dictCode) throws Exception{
    	String sql="SELECT REF_KEY_VALUE FROM system_dict A WHERE A.TYPE=:type AND A.DICT_CODE=:dictCode ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("type", type);
    	m.put("dictCode", dictCode);
    	searchBean.setFilterMap(m);
    	Object o = this.getAttributeValueBySql(sql, searchBean);
    	return (o==null)?"":o.toString();
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	SystemDict sd = (SystemDict)baseBean;
    	Map<String, Object> m=new HashMap<String, Object>();
    	String sql="SELECT COUNT(*) FROM system_dict A WHERE A.TYPE=:type AND A.DICT_CODE=:dictCode ";
    	m.put("type", sd.getType());
    	m.put("dictCode", sd.getDictCode());
    	if(baseBean.getId()!=null) {
    		sql = sql + " AND A.ID!=:id";
    		m.put("id", baseBean.getId());
    	}	
    	SearchBean searchBean=new SearchBean();
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
 
}
