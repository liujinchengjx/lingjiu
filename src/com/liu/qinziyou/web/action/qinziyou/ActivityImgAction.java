package com.liu.qinziyou.web.action.qinziyou;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.MsgBean.MsgCode;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.qinziyou.ActivityImg;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.qinziyou.IActivityService;
import com.liu.qinziyou.web.action.BaseAction;

public class ActivityImgAction  extends BaseAction{
	protected static final Log log = LogFactory.getLog(ActivityImgAction.class);
	private Integer topicId;
	private ActivityImg  activityImg;
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.add(activityImg);
			msg.setMsgCode(MsgCode.SUCCESS).setMsg("操作成功！");			
		}catch(ServiceException e){
			log.error("新增活动图片==="+e); 
			msg.setMsgCode(MsgCode.FAILURE).setMsg(e.getMessage());
		}
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.del(activityImg);
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("删除活动图片失败==="+e.getMessage());
		}	
		return msg;
	}//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean search(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			PageHelper ph = new PageHelper();
			ph=activityService.searchActivityImg(topicId,start,limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索活动图片列表失败==="+e.getMessage());
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchAll(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			PageHelper ph = new PageHelper();
			ph=activityService.searchActivityImg(topicId,start,100);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索活动图片列表失败==="+e.getMessage());
		}	
		return msg;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public ActivityImg getActivityImg() {
		return activityImg;
	}
	public void setActivityImg(ActivityImg activityImg) {
		this.activityImg = activityImg;
	}
	
	
	
}
