package com.liu.qinziyou.dao;

import java.util.Collection;
import java.util.List;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;

public interface IBaseDAO {
	
	/**
	 * 
	 * @param baseBean
	 */
	public Integer save(IBean baseBean);
	
	/**
	 * 
	 * @param baseBean
	 */
	public void delete(IBean baseBean) ;
	
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
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities   
     * @author 
     */
    public void saveOrUpdateCollection(Collection<IBean> entities);
	
    /**
     * 保存（插入或更新）单个对象
     *
     * @param Object entity   
     */
    public Integer saveOrUpdateObject(IBean baseBean);
        
	/**
	 * 
	 * @param baseBean
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(IBean baseBean,String propertyName, Object value) ;
	
	/**
	 * 
	 * @param po
	 * @return
	 */
	
	//public PageHelper getPageHelper(String hql,SearchBean searchBean, int currentPage, int pageSize);
	
	
	//public List selectDataByHQL(String hql,SearchBean searchBean);
	
	 /**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集    
     */
	public List getListResultBySql(Class entity, String sql,SearchBean searchBean);
    
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
    public Object getAttributeValueBySql(String sql,SearchBean searchBean) throws Exception;
    /**
     * 根据HQL语句取某个对象数组
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
   // public Object[] getArrayObjectByHql(String hql,SearchBean searchBean);
    /**
     * 根据SQL语句取某个对象数组
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
    public Object[] getArrayObjectBySql(String sql,SearchBean searchBean);
    
    public PageHelper getPageHelperBySql(Class entityClaz,String sql, SearchBean searchBean,int currentPage, int pageSize);
}
