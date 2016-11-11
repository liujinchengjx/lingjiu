package com.liu.qinziyou.services.qinziyou;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.IBaseService;


public interface IActivityService  extends IBaseService{
	 public  PageHelper searchActivityTopic(String searchValue ,Integer status,int currentPage, int pageSize) throws ServiceException;
	 
	 public  PageHelper searchActivityImg(Integer topicId,int currentPage, int pageSize) throws ServiceException;
	 
	 public  PageHelper searchActivityRegistrator(Integer topicId,int currentPage, int pageSize) throws ServiceException;
	 
	 public String uploadActivityFile(byte[] file,String fileName) throws ServiceException ;
	 
	 /**
	  * 上传封面图
	  * @param file
	  * @param fileName
	  * @param activityId
	  * @return
	  * @throws ServiceException
	  */
	 public String uploadActivityCoverImg(byte[] file,String fileName,Integer activityId) throws ServiceException;
	 
	 /**
	  * 上传活动图
	  * @param file
	  * @param fileName
	  * @param activityId
	  * @return
	  * @throws ServiceException
	  */
	 public String uploadActivityImg(byte[] file,String fileName,Integer activityId) throws ServiceException ;
	 
	 /**
	  * 变更状态
	  * @param status
	  * @param activityId
	  * @return
	  * @throws ServiceException
	  */
	 public void changeActivityStatus(Integer status,Integer activityId) throws ServiceException ;
}
