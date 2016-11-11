package com.liu.qinziyou.entity.qinziyou;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.liu.qinziyou.entity.BaseBean;

/**
 * 活动主题
 * @author jincheng
 *
 */

@Entity
@Table(name = "activiy_topic")
public class ActivityTopic extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8816618731704602328L;
	
	@Column(name="TOPIC_NAME",nullable=false)	
	private String topicName;//活动主题
	
	@Column(name="CITY",nullable=false)	
	private String city;//活动城市
	
	@Column(name="ADDRESS",nullable=false)	
	private String address;//活动地点
	
	@Column(name="LIMIT_COUNT",nullable=false)	
	private Integer limitCnt;//限额 -1表示不限制
	
	@Column(name="PRICE_AUDLT",nullable=false)	
	private Integer priceAdult;//成人价 ，单位分

	@Column(name="PRICE_CHILDREN",nullable=false)	
	private Integer pricecChildren;//小孩价 ，单位分
	
	@Column(name="GATHER_PLACE",nullable=false)	
	private String gatherPlace;//集合地点
	
	@Column(name="ACTIVITY_CROWD",nullable=false)	
	private String activityCrowd;//活动对象，人群
	
	@Column(name="ACTIVITY_FLOW",nullable=false)	
	private String activityFlow;//活动流程，人群
	
	@Column(name="ACTIVITY_DATE",nullable=false)	
	private Date activityDate;//活动日期
	
	@Column(name="FROM_TIME",nullable=false)
	private String fromTime;//活动开始时间  10:20 这样的格式
	
	@Column(name="END_TIME",nullable=false)
	private String endTime;//活动结束时间 11:20这样的格式
	
	@Column(name="TIPS",nullable=false)	
	private String tips;//温馨提示
	
	@Column(name="ACTIVITY_STATUS",nullable=false)
	private Integer activityStatus;//活动状态  10:暂存 20 已发布  30 已下架 
	
	@Column(name="ACTIVITY_REG",nullable=false)
	private String activityReg;//活动 报名方式
	
	@Column(name="MAIN_IMG_URL",nullable=true)
	private String mainImgUrl;//主图路径
	
	@Column(name="SUMMARY",nullable=false)	
	private String summary;//活动简介
	
	@Transient
	private String month;
	@Transient
	private String day;

	public String getTopicName() {
		return topicName;
	}
	
	

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	public Integer getLimitCnt() {
		return limitCnt;
	}

	public void setLimitCnt(Integer limitCnt) {
		this.limitCnt = limitCnt;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getPriceAdult() {
		return priceAdult;
	}

	public void setPriceAdult(Integer priceAdult) {
		this.priceAdult = priceAdult;
	}

	public Integer getPricecChildren() {
		return pricecChildren;
	}

	public void setPricecChildren(Integer pricecChildren) {
		this.pricecChildren = pricecChildren;
	}

	public String getGatherPlace() {
		return gatherPlace;
	}

	public void setGatherPlace(String gatherPlace) {
		this.gatherPlace = gatherPlace;
	}

	

	public String getActivityCrowd() {
		return activityCrowd;
	}

	public void setActivityCrowd(String activityCrowd) {
		this.activityCrowd = activityCrowd;
	}

	public String getActivityFlow() {
		return activityFlow;
	}

	public void setActivityFlow(String activityFlow) {
		this.activityFlow = activityFlow;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getActivityReg() {
		return activityReg;
	}

	public void setActivityReg(String activityReg) {
		this.activityReg = activityReg;
	}

	public String getMainImgUrl() {
		return mainImgUrl;
	}

	public void setMainImgUrl(String mainImgUrl) {
		this.mainImgUrl = mainImgUrl;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	public String getMonth(){
		Calendar c =  Calendar.getInstance();
		c.setTime(this.getActivityDate());
		Integer m = c.get(Calendar.MONTH)+1;
		this.month = m.toString();
		return month;
	}
	
	public String getDay(){
		Calendar c =  Calendar.getInstance();
		c.setTime(this.getActivityDate());
		Integer d = c.get(Calendar.DAY_OF_MONTH);
		this.day = d.toString();
		return day;
	}



	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
}
