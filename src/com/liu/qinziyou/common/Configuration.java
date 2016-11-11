package com.liu.qinziyou.common;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.PropertyResourceBundle;

public class Configuration {
	static String configFile = "parameter.properties";
	static PropertyResourceBundle resource;
	static {
		try {
			String filePath = Configuration.class.getClassLoader().getResource(configFile).getPath();
			Reader reader = new InputStreamReader(new FileInputStream(filePath));
			resource = new PropertyResourceBundle(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	public static String getConfigValue(String name) {
		String value = null;
		try {
			value = resource.getString(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
