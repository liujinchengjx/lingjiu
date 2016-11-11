package com.liu.qinziyou.entity.qinziyou;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;
/**
 * 活动现场图
 * @author jincheng
 *
 */
@Entity
@Table(name = "activity_img")
public class ActivityImg extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 979922296866349652L;

	@Column(name="TOPIC_ID",nullable=false)	
	private Integer topicId;//主题ID
	
	@Column(name="IMG_DESC",nullable=true)	
	private String imgDesc;//图片描述
	
	@Column(name="IMG_PATH",nullable=false)	
	private String imgPath;//图片路径
	
	@Column(name="PRAISE_CNT",nullable=false)	
	private Integer praiseCnt;//点赞数
	
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getPraiseCnt() {
		return praiseCnt;
	}
	public void setPraiseCnt(Integer praiseCnt) {
		this.praiseCnt = praiseCnt;
	}
	
	
}
