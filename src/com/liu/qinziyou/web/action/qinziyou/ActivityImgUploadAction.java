package com.liu.qinziyou.web.action.qinziyou;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.Configuration;
import com.liu.qinziyou.common.JsonUtil;
import com.liu.qinziyou.common.util.BeanLoader;
import com.liu.qinziyou.services.qinziyou.IActivityService;
import com.liu.qinziyou.web.action.MultipartAction;

public class ActivityImgUploadAction  extends MultipartAction{
	protected static final Log log = LogFactory.getLog(ActivityImgUploadAction.class);
	private Integer topicId;;
	public void uploadCoverImg(){
		String fileServerUrl = Configuration.getConfigValue("STATICS_FILESERVER_DOMAIN");
		Map obj = new HashMap();
		try {
			if (super.getFileItems().size() > 0) {
				FileItem fileItem = super.getFileItems().get(0);
				IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
				String path = activityService.uploadActivityCoverImg(fileItem.get(), fileItem.getName(),new Integer(id));
				obj.put("error", 0);
				obj.put("url", fileServerUrl + path);
	
			} else {
				obj.put("error", 1);
				obj.put("message", "上传的文件为空");
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("error", 1);
			obj.put("message", "上传文件失败");
	
		}
		responseToJson(obj, super.getResponse());
	}
	
	
	public void uploadImg(){
		String fileServerUrl = Configuration.getConfigValue("STATICS_FILESERVER_DOMAIN");
		Map obj = new HashMap();
		try {
			if (super.getFileItems().size() > 0) {
				FileItem fileItem = super.getFileItems().get(0);
				IActivityService activityService = (IActivityService)BeanLoader.getWebBean("activityService");	
				String path = activityService.uploadActivityImg(fileItem.get(), fileItem.getName(),new Integer(id));
				obj.put("error", 0);
				obj.put("url", fileServerUrl + path);
	
			} else {
				obj.put("error", 1);
				obj.put("message", "上传的文件为空");
	
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("error", 1);
			obj.put("message", "上传文件失败");
	
		}
		responseToJson(obj, super.getResponse());
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
	private void responseToJson(Object msgBean, HttpServletResponse resp) {
		HttpServletResponse response = resp;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.print(JsonUtil.toJson(msgBean));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	
}
