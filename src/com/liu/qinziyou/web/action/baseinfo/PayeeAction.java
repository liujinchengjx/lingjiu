package com.liu.qinziyou.web.action.baseinfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.business.Payee;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.baseinfo.IPayeeService;
import com.liu.qinziyou.web.action.BaseAction;

public class PayeeAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(PayeeAction.class);
	private Payee pe;
	private Integer payeeId;
	private String searchValue;
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{
			IPayeeService payeeService = (IPayeeService)BeanLoader.getWebBean("payeeService");
			payeeService.add(pe);			
			msg.setEntity(pe.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("添加收款人失败==="+e.getMessage());
		}	
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{
			IPayeeService payeeService = (IPayeeService)BeanLoader.getWebBean("payeeService");
			payeeService.update(pe);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("更新收款人失败==="+e.getMessage());
		}	
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{
			IPayeeService payeeService = (IPayeeService)BeanLoader.getWebBean("payeeService");			
			payeeService.del(pe);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("删除收款人失败==="+e.getMessage());
		}	
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{
			IPayeeService payeeService = (IPayeeService)BeanLoader.getWebBean("payeeService");			
			pe=(Payee)payeeService.getBeanById(Payee.class,payeeId);
			msg.setEntity(pe);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("获得收款人失败==="+e.getMessage());
		}	
		return msg; 
	}
	
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchPayee(){
		MsgBean msg = new MsgBean();
		try{
			IPayeeService payeeService = (IPayeeService)BeanLoader.getWebBean("payeeService");
			PageHelper ph=new PageHelper();
			ph=payeeService.searchPayee(searchValue,start,limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索收款人失败==="+e.getMessage());
		}catch(Exception e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索收款人失败==="+e.getMessage());
		}	
		return msg;
	}
	public Payee getPe() {
		return pe;
	}
	public void setPe(Payee pe) {
		this.pe = pe;
	}
	public Integer getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	
}
