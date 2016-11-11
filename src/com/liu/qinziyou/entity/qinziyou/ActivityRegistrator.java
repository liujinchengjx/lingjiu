package com.liu.qinziyou.entity.qinziyou;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.liu.qinziyou.entity.BaseBean;
/**
 * 活动报名
 * @author jincheng
 *
 */
@Entity
@Table(name = "activity_registrator")
public class ActivityRegistrator extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="TOPIC_NAME",nullable=false)	
	private String topicID;//活动主题
	
	@Column(name="REGISTRATOR",nullable=true)
	private String registrator;//报名人
	
	@Column(name="MOBLIE",nullable=true)
	private String moblie;//报名人
	
	@Column(name="ADULT_CNT",nullable=true)
	private Integer adultCnt;//大人数
	
	@Column(name="CHILD_CNT",nullable=true)
	private Integer childCnt;//小孩数
	
	@Column(name="REGISTRATOR_TIME",nullable=true)
	private java.sql.Timestamp  registratorTime;//报名时间

	public String getTopicID() {
		return topicID;
	}

	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}

	public String getRegistrator() {
		return registrator;
	}

	public void setRegistrator(String registrator) {
		this.registrator = registrator;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public Integer getAdultCnt() {
		return adultCnt;
	}

	public void setAdultCnt(Integer adultCnt) {
		this.adultCnt = adultCnt;
	}

	public Integer getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(Integer childCnt) {
		this.childCnt = childCnt;
	}

	public java.sql.Timestamp getRegistratorTime() {
		return registratorTime;
	}

	public void setRegistratorTime(java.sql.Timestamp registratorTime) {
		this.registratorTime = registratorTime;
	}
	
	
	
	
	
}
