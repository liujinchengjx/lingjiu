package com.liu.qinziyou.dao.attachment;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.IBaseDAO;

public interface IUpLoadFileDao extends IBaseDAO{

	/**
     * 获取附件信息集合
     * @return
     * @throws Exception
     */
    public  PageHelper searchUpLoadFile(String relationid, String fileType,Integer fileId,int currentPage, int pageSize) throws Exception;
    
    public  Integer getUpLoadFileCount(Integer reNo,String relationid, String fileType) throws Exception;
    /**
     * 获取附件存放路径
     * @return
     * @throws Exception
     */
    public   Object getAttachment(String fileType,String relationId) throws Exception;
    /**
     * 获取附件存放路径
     * @return
     * @throws Exception
     */
    public   String getAttachmentFilePath(String fileType,String relationId) throws Exception;
    public  Integer deleteAttachmentFile(String fileType,Integer relationId) throws Exception;
    public  Integer deleteAttachmentFile(Integer fileId) throws Exception;
    
    
}
