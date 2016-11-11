package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IDepartmentDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.Department;

public class DepartmentDaoImpl extends BaseDAOImpl implements IDepartmentDao {
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchDepartment(String searchValue,Integer opComanyId,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM um_department A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if (opComanyId!=null && opComanyId.intValue()>0){
    		sql=sql+" AND A.ENTERPRISE_ID=:opComanyId ";
    		m.put("opComanyId", opComanyId);
    	}
    	if ((searchValue!=null) && (!"".equals(searchValue))){
    	   sql=sql+" AND (A.DEPARTMENT_NAME LIKE :searchValue) ";
    	   m.put("searchValue", "%"+searchValue+"%");
    	}
    	sql=sql+" ORDER BY A.ID DESC ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Department.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    /**
     * 获取部门名称
     * @return
     * @throws Exception
     */
    public  String getDepartmentName(Integer departmentId) throws Exception{
    	String sql="SELECT DEPARTMENT_NAME FROM um_department A WHERE A.ID=:departmentId";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("departmentId", departmentId);    	
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return o.toString(); 
    }
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Department dep = (Department)baseBean;
    	String sql="SELECT COUNT(*) FROM um_department A WHERE A.DEPARTMENT_NAME =:departmentName ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("departmentName",dep.getDepartmentName()); 
    	if (dep.getId()!=null){
    		sql=sql+" AND A.ID!=:id";
    		m.put("id",dep.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number) this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
   }
}
