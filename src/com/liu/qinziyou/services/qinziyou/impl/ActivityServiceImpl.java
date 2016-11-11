package com.liu.qinziyou.services.qinziyou.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.upload.FileUploadHelper;
import com.liu.qinziyou.common.util.CommonUtil;
import com.liu.qinziyou.dao.qinziyou.IActivityImgDao;
import com.liu.qinziyou.dao.qinziyou.IActivityRegistratorDao;
import com.liu.qinziyou.dao.qinziyou.IActivityTopicDao;
import com.liu.qinziyou.entity.qinziyou.ActivityImg;
import com.liu.qinziyou.entity.qinziyou.ActivityTopic;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.qinziyou.IActivityService;

public class ActivityServiceImpl extends AbstractBaseService implements IActivityService{
	protected static final Log log = LogFactory.getLog(ActivityServiceImpl.class);
	private IActivityRegistratorDao activityRegistratorDao;
	private IActivityTopicDao activityTopicDao;
	private IActivityImgDao activityImgDao;
	
	@Override
	public PageHelper searchActivityTopic(String searchValue, Integer status,
			int currentPage, int pageSize) throws ServiceException {
		try {
			PageHelper ph = this.activityTopicDao.searchActivityTopic(searchValue, 
					status, currentPage, pageSize);
			
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("搜索活动主题列表分页信息时发生异常====" + e.getMessage());
			throw new ServiceException("搜索活动主题分页信息时发生异常");
		}
	}
	
	
	@Override
	public PageHelper searchActivityImg(Integer topicId, int currentPage,
			int pageSize) throws ServiceException {
		try {
			
			PageHelper ph = this.activityImgDao.searchActivityImg(topicId, currentPage, pageSize);
			
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("搜索活动图片列表分页信息时发生异常====" + e.getMessage());
			throw new ServiceException("搜索活动图片分页信息时发生异常");
		}
	}


	@Override
	public PageHelper searchActivityRegistrator(Integer topicId,
			int currentPage, int pageSize) throws ServiceException {
		try {
			
			PageHelper ph = this.activityRegistratorDao.searchActivityRegistrator(topicId, currentPage, pageSize);
			
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("搜索活动报名列表分页信息时发生异常====" + e.getMessage());
			throw new ServiceException("搜索活动报名分页信息时发生异常");
		}
	}
	
	@Override
	public String uploadActivityFile(byte[] file,String fileName) throws ServiceException {
		String picPath;
		try {
			picPath = FileUploadHelper.getInstance().uploadFile(
					file,
					CommonUtil.getExtensionName(fileName).replace(
							".", ""), FileUploadHelper.FileType.ACTIVITY_FILE);
		} catch (Exception e) {
			log.error("上传活动文件发生异常", e);
			throw new ServiceException("上传活动文件发生异常");
		}
		return picPath;
	}
	
	@Override
	public String uploadActivityCoverImg(byte[] file,String fileName,Integer activityId) throws ServiceException {
		String picPath;
		try {
			picPath = FileUploadHelper.getInstance().uploadActivityPicFile(
					file,
					CommonUtil.getExtensionName(fileName).replace(
							".", ""),activityId.toString(),"1");
			//更新图片路径到数据表里
			ActivityTopic activityTopic = (ActivityTopic) this.getBeanById(ActivityTopic.class,activityId);
			activityTopic.setMainImgUrl(picPath);
			this.activityTopicDao.save(activityTopic);
			
		} catch (Exception e) {
			log.error("上传活动文件发生异常", e);
			throw new ServiceException("上传活动文件发生异常");
		}
		return picPath;
	}
	
	@Override
	public String uploadActivityImg(byte[] file,String fileName,Integer activityId) throws ServiceException {
		String picPath;
		try {
			picPath = FileUploadHelper.getInstance().uploadActivityPicFile(
					file,
					CommonUtil.getExtensionName(fileName).replace(
							".", ""),activityId.toString(),"2");
			ActivityImg activityImg = new ActivityImg();
			activityImg.setTopicId(activityId);
			activityImg.setImgPath(picPath);
			activityImg.setPraiseCnt(0);
			activityImgDao.save(activityImg);
		} catch (Exception e) {
			log.error("上传活动文件发生异常", e);
			throw new ServiceException("上传活动文件发生异常");
		}
		return picPath;
	}
	
	

	@Override
	public void changeActivityStatus(Integer status, Integer activityId)
			throws ServiceException {
		try {
			ActivityTopic topic = (ActivityTopic)activityTopicDao.findById(ActivityTopic.class, activityId);
			if(status.intValue()==20 && (topic.getActivityStatus().intValue()!=10 && topic.getActivityStatus().intValue()!=30)){
				throw new ServiceException("必须是在暂存状态或下架状态才能发布");
			}
			if(status.intValue()==30 && topic.getActivityStatus().intValue()!=20){
				throw new ServiceException("必须是在已发布状态下才能下架");
			}
			activityTopicDao.changeActivityStatus(status, activityId);
		} catch (ServiceException e) {
			throw e;
		}catch (Exception e) {
			log.error("更改活动状态时异常", e);
			throw new ServiceException("更改活动状态时异常");
		}
	}


	public IActivityRegistratorDao getActivityRegistratorDao() {
		return activityRegistratorDao;
	}
	public void setActivityRegistratorDao(
			IActivityRegistratorDao activityRegistratorDao) {
		this.activityRegistratorDao = activityRegistratorDao;
	}
	public IActivityTopicDao getActivityTopicDao() {
		return activityTopicDao;
	}
	public void setActivityTopicDao(IActivityTopicDao activityTopicDao) {
		this.activityTopicDao = activityTopicDao;
	}
	public IActivityImgDao getActivityImgDao() {
		return activityImgDao;
	}
	public void setActivityImgDao(IActivityImgDao activityImgDao) {
		this.activityImgDao = activityImgDao;
	}
	
	
	
}
