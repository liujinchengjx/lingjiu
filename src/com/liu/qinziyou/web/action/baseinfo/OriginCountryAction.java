package com.liu.qinziyou.web.action.baseinfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.baseinfo.OriginCountry;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.baseinfo.IOriginCountryService;
import com.liu.qinziyou.web.action.BaseAction;

public class OriginCountryAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(OriginCountryAction.class);
	private OriginCountry oc;
	private Integer originCountryId;  
	private Integer discountFlag=-1; //0 是  1 否   是否最惠国
	private Integer epidemicFlag=-1;////0否   1 是  是否疫区
	private String searchValue;
	
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IOriginCountryService originCountryService = (IOriginCountryService)BeanLoader.getWebBean("originCountryService");
			originCountryService.add(oc);
			msg.setEntity(oc.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);			
		}catch(ServiceException e){
			e.printStackTrace();
			log.error("新增原产国失败==="+e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{						
			IOriginCountryService originCountryService = (IOriginCountryService)BeanLoader.getWebBean("originCountryService");
			originCountryService.update(oc);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("更新原产国失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{						
			IOriginCountryService originCountryService = (IOriginCountryService)BeanLoader.getWebBean("originCountryService");
			originCountryService.del(oc);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("删除原产国失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{						
			IOriginCountryService originCountryService = (IOriginCountryService)BeanLoader.getWebBean("originCountryService");
			oc=(OriginCountry)originCountryService.getBeanById(OriginCountry.class,oc.getId());
			msg.setEntity(oc);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("获取原产国失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean updateDiscountOrEpidemicFlag(){
		MsgBean msg = new MsgBean();
		try{						
			IOriginCountryService originCountryService = (IOriginCountryService)BeanLoader.getWebBean("originCountryService");
			originCountryService.updateDiscountOrEpidemicFlag(originCountryId,discountFlag,epidemicFlag);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error("更新原产国失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg;
	}
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchOriginCountry(){
		MsgBean msg = new MsgBean();
		try{
			IOriginCountryService originCountryService = (IOriginCountryService)BeanLoader.getWebBean("originCountryService");
			PageHelper ph=new PageHelper();
			ph=originCountryService.searchOriginCountry(searchValue,discountFlag,epidemicFlag,start,limit);
			msg.setOutRows(outRows);
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			log.error("搜索产国失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public OriginCountry getOc() {
		return oc;
	}
	public void setOc(OriginCountry oc) {
		this.oc = oc;
	}
	public Integer getDiscountFlag() {
		return discountFlag;
	}
	public void setDiscountFlag(Integer discountFlag) {
		this.discountFlag = discountFlag;
	}
	public Integer getEpidemicFlag() {
		return epidemicFlag;
	}
	public void setEpidemicFlag(Integer epidemicFlag) {
		this.epidemicFlag = epidemicFlag;
	}
	public Integer getOriginCountryId() {
		return originCountryId;
	}
	public void setOriginCountryId(Integer originCountryId) {
		this.originCountryId = originCountryId;
	}
	
}
