package com.liu.qinziyou.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * <p>Title: 系统框架</p>
 * <p>Description:类型转换</p>
 * <p>创建日期:2011-10-25</p>
 * @author li
 * @version 1.0 
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
 
	private String format = "yyyy-MM-dd";

    public DateJsonValueProcessor() {}

    public DateJsonValueProcessor(String format) {
    	this.format = format;
    }

	public Object processArrayValue(Object value, JsonConfig config) {
		String[] objs = {};
		if (value instanceof Date[]) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date[] dates = (Date[])value;
			objs = new String[dates.length];
			for (int i = 0; i < dates.length; i++) {
				objs[i] = sdf.format(dates[i]);
			}
		}
		return objs;
	}

	public Object processObjectValue(String key, Object value, JsonConfig config) {
		if (value instanceof Date) {
			return new SimpleDateFormat(format).format(value);
		}
		return value == null ? "" : value.toString();
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
