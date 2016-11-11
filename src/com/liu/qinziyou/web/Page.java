package com.liu.qinziyou.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.liu.qinziyou.common.Configuration;



public class Page  implements Serializable{
	
	
	public static enum  MsgCode{
		SUCCESS("1"), //成功
		FAILURE("2"), // 失败
		LOGIN_EXCEPTION("3"), // 登录异常
		DENY_ACCESS("4"); // 访问受限
		
		private String value;

		private MsgCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return value;
		}
	}
	
    private Map<String,Object> root = new HashMap<String,Object>();
    private String forward;
    private String direct;
    
    public Page(){
    	root.put("STATIC_PAGE_CSS_SERVER_URL",Configuration.getConfigValue("STATIC_PAGE_CSS_SERVER_URL"));
    	root.put("STATIC_PAGE_CSS_TIMESTAMP", Configuration.getConfigValue("STATIC_PAGE_CSS_TIMESTAMP"));
    	root.put("STATIC_PAGE_IMG_SERVER_URL", Configuration.getConfigValue("STATIC_PAGE_IMG_SERVER_URL"));
    	root.put("STATIC_PAGE_IMG_TIMESTAMP",Configuration.getConfigValue("STATIC_PAGE_IMG_TIMESTAMP"));
    	root.put("STATIC_PAGE_JS_SERVER_URL", Configuration.getConfigValue("STATIC_PAGE_JS_SERVER_URL"));
    	root.put("STATIC_PAGE_JS_TIMESTAMP", Configuration.getConfigValue("STATIC_PAGE_JS_TIMESTAMP"));
    	root.put("STATICS_FILESERVER_DOMAIN", Configuration.getConfigValue("STATICS_FILESERVER_DOMAIN"));
    	root.put("webDomain", Configuration.getConfigValue("webDomain"));

    }
    
    public Map<String,Object> put(String name, Object value) {
        root.put(name, value);
        return root;
    }
    
    public Map<String,Object> put(String name, int value) {
        root.put(name, new Integer(value));
        return root;
    }
    
    public Map<String,Object>  put(String name, double value) {
        root.put(name, new Double(value));
        return root;
    }

    public  Map<String,Object>  put(String name, boolean value) {
        root.put(name, new Boolean(value));
        return root;
    }
    
    public Page setMsg(String msg) {
    	root.put("MSG", msg);
    	return this;
    }
    
    public Page setMsgCode(MsgCode msgCode){
    	root.put("MSGCODE", msgCode.getValue());
    	return this;
    }
    
    public Map getRoot() {
        return root;
    }

	public String getForward() {
		return forward;
	}

	public Page setForward(String forward) {
		this.forward = forward;
		return this;
	}

	public String getDirect() {
		return direct;
	}

	public Page setDirect(String direct) {
		this.direct = direct;
		return this;
	}
  }
