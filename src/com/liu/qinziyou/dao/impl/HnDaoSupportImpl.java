package com.liu.qinziyou.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IDaoSupport;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;

public class HnDaoSupportImpl extends HibernateDaoSupport implements IDaoSupport {

	public void delete(IBean baseBean) {
		getHibernateTemplate().delete(baseBean);
	}

	public IBean findById(Class claz, Integer id) {
		IBean baseBean=(IBean)getHibernateTemplate().get(claz, id);
		return baseBean;//getHibernateTemplate().get(claz, id);
	}

	public Integer save(IBean baseBean) {
		getHibernateTemplate().save(baseBean);	
		return baseBean.getId();
	}

	public void update(IBean baseBean) {
		getHibernateTemplate().merge(baseBean);
	}
	/**
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities   
     * @author 
     */
    public void saveOrUpdateCollection(Collection entities){        
        getHibernateTemplate().saveOrUpdateAll(entities);
    }
    /**
     * 保存（插入或更新）单个对象
     *
     * @param IBean baseBean  
     */
    public Integer saveOrUpdateObject(IBean baseBean) {
        getHibernateTemplate().saveOrUpdate(baseBean);
        return baseBean.getId();
    }
	public List find(IBean baseBean, String propertyName, Object value){
		String queryString = "from " + baseBean.getClass().getName()
		+ " as model where model." + propertyName + "= ?";
		return getHibernateTemplate().find(queryString, value);
	}
	
	/*public PageHelper getPageHelper(String hql, SearchBean searchBean,int currentPage, int pageSize) {			
		PageHelper ph = new PageHelper();
		ph.setStart(currentPage);
		ph.setPageSize(pageSize);
		int totalRecords = getTotalRecords(hql, searchBean);
		ph.setTotal(totalRecords);
		List list = findByPage(hql, searchBean, ph.getOffSet(), ph.getPageSize());
		ph.setRows(list);
		return ph;
	}*/
	
	public PageHelper getPageHelperBySql(Class entityClaz,String sql, SearchBean searchBean,int currentPage, int pageSize) {			
		PageHelper ph = new PageHelper();
		ph.setStart(currentPage);
		ph.setPageSize(pageSize);
		int totalRecords = getTotalRecordsBySql(sql, searchBean);
		ph.setTotal(totalRecords);
		//findByPageBySql(entityClaz,sql, searchBean, ph.getOffSet(), ph.getPageSize());
		List list =findByPageBySql(entityClaz,sql, searchBean, ph.getStart(), ph.getPageSize());
		ph.setRows(list);
		return ph;
	}
	
	private int getTotalRecordsBySql(final String sql, final SearchBean searchBean){
		String querySql =  sql;
		int length = querySql.indexOf("ORDER BY") > 0 ? querySql.indexOf("ORDER BY") : querySql.length();
		StringBuilder stringBuilder = new StringBuilder("SELECT COUNT(*) FROM ( ");
		/*stringBuilder.append(querySql.substring(querySql.indexOf("SELECT") + 6,querySql.indexOf("FROM")));
		stringBuilder.append(" ) ");*/
		stringBuilder.append(querySql.substring(0, length));
		stringBuilder.append(" ) total ");
		SQLQuery query = getSession().createSQLQuery(stringBuilder.toString());
		for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
			if(entry.getValue().getClass().isArray()) {
				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
			}else{
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		Object o= query.uniqueResult();
		return (o==null)?0:((Number)o).intValue();
	}
	
	private List<IBean> findByPageBySql(Class claz,final String sql, final SearchBean searchBean,final int start, final int pageSize) {		
		String tsql = sql + " limit "+start+ ", "+ pageSize;//分页语句 //(offset+pageSize);//分页语句
		return this.getListResultBySql(claz, tsql, searchBean);
	}

	/**
	 * 根据HQL,条件值数组，开始位置，页面大小 来获得查询结果List
	 * @param hql
	 * @param values
	 * @param offset
	 * @param pageSize
	 * @return List 
	 */
	/*private List<IBean> findByPage(final String hql, final SearchBean searchBean,final int offset, final int pageSize) {			
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
					if(entry.getValue().getClass().isArray()) {
						query.setParameterList(entry.getKey(),(Object[])entry.getValue());
					}else{
						query.setParameter(entry.getKey(), entry.getValue());
					}
				}
				List result = query.setFirstResult(offset).setMaxResults(
						pageSize).list();
				return result;
			}
		});
		return list;
	}
*/	
	/**
	 * 根据HQL,条件值数组 获取记录总数
	 * @param hql
	 * @param values
	 * @return
	 */
	/*private int getTotalRecords(final String hql, final SearchBean searchBean){
		String queryHql =  hql;
		int length = queryHql.indexOf("ORDER BY") > 0 ? queryHql.indexOf("ORDER BY") : queryHql.length();
		StringBuilder stringBuilder = new StringBuilder("SELECT COUNT( ");
		stringBuilder.append(queryHql.substring(queryHql.indexOf("SELECT") + 6,queryHql.indexOf("FROM")));
		stringBuilder.append(" ) ");
		stringBuilder.append(queryHql.substring(queryHql.indexOf("FROM"), length));
		Query query = getSession().createQuery(stringBuilder.toString());
		for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
			if(entry.getValue().getClass().isArray()) {
				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
			}else{
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return ((Number) query.uniqueResult()).intValue();
	}*/
	/**
     * 通过特定的HQL语句获取相应的数据集
     *
     * @param String hql HQL语句
     * @return List 数据集
     */
    /*public List selectDataByHQL(String hql,SearchBean searchBean) {    	
    	 Session session = getSession(); //取得session
         Query query = session.createQuery(hql);         
         for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
        	 if(entry.getValue().getClass().isArray()) {
 				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
 			}else{
 				query.setParameter(entry.getKey(), entry.getValue());
 			}
  		 }
         List list = query.list();
         //releaseSession(session); //关闭    	
         return list;// getHibernateTemplate().find(hql);
    }
    *//**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集
     *//*
    public List selectDataBySQL(Class entity,SearchBean searchBean, String sql){
    	 Session session = getSession(); //取得session    
         SQLQuery query = session.createSQLQuery(sql);
         query.addEntity(entity);
         for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
 			query.setParameter(entry.getKey(), entry.getValue());
 		 }
         List list = query.list();
         // releaseSession(session); //关闭session
         return list;
    }*/
    
    /**
     * 根据HQL语句取某个对象
     * @param String hql
     * @return Object
     * @throws Exception   
     */
    /*public Object getObject(String hql,SearchBean searchBean){        
        Object object = null;
        Session session = getSession(); //取得session
        Query query = session.createQuery(hql);
        for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
 			query.setParameter(entry.getKey(), entry.getValue());
 		 }
        object = query.uniqueResult();
        //releaseSession(session); //关闭session
        return object;
    }*/
    
   /* public Object getObjectBySql(Class claz,String sql,SearchBean searchBean){        
    	return this.getObjetcResultBySql(claz, sql, searchBean);
    }*/
    
   
    /**
     * 执行hql语句，并返回被影响到的行数
     * @param hql
     * @return
     */
    /*public int executeHQL(String hql,SearchBean searchBean)   {  
	    Session session = getSession();
	    Query query = session.createQuery(hql);
	    for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
	    	if(entry.getValue().getClass().isArray()) {
 				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
 			}else{
 				query.setParameter(entry.getKey(), entry.getValue());
 			}
 		 }
	    int effect = query.executeUpdate();
	    //releaseSession(session);
	    return effect;
	  }*/
    /**
     * 执行sql语句，并返回被影响到的行数 
     * @param sql
     * @return
     */
	public int executeSQL(String sql,SearchBean searchBean) {
	    Session session = getSession();
	    Query query = session.createSQLQuery(sql);
	    for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
	    	if(entry.getValue().getClass().isArray()) {
				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
			}else{
				query.setParameter(entry.getKey(), entry.getValue());
			}
 		 }
	    int effect = query.executeUpdate();
	    return effect;
	  }
	 /**
     * 根据HQL语句取某实体的某属性值
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author 
     */
    /*public Object getAttributeValueByHql(String hql,SearchBean searchBean) throws Exception {
        Object object = null;
        Session session = getSession(); //取得session
        Query query = session.createQuery(hql);
        for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
 			query.setParameter(entry.getKey(), entry.getValue());
 		}
        List list = query.list();
        if (list.size() > 0) {
            object = (Object) list.get(0);
        }
        //releaseSession(session); //关闭session
        return object;
    }*/
	/**
     * 根据SQL语句取某实体的某属性值
     *
     * @param String sql
     * @return Object
     * @throws Exception   
     */
    public Object getAttributeValueBySql(String sql,SearchBean searchBean){
    	  List list =  getListResultBySql(null, sql, searchBean);
    	  return (list!=null && list.size()>0)?list.get(0):null;
    }
    /**
     * 根据HQL语句取某个对象数组
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
    /*public Object[] getArrayObjectByHql(String hql,SearchBean searchBean){        
        Object[] arrayObject = null;
        Session session = getSession(); //取得session
        Query query = session.createQuery(hql);
        for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
        	if(entry.getValue().getClass().isArray()) {
				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
			}else{
				query.setParameter(entry.getKey(), entry.getValue());
			}
 		 }        
        List list = query.list(); 
        int len=list.size();
        if (len > 0) {
        	for (int i=0;i<len;i++){   
        		if (list.get(i).getClass().isArray()){
        			arrayObject = (Object[]) list.get(i);
        		}
        	}
        }       
        return arrayObject;
    }*/
    
    public List getListResultBySql(Class claz,String sql,SearchBean searchBean){
    	 Session session = getSession(); //取得session        
         SQLQuery query = session.createSQLQuery(sql); 
         if(claz!=null){
        	 query.addEntity(claz);
        	// query.setResultTransformer(Transformers.aliasToBean(claz));
         }
         for(Entry<String,Object> entry:searchBean.getFilterMap().entrySet()){
         	if(entry.getValue().getClass().isArray()) {
 				query.setParameterList(entry.getKey(),(Object[])entry.getValue());
 			}else{
 				query.setParameter(entry.getKey(), entry.getValue());
 			}
  		 }     
         return  query.list();
      }
    
	public Object getObjectResultBySql(Class claz, String sql,
			SearchBean searchBean) {
		Session session = getSession(); // 取得session
		SQLQuery query = session.createSQLQuery(sql);
		if (claz != null) {
			query.addEntity(claz);
		}
		for (Entry<String, Object> entry : searchBean.getFilterMap().entrySet()) {
			if (entry.getValue().getClass().isArray()) {
				query.setParameterList(entry.getKey(), (Object[]) entry.getValue());
			} else {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.uniqueResult();
	}
	
    /**
     * 根据SQL语句取对象数组,一行里的多列
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
    public Object[] getArrayObjectBySql(String sql,SearchBean searchBean){        
    	Object[] arrayObject = null;
        List list =  getListResultBySql(null, sql, searchBean);
        if(list!=null && list.size()>0){
        	arrayObject = (Object[]) list.get(0);
        }
        return arrayObject;
    }
    
    
    
}
