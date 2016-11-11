package com.liu.qinziyou.web.action.systemmanager;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.tree.PopedomTree;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.systemmanager.Popedom;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IPopedomService;
import com.liu.qinziyou.web.action.BaseAction;

public class PopedomAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(PopedomAction.class);
	private Popedom pop;
	private String searchValue;	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			popedomService.add(pop);
			msg.setEntity(pop.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{						
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			popedomService.update(pop);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{						
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			popedomService.del(pop);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{						
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			pop=(Popedom)popedomService.getBeanById(Popedom.class,pop.getId());
			msg.setEntity(pop);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//读到权限列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean list(){
		MsgBean msg = new MsgBean();
		try{
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			PageHelper ph=new PageHelper();
			ph=popedomService.selectPopedomList(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);		
		}
		return msg;
	}
	//读到权限树
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean loadPopedomTree(){
		MsgBean msg = new MsgBean();
		try{
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			List<PopedomTree> lst=popedomService.loadPopedomTree();
			msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS).setRows(lst);				
		}catch(ServiceException e){
			log.error("加载权限树失败==="+e.getMessage());
			msg.setMsg("加载权限树失败==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchPopedom(){
		MsgBean msg = new MsgBean();
		try{
			IPopedomService popedomService = (IPopedomService)BeanLoader.getWebBean("popedomService");
			PageHelper ph=new PageHelper();
			ph=popedomService.searchPopedom(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setEntity(0).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);	
		}
		return msg;
	}
	public Popedom getPop() {
		return pop;
	}
	public void setPop(Popedom pop) {
		this.pop = pop;
	}
	
}
