package com.liu.qinziyou.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json日期格式化处理
 * @author Administrator
 *
 */
public class JsonDateValueProcessor implements JsonValueProcessor{
	 private String format = "yyyy-MM-dd HH:mm:ss";  
	 private SimpleDateFormat sdf = new SimpleDateFormat(format);  

	public Object processArrayValue(Object value, JsonConfig config) {
		// TODO Auto-generated method stub
		 return this.process(value);
	}

	
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return this.process(value);
	}
	private Object process(Object value) {
	   if(value==null) return null;
       if (Date.class.isAssignableFrom(value.getClass()))
            return sdf.format(value);
        else {
            return value;
        }
    }
	
}
