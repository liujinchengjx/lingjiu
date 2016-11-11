package com.liu.qinziyou.dao.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;

import com.liu.qinziyou.common.LoginUserUtil;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;
import com.liu.qinziyou.dao.IDaoSupport;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.IBean;
import com.liu.qinziyou.entity.SearchBean;



public  class BaseDAOImpl  implements IBaseDAO {
	private IDaoSupport daoSupport;
	protected static final Log log = LogFactory.getLog(BaseDAOImpl.class);

	public Integer save(IBean baseBean) {
		log.debug("saving " + baseBean.getClass().getName() + " instance");
		Integer id=0;
		try {
			if(baseBean  instanceof BaseBean){
				String createBy = LoginUserUtil.getLoginUser()==null?null:LoginUserUtil.getLoginUser().getRealName();
				System.out.println("createBy in save is:"+createBy);
				((BaseBean)baseBean).setCreateBy(createBy);
				((BaseBean)baseBean).setCreateTime(new Timestamp(System.currentTimeMillis()));
			}
			id=daoSupport.save(baseBean);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			re.printStackTrace();
			throw re;
		}
		return id;
	}

	public void delete(IBean baseBean) {
		log.debug("deleting " + baseBean.getClass().getName() + " instance");
		try {
			daoSupport.delete(baseBean);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IBean findById(Class claz, java.lang.Integer id) {
		log.debug("getting " + claz.getName() + " instance with id: "+ id);
		try {
			IBean instance = daoSupport.findById(claz, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void update(IBean baseBean) {
		log.debug("update " + baseBean.getClass().getName() + " instance");
		try {
			if(baseBean  instanceof BaseBean){
				String createBy = LoginUserUtil.getLoginUser()==null?null:LoginUserUtil.getLoginUser().getRealName();
				System.out.println("createBy in update is:"+createBy);
				((BaseBean)baseBean).setLastModifyBy(createBy);
				((BaseBean)baseBean).setLastModifyTime(new Timestamp(System.currentTimeMillis()));
			}
			daoSupport.update(baseBean);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}

	}
	/**
     * 保存（插入或更新）多个对象
     *
     * @param Collection entities   
     * @author 
     */
    public void saveOrUpdateCollection(Collection<IBean> entities){ 
    	log.debug("update " + entities.getClass().getName() + " instance");
    	try {
    		for(Iterator<IBean> it = entities.iterator();it.hasNext();){
    			IBean baseBean = it.next();
    			if(baseBean  instanceof BaseBean){
    				String createBy = LoginUserUtil.getLoginUser()==null?null:LoginUserUtil.getLoginUser().getRealName();
    				System.out.println("createBy in saveOrUpdateCollection is:"+createBy);
    				if(baseBean.getId()==null){
    					((BaseBean)baseBean).setCreateBy(createBy);
    					((BaseBean)baseBean).setCreateTime(new Timestamp(System.currentTimeMillis()));
    				}else{
    					((BaseBean)baseBean).setLastModifyBy(createBy);
    					((BaseBean)baseBean).setLastModifyTime(new Timestamp(System.currentTimeMillis()));    					
    				}
    			}
    		}
    		daoSupport.saveOrUpdateCollection(entities);
    	} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    }
    /**
     * 保存（插入或更新）单个对象
     *
     * @param Object entity   
     */
    public Integer saveOrUpdateObject(IBean baseBean){
    	log.debug("update " + baseBean.getClass().getName() + " instance");
    	Integer id=0;
    	try {
    		if(baseBean  instanceof BaseBean){
				String createBy = LoginUserUtil.getLoginUser()==null?null:LoginUserUtil.getLoginUser().getRealName();
				System.out.println("createBy in saveOrUpdateObject is:"+createBy);
				if(baseBean.getId()==null){
					((BaseBean)baseBean).setCreateBy(createBy);
					((BaseBean)baseBean).setCreateTime(new Timestamp(System.currentTimeMillis()));
					
				}else{
					((BaseBean)baseBean).setLastModifyBy(createBy);
					((BaseBean)baseBean).setLastModifyTime(new Timestamp(System.currentTimeMillis()));
				}
			}
    		id=daoSupport.saveOrUpdateObject(baseBean);
    	} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
    	return id;
    }
	public List findByProperty(IBean baseBean, String propertyName, Object value) {
		log.debug("finding " + baseBean.getClass().getName()
				+ " instance with property: " + propertyName + ", value: "
				+ value);
		try {
			return daoSupport.find(baseBean, propertyName, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	
/*	public PageHelper getPageHelper(String hql,SearchBean searchBean, int currentPage, int pageSize) {
		return daoSupport.getPageHelper(hql, searchBean, currentPage, pageSize);
	}*/
	
	
	public IDaoSupport getDaoSupport() {
		return daoSupport;
	}

	public void setDaoSupport(IDaoSupport daoSupport) {
		this.daoSupport = daoSupport;
	}
	
	
    /*public List selectDataByHQL(String hql,SearchBean searchBean) {
          return daoSupport.selectDataByHQL(hql,searchBean);
    }*/
    /**
     * 通过特定的SQL语句获取相应的数据集
     *
     * @param String sql SQL语句
     * @return List 数据集
     * @throws DataAccessException
     * @author Long XiangYong 2008-09-16
     */
    public List getListResultBySql(Class entity, String sql,SearchBean searchBean){
    	return daoSupport.getListResultBySql(entity, sql, searchBean);
         
    }
    
    /**
     * 根据HQL语句取某个对象
     * @param String hql
     * @return Object
     * @throws Exception   
     */
    /*public Object getObject(String hql,SearchBean searchBean){
    	return daoSupport.getObject(hql,searchBean);
    }
*/    
    public Object getObjectResultBySql(Class entityClaz,String sql,SearchBean searchBean){
    	return daoSupport.getObjectResultBySql(entityClaz, sql, searchBean);
    }
    /**
     * 执行hql语句，并返回被影响到的行数
     * @param hql
     * @return
     */
    /*public int executeHQL(String hql,SearchBean searchBean) {
    	return daoSupport.executeHQL(hql,searchBean);
    }*/
    
    /**
     * 执行sql语句，并返回被影响到的行数 
     * @param sql
     * @return
     */
	public int executeSQL(String sql,SearchBean searchBean){
		return daoSupport.executeSQL(sql,searchBean);
	}
	/**
     * 根据SQL语句取某实体的某属性值
     *
     * @param String sql
     * @return Object
     * @throws Exception   
     */
    public Object getAttributeValueBySql(String sql,SearchBean searchBean){
    	return daoSupport.getAttributeValueBySql(sql, searchBean);
    }
    /**
     * 根据HQL语句取某实体的某属性值
     *
     * @param String hql
     * @return Object
     * @throws Exception
     * @author 
     */
    /*public Object getAttributeValueByHql(String hql,SearchBean searchBean) throws Exception{
    	return daoSupport.getAttributeValueByHql(hql, searchBean);
    }*/
    /**
     * 根据HQL语句取某个对象数组
     * @param String hql
     * @return Object[]
     * @throws Exception   
     */
    /*public Object[] getArrayObjectByHql(String hql,SearchBean searchBean){
    	return daoSupport.getArrayObjectByHql(hql, searchBean);
    }*/
    /**
     * 根据SQL语句取某个对象数组
     * @param String sql
     * @return Object[]
     * @throws Exception   
     */
    public Object[] getArrayObjectBySql(String sql,SearchBean searchBean){
    	return daoSupport.getArrayObjectBySql(sql, searchBean);
    }
    
 

	public PageHelper getPageHelperBySql(Class entityClaz, String sql,
			SearchBean searchBean, int currentPage, int pageSize) {
		return daoSupport.getPageHelperBySql(entityClaz, sql, searchBean, currentPage, pageSize);
	}
}
