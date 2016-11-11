package com.liu.qinziyou.web.action.baseinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.baseinfo.AreaDict;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.baseinfo.IAreaDictService;
import com.liu.qinziyou.web.action.BaseAction;

public class AreaDictAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(AreaDictAction.class);
	private AreaDict ad;
	private String searchValue;
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			areaDictService.add(ad);
			Map map = new HashMap();
			map.put("text", ad.getName());
			map.put("id", ad.getId());
			map.put("level", ad.getLevel());
			map.put("parentId", ad.getParentId());
			map.put("hasChildren",false);
			msg.setEntity(map).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();
			log.error("保存失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{						
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			areaDictService.update(ad);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();
			log.error("更新失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{						
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			areaDictService.del(ad);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();
			log.error("删除失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{						
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			ad=(AreaDict)areaDictService.getBeanById(AreaDict.class,ad.getId());
			msg.setEntity(ad);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();
			log.error(" 获取字典失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	//获取字典列表失败
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean list(){
		MsgBean msg = new MsgBean();
		try{
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			PageHelper ph=new PageHelper();
			ph=areaDictService.selectAreaDict(start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();	
			log.error(" 获取字典列表失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}	
	//根据父级和地区级别读取地区
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getAreaByPLevel(){
		MsgBean msg = new MsgBean();
		try{
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			List<AreaDict> ls=new ArrayList<AreaDict>();
			List ls1=new ArrayList();			
			ls=areaDictService.selectAreaByPLevel(ad.getLevel(),Integer.valueOf(this.getId()));			
			for(AreaDict areaDict:ls){	
				Map map = new HashMap();
				map.put("text", areaDict.getName());
				map.put("id", areaDict.getId());
				map.put("level", areaDict.getLevel());
				map.put("parentId", areaDict.getParentId());
				map.put("hasChildren",4==areaDict.getLevel()? false:true);
				ls1.add(map);
			}
			msg.setEntity(ls1);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();		
			log.error("根据父级和地区级别读取地区失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}	
	//根据父级和地区级别读取地区
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getAreaByPLevel1(){
		MsgBean msg = new MsgBean();
		try{
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			List<AreaDict> ls=new ArrayList<AreaDict>();
			ls=areaDictService.selectAreaByPLevel(ad.getLevel(),Integer.valueOf(this.getId()));			
			msg.setOutRows(true);
			msg.setRows(ls);
			msg.setEntity(ls);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();		
			log.error("根据父级和地区级别读取地区失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}	
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchAreaDict(){
		MsgBean msg = new MsgBean();
		try{
			IAreaDictService areaDictService = (IAreaDictService)BeanLoader.getWebBean("areaDictService");
			PageHelper ph=new PageHelper();
			ph=areaDictService.searchAreaDict(searchValue,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			e.printStackTrace();	
			log.error("搜索地区失败==="+e.getMessage());
			msg.setMsg("失败 ").setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	public AreaDict getAd() {
		return ad;
	}
	public void setAd(AreaDict ad) {
		this.ad = ad;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
