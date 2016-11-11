package com.liu.qinziyou.dao.systemmanager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.util.StringUtil;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.dao.systemmanager.IPositionDao;
import com.liu.qinziyou.entity.BaseBean;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.systemmanager.Position;

public class PositionDaoImpl extends BaseDAOImpl implements IPositionDao {
	
	public List selectPosition(String positionCode,Integer positionLevel)  throws Exception{
		String sql="SELECT A.* FROM position A WHERE A.POSITION_CODE LIKE :positionCode AND A.POSITION_CODE<>:positionCodeNot AND A.POSISTION_LEVEL=:positionLevel ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("positionCode",positionCode+"%");
    	m.put("positionCodeNot",positionCode);
    	m.put("positionLevel",positionLevel);
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	List lst=this.getListResultBySql(Position.class, sql, searchBean);
		return lst;
	}
    /**
     * 冻结职位
     * @return
     * @throws Exception
     */
    public boolean froze(int positionId,int flag) throws Exception{
    	String sql=" UPDATE position A SET POSITION_STATUS=:positionStatus WHERE ID=:positionId";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("positionStatus", flag);
    	m.put("positionId", positionId);
    	searchBean.setFilterMap(m);
    	int row=this.executeSQL(sql, searchBean);
    	return (row>=1)?true:false;
    }
    /**
     * 搜索
     * @return
     * @throws Exception
     */
    public  PageHelper searchPosition(String positionName,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM position A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((positionName!=null) && (!"".equals(positionName))){
    	   sql=sql+" AND A.POSISTION_NAME LIKE :positionName ";
    	   m.put("positionName", "%"+positionName+"%");
    	}
    	sql=sql+" ORDER BY A.POSITION_CODE,A.POSISTION_LEVEL ";
    	searchBean.setFilterMap(m); 
    	ph=this.getPageHelperBySql(Position.class,sql, searchBean,currentPage, pageSize);
    	return ph; 
    }
    public String getMaxPositionCode(String parentPositionCode,Integer positionLevel) throws Exception{
    	String sql="SELECT MAX(A.POSITION_CODE) FROM position A WHERE A.POSITION_CODE LIKE :parentPositionCode AND A.POSITION_CODE<>:parentPositionCodeNot AND A.POSISTION_LEVEL=:positionLevel ";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("parentPositionCode",parentPositionCode+"%");
    	m.put("parentPositionCodeNot",parentPositionCode);
    	m.put("positionLevel",positionLevel);
    	sql=sql+" ORDER BY A.ID ";
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	String code="";
    	if(o==null){
    		code=parentPositionCode+"01";	
    	}else{
    		String temp=o.toString();
    		if (Integer.parseInt(temp.substring(temp.length()-2))>=99){
    			throw new Exception("职位数量已超出99个职位，每一级别下面的直属职位数量职不能超过99个！");
    		}
    		code=StringUtil.incStrForNum(temp);	
    	}
		return code;
	}
    /**
     * 检查新增加的数据是否和库里面有重复的
     * @return
     * @throws Exception
     */
    public  boolean checkForDuplicate(BaseBean baseBean) throws Exception{
    	Position p = (Position)baseBean;
    	String sql="SELECT COUNT(*) FROM position A WHERE A.POSISTION_NAME =:positionName ";    	
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("positionName",p.getPositionName()); 
    	if (p.getId()!=null){
    		sql=sql+" AND A.ID!=:id ";
    		m.put("id",p.getId());
    	}
    	searchBean.setFilterMap(m);
    	Number count =(Number) this.getObjectResultBySql(null,sql, searchBean);
    	return (count.intValue()>0)?true:false;
    }

   	@Override
	public Integer deletePosition(String positionCode) throws Exception {
		String sql = " DELETE FROM customer_position WHERE POSITION_CODE = :positionCode";
		SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("positionCode", positionCode);
    	searchBean.setFilterMap(m);
    	this.executeSQL(sql, searchBean);
    	sql = "UPDATE um_user A SET A.POSITION_CODE = null WHERE A.POSITION_CODE = :positionCode";
    	this.executeSQL(sql, searchBean);
    	sql = "DELETE FROM position WHERE POSITION_CODE = :positionCode";
    	return this.executeSQL(sql, searchBean);
	}
}
