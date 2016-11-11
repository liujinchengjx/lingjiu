package com.liu.qinziyou.dao.baseinfo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.baseinfo.IAreaDictDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.baseinfo.AreaDict;

public class AreaDictDaoImpl extends BaseDAOImpl implements IAreaDictDao {
	/**
     * 获取地区集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectAreaDict(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM area_dict A ORDER BY A.ID ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(AreaDict.class, sql, searchBean, currentPage, pageSize);
    	return ph; 
    }
    /**
     * 根据级别和父级Id读取地区
     * @param level
     * @param parentId
     * @return
     * @throws Exception
     */
     public List selectAreaByPLevel(int level,int parentId) throws Exception{
        String sql="SELECT A.* FROM area_dict A WHERE LEVEL=:level and PARENTID=:parentId ORDER BY A.ID ";
     	SearchBean searchBean=new SearchBean();
     	Map<String, Object> m=new HashMap<String, Object>();
     	m.put("level", level);
     	m.put("parentId", parentId);    	
     	searchBean.setFilterMap(m); 
     	return this.getListResultBySql(AreaDict.class, sql, searchBean);
     }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchAreaDict(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM area_dict A WHERE NAME LIKE :name ORDER BY A.ID ";
    	SearchBean searchBean=new SearchBean();
    	PageHelper ph=new PageHelper();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("text","%"+searchValue+"%");
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(AreaDict.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 获取地区名称
     * @param areaId
     * @return
     * @throws Exception
     */
    public String getAreaName(Integer areaId) throws Exception{
    	if (areaId==null) return "";
    	String sql=" SELECT A.NAME FROM area_dict A WHERE A.ID=:areaId ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("areaId",areaId);
    	searchBean.setFilterMap(m); 
    	String name=(String)this.getAttributeValueBySql(sql, searchBean);
    	return name; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	AreaDict ad = (AreaDict)baseBean;
    	String sql="SELECT COUNT(*) FROM area_dict A WHERE NAME=:name AND LEVEL=:level AND PARENTID=:parentId ORDER BY A.ID ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();    	
    	m.put("name",ad.getName());
    	m.put("level",ad.getLevel());
    	m.put("parentId",ad.getParentId());
    	if(baseBean.getId()!=null) {
    		sql = sql + " AND A.ID!=:id";
    		m.put("id", baseBean.getId());
    	}	
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue()>0?true:false;
    }
}
