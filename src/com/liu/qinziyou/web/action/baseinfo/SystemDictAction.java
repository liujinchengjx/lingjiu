package com.liu.qinziyou.web.action.baseinfo;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.ConstantMaps;
import com.liu.qinziyou.common.Constants;
import com.liu.qinziyou.common.JsonUtil;
import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.baseinfo.SystemDict;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.baseinfo.ISystemDictService;
import com.liu.qinziyou.web.action.BaseAction;

public class SystemDictAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(SystemDictAction.class);
	private SystemDict dic;
	private String searchValue;
	private Integer flag;
	private Integer systemDictId;
	private String type;
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try {
			ISystemDictService systemDictService = (ISystemDictService) BeanLoader.getWebBean("systemDictService");
			dic.setFlag(Constants.IsValid.FLAG_NO.getValue());//默认是无效的
			systemDictService.add(dic);
			msg.setEntity(dic.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error(e.getMessage());
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean edit(){
		MsgBean msg = new MsgBean();
		try{						
			ISystemDictService systemDictService = (ISystemDictService)BeanLoader.getWebBean("systemDictService");
			systemDictService.update(dic);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error(e.getMessage());
		}		
		return msg; 
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean del(){
		MsgBean msg = new MsgBean();
		try{						
			ISystemDictService systemDictService = (ISystemDictService)BeanLoader.getWebBean("systemDictService");
			systemDictService.del(dic);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error(e.getMessage());
		}	
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean get(){
		MsgBean msg = new MsgBean();
		try{						
			ISystemDictService systemDictService = (ISystemDictService)BeanLoader.getWebBean("systemDictService");
			dic=(SystemDict)systemDictService.getBeanById(SystemDict.class,dic.getId());
			msg.setEntity(dic);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error(e.getMessage());
		}	
		return msg;
	}
	//读到字典类型的map列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean dictTypes(){
		MsgBean msg = new MsgBean();
		try{
			msg.setRows(ConstantMaps.getDictTypesList());
			msg.setOutRows(true); //ConstantMaps.getDictTypes()
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error(e.getMessage());
		}
		return msg;
	}
	//根据Type读取字典列表
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean getDictsByType(){
		MsgBean msg = new MsgBean();
		try{
			ISystemDictService systemDictService = (ISystemDictService)BeanLoader.getWebBean("systemDictService");			
			List<SystemDict> ls=systemDictService.selectDictByType(type);	
			msg.setOutRows(outRows); //ConstantMaps.getDictTypes()
			msg.setRows(ls);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error(e.getMessage());
		}
		return msg;
	}
	//搜索
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchSystemDict(){
		MsgBean msg = new MsgBean();
		try{
			ISystemDictService systemDictService = (ISystemDictService)BeanLoader.getWebBean("systemDictService");
			PageHelper ph=new PageHelper();
			ph=systemDictService.searchSystemDict(searchValue,type,start,limit);	
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg("搜索字典错误==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索字典错误==="+e.getMessage());
		}
		return msg;
	}
	@InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean updateFlag(){
		MsgBean msg = new MsgBean();
		try{
			ISystemDictService systemDictService = (ISystemDictService)BeanLoader.getWebBean("systemDictService");
			systemDictService.updateFlag(systemDictId,flag);	
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			msg.setMsg("搜索更新字典状态错误==="+e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
			log.error("搜索更新字典状态错误==="+e.getMessage());
		}
		return msg;
	}
	public SystemDict getDic() {
		return dic;
	}
	public void setDic(SystemDict dic) {
		this.dic = dic;
	}
	
	
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getSystemDictId() {
		return systemDictId;
	}
	public void setSystemDictId(Integer systemDictId) {
		this.systemDictId = systemDictId;
	}

	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static void main(String[] args){
		
		SystemDict sd = new SystemDict();
		sd.setType("01");
		sd.setDictCode("01");
		sd.setDictEName("SANSUNG");
		sd.setDictName("三星");
		sd.setFlag(1);
		sd.setTypeName("品牌");
		SystemDictAction sda = new SystemDictAction();
		sda.setDic(sd);
		MsgBean msgBean = sda.add();
		String json=JsonUtil.toJson(msgBean);
		System.out.println(json);
		
	}
}
