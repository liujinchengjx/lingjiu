package com.liu.qinziyou.dao.attachment.impl;

import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.attachment.IUpLoadFileDao;
import com.liu.qinziyou.dao.impl.BaseDAOImpl;
import com.liu.qinziyou.entity.SearchBean;
import com.liu.qinziyou.entity.attachment.UpLoadFile;

public class UpLoadFileDaoImpl extends BaseDAOImpl implements IUpLoadFileDao {
	/**
     * 获取附件信息集合
     * @return
     * @throws Exception
     */
    public  PageHelper searchUpLoadFile(String relationid, String fileType,Integer fileId,int currentPage, int pageSize) throws Exception{
    	String sql="SELECT A.* FROM uploadfile A WHERE 1=1 ";
    	PageHelper ph=new PageHelper();
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	if ((relationid!=null) && (!"".equals(relationid))){
     	   sql=sql+" AND A.RELATIONID =:relationid";
     	   m.put("relationid", relationid);    	
     	}
    	if ((fileType!=null) && (!"".equals(fileType))){
      	   sql=sql+" AND A.FILETYPE =:fileType";
      	   m.put("fileType", fileType);    	
      	}
    	searchBean.setFilterMap(m);
    	ph=this.getPageHelperBySql(UpLoadFile.class, sql, searchBean, currentPage, pageSize);
    	return ph; 
    }
    public  Integer getUpLoadFileCount(Integer reNo,String relationid, String fileType) throws Exception{
    	String sql="";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	sql="SELECT COUNT(*) FROM uploadfile A WHERE  A.RELATIONID =:relationid AND A.FILETYPE =:fileType ";    	
    	m.put("relationid", relationid);
        m.put("fileType", fileType);
    	searchBean.setFilterMap(m);
    	Number count = (Number)this.getObjectResultBySql(null, sql, searchBean);
    	return count.intValue();
    }
    /**
    
    /**
     * 获取附件存放路径
     * @return
     * @throws Exception
     */
    public  Object getAttachment(String fileType,String relationId) throws Exception{
    	String sql="SELECT A.* FROM uploadfile A  WHERE A.FILETYPE=:fileType AND A.RELATIONID=:relationId";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("fileType", fileType);
    	m.put("relationId", relationId);
    	searchBean.setFilterMap(m); 
    	Object o=this.getObjectResultBySql(UpLoadFile.class, sql, searchBean);
    	return o;
    }
    /**
     * 获取附件存放路径
     * @return
     * @throws Exception
     */
    public   String getAttachmentFilePath(String fileType,String relationId) throws Exception{
    	String sql="SELECT DISTINCT A.FILEPATH FROM uploadfile A WHERE A.FILETYPE=:fileType " +
    			" AND A.RELATIONID=:relationId ORDER BY A.ID DESC LIMIT 0,1";
    	SearchBean searchBean=new SearchBean();
    	Map<String, Object> m=new HashMap<String, Object>();
    	m.put("fileType", fileType);
    	m.put("relationId", relationId);
    	searchBean.setFilterMap(m); 
    	Object o=this.getAttributeValueBySql(sql, searchBean);
    	return (o==null)?"": o.toString();
    }
    public  Integer deleteAttachmentFile(String fileType,Integer relationId) throws Exception{
    	String sql="DELETE FROM uploadfile WHERE FILETYPE=:fileType AND RELATIONID=:relationId ";
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("fileType", fileType);
		m.put("relationId", relationId);
		searchBean.setFilterMap(m); 
		int row =this.executeSQL(sql, searchBean);
		return row;
    }
    public Integer deleteAttachmentFile(Integer fileId) throws Exception{
    	String sql="DELETE FROM uploadfile WHERE ID=:fileId ";
		SearchBean searchBean=new SearchBean();
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("fileId", fileId);
		searchBean.setFilterMap(m); 
		int row =this.executeSQL(sql, searchBean);
		return row;
    }
}
