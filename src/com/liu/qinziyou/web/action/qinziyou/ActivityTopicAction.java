package com.liu.qinziyou.web.action.qinziyou;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.MsgBean.MsgCode;
import com.liu.qinziyou.common.aop.annotation.FreeMarkTemplate;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.qinziyou.ActivityTopic;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.qinziyou.IActivityService;
import com.liu.qinziyou.web.Page;
import com.liu.qinziyou.web.action.BaseAction;

public class ActivityTopicAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(ActivityTopicAction.class);
	private ActivityTopic activityTopic;
	private String searchValue;
	private Integer status;
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.add(activityTopic);
			msg.setMsgCode(MsgCode.SUCCESS).setMsg("操作成功！");			
		}catch(ServiceException e){
			log.error("新增活动主题==="+e); 
			msg.setMsgCode(MsgCode.FAILURE).setMsg(e.getMessage());
		}
		return msg;
	}
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.update(activityTopic);
			msg.setMsgCode(MsgCode.SUCCESS).setMsg("操作成功！");			
		}catch(ServiceException e){
			log.error("新增活动主题==="+e); 
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
			PageHelper ph=new PageHelper();
			ph=activityService.searchActivityTopic(searchValue,status,start,limit);	
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索列表失败==="+e.getMessage());
		}	
		return msg;
	}
	
	//首页
	@FreeMarkTemplate("/index.html")
	public Page index(){
		Page page = new Page();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			PageHelper ph=new PageHelper();
			ph=activityService.searchActivityTopic(searchValue,20,start,6);	
			page.put("topiclist", ph.getRows());
		}catch(ServiceException e){
			log.error("index==="+e.getMessage());
		}	
		return page;
	}
	
	//首页
	@FreeMarkTemplate("/activity_detail.html")
	public Page detail(){
		Page page = new Page();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			ActivityTopic activityTopic = (ActivityTopic)activityService.getBeanById(ActivityTopic.class, Integer.valueOf(id));	
			page.put("activity", activityTopic);
			PageHelper ph = new PageHelper();
			ph=activityService.searchActivityImg( Integer.valueOf(id),start,30);
			page.put("imglist", ph.getRows());
			page.put("imgtotal", ph.getTotal());
		}catch(ServiceException e){
			log.error("detail==="+e.getMessage());
		}	
		return page;
	}
	
	public static void main(String[] args){
		ActivityTopicAction a = new ActivityTopicAction();
		a.search();
	}
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.del(activityTopic);
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("删除活动主题失败==="+e.getMessage());
		}	
		return msg;
	}
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			ActivityTopic activityTopic = (ActivityTopic)activityService.getBeanById(ActivityTopic.class, Integer.valueOf(id));
			msg.setEntity(activityTopic).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("获取活动主题失败==="+e.getMessage());
		}	
		return msg;
	}
	
	public MsgBean changeActivityStatus(){
		MsgBean msg = new MsgBean();
		try{
			IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
			activityService.changeActivityStatus(status, new Integer(id));
			msg.setEntity(activityTopic).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("获取活动主题失败==="+e.getMessage());
		}	
		return msg;
	}

	public ActivityTopic getActivityTopic() {
		return activityTopic;
	}

	public void setActivityTopic(ActivityTopic activityTopic) {
		this.activityTopic = activityTopic;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
