package com.liu.qinziyou.web.action.qinziyou;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.MsgBean.MsgCode;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.qinziyou.ActivityRegistrator;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.qinziyou.IActivityService;
import com.liu.qinziyou.web.action.BaseAction;

public class ActivityRegistAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(ActivityRegistAction.class);
	private ActivityRegistrator activityRegistrator;
	private Integer topicId;
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.add(activityRegistrator);
			msg.setMsgCode(MsgCode.SUCCESS).setMsg("操作成功！");			
		}catch(ServiceException e){
			log.error("活动报名失败==="+e); 
			msg.setMsgCode(MsgCode.FAILURE).setMsg(e.getMessage());
		}
		return msg;
	}
	
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean search(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			PageHelper ph = new PageHelper();
			ph=activityService.searchActivityRegistrator(topicId,start,limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("报名成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索列表失败==="+e.getMessage());
		}	
		return msg;
	}

	public ActivityRegistrator getActivityRegistrator() {
		return activityRegistrator;
	}

	public void setActivityRegistrator(ActivityRegistrator activityRegistrator) {
		this.activityRegistrator = activityRegistrator;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
	
}
