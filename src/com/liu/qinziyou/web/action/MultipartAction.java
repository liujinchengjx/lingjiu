package com.liu.qinziyou.web.action;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
/**
 * multipartAction 所有上传文件和图片的类必须继承此类
 * @author Administrator
 *
 */
public class MultipartAction extends BaseAction{
	List<FileItem> fileItems;

	public List<FileItem> getFileItems() {
		return fileItems;
	}

	public void setFileItems(List<FileItem> fileItems) {
		this.fileItems = fileItems;
	}
	
}
