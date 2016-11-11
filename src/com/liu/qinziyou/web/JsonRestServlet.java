package com.liu.qinziyou.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.ApplicationCache;
import com.liu.qinziyou.common.MsgBean;
import com.liu.qinziyou.common.aop.ActionFactory;
import com.liu.qinziyou.common.aop.ActionInvoke;
import com.liu.qinziyou.web.action.BaseAction;



/**
 * 请求处理Servlet 处理形如 http://xxx.com:80/包名/类名/ID/方法名 的请求，并返回JSON
 * @author liujc
 *
 */
public class JsonRestServlet extends HttpServlet {
	private final static Log logger = LogFactory.getLog(JsonRestServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8352315328731387393L;
	
	private static  String ACTION_LOCATOR 	;
	private static  String ACTION_NAME 		= 	"actionName";
	private static  String ID 				= 	"id";
	private static  String METHOD_NAME		= 	"methodName";
	private static  String ACTION_SUFFIX;
	private static  String SUB_CLASS_PATH	=	"subClassPath";
	private static  String DEFAULT_URL_PATTERN;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String uri = req.getRequestURI();
			
		MsgBean msgBean = null;
		try{		
			//System.out.println("Thead Name in servlet:"+Thread.curreLntThread());
			Map<String,String> requestPathMap = urlToRequestPath(uri);
			String actionName = requestPathMap.get(ACTION_NAME);
			String methodName = requestPathMap.get(METHOD_NAME);
			String subClassPath = requestPathMap.get(SUB_CLASS_PATH);
			String id = requestPathMap.get(ID);
			
			String actionClassName = ACTION_LOCATOR+"."+actionName+ACTION_SUFFIX;
			
			actionClassName = ACTION_LOCATOR+subClassPath+"."+actionName+ACTION_SUFFIX;
			
			BaseAction action= (BaseAction)ActionFactory.getAction(actionClassName);
			//BaseAction action= (BaseAction)object;//实际Action
			action.setId(id);
			action.setRequest(req);
			action.setResponse(resp);
			
			//System.out.println("servlet before invoke"+Thread.currentThread());
			Method method = action.getClass().getMethod(methodName);//要执行的方法

			try{
				ActionInvoke ai = new ActionInvoke(action,method,null);
				Object obj = ai.excute();
				if(obj instanceof MsgBean){
					msgBean = (MsgBean)obj;
				}
				if(obj != null && obj.getClass().equals(void.class)){
					msgBean = null;
				}
				
			}catch(Throwable e){
				e.printStackTrace();
				logger.error(e);
				throw new Exception(e.getMessage());
			}
			//System.out.println("servlet after invoke"+Thread.currentThread());
			
		}catch(Throwable e){
			e.printStackTrace();
			msgBean = new MsgBean();	
			msgBean.setMsg(e.getMessage());
			msgBean.setMsgCode(MsgBean.MsgCode.FAILURE);
		}finally{
			if(msgBean!=null){
				responseToJson(msgBean,resp);
			}			
		}
		
	}
	
	private Map<String,String> urlToRequestPath(String uri) throws Exception{
		Map<String,String> map  = new HashMap<String,String>();
		uri = uri.replaceFirst(DEFAULT_URL_PATTERN, "");
		
		String[] uris = uri.split("/");
		if(uris.length<3) throw new Exception("请求路径错误");
		map.put(ACTION_NAME, uris[uris.length-3]);
		map.put(ID, uris[uris.length-2]);
		map.put(METHOD_NAME, uris[uris.length-1]);
		String subClassPath = "";
		for(int i = 0; i<uris.length-3;i++){
			subClassPath = subClassPath+"."+uris[i];
		}
		map.put(SUB_CLASS_PATH, subClassPath);
		return map;
	}
	
	private void responseToJson(MsgBean msgBean,HttpServletResponse resp){
		HttpServletResponse response = resp;
		//response.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out =null;
		try{
			out = resp.getWriter();
			String json="";
			if (msgBean.isOutRows()){	
				json=com.liu.qinziyou.common.JsonUtil.toJson(msgBean.getRows());
			}else{
				json=com.liu.qinziyou.common.JsonUtil.toJson(msgBean);//com.gosonic.scm.common.JsonUtil.conversionToJSON(msgBean);
			}
			out.print(json);
		}catch(Exception e){
			//log.error(e.getMessage());
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}

	@Override
	public void init() throws ServletException {
		ACTION_LOCATOR = super.getServletContext().getInitParameter("restPackageLocators");
		ACTION_SUFFIX  = super.getServletContext().getInitParameter("restActionSuffix");
		DEFAULT_URL_PATTERN =  super.getServletContext().getInitParameter("jsonrestDefaultUrlPattern");
		//ApplicationCache.initCheckPriceCache();
		super.init();
	}
	
	

	
	
}
