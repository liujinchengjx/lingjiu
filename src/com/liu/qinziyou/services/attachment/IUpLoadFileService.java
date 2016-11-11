package com.liu.qinziyou.services.attachment;


import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;

public interface IUpLoadFileService extends IBaseService{

	 /**
     * 获取附件信息集合
     * @return
     * @throws Exception
     */
    public  PageHelper searchUpLoadFile(String relationId,String fileType,
    		Integer fileId,int currentPage, int pageSize) throws ServiceException;
    
    public  void deleteUpLoadFile(Integer fileId) throws ServiceException;
    
}
