package com.liu.qinziyou.web.action.systemmanager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.PageHelper;
import com.liu.qinziyou.common.aop.annotation.InterceptorName;
import com.liu.qinziyou.common.aop.annotation.InterceptorNames;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.entity.systemmanager.Department;
import com.liu.qinziyou.exception.ServiceException;
import com.liu.qinziyou.services.systemmanager.IDepartmentService;
import com.liu.qinziyou.web.action.BaseAction;

public class DepartmentAction extends BaseAction{
	protected static final Log log = LogFactory.getLog(DepartmentAction.class);
	private Department dep;
    private Integer opComanyId;
    private String searchValue;
    @InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean add(){
		MsgBean msg = new MsgBean();
		try{						
			IDepartmentService departmentService = (IDepartmentService)BeanLoader.getWebBean("departmentService");
			departmentService.add(dep);
			msg.setEntity(dep.getId()).setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
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
			IDepartmentService departmentService = (IDepartmentService)BeanLoader.getWebBean("departmentService");
			departmentService.update(dep);			
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
			IDepartmentService departmentService = (IDepartmentService)BeanLoader.getWebBean("departmentService");
			departmentService.del(dep);			
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
			IDepartmentService departmentService = (IDepartmentService)BeanLoader.getWebBean("departmentService");
			dep=(Department)departmentService.getBeanById(Department.class,dep.getId());
			msg.setEntity(dep);			
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
    @InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean approve(){//复核
		MsgBean msg = new MsgBean();
		try{						
			IDepartmentService departmentService = (IDepartmentService)BeanLoader.getWebBean("departmentService");
//			departmentService.approved(role.getId(),0);			
			msg.setMsg("复核成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);
		}catch(ServiceException e){
			log.error(e.getMessage());
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}	
		return msg;
	}
	
	//搜索
    @InterceptorNames({ @InterceptorName("loginInterceptor") })
	public MsgBean searchDepartment(){
		MsgBean msg = new MsgBean();
		try{
			IDepartmentService departmentService = (IDepartmentService)BeanLoader.getWebBean("departmentService");
			PageHelper ph=departmentService.searchDepartment(searchValue,opComanyId,start,10000);	
			msg.setOutRows(outRows);//只用输出rows
			msg.setRows(ph.getRows());
			msg.setTotal(ph.getTotal());
			msg.setMsg("成功 ").setMsgCode(MsgBean.MsgCode.SUCCESS);	
		}catch(ServiceException e){
			log.error(e.getMessage());	
			msg.setMsg(e.getMessage()).setMsgCode(MsgBean.MsgCode.FAILURE);
		}
		return msg;
	}
	public Department getDep() {
		return dep;
	}
	public void setDep(Department dep) {
		this.dep = dep;
	}
	public Integer getOpComanyId() {
		return opComanyId;
	}
	public void setOpComanyId(Integer opComanyId) {
		this.opComanyId = opComanyId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
