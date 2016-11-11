package com.liu.qinziyou.web;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.liu.qinziyou.common.aop.ActionFactory;
import com.liu.qinziyou.common.aop.ActionInvoke;
import com.liu.qinziyou.common.aop.annotation.FreeMarkTemplate;
import com.liu.qinziyou.web.action.BaseAction;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

public class NormalRestServlet  extends HttpServlet{

	private final static Log logger = LogFactory.getLog(JsonRestServlet.class);

	private static final long serialVersionUID = 8352315328731387393L;

	private static String ACTION_LOCATOR;
	private static String ACTION_NAME = "actionName";
	private static String ID = "id";
	private static String METHOD_NAME = "methodName";
	private static String ACTION_SUFFIX;
	private static String SUB_CLASS_PATH = "subClassPath";
	private static String DEFAULT_URL_PATTERN;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		try {
			Map<String, String> requestPathMap = urlToRequestPath(uri);
			String actionName = requestPathMap.get(ACTION_NAME);
			String methodName = requestPathMap.get(METHOD_NAME);
			String subClassPath = requestPathMap.get(SUB_CLASS_PATH);
			String id = requestPathMap.get(ID);

			String actionClassName = ACTION_LOCATOR + "." + actionName + ACTION_SUFFIX;

			actionClassName = ACTION_LOCATOR + subClassPath + "." + actionName + ACTION_SUFFIX;

			BaseAction action = (BaseAction) ActionFactory.getAction(actionClassName);
			
			action.setId(id);
			action.setRequest(req);
			action.setResponse(resp);

			Method method = action.getClass().getMethod(methodName);// 要执行的方法
			try {
				//Page page = new Page();
				ActionInvoke ai = new ActionInvoke(action, method, null);
				Page page = (Page)ai.excute();
				
				String template = null;
				if(method.isAnnotationPresent(FreeMarkTemplate.class)){
					FreeMarkTemplate freeMarkTemplate = method.getAnnotation(FreeMarkTemplate.class);  
					template = freeMarkTemplate.value();
				}else{
					String tp = req.getParameter("tp");
					if(tp.indexOf("?")!=-1){
						tp = tp.substring(0, tp.indexOf("?"));
					}
					if(tp!=null){
						template=tp;
					}
				}
				
				String forward = page.getForward();
				String direct = page.getDirect();
				
				if(forward != null) { // forward request
					RequestDispatcher rd = req.getRequestDispatcher(forward);
					rd.forward(req, resp);
				} else if (direct != null) {
					resp.sendRedirect(direct);
				} else if (template != null) { // show a page with a
					// template
					// Get the template object
					Template t = cfg.getTemplate(template);

					// Prepare the HTTP response:
					// - Set the MIME-type and the charset of the output.
					// Note that the charset should be in sync with the
					// output_encoding setting.
					resp.setContentType("text/html; charset="
							+ cfg.getOutputEncoding());
					// - Prevent browser or proxy caching the page.
					// Note that you should use it only for development and for
					// interactive
					// pages, as it significantly slows down the Web site.
					resp.setHeader("Cache-Control",
							"no-store, no-cache, must-revalidate, "
									+ "post-check=0, pre-check=0");
					resp.setHeader("Pragma", "no-cache");
					//resp.setHeader("Expires", "Thu, 01 Dec 1994 00:00:00 GMT");
					Writer out = resp.getWriter();

					// Merge the data-model and the template
					try {
						t.process(page.getRoot(), out);
					} catch (TemplateException e) {
						throw new ServletException(
								"Error while processing FreeMarker template", e);
					}
				} else {
					throw new ServletException(
							"The action didn't specified a command.");
				}

			} catch (Throwable e) {
				e.printStackTrace();
				logger.error(e);
				throw new Exception(e.getMessage());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			
		} finally {
			
		}
	}

	private Map<String, String> urlToRequestPath(String uri) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		uri = uri.replaceFirst(DEFAULT_URL_PATTERN, "");
		boolean restFlage=false;
		if(uri.lastIndexOf(".")==-1){//不带.html的请求
			restFlage =true;
		}else{//带.html的请求
			uri =  uri.substring(0, uri.lastIndexOf("."));
		}
		
		String[] uris = uri.split("/");
		if (uris.length < 2)
			throw new Exception("请求路径错误");
		
		if(restFlage){
			map.put(METHOD_NAME, uris[uris.length - 1]);
			map.put(ID, uris[uris.length - 2]);
			map.put(ACTION_NAME, uris[uris.length -3]);
		}else{
			map.put(METHOD_NAME, uris[uris.length - 1]);
			map.put(ACTION_NAME, uris[uris.length - 2]);
		}
		
		
		String subClassPath = "";
		if(restFlage){
			for (int i = 0; i < uris.length - 3; i++) {
				subClassPath = subClassPath + "." + uris[i];
			}
		}else{
			for (int i = 0; i < uris.length - 2; i++) {
				subClassPath = subClassPath + "." + uris[i];
			}
		}
		
		map.put(SUB_CLASS_PATH, subClassPath);
		return map;
	}

	
	private Configuration cfg; 
	@Override
	public void init() throws ServletException {
		ACTION_LOCATOR = super.getServletContext().getInitParameter("restPackageLocators");
		ACTION_SUFFIX = super.getServletContext().getInitParameter("restActionSuffix");
		DEFAULT_URL_PATTERN = super.getServletContext().getInitParameter("normalDefaultUrlPattern");
		
		 // Initialize the FreeMarker configuration;
        // - Create a configuration instance
        cfg = new Configuration();
        // - At least in new projects, specify that you want the fixes that aren't
        //   100% backward compatible too (these are always very low-risk changes):
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));
        // - Templates are stoted in the WEB-INF/templates directory of the Web app.
        // cfg.setServletContextForTemplateLoading(
        //        getServletContext(), "WEB-INF/templates");
       
        try{
        	 cfg.setDirectoryForTemplateLoading(new File(com.liu.qinziyou.common.Configuration.getConfigValue("STATIC_TEMPLATE_FILEPATH")));
        }catch(IOException e){
        	e.printStackTrace();
        }
        
      
        // - Set update dealy to 0 for now, to ease debugging and testing.
        //   Higher value should be used in production environment.
        cfg.setTemplateUpdateDelay(0);
        // - When developing, set an error handler that prints errors so they are
		//   readable with a HTML browser, otherwise we just let the HTTP 500
		//   handler to deal with it.
        cfg.setTemplateExceptionHandler(
				isInDevelopmentMode()
						? TemplateExceptionHandler.HTML_DEBUG_HANDLER
						: TemplateExceptionHandler.RETHROW_HANDLER);
        // - Use beans wrapper (recommmended for most applications)
		BeansWrapper bw = new BeansWrapper();
		bw.setSimpleMapWrapper(true);
        cfg.setObjectWrapper(bw);
        // - Set the default charset of the template files
        cfg.setDefaultEncoding("UTF-8");
        // - Set the charset of the output. This is actually just a hint, that
        //   templates may require for URL encoding and for generating META element
        //   that uses http-equiv="Content-type".
        cfg.setOutputEncoding("UTF-8");
        // - Set the default locale
        cfg.setLocale(Locale.US);
		super.init();
	}
	private boolean isInDevelopmentMode() {
		// This should detect this with a system property for example.
		return true;
	}
	

}
