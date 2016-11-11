package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IPopedomDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.Popedom;

public class PopedomDaoImpl extends BaseDAOImpl implements IPopedomDao {
	/**
     * 获取权限集合
     * @return
     * @throws Exception
     */
    public  PageHelper selectPopedomList(int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_popedom A ORDER BY ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	ph=this.getPageHelperBySql(Popedom.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 获取所有权限集合
     * @return
     * @throws Exception
     */
    public  List selectPopedomList() throws Exception{
    	String sql="SELECT A.* FROM um_popedom A ORDER BY A.POPEDOM_GROUP ASC, A.MODULE_GROUP ASC";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> filterMap=new HashMap<String, Object>(); 
    	searchBean.setFilterMap(filterMap);
    	List lst=this.getListResultBySql(Popedom.class, sql, searchBean);
    	return lst; 
    }
    /**
     * 获取所有权限集合
     * @return
     * @throws Exception
     */
    public  List selectPopedomList(Integer popedomGroup,Integer moduleGroup) throws Exception{
    	String sql="SELECT A.* FROM um_popedom A WHERE A.POPEDOM_GROUP=:popedomGroup AND A.MODULE_GROUP=:moduleGroup ORDER BY A.POPEDOM ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>(); 
    	m.put("popedomGroup", popedomGroup);
    	m.put("moduleGroup", moduleGroup);
    	searchBean.setFilterMap(m);
    	List lst=this.getListResultBySql(Popedom.class, sql, searchBean);
    	return lst; 
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPopedom(String searchValue,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_popedom A WHERE A.POPEDOM_NAME LIKE :popedomName ORDER BY A.ID";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("popedomName", "%"+searchValue+"%");    	
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Popedom.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Popedom pop = (Popedom)baseBean;
    	String sql="SELECT COUNT(*) FROM um_popedom A WHERE A.POPEDOM_NAME LIKE :popedomName ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("popedomName", "%"+pop.getPopedomName()+"%");    	
    	if (pop.getId()!=null){
    		sql=sql+" AND A.ID=:id";
    		m.put("id",pop.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number) this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }
}
