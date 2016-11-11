package com.liu.qinziyou.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DataUtil {
	
	/**
	 * 
	 * @param doubleValue
	 * @param scale			小数位数
	 * @return
	 */
	public static Double round(Double doubleValue, int scale){   
		return DataUtil.round(doubleValue.doubleValue(), scale);
	}
	
	public static Double round(double doubleValue, int scale){     
		BigDecimal bd=new BigDecimal(doubleValue).setScale(scale, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	/**
	 * 计算时间差
	 * @param t1 末时间
	 * @param t2 始时间
	 * @param type 1，小时 2，分钟 3，秒 4，天
	 * @return
	 */
	public static float dateDiff(Timestamp t1, Timestamp t2, int type) {
		float i = t1.getTime() - t2.getTime();
		float f = 0.0f;// i / (1000 * 60);
		switch (type) {
		case 1:// hour
			f = i / (1000 * 60 * 60);
			break;
		case 2:// min
			f = i / (1000 * 60);
			break;
		case 3:// sec
			f = i / (1000);
			break;
		case 0: // defaut is day
			f = i / (1000 * 60 * 60 * 24);
		}
		String temp = "#,##0.0";
		try {
			return Float.valueOf(new java.text.DecimalFormat(temp).format(f));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
