package com.liu.qinziyou.dao;

import java.util.Collection;
import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;

public interface IDaoSupport {
	/**
	 * 
	 * @param baseBean
	 */
	public Integer save(IBean baseBean);
	
	/**
	 * 
	 * @param baseBean
	 */
	public void delete(IBean baseBean);
	
	/**
	 * 
	 * @param claz
	 * @param id
	 * @return
	 */
	public IBean findById(Class claz, java.lang.Integer id);
	
	/**
	 * 
	 * @param baseBean
	 */
	public void update(IBean baseBean);
	
	/**
	 * 
	 * @param hql
	 * @param searchBean
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	//public PageHelper getPageHelper(String hql,SearchBean searchBean, int currentPage, int pageSize);	
	
	/**
	 * 
	 * @param baseBean
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List find(IBean baseBean, String propertyName, Object value);
	
	/**
     * 通过特定的HQL语句获取相应的数据集
     *
     * @param String hql HQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    //public List selectDataByHQL(String hql,SearchBean searchBean);
       
    /**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    //public List selectDataBySQL(Class entity,SearchBean searchBean, String sql);
    
    /**
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities   
     * @author 
     */
    public void saveOrUpdateCollection(Collection entities);
    
    /**
     * 保存（插入或更新）单个对象
     * @param Object entity    
     */
    public Integer saveOrUpdateObject(IBean baseBean);
    
    /**
     * 根据HQL语句取某个对象
     * @param String hql
     * @return Object
     * @throws Exception   
     */
    //public Object getObject(String hql,SearchBean searchBean);
   
    /**
     * 执行hql语句，并返回被影响到的行数
     * @param hql
     * @return
     */
    //public int executeHQL(String hql,SearchBean searchBean)  ;
    
    /**
     * 执行sql语句，并返回被影响到的行数 
     * @param sql
     * @return
     */
	public int executeSQL(String sql,SearchBean searchBean);
	/**
     * 根据SQL语句取某实体的某属性值
     *
     * @param String sql
     * @return Object
     * @throws Exception   
     */
    public Object getAttributeValueBySql(String sql,SearchBean searchBean);
    /**
     * 根据HQL语句取某实体的某属性值
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author 
     */
    //public Object getAttributeValueByHql(String hql,SearchBean searchBean) throws Exception;
    /**
     * 根据HQL语句取某个对象数组
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
    //public Object[] getArrayObjectByHql(String hql,SearchBean searchBean);
    
    /**
     * 根据SQL语句取某个对象数组
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
    public Object[] getArrayObjectBySql(String sql,SearchBean searchBean);
    
    /**
     * 根据SQL预计
     * @param entityClaz  要返回的实体类class
     * @param sql
     * @param searchBean
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageHelper getPageHelperBySql(Class entityClaz,String sql, SearchBean searchBean,int currentPage, int pageSize);
    
    /**
     * 
     * @param entityClaz
     * @param sql
     * @param searchBean
     * @return
     */
    public List getListResultBySql(Class entityClaz,String sql,SearchBean searchBean);
    
    /**
     * 
     * @param entityClaz
     * @param sql
     * @param searchBean
     * @return
     */
    public Object getObjectResultBySql(Class entityClaz, String sql,SearchBean searchBean);
}
