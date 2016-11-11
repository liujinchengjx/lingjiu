package com.liu.qinziyou.common;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionLoader {
	static final String actionPackagePath = "com.liu.qinziyou.web.action";
	//访问路径和action class对应关系
	public static Map<String,Class> actionClassMap = new HashMap<String,Class>();
	public static List<Class> classList = new ArrayList<Class>();
	public static void main(String[] args) throws Exception{
		
		String actionPath = actionPackagePath.replace(".", "/");
		URL url = Thread.currentThread().getContextClassLoader().getResource(actionPath) ;
		getClassList(new File(url.toURI()),actionPackagePath);
		for(Class cls :classList){
			System.out.println(cls.getName());
		}
	}

	public static void getClassList(File dir, String packagePath)
			throws Exception {
		//System.out.println(dir.toString());
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				getClassList(file, packagePath + "." + file.getName());
			}else{
				String filename = file.getName();
				if(filename.endsWith(".class")){
					String className = packagePath + "." + file.getName();
					className = className.substring(0, className.length() - 6);
					classList.add(Class.forName(className));
				}
				
			}

		}

		

	}
}
