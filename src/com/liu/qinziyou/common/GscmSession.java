package com.liu.qinziyou.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * session  统一操作Session工具类，所有存取session的操作都在这里，目的是规范
 * @author liujc
 *
 */
public class GscmSession {

	private static final String STOCK_OUT = "stock_out";//session key
	private static final String REF_ORDERID = "reforder_id";//session key

	
	private static void setObject(HttpServletRequest request,String key,Object value){
		request.getSession(true).setAttribute(key, value);
	}
	
	private  static Object getObject(HttpServletRequest request,String key){
		HttpSession httpSession = request.getSession(false);
		return httpSession == null?null:httpSession.getAttribute(key);
	}
	
	private  static void removeObject(HttpServletRequest request,String key){
		HttpSession httpSession = request.getSession(false);
		if(httpSession!=null) httpSession.removeAttribute(key);
	}
	
	public static void setStockOutInfo(HttpServletRequest request,Object stockOutInfo){
		setObject(request,STOCK_OUT, stockOutInfo);
	}
	
	public static Object getStockOutInfo(HttpServletRequest request){
		return getObject(request,STOCK_OUT);
	}
	
	public static void removeStockOutInfo(HttpServletRequest request){
		removeObject(request,STOCK_OUT);
	}
	
	public static void setRefOrderId(HttpServletRequest request,Integer refOrderId){
		setObject(request,REF_ORDERID, refOrderId);
	}
	
	public static Object getRefOrderId(HttpServletRequest request){
		return getObject(request,REF_ORDERID);
	}
	
	public static void removeRefOrderId(HttpServletRequest request){
		removeObject(request,REF_ORDERID);
	}
	
	
	public static void main(String[] args ){
		HttpServletRequest request=null;
		Object stockOutInfo=null;
		GscmSession.setStockOutInfo(request, stockOutInfo);//设置出库单信息
		GscmSession.getStockOutInfo(request);//获取出库单信息
		GscmSession.removeStockOutInfo(request);
		
	}

}
