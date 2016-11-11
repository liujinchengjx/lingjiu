package com.liu.qinziyou.common;

import java.util.List;
/**
 * 转到前端数据统一封装类
 * @author liujc
 */
public class MsgBean {
	private MsgCode msgCode;
	private String msg;
	private Object entity;
	private String failureCode;//自定义的错误码，用这个的时候msgCode一定等于2
	private List rows ; 
	private int pageSize=10;
	private int total;
	private int start=1;	
	private boolean isOutRows=false;//是否指输出rows这一部分，这里是有时候只需输出rows这一部分到html端，
	public MsgBean(){//必须构造一下
		setMsgCode(MsgBean.MsgCode.NOTIPS);
	}
	
	public int getMsgCode() {
		switch(msgCode){
			case NOTIPS   : return 0;
			case SUCCESS  : return 1;
			case FAILURE : return 2; 
			case LOGIN_EXCEPTION : return 3;//登录超时
			default:return 0;
		}
		
	}
	public MsgBean setMsgCode(MsgCode msgCode) {
		this.msgCode = msgCode;
		return this;
	}
	public String getMsg() {
		if(this.msgCode==MsgCode.NOTIPS) msg="";
		return msg;
	}
	public MsgBean setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public enum MsgCode{
		NOTIPS,SUCCESS,FAILURE,LOGIN_EXCEPTION;
	}
	public Object getEntity() {
		return entity;
	}
	public MsgBean setEntity(Object entity) {
		this.entity = entity;
		return this;
	}
	public static MsgBean getInstance(){
		return new MsgBean();
	}
	public String getFailureCode() {
		return failureCode;
	}
	public void setFailureCode(String failureCode) {
		this.failureCode = failureCode;
	}
	
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getPageSize() {
		if(pageSize==0)
		{
			pageSize=10;
		}
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}	

	public boolean isOutRows() {
		return isOutRows;
	}
	public MsgBean setOutRows(boolean isOutRows) {
		this.isOutRows = isOutRows;
		return this;
	}		
}
