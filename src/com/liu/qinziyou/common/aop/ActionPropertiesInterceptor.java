package com.liu.qinziyou.common.aop;

//import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.ActionPropetiesMapper;
import com.liu.qinziyou.exception.InterceptException;
import com.liu.qinziyou.exception.PropetiesMappingException;
import com.liu.qinziyou.web.action.BaseAction;
import com.liu.qinziyou.web.action.MultipartAction;

/**
 * Action属性拦截器
 * 用于处理请求参数，并映射到Action的属性中
 * @author liujc
 * 2013-3-1
 *
 */
public class ActionPropertiesInterceptor extends AbstractInterceptor{
	private final static Log logger = LogFactory.getLog(ActionPropertiesInterceptor.class);

	@Override
	protected Object after(ActionInvoke invInfo) throws Exception{
		//logger.info("ActionPropertiesInterceptor after");
		return null; 
		
	}

	@Override
	protected Object before(ActionInvoke invInfo) throws  Exception{
		HttpServletRequest request = invInfo.getBaseAction().getRequest();
		/*Enumeration ems = request.getHeaderNames();
		while(ems.hasMoreElements()){
			String headerName = (String)ems.nextElement();
			String headerValue = request.getHeader(headerName);
			System.out.println("headerName="+headerName+" headerValue="+headerValue);
			
		}*/
		
		//处理请求参数
		Map<String,String> paramenterMap = new HashMap<String,String>();
		
		
		BaseAction action = invInfo.getBaseAction();
		if(!(action instanceof MultipartAction)){//普通请求ACTION
			Enumeration em = request.getParameterNames();
			while(em.hasMoreElements()){
				String name = (String)em.nextElement();
				String value = request.getParameter(name);
				paramenterMap.put(name, value);
			}
			//请求参数映射
			try{
				ActionPropetiesMapper.mappingPropeties(paramenterMap, invInfo.getBaseAction());
			}catch(PropetiesMappingException e){
				logger.error(e);
				throw new InterceptException(e.getMessage());
			}
			//logger.info("ActionPropertiesInterceptor");
		}else{//带有上传文件的请求
			
			try {
				boolean isMultipart = ServletFileUpload.isMultipartContent(request);
				if(isMultipart==false){
					throw new InterceptException("the request is not Multipart request");
				} 
				
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("utf-8");
				List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
				MultipartAction multipartAction = (MultipartAction)invInfo.getBaseAction();
				List<FileItem> realFileItems = new ArrayList<FileItem>();
				for(FileItem fileItem :items){
					if(fileItem.isFormField()){
						paramenterMap.put(fileItem.getFieldName(), fileItem.getString());
					}else{
						realFileItems.add(fileItem);
					}
				}
				multipartAction.setFileItems(realFileItems);//设置文件属性
				
				//设置普通属性
				try{
					ActionPropetiesMapper.mappingPropeties(paramenterMap, multipartAction);
				}catch(PropetiesMappingException e){
					logger.error(e);
					throw new InterceptException(e.getMessage());
				}
				//logger.info("ActionPropertiesInterceptor");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw new InterceptException(e.getMessage());
		
			}
		}
		return null;
//		Class clz=SystemDictAction.class;
//		Field[] fields=clz.getDeclaredFields(); 
//		for (Field field : fields) {
//		   System.out.println("属性名："+field.getName()+"类型："+field.getType().getSimpleName());
//		}
	}

}
