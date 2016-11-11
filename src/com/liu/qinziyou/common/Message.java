package com.liu.qinziyou.common;

public class Message implements java.io.Serializable{
	private MsgCode msgCode;
	private String msg;
	public int getMsgCode() {
		switch(msgCode){
			case NOTIPS   : return 0;
			case SUCCESS  : return 1;
			case FAILURE : return 2; 
			default:return 0;
		}
	}
	public Message setMsgCode(MsgCode msgCode) {
		this.msgCode = msgCode;
		return this;
	}
	public String getMsg() {
		if(this.msgCode==MsgCode.NOTIPS) msg="";
		return msg;
	}
	public Message setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public enum MsgCode{
		NOTIPS,SUCCESS,FAILURE;
	}
	
}
