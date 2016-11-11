package com.liu.qinziyou.entity.attachment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

@Entity
@Table(name = "uploadfile")
public class UpLoadFile extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="FILETYPE",nullable=false,length=30)	
	private String fileType;//文件类型 WT 委托单类型 STIN入库单管理这边上 DN发货管理这边的附件 WO 工作单上传的附件 
	
	@Column(name="RELATIONID",nullable=true,length=30)
	private String relationId;//关联id
	
	@Column(name="FILEID",nullable=true,length=50)
	private String fileId;//文件ID	
	
	@Column(name="FILEPATH",nullable=false)
	private String filePath;//类型，0 是常量值，1 选择项
	
	@Column(name="FILENAME",nullable=true)
	private String fileName;//上传文件名
	
	@Column(name="NEWFILENAME",nullable=true)
	private String newFileName;//保存文件名
	
	
	@Column(name="REMARK",nullable=true)
	private String remark;//备注
	
	@Column(name="REMARK1",nullable=true)
	private String remark1;//备注2

	@Column(name="FLAG",nullable=true)
	private Integer flag;////0 有效  1 无效
	
	@Transient
	private String fileUrl;//文件下载地址
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
}
