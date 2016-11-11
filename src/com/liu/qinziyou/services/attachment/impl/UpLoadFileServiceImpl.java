package com.liu.qinziyou.services.attachment.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.Configuration;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.dao.attachment.IUpLoadFileDao;
import com.liu.qinziyou.entity.attachment.UpLoadFile;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.AbstractBaseService;
import com.liu.qinziyou.services.attachment.IUpLoadFileService;

public class UpLoadFileServiceImpl extends AbstractBaseService implements IUpLoadFileService {
	 protected static final Log log = LogFactory.getLog(UpLoadFileServiceImpl.class);
	 private IUpLoadFileDao upLoadFileDao;

	 public  PageHelper searchUpLoadFile(String relationId,String fileType,
			 Integer fileId,int currentPage, int pageSize) throws ServiceException{
		try {
			PageHelper ph = this.upLoadFileDao.searchUpLoadFile(relationId,fileType,fileId,currentPage, pageSize);
			List<UpLoadFile> ufList=(List)ph.getRows();
			for (UpLoadFile up : ufList) {
				up.setFileUrl(Configuration.getConfigValue("statics_fileserver_url")+"/"+up.getFilePath());
			}
			return ph;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取上传附件信息列表分页时发生异常====" + e.getMessage());
			throw new ServiceException("获取上传附件信息列表分页时发生异常");
		}
	}
	
	public  void deleteUpLoadFile(Integer fileId) throws ServiceException{
		try{	
			upLoadFileDao.deleteAttachmentFile(fileId);	
		}catch (ServiceException e){
			throw e;
		}catch (Exception e){
			e.printStackTrace();
			log.error("删除附件时发生异常===="+e.getMessage());
			throw new ServiceException("删除附件时失败");
		}
	}
	
	public IUpLoadFileDao getUpLoadFileDao() {
		return upLoadFileDao;
	}

	public void setUpLoadFileDao(IUpLoadFileDao upLoadFileDao) {
		this.upLoadFileDao = upLoadFileDao;
	}

}
